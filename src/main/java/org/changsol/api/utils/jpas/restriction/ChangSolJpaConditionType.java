package org.changsol.api.utils.jpas.restriction;

enum ChangSolJpaConditionType {
	EQUALS("같음 (=)"),
	NOT_EQUAL("같지 않음 (!=)"),
	EQUALS_TRIM("양끝 공백 제거 후 같음"),
	NOT_EQUALS_TRIM("양끝 공백 제거 후 같지 않음"),
	IN("IN"),
	NOT_IN("NOT IN"),
	LESS_THAN_EQUAL("작거나 같은 경우 (<=)"),
	LESS_THAN_NOT_EQUAL("작고 같지 않은 경우 (<)"),
	GREATER_THAN_EQUAL("크거나 같은 경우 (>=)"),
	GREATER_THAN_NOT_EQUAL("크고 같지 않은 경우 (>)"),
	IS_NULL("IS NULL"),
	IS_NOT_NULL("IS NOT NULL"),
	LIKE("LIKE"),
	ILIKE("UPPER LIKE"),
	NOT_LIKE("NOT LIKE"),
	NOT_ILIKE("UPPER NOT LIKE"),
	BETWEEN("BETWEEN"),
	COLUMN_EQUALS("같은 컬럼 값 (key1 = key2)"),
	COLUMN_NOT_EQUALS("같지 않은 컬럼 값 (key1 != key2)");

	public final String title;

	ChangSolJpaConditionType(String title) {
		this.title = title;
	}
}
