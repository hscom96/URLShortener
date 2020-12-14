package com.soulmates.urlshortener.service;


import com.soulmates.urlshortener.common.util.Base62;
import com.soulmates.urlshortener.config.MockTest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;

public class base62Test extends MockTest {
    @InjectMocks
    private Base62 base62;

    @Test
    @DisplayName("base62 변환")
    public void encode(){
        //given
        int id = 4321;
        //when
        String result = base62.encode(id);
        // then
        assertThat(result).isEqualTo("bhH");
    }

    @Test
    @DisplayName("base62 decode")
    public void decode(){
        //given
        String url = "bhH";
        //when
        int result = base62.decode(url);
        // then
        assertThat(result).isEqualTo(4321);
    }
}
