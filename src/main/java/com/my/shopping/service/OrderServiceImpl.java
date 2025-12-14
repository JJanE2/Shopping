package com.my.shopping.service;

import com.my.shopping.domain.cart.Cart;
import com.my.shopping.domain.cart.CartItem;
import com.my.shopping.domain.order.Order;
import com.my.shopping.domain.order.dto.OrderCreateDto;
import com.my.shopping.domain.order.dto.OrderUpdateDto;
import com.my.shopping.domain.orderProduct.OrderProduct;
import com.my.shopping.domain.orderProduct.dto.OrderProductCreateDto;
import com.my.shopping.mapper.OrderMapper;
import com.my.shopping.mapper.OrderProductMapper;
import com.my.shopping.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final OrderProductMapper orderProductMapper;

    @Override
    @Transactional
    public Long insert(OrderCreateDto orderCreateDto) {
        orderMapper.insert(orderCreateDto);
        Long orderId = orderCreateDto.getId();
        List<OrderProductCreateDto> productDtos = orderCreateDto.getProducts();
        insertOrderProducts(orderId, productDtos);
        return orderId;
    }

    private void insertOrderProducts(Long orderId, List<OrderProductCreateDto> productDtos) {
        for (OrderProductCreateDto productDto : productDtos) {
            // 개별 상품에 대한 재고확인 및 소진
            int isQuantityUpdated = productMapper.decreaseStock(productDto.getProductId(), productDto.getQuantity());
            if (isQuantityUpdated == 0) {
                throw new IllegalStateException("재고 부족 또는 동시 주문으로 인해 주문실패");
            }

            productDto.setOrderId(orderId);
            orderProductMapper.insert(productDto);
        }
    }

    @Override
    public Order findById(Long id) {
        return orderMapper.findById(id);
    }

    @Override
    public List<Order> findByMemberId(Long memberId) {
        return orderMapper.findByMemberId(memberId);
    }

    @Override
    @Transactional
    public int update(OrderUpdateDto orderUpdateDto) {
        return orderMapper.update(orderUpdateDto);
    }

    @Override
    public int delete(Long id) {
        return orderMapper.delete(id);
    }

    @Override
    @Transactional
    public void cancel(Long id) {
        Order order = orderMapper.findById(id);
        String status = order.getStatus();
        if (status.equals("PAID")) {
            // products 에 대해 재고 원복
            restoreStock(id);
            orderMapper.updateStatus(id, "CANCELED");
            return;
        } else if (status.equals("CANCELED")) {
            throw new IllegalStateException("이미 주문이 취소되었습니다.");
        }
        throw new IllegalStateException("현재 상태에서 주문을 취소할 수 없습니다.");
    }

    private void restoreStock(Long orderId) {
        List<OrderProduct> orderProducts = orderProductMapper.findByOrderId(orderId);
        for (OrderProduct orderProduct : orderProducts) {
            productMapper.increaseStock(orderProduct.getProductId(), orderProduct.getQuantity());
        }
    }

    @Override
    public List<Order> findByOwnerId(Long ownerId) {
        return orderMapper.findByOwnerId(ownerId);
    }

    @Override
    public String getNextStatus(String currentStatus) {
        return switch (currentStatus) {
            case "PAID" -> "SHIPPING";
            case "SHIPPING" -> "COMPLETED";
            default -> currentStatus; // COMPLETED / CANCELED 면 그대로
        };
    }

    @Override
    @Transactional
    public String advanceStatus(Long orderId) {
        Order order = orderMapper.findById(orderId);
        String nextStatus = getNextStatus(order.getStatus());

        orderMapper.updateStatus(orderId, nextStatus);
        return nextStatus;
    }

    @Override
    @Transactional
    public void forceCancel(Long id) {
        Order order = orderMapper.findById(id);
        String status = order.getStatus();

        // 이미 취소된 상태 처리
        if (status.equals("CANCELED")) {
            throw new IllegalStateException("이미 주문이 취소되었습니다.");
        }

        restoreStock(id);
        orderMapper.updateStatus(id, "CANCELED");
    }

    @Override
    @Transactional
    public Long orderFromCart(Cart cart) {
        OrderCreateDto orderCreateDto = convertCartToOrderDto(cart);
        return insert(orderCreateDto);
    }

    private OrderCreateDto convertCartToOrderDto(Cart cart) {
        OrderCreateDto dto = new OrderCreateDto();
        dto.setMemberId(cart.getMemberId());

        int total = 0;

        for (CartItem item : cart.getItems()) {
            OrderProductCreateDto p = new OrderProductCreateDto();
            p.setProductId(item.getProductId());
            p.setProductName(item.getProductName());
            p.setPrice(item.getPrice());
            p.setQuantity(item.getQuantity());
            dto.getProducts().add(p);

            total += item.getPrice() * item.getQuantity();
        }
        dto.setTotalPrice(total);
        return dto;
    }

    @Override
    public OrderProduct findByOrderProductId(Long orderProductId) {
        return orderMapper.findByOrderProductId(orderProductId);
    }

    @Override
    public void markReviewAsWritten(Long orderProductId) {
        orderMapper.setHasWrittenReview(orderProductId);
    }
}