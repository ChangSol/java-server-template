package org.changsol.api.apps.users.repository;

import org.changsol.api.apps.users.entity.Users;
import org.changsol.api.utils.bases.repository.ChangSolBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Sample Master Repo Class
 */
@Repository
public interface UserRepository extends ChangSolBaseRepository<Users, Long> {

	Optional<Users> findByLoginId(String loginId);
}
