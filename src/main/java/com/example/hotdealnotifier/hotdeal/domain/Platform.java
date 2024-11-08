package com.example.hotdealnotifier.hotdeal.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Platform {

    FM_KOREA("에펨코리아"), QUASAR_ZONE("퀘이사존"), ARCA_LIVE("아카라이브"), INVEN("인벤");

    private final String text;
}
