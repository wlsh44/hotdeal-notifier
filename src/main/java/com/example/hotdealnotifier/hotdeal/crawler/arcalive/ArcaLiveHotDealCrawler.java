package com.example.hotdealnotifier.hotdeal.crawler.arcalive;

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
public class ArcaLiveHotDealCrawler implements HotDealCrawler {

    private static final String BASE_URL = "https://arca.live";

    @Override
    public List<HotDeal> crawl() {
        try {
            Document document = Jsoup.connect(BASE_URL + "/b/hotdeal").get();

            Element hotDealElementList = document.select("div.list-table").first();

            return hotDealElementList.select("div.hybrid.vrow").stream()
                    .map(this::createHotDeal)
                    .toList();
        } catch (Exception e) {
            log.error("아카라이브 핫딜 크롤링 실패", e);
            return Collections.emptyList();
        }
    }

    @Override
    public Platform getPlatform() {
        return Platform.ARCA_LIVE;
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
        return "https:" + hotDeal.select("a.preview-image").first()
                .select("img").first()
                .attr("src");
    }

    private String getUrl(Element hotDeal) {
        return BASE_URL + hotDeal.select("a.preview-image").first()
                .attr("href");
    }

    private String getPrice(Element hotDeal) {
        return hotDeal.select("span.deal-price").first()
                .text()
                .trim();
    }

    private String getTitle(Element hotDeal) {
        Elements select = hotDeal.select("span.col-title").first()
                .select("a.title");
        return select.first()
                .ownText()
                .trim();
    }

    private String getShoppingMall(Element hotDeal) {
        return hotDeal.select("div.deal").first()
                .select("span.deal-store").first()
                .text();
    }
}
