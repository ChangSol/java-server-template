package org.changsol.api.utils.jpas.restriction;

/**
 * Predicate WHERE 절 AND, OR TYPE
 */
enum ChangSolJpaRestrictionType {
	AND("AND"),
	OR("OR");

	public final String title;

	ChangSolJpaRestrictionType(String title) {
		this.title = title;
	}
}
