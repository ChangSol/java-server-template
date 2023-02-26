package org.changsol.api.apps.bases.enums;

import org.springframework.data.domain.Sort;

public enum SortType {
	ASC("오름차순",Sort.Direction.ASC),
	DESC("내림차순", Sort.Direction.DESC);

	public final String title;
	public final Sort.Direction direction;

	SortType(String title, Sort.Direction direction) {
		this.title = title;
		this.direction = direction;
	}
}
