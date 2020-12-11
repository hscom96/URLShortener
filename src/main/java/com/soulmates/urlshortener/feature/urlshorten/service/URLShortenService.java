package com.soulmates.urlshortener.feature.urlshorten.service;

import com.soulmates.urlshortener.common.constants.ErrorEnum;
import com.soulmates.urlshortener.common.exception.CustomException;
import com.soulmates.urlshortener.common.util.Base62;
import com.soulmates.urlshortener.common.util.URLCombiner;
import com.soulmates.urlshortener.feature.urlshorten.dto.ShortenURLResponse;
import com.soulmates.urlshortener.feature.urlshorten.model.URLShorten;
import com.soulmates.urlshortener.feature.urlshorten.repos.URLShortenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class URLShortenService {
    private final URLShortenRepository urlShortenRepos;
    private final Base62 base62;
    private final URLCombiner urlCombiner;

    public ShortenURLResponse shortenURL(String originURL) {
        Optional<URLShorten> urlShorten = urlShortenRepos.findByOriginURL(originURL);
        if(urlShorten.isPresent()){
            urlShorten.get().increaseCount(1);
            urlShortenRepos.save(urlShorten.get());
            String shortPath = urlCombiner.combinePathWithHost("short/"+base62.encode(urlShorten.get().getId()));
            return new ShortenURLResponse(originURL, shortPath, urlShorten.get().getCount());
        }

        URLShorten savedURL = urlShortenRepos
                .save(URLShorten.builder()
                        .originURL(originURL)
                        .build());
        String shortPath = urlCombiner.combinePathWithHost("short/"+base62.encode(savedURL.getId()));
        return new ShortenURLResponse(originURL, shortPath, 0);
    }

    public String restoreURL(String shortURL) {
        int id = base62.decode(shortURL);
        return urlShortenRepos
                .findById(id)
                .orElseThrow(() -> new CustomException(ErrorEnum.URL_SHORTEN_NOT_FOUND))
                .getOriginURL();
    }
}
