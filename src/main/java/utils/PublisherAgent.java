package utils;

import daos.NotificationDAO;
import daos.UserDAO;
import entities.NotificationGeneric;
import entities.User;

import java.sql.SQLException;
import java.util.List;

public class PublisherAgent {

    public static boolean sendNotificationToSubscribers(NotificationGeneric notification) {
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
        }else{
            System.out.println(">>> There are no subscribers.");
        }
        return true;
    }

    public static boolean sendNotificationToUser(NotificationGeneric notification) {
        UserDAO userDao = new UserDAO();
        User user = notification.getUser();
        if (user != null) {
            NotificationDAO notifDao = new NotificationDAO();
            notifDao.publishNotificationToUser(notification, user);
        }else{
            System.out.println("\n>>> There are no subscribers.");
        }
        return true;
    }

}