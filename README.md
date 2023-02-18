# ChangSol Server Template

### SpringBoot Server(API) Service를 위한 Template

### 버전
Java 16<br>
Spring Boot 2.5.3<br>
Gradle-7.1<br>
QueryDSL 4.4.0

### 기본환경
- 사용포트 : 8080<br>
- swagger url : http://127.0.0.1:8080/swagger-ui

### 기술 스택
- JAVA
- SpringBoot
- JPA (Spring Data JPA), Hibernate
- QueryDSL
- MySQL or PostgreSQL or H2 선택

### 구축 내용
- 초기 SpringBoot 설정
- JPA(Spring-JPA) 사용
- H2 DB를 사용하여 Sample 진행
- SpringDoc를 통한 OpenAPI(3.0) 진행 (Swagger3)


### ERD 
- https://www.erdcloud.com/

### Swagger 2 -> 3
- Springfox -> SpringDoc로 변경 Springboot에서 SpringDoc OpenAPI 3.0
- 달라진 annotation
- @Api -> @Tag
- @ApiIgnore -> @Parameter(hidden = true) or @Operation(hidden = true) or @Hidden
- @ApiImplicitParam -> @Parameter
- @ApiImplicitParams -> @Parameters
- @ApiModel -> @Schema
- @ApiModelProperty(hidden = true) -> @Schema(accessMode = READ_ONLY)
- @ApiModelProperty -> @Schema
- @ApiOperation(value = "foo", notes = "bar") -> @Operation(summary = "foo", description = "bar")
- @ApiParam -> @Parameter
- @ApiResponse(code = 404, message = "foo") -> @ApiResponse(responseCode = "404", description = "foo")

### Code Style
- chang_sol_code_style.xml 참고

### Custom Jpa Specification (동적쿼리예시)
- 조건
```java
/**
* SampleMaster Data Get
* @param request 검색조건
* @return SampleMasterDto.Response 리스트
*/
public List<SampleMasterDto.Response> getSampleMasterList(SampleMasterDto.Request request) {
    //조건
    ChangSolJpaRestriction restriction = new ChangSolJpaRestriction();
    if (ChangSolUtil.isNotBlank(request.getKeyword())) {
        restriction.like("masterName", "테스트");
    }

    return sampleMasterRepository.findAll(restriction.toSpecification())
                                 .stream()
                                 .map(SampleMasterMapper.INSTANCE::response)
                                 .toList();
}
```
- fetch join
    - 자식 fetch 시 distinct true 
```java
/**
* SampleMaster Data Get
* @param request 검색조건
* @return SampleMasterDto.Response 리스트
*/
public List<SampleMasterDto.Response> getSampleMasterList(SampleMasterDto.Request request) {
    //조건
    ChangSolJpaRestriction restriction = new ChangSolJpaRestriction();
    if (ChangSolUtil.isNotBlank(request.getKeyword())) {
        restriction.like("masterName", "테스트");
    }
	
    restriction.addFetch("sampleDetails", JoinType.LEFT);

    return sampleMasterRepository.findAll(restriction.toSpecification())
                                 .stream()
                                 .map(SampleMasterMapper.INSTANCE::response)
                                 .toList();
}
```