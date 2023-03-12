package org.changsol.api.utils.jpas.restriction;

import com.google.common.collect.Lists;
import org.hibernate.query.criteria.internal.path.ListAttributeJoin;
import org.hibernate.query.criteria.internal.path.SetAttributeJoin;
import org.hibernate.validator.internal.engine.groups.Group;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * Hibernate Restriction Custom Class
 */
@SuppressWarnings("unused")
public class ChangSolJpaRestriction {

	/**
	 * Predicate Where Type
	 */
	private final ChangSolJpaRestrictionType type;

	/**
	 * 조건 목록
	 */
	private final List<ChangSolJpaCondition> conditionList = Lists.newArrayList();

	/**
	 * Child Predicate List
	 */
	private final List<ChangSolJpaRestriction> childList = Lists.newArrayList();

	/**
	 * Fetch List
	 */
	private final List<ChangSolJpaJoin> fetchList = Lists.newArrayList();

	/**
	 * Join List
	 */
	private final List<ChangSolJpaJoin> joinList = Lists.newArrayList();

	/**
	 * Query Distinct
	 */
	private boolean isDistinctQuery = false;

	public ChangSolJpaRestriction() {
		type = ChangSolJpaRestrictionType.AND;
	}

	public ChangSolJpaRestriction(ChangSolJpaRestrictionType type) {
		this.type = type;
	}

	// region Clear Method

	/**
	 * All Clear
	 */
	public void clear() {
		conditionList.clear();
		childList.clear();
		fetchList.clear();
	}

	/**
	 * Condition List Clear
	 */
	public void conditionClear() {
		conditionList.clear();
	}

	/**
	 * Child List Clear
	 */
	public void childClear() {
		childList.clear();
	}

	/**
	 * Fetch List Clear
	 */
	public void fetchClear() {
		fetchList.clear();
	}

	/**
	 * Join List Clear
	 */
	public void joinClear() {
		joinList.clear();
	}
	// endregion

	// region Add Method

	/**
	 * Child Add
	 */
	public void addChild(ChangSolJpaRestriction predicate) {
		childList.add(predicate);
	}

	/**
	 * Fetch Add
	 */
	public void addFetch(String columnName, JoinType joinType) {
		this.fetchList.add(new ChangSolJpaJoin(columnName, joinType));
	}

	/**
	 * Join Add
	 */
	public void addJoin(String columnName, JoinType joinType) {
		this.joinList.add(new ChangSolJpaJoin(columnName, joinType));
	}
	// endregion

	// region Remove Method

	/**
	 * Fetch Remove
	 */
	public void removeFetch(String columnName) {
		List<ChangSolJpaJoin> fetchJoinList = this.fetchList.stream()
															.filter(fetch -> !fetch.columnName().equals(columnName))
															.toList();
		this.fetchList.clear();
		this.fetchList.addAll(fetchJoinList);
	}

	/**
	 * Join Remove
	 */
	public void removeJoin(String columnName) {
		List<ChangSolJpaJoin> fetchJoinList = this.joinList.stream()
														   .filter(join -> !join.columnName().equals(columnName))
														   .toList();
		this.joinList.clear();
		this.joinList.addAll(fetchJoinList);
	}
	// endregion

	// region Condition Method

	/**
	 * 같음 (=)
	 *
	 * @param columnName 컬럼명
	 * @param value      값
	 */
	public void equals(String columnName, Object value) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.EQUALS)
											  .columnName1(columnName)
											  .value1(value)
											  .build());
	}

	/**
	 * 같지 않음 (!=)
	 *
	 * @param columnName 컬럼명
	 * @param value      값
	 */
	public void notEquals(String columnName, Object value) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.NOT_EQUAL)
											  .columnName1(columnName)
											  .value1(value)
											  .build());
	}

	/**
	 * 양끝 공백 제거 후 같음
	 *
	 * @param columnName 컬럼명
	 * @param value      값
	 */
	public void equalsTrim(String columnName, Object value) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.EQUALS_TRIM)
											  .columnName1(columnName)
											  .value1(value)
											  .build());
	}

	/**
	 * 양끝 공백 제거 후 같지 않음
	 *
	 * @param columnName 컬럼명
	 * @param value      값
	 */
	public void notEqualsTrim(String columnName, Object value) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.NOT_EQUALS_TRIM)
											  .columnName1(columnName)
											  .value1(value)
											  .build());
	}

	/**
	 * IN
	 *
	 * @param columnName 컬럼명
	 * @param valueList  값
	 */
	public void in(String columnName, Collection<?> valueList) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.IN)
											  .columnName1(columnName)
											  .value1(valueList)
											  .build());
	}

	/**
	 * NOT IN
	 *
	 * @param columnName 컬럼명
	 * @param valueList  값
	 */
	public void notIn(String columnName, Collection<?> valueList) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.NOT_IN)
											  .columnName1(columnName)
											  .value1(valueList)
											  .build());
	}

	/**
	 * 작거나 같은 경우 (<=)
	 *
	 * @param columnName 컬럼명
	 * @param value      값
	 */
	public void lessThanEquals(String columnName, Object value) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.LESS_THAN_EQUAL)
											  .columnName1(columnName)
											  .value1(value)
											  .build());
	}

	/**
	 * 작고 같지 않은 경우 (<)
	 *
	 * @param columnName 컬럼명
	 * @param value      값
	 */
	public void lessThanNotEquals(String columnName, Object value) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.LESS_THAN_NOT_EQUAL)
											  .columnName1(columnName)
											  .value1(value)
											  .build());
	}

	/**
	 * 크거나 같은 경우 (>=)
	 *
	 * @param columnName 컬럼명
	 * @param value      값
	 */
	public void greaterThanEquals(String columnName, Object value) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.GREATER_THAN_EQUAL)
											  .columnName1(columnName)
											  .value1(value)
											  .build());
	}

	/**
	 * 크고 같지 않은 경우 (<)
	 *
	 * @param columnName 컬럼명
	 * @param value      값
	 */
	public void greaterThanNotEquals(String columnName, Object value) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.GREATER_THAN_NOT_EQUAL)
											  .columnName1(columnName)
											  .value1(value)
											  .build());
	}

	/**
	 * IS NULL
	 *
	 * @param columnName 컬럼명
	 */
	public void isNull(String columnName) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.IS_NULL)
											  .columnName1(columnName)
											  .build());
	}

	/**
	 * IS NOT NULL
	 *
	 * @param columnName 컬럼명
	 */
	public void isNotNull(String columnName) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.IS_NOT_NULL)
											  .columnName1(columnName)
											  .build());
	}

	/**
	 * LIKE
	 *
	 * @param columnName 컬럼명
	 * @param value      값
	 */
	public void like(String columnName, String value) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.LIKE)
											  .columnName1(columnName)
											  .value1(value)
											  .build());
	}

	/**
	 * UPPER LIKE
	 *
	 * @param columnName 컬럼명
	 * @param value      값
	 */
	public void iLike(String columnName, String value) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.ILIKE)
											  .columnName1(columnName)
											  .value1(value)
											  .build());
	}

	/**
	 * NOT LIKE
	 *
	 * @param columnName 컬럼명
	 * @param value      값
	 */
	public void notLike(String columnName, String value) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.NOT_LIKE)
											  .columnName1(columnName)
											  .value1(value)
											  .build());
	}

	/**
	 * UPPER NOT LIKE
	 *
	 * @param columnName 컬럼명
	 * @param value      값
	 */
	public void notILike(String columnName, String value) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.NOT_ILIKE)
											  .columnName1(columnName)
											  .value1(value)
											  .build());
	}

	/**
	 * BETWEEN
	 *
	 * @param columnName 컬럼명
	 * @param firstValue 첫번째 값
	 * @param lastValue  마지막 값
	 */
	public void between(String columnName, Object firstValue, Object lastValue) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.BETWEEN)
											  .columnName1(columnName)
											  .value1(firstValue)
											  .value2(lastValue)
											  .build());
	}

	/**
	 * 같은 컬럼 값 (key1 = key2)
	 *
	 * @param columnName1 컬럼명1
	 * @param columnName2 컬럼명2
	 */
	public void columnValueEquals(String columnName1, String columnName2) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.COLUMN_EQUALS)
											  .columnName1(columnName1)
											  .columnName2(columnName2)
											  .build());
	}

	/**
	 * 같지 않은 컬럼 값 (key1 != key2)
	 *
	 * @param columnName1 컬럼명1
	 * @param columnName2 컬럼명2
	 */
	public void columnValueNotEquals(String columnName1, String columnName2) {
		conditionList.add(ChangSolJpaCondition.builder()
											  .conditionType(ChangSolJpaConditionType.COLUMN_NOT_EQUALS)
											  .columnName1(columnName1)
											  .columnName2(columnName2)
											  .build());
	}
	// endregion

	// region Specification Method
	public <T> Specification<T> toSpecification() {
		// Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder
		Specification<T> specification = (root, query, criteriaBuilder) -> {

			// region Fetch
			if (!fetchList.isEmpty()) {
				if (root.getJavaType().equals(query.getResultType())) { // data query 와 count query 를 구분
					for (ChangSolJpaJoin fetch : fetchList) {
						if (fetch.columnName().contains(".")) {
							Set<? extends Fetch<?, ?>> fetches = root.getFetches();
							Fetch<?, ?> addFetch = null;
							for (String field : fetch.columnName().split("\\.")) {
								if (fetches.stream().anyMatch(x -> x.getAttribute().getName().equals(field))) {
									addFetch = fetches.stream()
													  .filter(x -> x.getAttribute().getName().equals(field))
													  .findAny()
													  .orElse(null);
								} else {
									addFetch = Objects.requireNonNullElse(addFetch, root).fetch(field, fetch.joinType());
								}
								if (addFetch != null) {
									if (isCollectionType(addFetch.getClass())) {
										isDistinctQuery = true;
									}

									fetches = addFetch.getFetches();
								}
							}
						} else {
							if (root.getFetches().stream().anyMatch(x -> x.getAttribute().getName().equals(fetch.columnName()))) {
								continue;
							}
							Fetch<?, ?> addFetch = root.fetch(fetch.columnName(), fetch.joinType());

							if (isCollectionType(addFetch.getClass())) {
								isDistinctQuery = true;
							}
						}
					}
				}
			}
			// endregion

			// region join
			if (!joinList.isEmpty()) {
				if (root.getJavaType().equals(query.getResultType())) { // data query 와 count query 를 구분
					for (ChangSolJpaJoin join : joinList) {
						if (join.columnName().contains(".")) {
							Set<? extends Join<?, ?>> joins = root.getJoins();
							Join<?, ?> joinObj = null;
							for (String field : join.columnName().split("\\.")) {
								if (joins.stream().anyMatch(x -> x.getAttribute().getName().equals(field))) {
									joinObj = joins.stream()
												   .filter(x -> x.getAttribute().getName().equals(field))
												   .findAny()
												   .orElse(null);
								} else {
									joinObj = Objects.requireNonNullElse(joinObj, root).join(field, join.joinType());
								}

								if (joinObj != null) {
									if (isCollectionType(joinObj.getClass())) {
										isDistinctQuery = true;
									}

									joins = joinObj.getJoins();
								}
							}
						} else {
							if (root.getFetches().stream().anyMatch(x -> x.getAttribute().getName().equals(join.columnName()))) {
								continue;
							}

							Join<T, ?> joinObj = root.join(join.columnName(), join.joinType());

							if (isCollectionType(joinObj.getClass())) {
								isDistinctQuery = true;
							}
						}
					}
				}
			}
			// endregion

			List<Predicate> predicateList = Lists.newArrayList();
			for (ChangSolJpaCondition condition : conditionList) {
				String columnName1 = condition.getColumnName1();
				String columnName2 = condition.getColumnName2();
				Object value1 = condition.getValue1();
				Object value2 = condition.getValue2();

				predicateList.addAll(getPredicateList(condition.getConditionType(), root, criteriaBuilder, columnName1, columnName2, value1, value2));
			}

			// query distinct 처리여부
			query.distinct(isDistinctQuery);

			if (predicateList.size() > 1) {
				Predicate[] ps = predicateList.toArray(new Predicate[]{});
				return type == ChangSolJpaRestrictionType.AND ? criteriaBuilder.and(ps) : criteriaBuilder.or(ps);
			} else if (predicateList.size() == 1) {
				return predicateList.get(0);
			} else {
				return null;
			}
		};

		if (!childList.isEmpty()) {
			for (ChangSolJpaRestriction child : childList) {
				specification = type == ChangSolJpaRestrictionType.AND ? specification.and(child.toSpecification()) : specification.or(child.toSpecification());
			}
		}

		return specification;
	}

	private List<Predicate> getPredicateList(ChangSolJpaConditionType conditionType,
											 Root<?> root,
											 CriteriaBuilder criteriaBuilder,
											 String columnName1,
											 String columnName2,
											 Object value1,
											 Object value2) {
		List<Predicate> predicateList = Lists.newArrayList();
		switch (conditionType) {
			case EQUALS -> predicateList.add(criteriaBuilder.equal(getPath(root, columnName1), value1));
			case NOT_EQUAL -> predicateList.add(criteriaBuilder.notEqual(getPath(root, columnName1), value1));
			case EQUALS_TRIM -> predicateList.add(criteriaBuilder.equal(criteriaBuilder.trim(getPath(root, columnName1)), value1));
			case NOT_EQUALS_TRIM -> predicateList.add(criteriaBuilder.notEqual(criteriaBuilder.trim(getPath(root, columnName1)), value1));
			case IN -> {
				final Path<Group> group = getPath(root, columnName1);
				if (group != null) {
					predicateList.add(group.in((Collection<?>) value1));
				}
			}
			case NOT_IN -> {
				final Path<Group> group = getPath(root, columnName1);
				if (group != null) {
					predicateList.add(group.in((Collection<?>) value1).not());
				}
			}
			case LESS_THAN_EQUAL -> predicateList.addAll(getPredicateLessThen(criteriaBuilder, root, columnName1, value1, true));
			case LESS_THAN_NOT_EQUAL -> predicateList.addAll(getPredicateLessThen(criteriaBuilder, root, columnName1, value1, false));
			case GREATER_THAN_EQUAL -> predicateList.addAll(getPredicateItemGreaterThen(criteriaBuilder, root, columnName1, value1, true));
			case GREATER_THAN_NOT_EQUAL -> predicateList.addAll(getPredicateItemGreaterThen(criteriaBuilder, root, columnName1, value1, false));
			case IS_NULL -> predicateList.add(criteriaBuilder.isNull(getPath(root, columnName1)));
			case IS_NOT_NULL -> predicateList.add(criteriaBuilder.isNotNull(getPath(root, columnName1)));
			case LIKE -> predicateList.add(criteriaBuilder.like(getPath(root, columnName1), value1.toString()));
			case ILIKE -> predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(getPath(root, columnName1)), value1.toString().toUpperCase()));
			case NOT_LIKE -> predicateList.add(criteriaBuilder.not(criteriaBuilder.like(getPath(root, columnName1), value1.toString())));
			case NOT_ILIKE -> predicateList.add(criteriaBuilder.not(criteriaBuilder.like(criteriaBuilder.upper(getPath(root, columnName1)), value1.toString().toUpperCase())));
			case BETWEEN -> predicateList.addAll(getPredicateItemBetween(criteriaBuilder, root, columnName1, value1, value2));
			case COLUMN_EQUALS -> predicateList.add(criteriaBuilder.equal(getPath(root, columnName1), getPath(root, columnName2)));
			case COLUMN_NOT_EQUALS -> predicateList.add(criteriaBuilder.notEqual(getPath(root, columnName1), getPath(root, columnName2)));
		}

		return predicateList;
	}

	private List<Predicate> getPredicateLessThen(CriteriaBuilder criteriaBuilder, Root<?> root, String columnName, Object value, boolean isEquals) {
		List<Predicate> predicateList = new ArrayList<>();

		if (isEquals) {
			if (value instanceof Date) {
				predicateList.add(criteriaBuilder.lessThanOrEqualTo(getPath(root, columnName), (Date) value));
			} else if (value instanceof LocalDateTime) {
				predicateList.add(criteriaBuilder.lessThanOrEqualTo(getPath(root, columnName), (LocalDateTime) value));
			} else if (value instanceof LocalDate) {
				predicateList.add(criteriaBuilder.lessThanOrEqualTo(getPath(root, columnName), (LocalDate) value));
			} else if (value instanceof LocalTime) {
				predicateList.add(criteriaBuilder.lessThanOrEqualTo(getPath(root, columnName), (LocalTime) value));
			} else {
				predicateList.add(criteriaBuilder.le(getPath(root, columnName), (Number) value));
			}
		} else {
			if (value instanceof Date) {
				predicateList.add(criteriaBuilder.lessThan(getPath(root, columnName), (Date) value));
			} else if (value instanceof LocalDateTime) {
				predicateList.add(criteriaBuilder.lessThan(getPath(root, columnName), (LocalDateTime) value));
			} else if (value instanceof LocalDate) {
				predicateList.add(criteriaBuilder.lessThan(getPath(root, columnName), (LocalDate) value));
			} else if (value instanceof LocalTime) {
				predicateList.add(criteriaBuilder.lessThan(getPath(root, columnName), (LocalTime) value));
			} else {
				predicateList.add(criteriaBuilder.lt(getPath(root, columnName), (Number) value));
			}
		}

		return predicateList;
	}

	private List<Predicate> getPredicateItemGreaterThen(CriteriaBuilder criteriaBuilder, Root<?> root, String columnName, Object value, boolean isEquals) {
		List<Predicate> predicateList = new ArrayList<>();

		if (isEquals) {
			if (value instanceof Date) {
				predicateList.add(criteriaBuilder.greaterThanOrEqualTo(getPath(root, columnName), (Date) value));
			} else if (value instanceof LocalDateTime) {
				predicateList.add(criteriaBuilder.greaterThanOrEqualTo(getPath(root, columnName), (LocalDateTime) value));
			} else if (value instanceof LocalDate) {
				predicateList.add(criteriaBuilder.greaterThanOrEqualTo(getPath(root, columnName), (LocalDate) value));
			} else if (value instanceof LocalTime) {
				predicateList.add(criteriaBuilder.greaterThanOrEqualTo(getPath(root, columnName), (LocalTime) value));
			} else {
				predicateList.add(criteriaBuilder.ge(getPath(root, columnName), (Number) value));
			}
		} else {
			if (value instanceof Date) {
				predicateList.add(criteriaBuilder.greaterThan(getPath(root, columnName), (Date) value));
			} else if (value instanceof LocalDateTime) {
				predicateList.add(criteriaBuilder.greaterThan(getPath(root, columnName), (LocalDateTime) value));
			} else if (value instanceof LocalDate) {
				predicateList.add(criteriaBuilder.greaterThan(getPath(root, columnName), (LocalDate) value));
			} else if (value instanceof LocalTime) {
				predicateList.add(criteriaBuilder.greaterThan(getPath(root, columnName), (LocalTime) value));
			} else {
				predicateList.add(criteriaBuilder.gt(getPath(root, columnName), (Number) value));
			}
		}

		return predicateList;
	}

	private List<Predicate> getPredicateItemBetween(CriteriaBuilder criteriaBuilder, Root<?> root, String columnName, Object value1, Object value2) {
		List<Predicate> predicateList = new ArrayList<>();

		if (value1 instanceof Date) {
			predicateList.add(criteriaBuilder.between(getPath(root, columnName), (Date) value1, (Date) value2));
		} else if (value1 instanceof LocalDateTime) {
			predicateList.add(criteriaBuilder.between(getPath(root, columnName), (LocalDateTime) value1, (LocalDateTime) value2));
		} else if (value1 instanceof LocalDate) {
			predicateList.add(criteriaBuilder.between(getPath(root, columnName), (LocalDate) value1, (LocalDate) value2));
		} else if (value1 instanceof LocalTime) {
			predicateList.add(criteriaBuilder.between(getPath(root, columnName), (LocalTime) value1, (LocalTime) value2));
		} else {
			predicateList.add(criteriaBuilder.between(getPath(root, columnName), (Double) value1, (Double) value2));
		}
		return predicateList;
	}

	private <T> Path<T> getPath(Root<?> root, String columnName) {
		Path<T> path = null;
		Join<?, ?> joinOption = null;
		final String[] COLUMN_NAMES = columnName.split("\\.");
		if (COLUMN_NAMES.length > 1) {
			int count = 1;
			for (String key : COLUMN_NAMES) {
				if (count != COLUMN_NAMES.length) {
					joinOption = joinOption == null ? root.join(key, JoinType.LEFT) : joinOption.join(key, JoinType.LEFT);
				} else {
					path = joinOption.get(key);
				}
				if (isCollectionType(joinOption.getClass())) {
					isDistinctQuery = true;
				}

				// if (path == null) {
				// 	joinOption = root.join(key, JoinType.LEFT);
				// 	path = root.get(key);
				// } else {
				// 	if (count != COLUMN_NAMES.length) {
				// 		joinOption = joinOption.join(key, JoinType.LEFT);
				// 	}
				// 	if (isCollectionType(path.getJavaType())) {
				// 		isDistinctQuery = true;
				// 		path = joinOption.get(key);
				// 	} else {
				// 		path = path.get(key);
				// 	}
				// }
				count++;
			}
		} else {
			path = root.get(columnName);
		}
		return path;
	}

	private boolean isCollectionType(Class<?> clazz) {
		return Set.class.isAssignableFrom(clazz)
			|| List.class.isAssignableFrom(clazz)
			|| SetAttributeJoin.class.isAssignableFrom(clazz)
			|| ListAttributeJoin.class.isAssignableFrom(clazz);
	}
	// endregion

}
