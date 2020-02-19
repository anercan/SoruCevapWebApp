package com.anercan.sorucevap.service.impl;

import com.anercan.sorucevap.SorucevapApplication;
import com.anercan.sorucevap.resource.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BaseService {

    protected static final Logger logger = LoggerFactory.getLogger(SorucevapApplication.class);

    @Autowired
    protected Environment env;

    Date date = new Date();

    protected JsonResponse createFailResult() {
        JsonResponse response = new JsonResponse();

        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        response.setMessage("Fail Result");
        response.setValue(Boolean.FALSE);

        return response;
    }

    protected JsonResponse createFailResult(String message) {
        JsonResponse response = new JsonResponse();

        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        response.setMessage(message);
        response.setValue(Boolean.FALSE);

        return response;
    }

}
