package com.anercan.sorucevap.service.impl;

import com.anercan.sorucevap.config.PropertyUtil;
import com.anercan.sorucevap.dao.PropertyRepository;
import com.anercan.sorucevap.entity.Property;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @PostConstruct
    public void getPropertiesToStaticMap(){
        if(PropertyUtil.isEmpty()){
            List<Property> propertyList = propertyRepository.findAll();
            if(!propertyList.isEmpty()){
                propertyList.stream().forEach(property -> PropertyUtil.addProperty(property.getKey(),property.getValue()));
            }
        }
    }

}
