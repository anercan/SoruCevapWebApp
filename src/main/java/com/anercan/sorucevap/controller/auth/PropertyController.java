package com.anercan.sorucevap.controller.auth;

import com.anercan.sorucevap.client.resource.JsonResponse;
import com.anercan.sorucevap.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/property")
public class PropertyController extends BaseController {

    @Autowired
    PropertyService propertyService;

    @GetMapping("/refresh")
    public JsonResponse<Boolean> refreshProperties() {
        return createJsonResponse(propertyService.refresh());
    }

}
