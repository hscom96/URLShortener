package com.soulmates.urlshortener.feature.urlshorten.controller;

import com.soulmates.urlshortener.common.constants.ErrorEnum;
import com.soulmates.urlshortener.feature.urlshorten.dto.ShortenURLRequest;
import com.soulmates.urlshortener.feature.urlshorten.dto.ShortenURLResponse;
import com.soulmates.urlshortener.feature.urlshorten.service.URLShortenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/short")
@RestController
public class URLShortenController {
    private final URLShortenService urlShortenService;

    @PostMapping
    public ResponseEntity<?> shortenURL(@RequestBody @Valid ShortenURLRequest shortenURLRequest, Errors error){
        if(error.hasErrors()) {
            String errMsg = Objects.requireNonNull(error.getFieldError()).getDefaultMessage();
            log.info(errMsg);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errMsg);
        }

        ShortenURLResponse shortenURLResponse = urlShortenService.shortenURL(shortenURLRequest.getOriginURL());
        return ResponseEntity.ok(shortenURLResponse);
    }

    @GetMapping("/{shortURL}")
    public void redirectURL(@PathVariable(value = "shortURL") String shortURL, HttpServletResponse httpServletResponse) throws IOException {
        String originURL = urlShortenService.restoreURL(shortURL);
        httpServletResponse.sendRedirect(originURL);
    }
}
