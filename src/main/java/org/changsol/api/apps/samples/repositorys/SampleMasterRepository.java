package org.changsol.api.apps.samples.repositorys;

import org.changsol.api.apps.samples.entitys.SampleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Sample Master Repo Class
 */
@Repository
public interface SampleMasterRepository extends JpaRepository<SampleMaster, Long>, JpaSpecificationExecutor<SampleMaster> {

}
