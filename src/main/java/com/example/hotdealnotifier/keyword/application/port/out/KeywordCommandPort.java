package com.example.hotdealnotifier.keyword.application.port.out;

import com.example.hotdealnotifier.keyword.domain.Keyword;
import com.example.hotdealnotifier.user.domain.User;

public interface KeywordCommandPort {
    Keyword save(Keyword keyword);

    void remove(String keyword, Long userId);
}
