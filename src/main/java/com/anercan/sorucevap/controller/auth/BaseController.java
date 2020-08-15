package com.anercan.sorucevap.controller.auth;

import com.anercan.sorucevap.resource.JsonResponse;
import com.anercan.sorucevap.resource.ServiceResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseController {

    protected JsonResponse createJsonResponse(ServiceResult response) {
        return new JsonResponse(response.getValue(), response.getStatus(), response.getMessage());
    }
}
