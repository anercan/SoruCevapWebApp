package com.anercan.sorucevap.controller.auth;

import com.anercan.sorucevap.resource.JsonResponse;
import com.anercan.sorucevap.service.impl.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/property")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @GetMapping("/refresh")
    public JsonResponse<Boolean> refreshProperties() {
        return propertyService.refresh();
    }

}
