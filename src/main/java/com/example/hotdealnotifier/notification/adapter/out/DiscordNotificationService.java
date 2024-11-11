package com.example.hotdealnotifier.notification.adapter.out;

import com.example.hotdealnotifier.notification.domain.Notification;
import com.example.hotdealnotifier.notification.domain.Target;
import com.example.hotdealnotifier.user.domain.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
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
        TextChannel textChannel = jda.getTextChannelById(channelId);
        ThreadChannel thread = textChannel.createThreadChannel(notification.getMessage()).complete();
        String userTag = String.format("<@%s>", "");
        thread.sendMessage(userTag).queue();
    }
}
