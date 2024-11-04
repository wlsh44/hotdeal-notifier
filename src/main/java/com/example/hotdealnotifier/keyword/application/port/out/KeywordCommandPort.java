package com.example.hotdealnotifier.keyword.application.port.out;

import com.example.hotdealnotifier.keyword.domain.Keyword;

public interface KeywordCommandPort {
    Keyword save(Keyword keyword);
}
