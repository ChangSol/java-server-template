package org.changsol.api.apps.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UserDto
 */
public class UserDto {

    @Schema(description = "UserDto Response")
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        @Schema(description = "고유ID")
        private Long id;
        @Schema(description = "아이디")
        private String loginId;
    }

}
