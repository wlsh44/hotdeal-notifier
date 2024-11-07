package com.example.hotdealnotifier.keyword.domain;

import lombok.Getter;

@Getter
public class Keyword {

    private KeywordId id;
    private Long userId;
    private String text;

    private Keyword(KeywordId id, Long userId, String text) {
        this.id = id;
        this.userId = userId;
        this.text = text;
    }

    public static Keyword withId(KeywordId keywordId, String keyword, Long userId) {
        return new Keyword(keywordId, userId, keyword);
    }

    public static Keyword of(String keyword, Long userId) {
        return new Keyword(null, userId, keyword);
    }

    public record KeywordId(Long value) {
    }
}
