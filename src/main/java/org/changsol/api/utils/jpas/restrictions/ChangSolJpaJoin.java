package org.changsol.api.utils.jpas.restrictions;

import javax.persistence.criteria.JoinType;

/**
 * JPA Restriction Join Class
 */
record ChangSolJpaJoin(String columnName, JoinType joinType) {

}
