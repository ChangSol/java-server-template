package org.changsol.api.apps.samples.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.changsol.api.apps.users.domain.Users;
import org.changsol.api.utils.bases.domain.ChangSolBaseDomainIdentity;
import org.hibernate.annotations.Comment;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * @author ChangSol
 * @version 0.0.1
 * @see SampleDetail
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SampleDetail extends ChangSolBaseDomainIdentity {
    @Comment("상세명")
    private String detailName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Comment("마스터 정보")
    private SampleMaster sampleMaster;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Comment("유저 정보")
    private Users user;
}