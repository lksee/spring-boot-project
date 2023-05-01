package com.playdata.springbootproject.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloResponseDtoTest {

    @Test
    public void lombokTest() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);      // 생성자로 인해 name 필드에 test가 들어갔는지 검증
        assertThat(dto.getAmount()).isEqualTo(amount);  // 생성자로 인해 amount 필드에 1000이 들어갔는지 검증
    }
}