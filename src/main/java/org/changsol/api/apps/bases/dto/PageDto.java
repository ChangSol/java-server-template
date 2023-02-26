package org.changsol.api.apps.bases.dto;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 * Page Dto Class
 */
public class PageDto {

	@Getter
	@Setter
	@Schema(title = "PageDto Request")
	public static class Request {

		@NotNull(message = "Please enter page number")
		@Schema(title = "페이지 번호", example = "1")
		private Integer page = 1;

		@NotNull(message = "Please enter page limit number")
		@Schema(title = "한 페이지 당 갯수", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
		private Integer limit = 20;

		@Schema(title = "정렬 목록")
		private List<SortDto> sortList = Lists.newArrayList();

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
	@Schema(title = "PageDto Response")
	public static class Response<T> {

		@Schema(title = "페이지 총 갯수")
		private long pageTotal;

		@Schema(title = "현재 페이지 번호")
		private int page;

		@Schema(title = "시작 시점 숫자")
		private int offset;

		@Schema(title = "한 페이지 당 갯수")
		private int limit;

		@Schema(title = "다음 페이지 여부")
		private Boolean isNext;

		@Schema(title = "이전 페이지 여부")
		private Boolean isPrev;

		@Schema(title = "데이터 총 갯수")
		private long dataTotalCount;

		@Schema(title = "페이지 데이터 총 갯수")
		private long dataCount;

		@Schema(title = "데이터 목록")
		private List<T> dataList;

		/**
		 * Response 형태로 반환
		 **/
		public static <X, Y> Response<X> toResponse(Page<Y> page, List<X> dataList) {

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
								  PAGE_NUMBER > 1,
								  PAGE_NUMBER < pageTotal,
								  page.getTotalElements(),
								  dataList.size(),
								  dataList);
		}

		/**
		 * Response 형태로 반환
		 **/
		public static <X> Response<X> toResponse(Request request, List<X> dataList) {
			// 별도 페이징 처리
			if ((request.getPage() - 1) * request.limit > dataList.size()) {
				request.setPage(dataList.size() / request.limit + 1);
			}

			PageRequest pageRequest = PageRequest.of(request.getPage(), request.getLimit());

			// offset
			final int START = (int) pageRequest.getOffset();
			final int END = Math.min((START + pageRequest.getPageSize()), dataList.size());

			Page<X> page = new PageImpl<>(dataList.subList(END == 0 ? END : START, END), pageRequest, dataList.size());

			return Response.toResponse(page, page.getContent());
		}
	}
}

