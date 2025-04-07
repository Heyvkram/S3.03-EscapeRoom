package entities;

import lombok.Data;

@Data
public class User implements NotificationSubscriberInterface {
    private Long id;
    private String nickName;
    private String name;
    private String surname;
    private String idCard;
    private String addressStreet;
    private Integer addressNumber;
    private String addressFloor;
    private String addressDoor;
    private String city;
    private String zipCode;
    private String country;
    private String phoneNumber;
    private String mail;
    private String notifiable;

    public String getWholeName() {
        return name + " " + surname ;
    }

    public void printBasicInfoValues() {
        System.out.printf("     %-5d %-25s ,%-25s | Email: %-30s  Nickname: %-25s%n", getId(), getSurname(), getName(), getMail(), getNickName());
    }

    @Override
    public String readNotification() {
        return getNotifiable();
    }
}
