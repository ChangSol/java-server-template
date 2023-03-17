package org.changsol.api.apps.samples.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.changsol.api.apps.users.domain.Users;
import org.changsol.api.utils.bases.domain.ChangSolBaseDomainIdentity;
import org.hibernate.annotations.Comment;

/**
 * @author ChangSol
 * @version 0.0.1
 * @see SampleDetailSub
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SampleDetailSub extends ChangSolBaseDomainIdentity {
    @Comment("상세서브명")
    private String detailSubName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Comment("디테일 정보")
    private SampleDetail sampleDetail;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Comment("유저 정보")
    private Users user;
}
