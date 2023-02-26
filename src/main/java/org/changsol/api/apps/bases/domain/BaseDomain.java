package org.changsol.api.apps.bases.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Domain Base Class
 */
@Getter
@MappedSuperclass //자식에게 매핑정보만 제공
@EntityListeners(AuditingEntityListener.class) //Audit
public class BaseDomain {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
