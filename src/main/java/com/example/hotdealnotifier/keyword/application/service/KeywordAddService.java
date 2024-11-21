package com.example.hotdealnotifier.keyword.application.service;

import com.example.hotdealnotifier.keyword.application.port.out.KeywordCommandPort;
import com.example.hotdealnotifier.keyword.domain.Keyword;
import com.example.hotdealnotifier.user.application.port.out.UserCommandPort;
import com.example.hotdealnotifier.user.application.port.out.UserQueryPort;
import com.example.hotdealnotifier.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordAddService {

    private final KeywordCommandPort keywordCommandPort;
    private final UserCommandPort userCommandPort;
    private final UserQueryPort userQueryPort;

    public void addKeyword(String keyword, String externalId) {
        userQueryPort.findByExternalId(externalId)
                .ifPresentOrElse(
                        user -> saveKeyword(keyword, user.getId().id()),
                        () -> saveKeyword(keyword, userCommandPort.save(new User(externalId)).getId().id())
                );
    }

    private void saveKeyword(String keyword, Long userId) {
        keywordCommandPort.save(Keyword.of(keyword, userId));
    }
}
