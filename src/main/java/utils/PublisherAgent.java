package utils;

import daos.NotificationDAO;
import daos.UserDAO;
import entities.NotificationInterface;
import entities.User;

import java.sql.SQLException;
import java.util.List;

public class PublisherAgent {

    public static boolean sendNotificationToSubscribers(NotificationInterface notification) {
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

}
