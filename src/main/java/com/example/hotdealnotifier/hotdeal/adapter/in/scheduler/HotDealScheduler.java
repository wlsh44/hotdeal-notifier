package com.example.hotdealnotifier.hotdeal.adapter.in.scheduler;

import com.example.hotdealnotifier.hotdeal.application.port.in.HotDealUseCase;
import com.example.hotdealnotifier.hotdeal.crawler.HotDealDetector;
import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class HotDealScheduler {

    private final HotDealUseCase hotDealUseCase;

    @Scheduled(fixedDelay = 1000 * 60)
    public void schedule() {
        log.info("핫딜 크롤링 시작 {}", LocalDateTime.now());
        hotDealUseCase.crawl();
    }
}
