package org.changsol.api.utils.mapper;

import org.mapstruct.MappingTarget;

public interface BaseMapper<E, R, C, U> {
    R toResponse(E entity);
    E entityCreate(C createDto);
    void entityUpdate(E sampleMaster, @MappingTarget U updateDto);
}
