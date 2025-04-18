package entities;

import lombok.Data;
import utils.PublisherAgent;

@Data
public class GameSession implements NotificationSenderInterface, CalculablePriceInterface {

    private Long id;
    private Long roomId;
    private String roomName;
    private Long userId;
    private String userNickname;
    private double price;
    private String status;
    private String paymentType;
    private NotificationSenderInterface publisherAgent;

    public GameSession() {
    }

    public void setPublisherAgent(NotificationSenderInterface publisherAgent) {
        this.publisherAgent=publisherAgent;
    }

    public void setAccepted(String accepted) {
        this.status = accepted;
        pushNotification();
    }

    public void pushNotification() {

    }

    public void printBasicInfoValues() {
        System.out.printf("  -  Id: %-5s  Nickname: %-30s%n", getId(), getUserNickname());
        System.out.printf("                Room name: %-15s    Price: %-30s%n", getRoomName(), getPrice());
        System.out.printf("                Status: %-30s%n", getStatus());
        System.out.println("\n");
    }

    @Override
    public boolean sendNotificationToSubscribers(Long notificationId) {
        return false;
    }

    @Override
    public boolean sendNotificationToSubscribers(NotificationGeneric notification) {
        return false;
    }

    @Override
    public void sendNotificationToSessionSubscribers(Long sessionId, boolean value) {

    }
}
