package com.example.social_network.service.notification;
import com.example.social_network.model.notification.Notification;
import com.example.social_network.repo.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private NotificationRepo notificationRepo;

    @Override
    public Iterable<Notification> findAll() {
        return null;
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepo.save(notification);
    }

    @Override
    public void remove(Long id) {
        notificationRepo.deleteById(id);
    }

    @Override
    public Iterable<Notification> findAllNotificationByUserId(Long id) {
        return this.notificationRepo.findAllNotificationByUserId(id);
    }
}
