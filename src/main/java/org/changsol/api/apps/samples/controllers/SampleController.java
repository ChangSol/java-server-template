package org.changsol.api.apps.samples.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.changsol.api.apps.samples.dtos.SampleMasterDto;
import org.changsol.api.apps.samples.services.SampleMasterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "00.Sample", description = "Sample API 입니다.") //Swagger API 명 설정
@RequiredArgsConstructor //의존성주입을 위한 롬복 어노테이션 선언
@RestController //Rest 컨트롤러 선언
@RequestMapping(path = "/v1/samples") //경로 지정
public class SampleController {
    private final SampleMasterService sampleMasterService;

    @Operation(summary = "sample master getList", description = "sample master 데이터 목록 가져오기")
    @GetMapping("/master")
    public List<SampleMasterDto.Response> getSampleMasterList(SampleMasterDto.Request request){
        return sampleMasterService.getSampleMasterList(request);
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
