package com.example.hotdealnotifier.hotdeal.crawler;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class FmKoreaHotDealCrawler implements HotDealCrawler {

    public static final String BASE_URL = "https://www.fmkorea.com";
    private String phpsessid = "mp4s9lhg71i3mfb3kj3j1i9i02";

    @Override
    public List<HotDeal> crawl() {
        try {
            Connection.Response response = Jsoup.connect(BASE_URL + "/hotdeal")
                    .cookie("PHPSESSID", phpsessid).execute();
            this.phpsessid = Objects.isNull(response.cookie("PHPSESSID")) ? "" : response.cookie("PHPSESSID");
            Document document = response.parse();
            Elements hotDealElementList = document.select("div.fm_best_widget")
                    .select("ul");

            return hotDealElementList.select("li").stream()
                    .map(this::createHotDeal)
                    .flatMap(Optional::stream)
                    .toList();
        } catch (Exception e) {
            log.error("에펨코리아 핫딜 크롤링 실패", e);
            return Collections.emptyList();
        }
    }

    @Override
    public Platform getPlatform() {
        return Platform.FM_KOREA;
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
            log.warn("에펨코리아 핫딜 파싱 실패", e);
            return Optional.empty();
        }
    }

    private String getUrl(Element hotDeal) {
        return BASE_URL + hotDeal.select("a").first()
                .attr("href");
    }

    private String getShoppingMall(Element hotDeal) {
        return hotDeal.select("div.hotdeal_info")
                .select("span")
                .get(0)
                .select("a").first()
                .text();
    }

    private String getPrice(Element hotDeal) {
        return hotDeal.select("div.hotdeal_info")
                .select("span")
                .get(1)
                .select("a").first()
                .text();
    }

    private String getTitle(Element hotDeal) {
        return hotDeal.select("h3")
                .select("a").first()
                .ownText();
    }

    private String getImage(Element hotDeal) {
        Elements anchorAttribute = hotDeal.select("a");
        int imageIndex = anchorAttribute.get(0).hasClass("pc_voted_count") ? 1 : 0;
        return "https:" + anchorAttribute.get(imageIndex)
                .select("img")
                .attr("data-original");
    }
}
