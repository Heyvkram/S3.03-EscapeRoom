package entities;

import lombok.Data;
import utils.PublisherAgent;

@Data
public class GameSession{

    private Long id;
    private Long room_id;
    private Long payment_id;
    private Long user_id;
    private int accepted;
    private NotificationSenderInterface publisherAgent;

    public GameSession() {
    }
    public void setPublisherAgent(NotificationSenderInterface publisherAgent) {
        this.publisherAgent=publisherAgent;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
        pushNotification();
    }

    public void pushNotification() {

    }
}
