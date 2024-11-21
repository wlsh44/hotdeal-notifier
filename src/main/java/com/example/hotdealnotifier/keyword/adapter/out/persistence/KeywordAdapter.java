package com.example.hotdealnotifier.keyword.adapter.out.persistence;

import com.example.hotdealnotifier.keyword.application.port.out.KeywordCommandPort;
import com.example.hotdealnotifier.keyword.application.port.out.KeywordQueryPort;
import com.example.hotdealnotifier.keyword.domain.Keyword;
import com.example.hotdealnotifier.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KeywordAdapter implements KeywordQueryPort, KeywordCommandPort {

    private final KeywordMapper keywordMapper;
    private final KeywordJpaRepository keywordJpaRepository;

    @Override
    public List<Keyword> findAllByUserId(User.UserId userId) {
        return keywordJpaRepository.findAllByUserId(userId.id()).stream()
                .map(keywordMapper::mapToDomainEntity)
                .toList();
    }

    @Override
    public Keyword save(Keyword keyword) {
        KeywordEntity keywordEntity = keywordJpaRepository.save(keywordMapper.mapToJpaEntity(keyword));
        return keywordMapper.mapToDomainEntity(keywordEntity);
    }

    @Override
    public void remove(String text, Long userId) {
        keywordJpaRepository.deleteByTextAndUserId(text, userId);
    }
}
