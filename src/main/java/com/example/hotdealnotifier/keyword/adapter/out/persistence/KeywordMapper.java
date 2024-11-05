package com.example.hotdealnotifier.keyword.adapter.out.persistence;

import com.example.hotdealnotifier.keyword.domain.Keyword;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class KeywordMapper {

    Keyword mapToDomainEntity(KeywordEntity keywordEntity) {
        return Keyword.withId(
                new Keyword.KeywordId(keywordEntity.getId()),
                keywordEntity.getText(),
                keywordEntity.getUserId()
        );
    }

    KeywordEntity mapToJpaEntity(Keyword keyword) {
        return KeywordEntity.builder()
                .id(Objects.isNull(keyword.getId()) ? null : keyword.getId().value())
                .text(keyword.getText())
                .userId(keyword.getUserId())
                .build();
    }
}
