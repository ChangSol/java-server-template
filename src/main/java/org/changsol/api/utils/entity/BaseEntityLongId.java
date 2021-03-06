package org.changsol.api.utils.entity;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass //자식에게 매핑정보만 제공
public class BaseEntityLongId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
}
