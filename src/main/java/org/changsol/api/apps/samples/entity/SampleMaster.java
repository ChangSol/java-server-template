package org.changsol.api.apps.samples.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.changsol.api.apps.bases.domain.BaseDomainIdentity;

import javax.persistence.Entity;

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
    private String masterName;
}
