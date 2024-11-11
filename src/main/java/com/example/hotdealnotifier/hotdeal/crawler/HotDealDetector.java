package com.example.hotdealnotifier.hotdeal.crawler;

import com.example.hotdealnotifier.hotdeal.application.port.out.HotDealCommandPort;
import com.example.hotdealnotifier.hotdeal.application.port.out.HotDealQueryPort;
import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

//domain service
@Slf4j
@Component
public class HotDealDetector {

    private final Map<Platform, HotDealCrawler> hotDealCrawlerMap;
    private final HotDealCommandPort hotDealCommandPort;
    private final HotDealQueryPort hotDealQueryPort;


    public HotDealDetector(List<HotDealCrawler> hotDealCrawlers, HotDealCommandPort hotDealCommandPort, HotDealQueryPort hotDealQueryPort) {
        this.hotDealCrawlerMap = hotDealCrawlers.stream()
                .collect(Collectors.toMap(HotDealCrawler::getPlatform, hotDealCrawler -> hotDealCrawler));
        this.hotDealCommandPort = hotDealCommandPort;
        this.hotDealQueryPort = hotDealQueryPort;
    }

    public List<HotDeal> detect() {
        Set<Platform> platforms = hotDealCrawlerMap.keySet();
        List<HotDeal> newHotDealTotalList = new ArrayList<>();
        for (Platform platform : platforms) {
            log.info("{} 크롤링 시작 {}", platform.getText(), LocalDateTime.now());
            HotDealCrawler hotDealCrawler = hotDealCrawlerMap.get(platform);
            List<HotDeal> savedHotDealList = hotDealQueryPort.findAllCacheBy(platform);

            List<HotDeal> hotDealList = hotDealCrawler.crawl();
            if (isSameHotDealList(savedHotDealList, hotDealList)) {
                log.info("새 핫딜 없음");
                continue;
            }

            List<HotDeal> newHotDealList = getNewHotDealList(hotDealList, savedHotDealList);
//            newHotDealList.forEach(newHotDeal -> log.info(newHotDeal.toString()));
            log.info("{} 새 핫딜 감지, 총 개수 {}", platform.getText(), newHotDealList.size());

            hotDealCommandPort.saveHotDealCacheList(platform, hotDealList);
            newHotDealTotalList.addAll(newHotDealList);
        }
        return newHotDealTotalList;
    }

    private boolean isSameHotDealList(List<HotDeal> savedHotDealList, List<HotDeal> hotDealList) {
        return hotDealList.stream()
                .allMatch(hotDeal -> hotDeal.isContainedInHotDealList(savedHotDealList));
    }

    private List<HotDeal> getNewHotDealList(List<HotDeal> hotDealList, List<HotDeal> savedHotDealList) {
        return hotDealList.stream()
                .filter(hotDeal -> !hotDeal.isContainedInHotDealList(savedHotDealList))
                .toList();
    }
}
