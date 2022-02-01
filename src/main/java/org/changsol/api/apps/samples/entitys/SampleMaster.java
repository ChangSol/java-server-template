package org.changsol.api.apps.samples.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.changsol.api.apps.samples.dtos.SampleMasterDto;
import org.changsol.api.apps.samples.mappers.SampleMasterMapper;
import org.changsol.api.utils.entity.BaseEntityLongId;

import javax.persistence.Entity;

/**
 * @author ChangSol
 * @version 0.0.1
 * @see SampleMaster
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SampleMaster extends BaseEntityLongId {
    private String masterName;

    public SampleMasterDto.Response toResponse(){
        return SampleMasterMapper.INSTANCE.toResponse(this);
    }
}
