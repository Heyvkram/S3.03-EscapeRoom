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
        System.out.printf("  -  Id: %-5s  Nickname: %-15s   Name: %-30s%n", getId(), getNickName(), getWholeName());
        System.out.printf("                Notifiable: %-15s Phonenumber: %-10s  email: %-30s%n", ((getNotifiable()==null)?"":getNotifiable()), getPhoneNumber(), getMail() );
    }

}