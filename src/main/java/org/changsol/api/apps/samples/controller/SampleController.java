package org.changsol.api.apps.samples.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.changsol.api.apps.samples.dto.SampleMasterDto;
import org.changsol.api.apps.samples.service.SampleMasterService;
import org.changsol.api.utils.page.ChangSolPageUtils;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "00.Sample", description = "Sample API 입니다.") //Swagger API 명 설정
@RequiredArgsConstructor //의존성주입을 위한 롬복 어노테이션 선언
@RestController //Rest 컨트롤러 선언
@RequestMapping(path = "/v1/samples") //경로 지정
public class SampleController {
    private final SampleMasterService sampleMasterService;

    @Operation(summary = "sample master getList", description = "sample master 데이터 목록 가져오기")
    @GetMapping("/master")
    public List<SampleMasterDto.Response> getSampleMasterList(@ParameterObject SampleMasterDto.Request request){
        return sampleMasterService.getSampleMasterList(request);
    }

    @Operation(summary = "sample master get page", description = "sample master 데이터 목록 페이징 가져오기")
    @GetMapping("/master/page")
    public ChangSolPageUtils.Response<SampleMasterDto.Response> getSampleMasterPage(@ParameterObject SampleMasterDto.RequestPage request){
        return sampleMasterService.getSampleMasterPage(request);
    }

    @Operation(summary = "sample master create", description = "sample master 데이터 생성")
    @PostMapping("/master")
    public SampleMasterDto.Response createOne(@RequestBody SampleMasterDto.CreateOrUpdate createOrUpdate){
        return sampleMasterService.createOne(createOrUpdate);
    }

    @Operation(summary = "sample master update", description = "sample master 데이터 갱신")
    @PutMapping("/master/{id}")
    public SampleMasterDto.Response create(@PathVariable Long id, @RequestBody SampleMasterDto.CreateOrUpdate createOrUpdate){
        return sampleMasterService.updateOne(id, createOrUpdate);
    }

    @Operation(summary = "sample master delete", description = "sample master 데이터 삭제")
    @DeleteMapping("/master/{id}")
    public SampleMasterDto.Response deleteOne(@PathVariable Long id){
        return sampleMasterService.deleteOne(id);
    }
}
