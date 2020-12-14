package com.soulmates.urlshortener;

import com.soulmates.urlshortener.config.IntegrationTest;
import com.soulmates.urlshortener.feature.urlshorten.dto.ShortenURLRequest;
import com.soulmates.urlshortener.feature.urlshorten.service.URLShortenService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class URLShrotenTest extends IntegrationTest {
    @Autowired
    private URLShortenService urlShortenService;

    @Test
    @DisplayName("origin URL to Short URL 계산")
    public void shortenURL() throws Exception {

        //given
        ShortenURLRequest shortenURLRequest = ShortenURLRequest.builder()
                .originURL("http://wiki.smilegate.net:8090/#recently-worked").build();

        //when & then
        this.mockMvc
                .perform(post("/short")
                        .content(objectMapper.writeValueAsString(shortenURLRequest))
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("originURL").isString())
                .andExpect(jsonPath("shortURL").isString())
                .andExpect(jsonPath("count").isNumber());
    }
}
