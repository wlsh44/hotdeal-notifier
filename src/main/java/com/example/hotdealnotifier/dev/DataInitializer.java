package com.example.hotdealnotifier.dev;

import com.example.hotdealnotifier.keyword.application.port.out.KeywordCommandPort;
import com.example.hotdealnotifier.keyword.domain.Keyword;
import com.example.hotdealnotifier.user.application.port.out.UserCommandPort;
import com.example.hotdealnotifier.user.domain.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"dev", "local"})
@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserCommandPort userCommandPort;
    private final KeywordCommandPort keywordCommandPort;

    @PostConstruct
    public void init() {
        User user = userCommandPort.save(new User("externalId"));
        keywordCommandPort.save(Keyword.of("k", user.getId().id()));
    }
}
