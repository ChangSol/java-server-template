package org.changsol.api.utils.page;

import org.springframework.data.domain.Sort;

enum ChangSolSortType {
	ASC("오름차순",Sort.Direction.ASC),
	DESC("내림차순", Sort.Direction.DESC);

	public final String title;
	public final Sort.Direction direction;

	ChangSolSortType(String title, Sort.Direction direction) {
		this.title = title;
		this.direction = direction;
	}
}
