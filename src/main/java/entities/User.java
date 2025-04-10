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
        System.out.printf(" Id: %-5s  %-30s%n",getId(), getWholeName());
        System.out.printf("     Nickname: %-15s  Notifiable: %-10s  email: %-30s%n", getNickName(), ((getNotifiable()==null)?"":getNotifiable()), getMail() );
    }

}