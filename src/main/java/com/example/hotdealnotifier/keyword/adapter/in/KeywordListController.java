package com.example.hotdealnotifier.keyword.adapter.in;

import com.example.hotdealnotifier.common.discord.Command;
import com.example.hotdealnotifier.keyword.application.service.KeywordAddService;
import com.example.hotdealnotifier.keyword.application.service.KeywordListService;
import com.example.hotdealnotifier.keyword.application.service.dto.KeywordDto;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KeywordListController implements Command {

    private final KeywordListService keywordListService;

    @Override
    public String getName() {
        return "keyword-list";
    }

    @Override
    public String getDescription() {
        return "키워드 목록 조회";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        List<KeywordDto> keywordList = keywordListService.findKeywordList(event.getUser().getId());
        String message = keywordList.stream()
                .map(KeywordDto::keyword)
                .collect(Collectors.joining(", "));
        event.reply(message).queue();
    }
}
