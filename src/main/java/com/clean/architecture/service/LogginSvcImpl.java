package com.clean.architecture.service;

import com.clean.architecture.model.LoggingModel;
import com.clean.architecture.repository.LoggingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class LogginSvcImpl implements LoggingSvc{

    @Autowired
    private LoggingRepository loggingRepository;

    @Override
    public void createLog(HashMap<String, Object> data, String type) {
        LoggingModel log = new LoggingModel();
        log.setId(UUID.randomUUID().toString());
        log.setData(data);
        log.setType(type);
        loggingRepository.save(log);
    }
}
