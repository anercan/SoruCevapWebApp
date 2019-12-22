package com.anercan.sorucevap.service.impl;

import com.anercan.sorucevap.SorucevapApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BaseService {

    protected static final Logger logger = LoggerFactory.getLogger(SorucevapApplication.class);

    @Autowired
    protected Environment env;

    Date date = new Date();

}
