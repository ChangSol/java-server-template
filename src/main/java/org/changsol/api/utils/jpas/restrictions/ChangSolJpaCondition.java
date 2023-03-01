package org.changsol.api.utils.jpas.restrictions;

import lombok.Builder;
import lombok.Getter;

/**
 * JPA 조건 클래스
 */
@Builder
@Getter
class ChangSolJpaCondition {

	/**
	 * 조건 타입
	 */
	private ChangSolJpaConditionType conditionType;

	/**
	 * 컬럼명1
	 */
	private String columnName1;

	/**
	 * 컬럼명2
	 */
	private String columnName2;

	/**
	 * 값1
	 */
	private Object value1;

	/**
	 * 값2
	 */
	private Object value2;

}
