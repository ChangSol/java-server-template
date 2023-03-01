package org.changsol.api.utils.bases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ChangSolBaseRepository<DOMAIN, ID_TYPE> extends JpaRepository<DOMAIN, ID_TYPE>, JpaSpecificationExecutor<DOMAIN> {

}
