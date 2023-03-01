package org.changsol.api.apps.samples.repository;

import org.changsol.api.apps.samples.entity.SampleMaster;
import org.changsol.api.utils.bases.repository.ChangSolBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Sample Master Repo Class
 */
@Repository
public interface SampleMasterRepository extends ChangSolBaseRepository<SampleMaster, Long> {

}
