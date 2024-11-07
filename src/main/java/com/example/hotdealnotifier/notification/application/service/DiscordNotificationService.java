package com.example.hotdealnotifier.notification.application.service;

import com.example.hotdealnotifier.notification.domain.Notification;
import com.example.hotdealnotifier.notification.domain.Target;
import com.example.hotdealnotifier.user.domain.User;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscordNotificationService {

    private final JDA jda;

    @Value("${discord.hotdeal.channel}")
    private String channelId;

    public void sendNotification(Notification notification) {
        List<Target> targets = notification.getTargets();
        jda.getTextChannelById(channelId).sendMessage(notification.getMessage()).queue();

    }
}
