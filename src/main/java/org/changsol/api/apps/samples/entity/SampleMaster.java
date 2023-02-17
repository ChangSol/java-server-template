package org.changsol.api.apps.samples.entity;

import com.google.common.collect.Sets;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.changsol.api.apps.bases.domain.BaseDomainIdentity;

import javax.persistence.Entity;
import org.hibernate.annotations.Comment;

/**
 * @author ChangSol
 * @version 0.0.1
 * @see SampleMaster
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SampleMaster extends BaseDomainIdentity {
    @Comment("마스터명")
    private String masterName;

    /**
     * 디테일 목록
     */
    @OneToMany(mappedBy = "sampleMaster", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SampleDetail> sampleDetails = Sets.newHashSet();
}
