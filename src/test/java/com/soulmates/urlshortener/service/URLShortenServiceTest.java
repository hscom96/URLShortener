package com.soulmates.urlshortener.service;


import com.soulmates.urlshortener.common.constants.ErrorEnum;
import com.soulmates.urlshortener.common.exception.CustomException;
import com.soulmates.urlshortener.common.util.Base62;
import com.soulmates.urlshortener.common.util.URLCombiner;
import com.soulmates.urlshortener.config.MockTest;
import com.soulmates.urlshortener.feature.urlshorten.dto.ShortenURLResponse;
import com.soulmates.urlshortener.feature.urlshorten.model.URLShorten;
import com.soulmates.urlshortener.feature.urlshorten.repos.URLShortenRepository;
import com.soulmates.urlshortener.feature.urlshorten.service.URLShortenService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class URLShortenServiceTest extends MockTest {
    @InjectMocks
    private URLShortenService urlShortenService;
    @Mock
    private Base62 base62;
    @Mock
    private URLShortenRepository urlShortenRepos;
    @Mock
    private URLCombiner urlCombiner;

    @Test
    @DisplayName("단축된 url로 origin URL 탐색")
    public void restoreURL(){
        //given
        String shortURL = "http://localhost:8030/short/b";
        Optional<URLShorten> urlShorten = Optional.of(URLShorten.builder()
                .originURL("http://wiki.smilegate.net:8090/#recently-worked")
                .count(0).build());

        //when
        when(urlShortenRepos.findById(anyInt())).thenReturn(urlShorten);
        String originURL = urlShortenService.restoreURL(shortURL);

        //then
        assertAll(
                ()->assertThat(originURL).isEqualTo("http://wiki.smilegate.net:8090/#recently-worked")
        );
    }

    @Test
    @DisplayName("존재하지 않는 단축된 url로 origin URL 탐색 (실패)")
    public void restoreURL_notexist_fail(){
        //given
        String shortURL = "http://localhost:8030/short/b";

        //when
        when(urlShortenRepos.findById(anyInt())).thenReturn(Optional.empty());

        //then
        CustomException exception = assertThrows(CustomException.class,
                ()->{
                    urlShortenService.restoreURL(shortURL);
                }
        );
        assertTrue(exception.getErrorEnum().equals(ErrorEnum.URL_SHORTEN_NOT_FOUND));
    }

    @Test
    @DisplayName("저장안된 URL 단축")
    public void shortenURL_notsave(){
        //given
        String originURL = "http://wiki.smilegate.net:8090/#recently-worked";
        Optional<URLShorten> urlShorten = Optional.empty();
        URLShorten savedURL = URLShorten.builder()
                .id(1)
                .originURL(originURL).build();

        //when
        when(urlShortenRepos.findByOriginURL(originURL)).thenReturn(urlShorten);
        when(urlShortenRepos.save(any(URLShorten.class))).thenReturn(savedURL);
        when(base62.encode(savedURL.getId())).thenReturn("b");
        when(urlCombiner.combinePathWithHost(anyString())).thenReturn("http://localhost:8030/short/b");

        ShortenURLResponse shortenURLResponse = urlShortenService.shortenURL(originURL);

        //then
        assertAll(
                ()->assertThat(shortenURLResponse.getOriginURL()).isEqualTo("http://wiki.smilegate.net:8090/#recently-worked"),
                ()->assertThat(shortenURLResponse.getShortURL()).isEqualTo("http://localhost:8030/short/b"),
                ()->assertThat(shortenURLResponse.getCount()).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("저장되어있는 URL 단축")
    public void shortenURL_save(){
        //given
        String originURL = "http://wiki.smilegate.net:8090/#recently-worked";
        Optional<URLShorten> urlShorten = Optional.of(URLShorten.builder()
                .originURL(originURL)
                .count(0).build());
        //when
        when(urlShortenRepos.findByOriginURL(originURL)).thenReturn(urlShorten);
        when(urlCombiner.combinePathWithHost(anyString())).thenReturn("http://localhost:8030/short/b");

        ShortenURLResponse shortenURLResponse = urlShortenService.shortenURL(originURL);

        //then
        assertAll(
                ()->assertThat(shortenURLResponse.getOriginURL()).isEqualTo("http://wiki.smilegate.net:8090/#recently-worked"),
                ()->assertThat(shortenURLResponse.getShortURL()).isEqualTo("http://localhost:8030/short/b"),
                ()->assertThat(shortenURLResponse.getCount()).isEqualTo(1)
        );
    }
}
