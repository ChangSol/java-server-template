package org.changsol.api.apps.samples.repository;

import org.changsol.api.apps.bases.repository.BaseRepository;
import org.changsol.api.apps.samples.entity.SampleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Sample Master Repo Class
 */
@Repository
public interface SampleMasterRepository extends BaseRepository<SampleMaster, Long> {

}
