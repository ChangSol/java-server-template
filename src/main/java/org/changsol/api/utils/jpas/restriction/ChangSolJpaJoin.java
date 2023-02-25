package org.changsol.api.utils.jpas.restriction;

import javax.persistence.criteria.JoinType;

/**
 * JPA Restriction Join Class
 */
record ChangSolJpaJoin(String columnName, JoinType joinType) {

}
