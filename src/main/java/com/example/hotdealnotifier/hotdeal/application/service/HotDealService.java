package com.example.hotdealnotifier.hotdeal.application.service;

import com.example.hotdealnotifier.hotdeal.application.port.in.HotDealUseCase;
import com.example.hotdealnotifier.hotdeal.application.port.out.HotDealCommandPort;
import com.example.hotdealnotifier.hotdeal.crawler.HotDealDetector;
import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.NewHotDealDetectedEvent;
import com.example.hotdealnotifier.keyword.application.port.out.KeywordQueryPort;
import com.example.hotdealnotifier.keyword.domain.Keyword;
import com.example.hotdealnotifier.user.application.port.out.UserQueryPort;
import com.example.hotdealnotifier.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//usecase
@Service
@Transactional(noRollbackFor = Exception.class)
@RequiredArgsConstructor
public class HotDealService implements HotDealUseCase {

    private final HotDealDetector hotDealDetector;
    private final KeywordQueryPort keywordQueryPort;
    private final UserQueryPort userPort;
    private final ApplicationEventPublisher publisher;
    private final HotDealCommandPort hotDealCommandPort;

    @Override
    public void crawl() {
        List<HotDeal> newHotDealList = hotDealDetector.detect();
        if (newHotDealList.isEmpty()) {
            return;
        }

        List<User> userList = userPort.findAll();
        Map<HotDeal, List<User>> hotDealUserListMap = new HashMap<>();
        newHotDealList.forEach(
                hotDeal -> fillHotDealUserMapIfHotDealContainKeyword(hotDeal, userList, hotDealUserListMap)
        );

        hotDealCommandPort.saveAll(newHotDealList);
        publisher.publishEvent(new NewHotDealDetectedEvent(hotDealUserListMap));
    }

    private void fillHotDealUserMapIfHotDealContainKeyword(HotDeal hotDeal, List<User> userList, Map<HotDeal, List<User>> hotDealUserListMap) {
        for (User user : userList) {
            List<Keyword> keywordList = keywordQueryPort.findAllByUserId(user.getId());
            if (!isContainingKeyword(hotDeal, keywordList)) {
                continue;
            }
            addUserToHotDealUserMap(hotDeal, user, hotDealUserListMap);
        }
    }

    private boolean isContainingKeyword(HotDeal hotDeal, List<Keyword> keywordList) {
        return keywordList.stream()
                .anyMatch(keyword -> hotDeal.isContainingKeyword(keyword.getText()));
    }

    private void addUserToHotDealUserMap(HotDeal hotDeal, User user, Map<HotDeal, List<User>> hotDealUserListMap) {
        List<User> hotDealUserList = hotDealUserListMap.getOrDefault(hotDeal, new ArrayList<>());
        hotDealUserList.add(user);
        hotDealUserListMap.put(hotDeal, hotDealUserList);
    }
}
