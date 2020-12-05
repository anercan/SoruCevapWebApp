package com.anercan.sorucevap.service;

import com.anercan.sorucevap.dao.BaseRepository;
import com.anercan.sorucevap.entity.BaseEntity;
import com.anercan.sorucevap.client.resource.ServiceResult;

import java.util.Date;
import java.util.Optional;

public abstract class AbstractEntityService<Entity extends BaseEntity> extends BaseService {

    abstract BaseRepository<Entity, Long> getRepository();

    public ServiceResult<Entity> getById(Long id) {
        Optional<Entity> entity = getRepository().findById(id);
        if (entity.isPresent()) {
            return new ServiceResult<>(entity.get());
        }
        return createFailResult("Entity Couldn't Found");
    }

    public ServiceResult<Entity> save(Entity entity) {
        preSave(entity);
        Entity response = getRepository().save(entity);
        log.info(entity.getClass().getName() + entity.getCreatedDate() == null ? " created" : " updated" + " :{}", response);
        return createServiceResult(response);
    }

    void preSave(Entity entity) {
        if (entity.getCreatedDate() == null) {
            entity.setCreatedDate(new Date());
        } else {
            entity.setModifiedDate(new Date());
        }
    }

}
