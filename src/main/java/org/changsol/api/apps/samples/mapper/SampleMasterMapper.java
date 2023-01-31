package org.changsol.api.apps.samples.mapper;

import org.changsol.api.apps.samples.dto.SampleMasterDto;
import org.changsol.api.apps.samples.entity.SampleMaster;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SampleMasterMapper {

    SampleMasterMapper INSTANCE = Mappers.getMapper(SampleMasterMapper.class);

    //Response Mapping
    SampleMasterDto.Response response(SampleMaster sampleMaster);

    //Create Map
    SampleMaster create(SampleMasterDto.CreateOrUpdate createOrUpdate);

    //Update Map
    void update(SampleMaster sampleMaster, @MappingTarget SampleMasterDto.CreateOrUpdate createOrUpdate);
}
