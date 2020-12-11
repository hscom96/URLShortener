package com.soulmates.urlshortener.feature.urlshorten.repos;

import com.soulmates.urlshortener.feature.urlshorten.model.URLShorten;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface URLShortenRepository  extends JpaRepository<URLShorten, Integer> {
    public Optional<URLShorten> findByOriginURL(String url);
}
