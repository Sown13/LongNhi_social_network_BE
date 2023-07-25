package com.example.social_network.service.notification;

import com.example.social_network.model.notification.Notification;
import com.example.social_network.service.IGeneralService;

import java.util.Optional;

public interface INotificationService extends IGeneralService<Notification> {

    Iterable<Notification> findAllNotificationByUserId(Long id);
}
