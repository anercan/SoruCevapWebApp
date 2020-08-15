package com.anercan.sorucevap.service;

import com.anercan.sorucevap.config.PropertyUtil;
import com.anercan.sorucevap.dao.PropertyRepository;
import com.anercan.sorucevap.entity.Property;
import com.anercan.sorucevap.resource.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class PropertyService extends BaseService {

    @Autowired
    PropertyRepository propertyRepository;

    @PostConstruct
    public void getPropertiesToStaticMap() {
        if (PropertyUtil.isEmpty()) {
            List<Property> propertyList = propertyRepository.findAll();
            if (!propertyList.isEmpty()) {
                propertyList.stream().forEach(property -> PropertyUtil.addProperty(property.getKey(), property.getValue()));
            }
        }
    }

    public ServiceResult<Boolean> refresh() {
        List<Property> propertyList = propertyRepository.findAll();
        if (!propertyList.isEmpty()) {
            propertyList.stream().forEach(property -> PropertyUtil.addProperty(property.getKey(), property.getValue()));
        } else {
            return createFailResult();
        }
        return createServiceResult(Boolean.TRUE);
    }

}
