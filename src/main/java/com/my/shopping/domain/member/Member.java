package com.my.shopping.domain.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias(value = "Member")
@Data
@NoArgsConstructor
public class Member {
    private Long id;
    private String nickname;
    private String loginId;
    private String password;
    private String role;
}