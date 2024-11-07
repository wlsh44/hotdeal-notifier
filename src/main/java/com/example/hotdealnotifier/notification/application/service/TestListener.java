package com.example.hotdealnotifier.notification.application.service;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class TestListener extends ListenerAdapter {
//    private final DiscordNotificationService discordNotificationService;

//    public TestListener(DiscordNotificationService discordNotificationService) {
//        this.discordNotificationService = discordNotificationService;
//    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        System.out.println("event.getMessage() = " + event.getMessage());
        String message = event.getMessage().getContentDisplay();
        if (message.startsWith("!ping")) {
            event.getChannel().sendMessage("pong!").queue();
        }
    }
}
