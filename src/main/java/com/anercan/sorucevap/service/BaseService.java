package com.anercan.sorucevap.service;

import com.anercan.sorucevap.SorucevapApplication;
import com.anercan.sorucevap.resource.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BaseService {

    protected static final Logger log = LoggerFactory.getLogger(SorucevapApplication.class);

    @Autowired
    protected Environment env;

    Date date = new Date();

    protected ServiceResult createServiceResult(Object value) {
        return new ServiceResult(value);
    }

    protected ServiceResult createServiceResult(Object value, String message) {
        return new ServiceResult(value, message);
    }

    protected ServiceResult createFailResult(String message) {
        return new ServiceResult(null, message, HttpStatus.BAD_REQUEST);
    }

    protected ServiceResult createFailResult() {
        return new ServiceResult(null, "", HttpStatus.BAD_REQUEST);
    }

}
