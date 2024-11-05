package com.example.hotdealnotifier.common.jpa;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@FilterDef(name = JpaFilterManager.DELETED_FILTER, parameters = @ParamDef(name = JpaFilterManager.DELETED_FILTER_PARAM, type = Boolean.class))
@Filter(name = JpaFilterManager.DELETED_FILTER, condition = "(deleted_at is not null) = :" + JpaFilterManager.DELETED_FILTER_PARAM)
@MappedSuperclass
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDate deletedAt;
}
