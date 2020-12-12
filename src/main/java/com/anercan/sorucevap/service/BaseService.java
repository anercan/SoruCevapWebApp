package com.anercan.sorucevap.service;

import com.anercan.sorucevap.SorucevapApplication;
import com.anercan.sorucevap.client.resource.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class BaseService {

    protected static final Logger log = LoggerFactory.getLogger(SorucevapApplication.class);

    @Autowired
    protected HttpServletRequest request;

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

    protected ServiceResult createFailResult(String redirect, String message) {
        return new ServiceResult(null, message, redirect, HttpStatus.BAD_REQUEST);
    }

    protected ServiceResult createFailResult() {
        return new ServiceResult(null, "", HttpStatus.BAD_REQUEST);
    }

    protected Long getUserId() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ((CustomUserDetail) principal).getUserId();
        } catch (Exception e) {
            log.info("Exception when get current user : {}", SecurityContextHolder.getContext().getAuthentication(), e);
            return null;
        }

    }


}
