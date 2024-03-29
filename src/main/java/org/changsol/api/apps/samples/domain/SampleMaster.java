package org.changsol.api.apps.samples.domain;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.changsol.api.utils.bases.domain.ChangSolBaseDomainIdentity;
import org.hibernate.annotations.Comment;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * @author ChangSol
 * @version 0.0.1
 * @see SampleMaster
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SampleMaster extends ChangSolBaseDomainIdentity {
    @Comment("마스터명")
    private String masterName;

    /**
     * 디테일 목록
     */
    @OneToMany(mappedBy = "sampleMaster", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SampleDetail> sampleDetails = Sets.newHashSet();
}
