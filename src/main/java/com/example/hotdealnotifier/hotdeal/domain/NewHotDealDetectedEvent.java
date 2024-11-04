package com.example.hotdealnotifier.hotdeal.domain;

import com.example.hotdealnotifier.user.domain.User;
import org.springframework.context.ApplicationEvent;

import java.util.List;
import java.util.Map;

public record NewHotDealDetectedEvent(
        Map<HotDeal, List<User>> hotDealUserListMap
) {
}
