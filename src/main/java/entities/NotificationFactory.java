package entities;

import utils.EnumConstants;

public class NotificationFactory {
    public static NotificationInterface getNotification(String notificationType) {
        NotificationInterface notification;
        if (EnumConstants.NOTIFICATION_SHIPPING_TYPE.EMAIL.getDescription().equals(notificationType)) {
            notification = new NotificationSMS();
        } else if (EnumConstants.NOTIFICATION_SHIPPING_TYPE.SMS.getDescription().equals(notificationType)) {
            notification = new NotificationEmail();
        } else {
            notification = new NotificationGeneric();
        }
        return notification;
    }
}
