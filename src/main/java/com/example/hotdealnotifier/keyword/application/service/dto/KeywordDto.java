package com.example.hotdealnotifier.keyword.application.service.dto;

import com.example.hotdealnotifier.keyword.domain.Keyword;

public record KeywordDto(
        String keyword
) {

    public static KeywordDto from(Keyword keyword) {
        return new KeywordDto(keyword.getText());
    }
}
