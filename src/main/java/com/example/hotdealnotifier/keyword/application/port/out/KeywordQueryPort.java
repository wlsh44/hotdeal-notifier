package com.example.hotdealnotifier.keyword.application.port.out;

import com.example.hotdealnotifier.keyword.domain.Keyword;
import com.example.hotdealnotifier.user.domain.User;

import java.util.List;

public interface KeywordQueryPort {
    List<Keyword> findAllByUserId(User.UserId userId);
}
