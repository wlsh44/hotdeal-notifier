package com.example.hotdealnotifier.notification.domain;

import lombok.Getter;

@Getter
public class Target {

    private final String name;
    private final String notificationId;

    public Target(String name, String notificationId) {
        this.name = name;
        this.notificationId = notificationId;
    }
}
