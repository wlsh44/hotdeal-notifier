package com.example.hotdealnotifier.notification.adapter.out;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.notification.domain.HotDealInfo;
import com.example.hotdealnotifier.notification.domain.Notification;
import com.example.hotdealnotifier.notification.domain.Target;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscordNotificationService {

    private static final String DISCORD_TAG_FORMAT = "<@%s>";
    private final JDA jda;

    @Value("${discord.hotdeal.channel}")
    private String channelId;

    public Notification sendNotification(HotDealInfo hotDealInfo, List<Target> targets) {
        TextChannel textChannel = jda.getTextChannelById(channelId);

        MessageEmbed message = createMessage(hotDealInfo);
        Notification notification = createNotification(message.getDescription(), targets);

        ThreadChannel thread = createTheadAndSendHotDealInfo(hotDealInfo, textChannel, message);

        tagHotDealTargets(targets, thread);
        return notification;
    }

    private void tagHotDealTargets(List<Target> targets, ThreadChannel thread) {
        targets.forEach(target -> {
            String userTag = DISCORD_TAG_FORMAT.formatted(target.getNotificationId());
            thread.sendMessage(userTag).queue();
        });
    }

    private ThreadChannel createTheadAndSendHotDealInfo(HotDealInfo hotDealInfo, TextChannel textChannel, MessageEmbed message) {
        ThreadChannel thread = textChannel.createThreadChannel(hotDealInfo.title()).complete();
        thread.sendMessageEmbeds(message).queue();
        return thread;
    }

    private Notification createNotification(String message, List<Target> targets) {
        return new Notification(message, targets);
    }

    private MessageEmbed createMessage(HotDealInfo hotDealInfo) {
        return new EmbedBuilder()
//                .setTitle(hotDealInfo.title())
                .setDescription("""
                        ### 제목: %s
                        **가격: %s**
                        **링크: %s**
                        **쇼핑몰: %s**
                        핫딜 플랫폼: %s
                        """
                        .formatted(hotDealInfo.title(), hotDealInfo.price(), hotDealInfo.url(), hotDealInfo.shoppingMall(), hotDealInfo.platform().getText()))
                .setImage(hotDealInfo.image())
                .build();
    }
}
