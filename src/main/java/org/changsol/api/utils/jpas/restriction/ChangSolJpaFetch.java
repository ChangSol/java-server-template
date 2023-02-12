package org.changsol.api.utils.jpas.restriction;

import javax.persistence.criteria.JoinType;
import lombok.Getter;

/**
 * JPA FETCH Class
 */
@Getter
class ChangSolJpaFetch {
	private final String columnName;

	private final JoinType joinType;

	public ChangSolJpaFetch(String columnName, JoinType joinType) {
		this.columnName = columnName;
		this.joinType = joinType;
	}

}
