package com.example.hotdealnotifier.notification.domain;

import lombok.Getter;

@Getter
public class Target {

    private final Long id;
    private final String name;
    private final String notificationId;

    public Target(Long id, String name, String notificationId) {
        this.id = id;
        this.name = name;
        this.notificationId = notificationId;
    }
}
