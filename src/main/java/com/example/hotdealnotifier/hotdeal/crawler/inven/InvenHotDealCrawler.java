package com.example.hotdealnotifier.hotdeal.crawler.inven;

import com.example.hotdealnotifier.hotdeal.crawler.HotDealCrawler;
import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class InvenHotDealCrawler implements HotDealCrawler {

    private static final String BASE_URL = "https://party.inven.co.kr";

    @Override
    public List<HotDeal> crawl() {
        try {
            Document document = Jsoup.connect(BASE_URL + "/hotdeal/list/").get();

            Element hotDealElementList = document.select("div.list-board").first();

            return hotDealElementList.select("div.list-item").stream()
                    .map(this::createHotDeal)
                    .toList();
        } catch (Exception e) {
            log.error("인벤 핫딜 크롤링 실패", e);
            return Collections.emptyList();
        }
    }

    @Override
    public Platform getPlatform() {
        return Platform.INVEN;
    }

    private HotDeal createHotDeal(Element hotDeal) {
        String title = getTitle(hotDeal);
        String url = getUrl(hotDeal);
        String image = getImage(hotDeal);
        String price = getPrice(hotDeal);
        String shoppingMall = getShoppingMall(hotDeal);
//        log.info("title: {}\nurl: {}\nimage: {}\nprice: {}\nshoppingMall: {}", title, url, image, price, shoppingMall);
        return HotDeal.of(title, url, price, image, shoppingMall, getPlatform());
    }

    private String getImage(Element hotDeal) {
        return hotDeal.select("a.thumbnail").first()
                .select("img").first()
                .attr("src");
    }

    private String getUrl(Element hotDeal) {
        return hotDeal.select("a.thumbnail").first()
                .attr("href");
    }

    private String getPrice(Element hotDeal) {
        return hotDeal.select("p.price").first()
                .text();
    }

    private String getTitle(Element hotDeal) {
        return hotDeal.select("div.title").first()
                .select("a").first()
                .ownText()
                .trim();
    }

    private String getShoppingMall(Element hotDeal) {
        return hotDeal.select("p.shop").first()
                .text();
    }
}
