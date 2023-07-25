package com.example.social_network.repo;

import com.example.social_network.model.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {
    @Query(value = "select * from social_network.notification join social_network.users u on u.user_id = notification.user_id_user_id where u.user_id = :id ", nativeQuery = true)
    Iterable<Notification> findAllNotificationByUserId(Long id);
}
