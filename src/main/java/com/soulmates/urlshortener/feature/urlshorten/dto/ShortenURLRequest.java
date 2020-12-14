package com.soulmates.urlshortener.feature.urlshorten.dto;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

@RequiredArgsConstructor @AllArgsConstructor
@Builder
@Setter @Getter
public class ShortenURLRequest {
    @URL(message = "not correct url format")
    @NotEmpty(message = "url empty error")
    private String originURL;
}
