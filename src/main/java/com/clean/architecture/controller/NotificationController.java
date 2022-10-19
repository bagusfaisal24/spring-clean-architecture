package com.clean.architecture.controller;

import com.clean.architecture.form.NotificationForm;
import com.clean.architecture.service.NotificationSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationSvc notificationSvc;

    @PostMapping
    public ResponseEntity<Object> getNotif(@RequestBody NotificationForm form){
        return notificationSvc.getNotif(form);
    }
}
