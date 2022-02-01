package org.changsol.api.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Swagger View Config Class
 */
@Component
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
        Info info = new Info()
                .title("Changsol Server Template API")
                .version(appVersion)
                .description("창솔 서버 템플릿 API입니다.")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact().name("changsol-github").url("https://github.com/ChangSol"))
                .license(new License().name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"));

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

//    //Swagger 설정의 핵심이 되는 Bean
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.OAS_30)
//                //Swagger 에서 제공해주는 기본 응답 코드 (200, 401, 403, 404)
//                //false 로 설정하면 기본 응답 코드를 노출하지 않음
//                .useDefaultResponseMessages(false)
//                .select()
//                //api 스펙이 작성되어 있는 패키지 (Controller) 를 지정
//                .apis(RequestHandlerSelectors.any())
////                .paths(PathSelectors.ant("/v1/**"))
//                //apis 에 있는 API 중 특정 path
//                .paths(PathSelectors.any())
//                .build()
//                //Swagger UI 로 노출할 정보
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Changsol-Server-Template Swagger")
//                .description("Changsol-Server-Template Swagger Configuration")
//                .version("1.0")
//                .build();
//    }
}
