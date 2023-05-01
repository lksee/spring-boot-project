package com.playdata.springbootproject.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "Guest"), // Spring Security에서는 권한 코드에 항상 ROLE_이 앞에 있어야만 한다.
    USER("ROLE_USER", "User");    // 그래서 코드별 키 값을 ROLE_GUEST, ROLE_USER 등으로 지정한다.

    private final String key;
    private final String title;
}
