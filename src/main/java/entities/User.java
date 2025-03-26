package entities;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String lastName1;
    private String lastName2;
    private String adress_street;
    private Integer adress_number;
    private String adress_floor;
    private String adress_door;
    private String city;
    private String zip_code;
    private String country;
    private String phone;
    private String mail;

    public String getWholeName() {
        return name + " " + lastName1 + " " + lastName2;
    }
    public void printBasicInfoValues() {
        System.out.printf("%-5d  %-25s  %-25s  %-25s\n", getId(), getName(), getLastName1(), getLastName2());
    }

}
