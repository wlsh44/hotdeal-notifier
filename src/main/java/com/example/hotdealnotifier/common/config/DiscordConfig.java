package com.example.hotdealnotifier.common.config;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DiscordConfig {

    private final String token;


    public DiscordConfig(
            @Value("${discord.bot.token}") String token
    ) {
        this.token = token;
    }

    @Bean
    public JDA jda(List<ListenerAdapter> listenerAdapters) {
        return JDABuilder.createDefault(token)
                .setActivity(Activity.playing("hihihi"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(listenerAdapters.toArray())
                .build();
    }
}

