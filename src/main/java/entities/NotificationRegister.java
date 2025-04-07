package entities;

import lombok.Data;

@Data
public class NotificationRegister {
    private long id;
    private long notificationId;
    private long gameId;
    private long userId;
    private String status;
}