package com.soulmates.urlshortener.feature.urlshorten.model;

import com.soulmates.urlshortener.common.model.TimeBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor @RequiredArgsConstructor
@Getter @Builder
@Entity
public class URLShorten extends TimeBaseEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Lob
    @Column(nullable = false)
    private String originURL;

    @Column(nullable = false)
    private int count=0;

    public void increaseCount(int num){
        count+=num;
    }
}
