package com.anercan.sorucevap.service;

import com.anercan.sorucevap.dao.BaseRepository;
import com.anercan.sorucevap.entity.BaseEntity;
import com.anercan.sorucevap.resource.ServiceResult;

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
        String isCreated = entity.getCreated_date() == null ? " created" : " updated";
        preSave(entity);
        Entity response = getRepository().save(entity);
        log.info(entity.getClass().getName() + isCreated + " :{}", response);
        return createServiceResult(response);
    }

    Entity preSave(Entity entity) {
        if (entity.getCreated_date() == null) {
            entity.setCreated_date(new Date());
        } else {
            entity.setModified_date(new Date());
        }
        return entity;
    }

}
