package com.anercan.sorucevap.service;

import com.anercan.sorucevap.client.resource.ServiceResult;
import com.anercan.sorucevap.config.PropertyUtil;
import com.anercan.sorucevap.dao.ClientPropertyRepository;
import com.anercan.sorucevap.dao.PropertyRepository;
import com.anercan.sorucevap.entity.ClientProperty;
import com.anercan.sorucevap.entity.ConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.List;

@Component
public class PropertyService extends BaseService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    ClientPropertyRepository clientPropertyRepository;

    @PostConstruct
    public void getPropertiesToStaticMap() {
        if (PropertyUtil.isEmpty()) {
            List<ConfigProperty> configPropertyList = propertyRepository.findAll();
            List<ClientProperty> clientPropertyList = clientPropertyRepository.findAll();
            if (CollectionUtils.isEmpty(configPropertyList) || CollectionUtils.isEmpty(clientPropertyList) ) {
                configPropertyList.stream().forEach(configProperty -> PropertyUtil.addProperty(configProperty.getKey(), configProperty.getValue()));
                clientPropertyList.stream().forEach(clientProperty -> PropertyUtil.addClientPropery(clientProperty.getKey(), clientProperty.getTrValue()));
            }
        }
    }

    public ServiceResult<Boolean> refresh() {
        List<ConfigProperty> configPropertyList = propertyRepository.findAll();
        List<ClientProperty> clientPropertyList = clientPropertyRepository.findAll();
        if (CollectionUtils.isEmpty(configPropertyList) || CollectionUtils.isEmpty(clientPropertyList) ) {
            configPropertyList.stream().forEach(configProperty -> PropertyUtil.addProperty(configProperty.getKey(), configProperty.getValue()));
            clientPropertyList.stream().forEach(clientProperty -> PropertyUtil.addClientPropery(clientProperty.getKey(), clientProperty.getTrValue()));
        } else {
            return createFailResult();
        }
        return createServiceResult(Boolean.TRUE);
    }

}
