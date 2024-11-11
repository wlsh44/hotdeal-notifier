package com.example.hotdealnotifier.notification.application.service;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.NewHotDealDetectedEvent;
import com.example.hotdealnotifier.notification.adapter.out.DiscordNotificationService;
import com.example.hotdealnotifier.notification.domain.Notification;
import com.example.hotdealnotifier.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HotDealNotifyService {

    private final DiscordNotificationService discordNotificationService;

    @EventListener
    public void notify(NewHotDealDetectedEvent event) {
        Map<HotDeal, List<User>> hotDealListMap = event.hotDealUserListMap();
        hotDealListMap.forEach((hotDeal, userList) -> {
            discordNotificationService.sendNotification(new Notification(hotDeal.getTitle(), List.of()));
        });
    }
}
