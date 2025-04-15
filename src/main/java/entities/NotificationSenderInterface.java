package entities;

public interface NotificationSenderInterface {

    public boolean sendNotificationToSubscribers(Long notificationId);

    public boolean sendNotificationToSubscribers(NotificationGeneric notification);

    public void sendNotificationToSessionSubscribers(Long sessionId, boolean value);

}
