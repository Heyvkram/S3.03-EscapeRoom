package entities;

import lombok.Data;
import utils.EntryUtils;

import java.util.Scanner;

@Data
public class User {
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

    public String getWholeName() {
        return name + " " + surname ;
    }

    public void printBasicInfoValues() {
        System.out.printf("     %-5d  %-25s  %-25s ,Nickname: %-25s%n", getId(), getName(), getSurname(), getNickName());
    }

}
