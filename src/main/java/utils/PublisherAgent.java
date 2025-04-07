package utils;

import daos.NotificationDAO;
import daos.UserDAO;
import entities.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PublisherAgent implements NotificationSenderInterface {

    public static final boolean NOTIFICATION_TYPE_ACCEPTED = true;
    public static final boolean NOTIFICATION_TYPE_REJECTED_OR_OTHER = false;

    public boolean sendNotificationToSubscribers(Long notificationId) {
        NotificationDAO notifDao = new NotificationDAO();
        Optional<NotificationSMS> notifOpt = null;
        try {
            notifOpt = notifDao.getNotificationById(notificationId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return sendNotificationToSubscribers(notifOpt.get());
    }

    public boolean sendNotificationToSubscribers(NotificationInterface notification) {
        UserDAO userDao = new UserDAO();

        List<User> userList = null;
        try {
            userList = userDao.getUsersNotifiables();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        if (!userList.isEmpty()) {
            NotificationDAO notifDao = new NotificationDAO();
            notifDao.publishNotification(notification, userList);
        } else {
            System.out.println(">>> There are no subscribers.");
        }
        return true;
    }

    @Override
    public void sendNotificationToSessionSubscribers(Long sessionId, boolean message) {

        // LoadSession
        // Get users email
        // send notification to session users

        System.out.println("sessionId: " + sessionId +" message:" + sessionId);
    }

}
