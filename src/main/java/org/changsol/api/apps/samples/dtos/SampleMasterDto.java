package org.changsol.api.apps.samples.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SampleMasterDto
 */
public class SampleMasterDto {

    @Schema(description = "SampleMaster Request")
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request {
        private String keyword;
    }

    @Schema(description = "SampleMaster Response")
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        @Schema(description = "고유ID")
        private Long id;
        @Schema(description = "마스터 이름")
        private String masterName;
    }

    @Schema(description = "SampleMaster CreateOrUpdate")
    @Getter
    @Setter
    @NoArgsConstructor
    public static class CreateOrUpdate {
        @Schema(description = "마스터 이름", example = "창솔루션")
        private String masterName;
    }

}
