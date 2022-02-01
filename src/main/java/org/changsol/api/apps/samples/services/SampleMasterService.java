package org.changsol.api.apps.samples.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.changsol.api.apps.samples.dtos.SampleMasterDto;
import org.changsol.api.apps.samples.entitys.SampleMaster;
import org.changsol.api.apps.samples.mappers.SampleMasterMapper;
import org.changsol.api.apps.samples.repositorys.SampleMasterRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SampleMasterService {
    private final SampleMasterRepository sampleMasterRepository;

    /**
     * SampleMaster Data Get
     * @param request 검색조건
     * @return SampleMasterDto.Response 리스트
     */
    public List<SampleMasterDto.Response> getSampleMasterList(SampleMasterDto.Request request) {
        //조건
        if (StringUtils.isNotBlank(request.getKeyword())) {

        }

        return sampleMasterRepository.findAll().stream().map(SampleMaster::toResponse).collect(Collectors.toList());
    }

    /**
     * SampleMaster Data Create One
     * @param createOrUpdate 생성조건
     * @return SampleMasterDto.Response
     */
    @Transactional
    public SampleMasterDto.Response createOne(SampleMasterDto.CreateOrUpdate createOrUpdate){
        //New Object
        SampleMaster sampleMaster = SampleMasterMapper.INSTANCE.entityCreate(createOrUpdate);
        sampleMasterRepository.save(sampleMaster);
        return sampleMaster.toResponse();
    }

    /**
     * SampleMaster Data Update One
     * @param id 고유ID
     * @param createOrUpdate 갱신조건
     * @return SampleMasterDto.Response
     */
    @Transactional
    public SampleMasterDto.Response updateOne(Long id, SampleMasterDto.CreateOrUpdate createOrUpdate){
        //Find Object
        SampleMaster sampleMaster = sampleMasterRepository.findById(id).orElseThrow(() -> new NotFoundException("SampleMaster를 찾을 수 없습니다."));

        //Mapping
        SampleMasterMapper.INSTANCE.entityUpdate(sampleMaster, createOrUpdate);

        return sampleMaster.toResponse();
    }

    /**
     * SampleMaster Data Delete One
     * @param id 고유 ID
     * @return SampleMasterDto.Response
     */
    @Transactional
    public SampleMasterDto.Response deleteOne(Long id){
        //Find Object
        SampleMaster sampleMaster = sampleMasterRepository.findById(id).orElseThrow(() -> new NotFoundException("SampleMaster를 찾을 수 없습니다."));

        //Delete
        sampleMasterRepository.delete(sampleMaster);

        return sampleMaster.toResponse();
    }
}
