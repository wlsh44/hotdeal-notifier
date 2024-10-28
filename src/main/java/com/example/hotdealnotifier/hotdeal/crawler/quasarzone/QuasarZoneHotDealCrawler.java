package com.example.hotdealnotifier.hotdeal.crawler.quasarzone;

import com.example.hotdealnotifier.hotdeal.crawler.HotDealCrawler;
import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
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
                    .select("tbody");

            return hotDealElementList.select("tr").stream()
                    .map(this::createHotDeal)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Platform getPlatform() {
        return Platform.QUASAR_ZONE;
    }

    private HotDeal createHotDeal(Element hotDeal) {
        String url = getUrl(hotDeal);
        String image = getImage(hotDeal);
        String shoppingMallAndTitle = getShoppingMallAndTitle(hotDeal);

        Matcher matcher = pattern.matcher(shoppingMallAndTitle);
        if (!matcher.matches()) {
            throw new RuntimeException("퀘이사존 쇼핑몰, 타이틀 파싱 실패: [xx] xxx 형식이 아님");
        }

        String shoppingMall = matcher.group(1);
        String title = matcher.group(2);
        String price = getPrice(hotDeal);
//        log.info("title: {}\nurl: {}\nimage: {}\nprice: {}\nshoppingMall: {}", title, url, image, price, shoppingMall);
        return HotDeal.builder()
                .title(title)
                .url(url)
                .image(image)
                .shoppingMall(shoppingMall)
                .price(price)
                .platform(Platform.QUASAR_ZONE)
                .build();
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
        return hotDeal.select("div.market-info-list")
                .select("img").first()
                .attr("src");
    }

    private String getUrl(Element hotDeal) {
        return BASE_URL + hotDeal.select("div.market-info-list")
                .select("a").first()
                .attr("href");
    }
}
