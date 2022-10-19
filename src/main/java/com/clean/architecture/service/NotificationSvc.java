package com.clean.architecture.service;

import com.clean.architecture.form.NotificationForm;
import org.springframework.http.ResponseEntity;

public interface NotificationSvc {

    ResponseEntity<Object> getNotif(NotificationForm form);
}
