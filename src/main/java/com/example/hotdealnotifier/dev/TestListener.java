package com.example.hotdealnotifier.dev;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"dev", "local"})
@Component
public class TestListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        String message = event.getMessage().getContentDisplay();
        if (message.startsWith("!ping")) {
            event.getChannel().sendMessage("pong!").queue();
        }
    }
    
    
}
