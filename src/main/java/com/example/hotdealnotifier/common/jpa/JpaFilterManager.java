package com.example.hotdealnotifier.common.jpa;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaFilterManager {

    public static final String DELETED_FILTER = "deletedFilter";
    public static final String DELETED_FILTER_PARAM = "isDeleted";

    private final EntityManager em;

    public void enableFilter(String filterName, String paramName, Object paramValue) {
        Session session = em.unwrap(Session.class);
        Filter filter = session.enableFilter(filterName);
        filter.setParameter(paramName, paramValue);
    }

    public void disableFilter(String filterName) {
        Session session = em.unwrap(Session.class);
        session.disableFilter(filterName);
    }
}
