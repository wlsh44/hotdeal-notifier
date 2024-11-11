package com.example.hotdealnotifier.common.discord;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandEventListener extends ListenerAdapter {

    private final List<Command> commands;

    @Override
    public void onReady(ReadyEvent event) {
        for(Guild guild : event.getJDA().getGuilds()) {
            for(Command command : commands) {
                if(command.getOptions() == null) {
                    guild.upsertCommand(command.getName(), command.getDescription()).queue();
                } else {
                    guild.upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
                }
            }
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        for(Command command : commands) {
            if(command.getName().equals(event.getName())) {
                try {
                    command.execute(event);
                } catch (Exception e) {
                    log.error("", e);
                    event.reply(e.getMessage()).queue();
                }
                return;
            }
        }
    }
}
