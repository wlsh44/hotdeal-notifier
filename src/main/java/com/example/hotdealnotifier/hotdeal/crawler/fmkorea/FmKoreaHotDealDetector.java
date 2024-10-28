package com.example.hotdealnotifier.hotdeal.crawler.fmkorea;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FmKoreaHotDealDetector {

    private final FmKoreaHotDealCrawler fmKoreaHotDealCrawler;
    private List<HotDeal> savedHotDealList = Collections.emptyList();

    public void detect() {
        List<HotDeal> hotDealList = fmKoreaHotDealCrawler.crawl();
        if (savedHotDealList.equals(hotDealList)) {
            return;
        }
        log.info("새 핫딜 감지");
        List<HotDeal> newHotDealList = hotDealList.stream()
                .filter(hotDeal -> !savedHotDealList.contains(hotDeal))
                .toList();
        newHotDealList.forEach(newHotDeal -> log.info(newHotDeal.toString()));
        savedHotDealList = hotDealList;
    }
}
