package com.example.hotdealnotifier.keyword.adapter.in;

import com.example.hotdealnotifier.common.discord.Command;
import com.example.hotdealnotifier.keyword.application.service.KeywordAddService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KeywordAddController implements Command {

    private static final String KEYWORD = "keyword";

    private final KeywordAddService keywordAddService;

    @Override
    public String getName() {
        return "keyword-add";
    }

    @Override
    public String getDescription() {
        return "키워드 추가";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.STRING, KEYWORD, "추가할 키워드 이름", true)
        );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping keywordMapping = event.getOption(KEYWORD);
        assert keywordMapping != null : "keyword 옵션 isRequired -> true 이기 때문에 null일 수 없음";

        String keyword = keywordMapping.getAsString();
        keywordAddService.addKeyword(keyword, event.getUser().getId());
        event.reply("%s 키워드 추가 완료".formatted(keyword)).queue();
    }
}
