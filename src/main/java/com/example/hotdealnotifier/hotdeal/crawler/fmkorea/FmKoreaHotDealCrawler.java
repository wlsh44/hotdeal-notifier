package com.example.hotdealnotifier.hotdeal.crawler.fmkorea;

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

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FmKoreaHotDealCrawler implements HotDealCrawler {

    public static final String BASE_URL = "https://www.fmkorea.com";

    @Override
    public List<HotDeal> crawl() {
        try {
            Document document = Jsoup.connect(BASE_URL + "/hotdeal")
                    .cookie("PHPSESSID", "mp4s9lhg71i3mfb3kj3j1i9i02").get();
            Elements hotDealElementList = document.select("div.fm_best_widget")
                    .select("ul");

            return hotDealElementList.select("li").stream()
                    .map(this::createHotDeal)
                    .toList();
        } catch (Exception e) {
            log.error("에펨코리아 핫딜 크롤링 실패", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Platform getPlatform() {
        return Platform.FM_KOREA;
    }

    private HotDeal createHotDeal(Element hotDeal) {
        String url = getUrl(hotDeal);
        String image = getThumbnailImage(hotDeal);
        String title = getTitle(hotDeal);
        String price = getPrice(hotDeal);
        String shoppingMall = getShoppingMall(hotDeal);
//        log.info("title: {}\nurl: {}\nimage: {}\nprice: {}", title, url, image, price);
        return HotDeal.builder()
                .title(title)
                .image(image)
                .price(price)
                .shoppingMall(shoppingMall)
                .platform(Platform.FM_KOREA)
                .url(url)
                .build();
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

    private String getThumbnailImage(Element hotDeal) {
        Elements anchorAttribute = hotDeal.select("a");
        int imageIndex = anchorAttribute.get(0).hasClass("pc_voted_count") ? 1 : 0;
        return "https:" + anchorAttribute.get(imageIndex)
                .select("img")
                .attr("data-original");
    }
}
