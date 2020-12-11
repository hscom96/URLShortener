package com.soulmates.urlshortener.common.util;

import org.springframework.stereotype.Service;

@Service
public class Base62 {
    private static final char[] BASE62 = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public String encode(int id) {
        StringBuilder shortURL = new StringBuilder("");
        while (id > 0) {
            shortURL.append(BASE62[id % 62]);
            id /= 62;
        }
        return shortURL.reverse().toString();
    }

    public int decode(String str) {
        int id = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ('a' <= ch && ch <= 'z') {
                id = id * 62 + ch - 'a';
            } else if ('A' <= ch && ch <= 'Z') {
                id = id * 62 + ch - 'A' + 36;
            } else if ('0' <= ch && ch <= '9') {
                id = id * 62 + ch - '0' + 26;
            }
        }
        return id;
    }
}
