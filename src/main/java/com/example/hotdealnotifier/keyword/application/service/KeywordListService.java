package com.example.hotdealnotifier.keyword.application.service;

import com.example.hotdealnotifier.keyword.application.port.out.KeywordQueryPort;
import com.example.hotdealnotifier.keyword.application.service.dto.KeywordDto;
import com.example.hotdealnotifier.user.application.port.out.UserQueryPort;
import com.example.hotdealnotifier.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KeywordListService {

    private final UserQueryPort userQueryPort;
    private final KeywordQueryPort keywordQueryPort;

    public List<KeywordDto> findKeywordList(String externalId) {
        User user = userQueryPort.findByExternalId(externalId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        return keywordQueryPort.findAllByUserId(user.getId()).stream()
                .map(KeywordDto::from)
                .toList();
    }
}
