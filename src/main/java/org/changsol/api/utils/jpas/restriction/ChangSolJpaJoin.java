package org.changsol.api.utils.jpas.restriction;

import javax.persistence.criteria.JoinType;
import lombok.Getter;

/**
 * JPA Restriction Join Class
 */
@Getter
class ChangSolJpaJoin {
	private final String columnName;

	private final JoinType joinType;

	public ChangSolJpaJoin(String columnName, JoinType joinType) {
		this.columnName = columnName;
		this.joinType = joinType;
	}

}
