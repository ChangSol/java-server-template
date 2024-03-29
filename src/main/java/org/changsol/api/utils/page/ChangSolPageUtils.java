package org.changsol.api.utils.page;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

/**
 * Page Util Class
 */
public class ChangSolPageUtils {

	@Getter
	@Setter
	@Schema(title = "Page Request")
	public static class Request {

		@NotNull(message = "Please enter page number")
		@Schema(description = "페이지 번호", example = "1")
		private Integer page;

		@NotNull(message = "Please enter page limit number")
		@Schema(description = "한 페이지 당 갯수", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
		private Integer limit;

		@Schema(description = "정렬 필드")
		private String sortColumn;

		@Schema(description = "정렬 타입")
		private ChangSolSortType sortType;

		/**
		 * 페이지 숫자 GET
		 *
		 * @return Integer
		 */
		public Integer getPage() {
			return this.page < 1 ? 0 : this.page - 1;
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Schema(title = "Page Response")
	public static class Response<T> {

		@Schema(description = "페이지 총 갯수")
		private long pageTotal;

		@Schema(description = "현재 페이지 번호")
		private int page;

		@Schema(description = "시작 시점 숫자")
		private int offset;

		@Schema(description = "한 페이지 당 갯수")
		private int limit;

		@Schema(description = "다음 페이지 여부")
		private Boolean isNext;

		@Schema(description = "이전 페이지 여부")
		private Boolean isPrev;

		@Schema(description = "데이터 총 갯수")
		private long dataTotalCount;

		@Schema(description = "페이지 데이터 총 갯수")
		private long dataCount;

		@Schema(description = "데이터 목록")
		private List<T> dataList;
	}


	/**
	 * Page Response Return
	 **/
	public static <X, Y> Response<X> valueOf(Page<Y> page, List<X> dataList) {

		final int PAGE_NUMBER = page.getNumber() + 1; // 0페이지 -> 1페이지로 변경

		long pageTotal;
		// 페이지 총 수
		if (page.getTotalElements() % page.getSize() != 0) {
			pageTotal = page.getTotalElements() / page.getSize() + 1;
		} else {
			pageTotal = page.getTotalElements() / page.getSize();
		}

		return new Response<>(pageTotal,
							  PAGE_NUMBER,
							  page.getNumber() * page.getSize(),
							  page.getSize(),
							  PAGE_NUMBER < pageTotal,
							  PAGE_NUMBER > 1,
							  page.getTotalElements(),
							  dataList.size(),
							  dataList);
	}
}

