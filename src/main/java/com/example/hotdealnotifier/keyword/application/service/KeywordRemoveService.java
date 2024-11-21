package com.example.hotdealnotifier.keyword.application.service;

import com.example.hotdealnotifier.keyword.application.port.out.KeywordCommandPort;
import com.example.hotdealnotifier.keyword.application.port.out.KeywordQueryPort;
import com.example.hotdealnotifier.user.application.port.out.UserQueryPort;
import com.example.hotdealnotifier.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class KeywordRemoveService {

//    private final KeywordQueryPort keywordQueryPort;
    private final KeywordCommandPort keywordCommandPort;
    private final UserQueryPort userQueryPort;

    public void removeKeyword(String keyword, String externalId) {
        User user = userQueryPort.findByExternalId(externalId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        keywordCommandPort.remove(keyword, user.getId().id());
    }
}
