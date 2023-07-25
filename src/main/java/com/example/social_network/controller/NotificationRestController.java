package com.example.social_network.controller;

import com.example.social_network.model.notification.Notification;
import com.example.social_network.service.notification.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/notifications")
public class NotificationRestController {
    @Autowired
    private INotificationService notificationService;

    @PostMapping()
    public ResponseEntity<Void> saveNotification(@RequestBody Notification notification) {
        try {
            notificationService.save(notification);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<Iterable<Notification>> findAllNotificationByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(notificationService.findAllNotificationByUserId(id), HttpStatus.OK);
    }
}
