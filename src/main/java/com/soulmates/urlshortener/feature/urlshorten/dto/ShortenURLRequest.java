package com.soulmates.urlshortener.feature.urlshorten.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

@RequiredArgsConstructor
@Setter @Getter
public class ShortenURLRequest {
    @URL(message = "not correct url format")
    @NotEmpty(message = "url empty error")
    private String originURL;
}
