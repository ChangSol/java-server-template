package org.changsol.api.apps.bases.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.changsol.api.apps.bases.enums.SortType;

/**
 * Sort Dto Class
 */
@Getter
@Setter
public class SortDto {

	@Schema(title = "정렬 필드")
	private String sortColumnName;

	@Schema(title = "정렬 타입")
	private SortType sortType;
}

