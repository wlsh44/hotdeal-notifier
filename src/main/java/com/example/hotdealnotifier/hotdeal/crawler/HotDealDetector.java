package com.example.hotdealnotifier.hotdeal.crawler;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class HotDealDetector {

    private final Map<Platform, List<HotDeal>> savedHotDealMap;
    private final Map<Platform, HotDealCrawler> hotDealCrawlerMap;

    public HotDealDetector(List<HotDealCrawler> hotDealCrawlers) {
        this.hotDealCrawlerMap = hotDealCrawlers.stream()
                .collect(Collectors.toMap(HotDealCrawler::getPlatform, hotDealCrawler -> hotDealCrawler));
        this.savedHotDealMap = hotDealCrawlers.stream()
                .collect(Collectors.toMap(HotDealCrawler::getPlatform, v -> Collections.emptyList()));
    }

    public void detect() {
        Set<Platform> platforms = hotDealCrawlerMap.keySet();
        for (Platform platform : platforms) {
            log.info("{} 크롤링 시작 {}", platform.getText(), LocalDateTime.now());
            HotDealCrawler hotDealCrawler = hotDealCrawlerMap.get(platform);
            List<HotDeal> savedHotDealList = savedHotDealMap.get(platform);

            List<HotDeal> hotDealList = hotDealCrawler.crawl();
            if (savedHotDealList.equals(hotDealList)) {
                continue;
            }
            log.info("새 핫딜 감지");
            List<HotDeal> newHotDealList = getNewHotDealList(hotDealList, savedHotDealList);

            newHotDealList.forEach(newHotDeal -> log.info(newHotDeal.toString()));
            savedHotDealMap.put(platform, newHotDealList);
        }
    }

    private List<HotDeal> getNewHotDealList(List<HotDeal> hotDealList, List<HotDeal> savedHotDealList) {
        return hotDealList.stream()
                .filter(hotDeal -> !savedHotDealList.contains(hotDeal))
                .toList();
    }
}