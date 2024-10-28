package com.example.hotdealnotifier.hotdeal.scheculer;

import com.example.hotdealnotifier.hotdeal.crawler.fmkorea.FmKoreaHotDealDetector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class HotDealScheduler {

    private final FmKoreaHotDealDetector fmKoreaHotDealDetector;

    @Scheduled(fixedDelay = 1000 * 60)
    public void schedule() {
        log.info("핫딜 크롤링 시작 {}", LocalDateTime.now());
        fmKoreaHotDealDetector.detect();
    }
}
