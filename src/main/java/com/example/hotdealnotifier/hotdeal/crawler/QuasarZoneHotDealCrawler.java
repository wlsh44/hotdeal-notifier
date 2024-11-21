package com.example.hotdealnotifier.hotdeal.crawler;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuasarZoneHotDealCrawler implements HotDealCrawler {

    private static final String BASE_URL = "https://quasarzone.com";
    private static final Pattern pattern = Pattern.compile("\\[(.*?)\\]\\s*(.*)");

    @Override
    public List<HotDeal> crawl() {
        try {
            Document document = Jsoup.connect(BASE_URL + "/bbs/qb_saleinfo").get();

            Elements hotDealElementList = document.select("div.market-type-list").first()
                    .select("tbody")
                    .select("tr");

            return hotDealElementList.stream()
                    .map(this::createHotDeal)
                    .flatMap(Optional::stream)
                    .toList();
        } catch (Exception e) {
            log.error("퀘이사존 핫딜 크롤링 실패", e);
            return Collections.emptyList();
        }
    }

    @Override
    public Platform getPlatform() {
        return Platform.QUASAR_ZONE;
    }

    private Optional<HotDeal> createHotDeal(Element hotDeal) {
        try {
            String title = getTitle(hotDeal);
            String url = getUrl(hotDeal);
            String image = getImage(hotDeal);
            String price = getPrice(hotDeal);
            String shoppingMall = getShoppingMall(hotDeal);
            log.debug("title: {}\nurl: {}\nimage: {}\nprice: {}\nshoppingMall: {}", title, url, image, price, shoppingMall);
            return Optional.of(HotDeal.of(title, url, price, image, shoppingMall, getPlatform()));
        } catch (Exception e) {
            log.warn("퀘이사존 핫딜 파싱 실패", e);
            return Optional.empty();
        }
    }

    private String getTitle(Element hotDeal) {
        String shoppingMallAndTitle = getShoppingMallAndTitle(hotDeal);
        Matcher matcher = pattern.matcher(shoppingMallAndTitle);
        if (!matcher.matches()) {
            throw new RuntimeException("퀘이사존 쇼핑몰, 타이틀 파싱 실패: [xx] xxx 형식이 아님, title: " + shoppingMallAndTitle);
        }
        return matcher.group(2);
    }

    private String getShoppingMall(Element hotDeal) {
        String shoppingMallAndTitle = getShoppingMallAndTitle(hotDeal);
        Matcher matcher = pattern.matcher(shoppingMallAndTitle);
        if (!matcher.matches()) {
            throw new RuntimeException("퀘이사존 쇼핑몰, 타이틀 파싱 실패: [xx] xxx 형식이 아님, title: " + shoppingMallAndTitle);
        }
        return matcher.group(1);
    }

    private String getPrice(Element hotDeal) {
        return hotDeal.select("div.market-info-sub")
                .select("span").get(1)
                .select("span.text-orange").first()
                .text()
                .replaceAll(" ", "");
    }

    private String getShoppingMallAndTitle(Element hotDeal) {
        return hotDeal.select("p.tit")
                .select("a").first()
                .select("span").first()
                .text();
    }

    private String getImage(Element hotDeal) {
        Element img = hotDeal.select("div.market-info-list")
                .select("img").first();
        return Objects.isNull(img) ? null : img.attr("src");
    }

    private String getUrl(Element hotDeal) {
        return BASE_URL + hotDeal.select("div.market-info-list")
                .select("a").first()
                .attr("href");
    }
}
