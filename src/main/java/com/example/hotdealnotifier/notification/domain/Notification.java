package com.example.hotdealnotifier.notification.domain;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import lombok.Getter;

import java.util.List;

@Getter
public class Notification {

    private final String message;
    private final List<Target> targets;

    public Notification(String message, List<Target> targets) {
        this.message = message;
        this.targets = targets;
    }

}
