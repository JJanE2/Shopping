package com.my.shopping.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            MemberNotFoundException.class,
            ProductNotFoundException.class,
            OrderNotFoundException.class,
            ReviewNotFoundException.class
    })
    public String handleNotFoundException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/404";
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(Exception e, Model model) {
        model.addAttribute("errorMessage", "주소 또는 입력 값을 다시 확인해 주세요.");
        return "error/400";
    }

    @ExceptionHandler(LoginRequiredException.class)
    public String handleLoginRequired() {
        return "redirect:/login";
    }

    @ExceptionHandler(CustomAccessDeniedException.class)
    public String handleAccessDenied(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/403";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return "error/500";
    }
}