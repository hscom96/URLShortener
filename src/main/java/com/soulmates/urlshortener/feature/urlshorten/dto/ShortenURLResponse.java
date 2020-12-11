package com.soulmates.urlshortener.feature.urlshorten.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class ShortenURLResponse {
    private String originURL;

    private String shortURL;

    private int count=0;
}
