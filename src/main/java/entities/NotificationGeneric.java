package entities;

import lombok.Data;
import utils.DateUtils;

import java.time.LocalDateTime;

@Data
public class NotificationGeneric implements NotificationInterface {
    private long id;
    private String title;
    private String shortDescription;
    private String message;
    private String type;
    private LocalDateTime registerDate;
    private LocalDateTime  modificationDate;

    public void printBasicInfoValues() {
        System.out.printf("     %-5d %-25s | Title: %-30s  short description: %-25s%n", getId(), getTitle(), getType(), getShortDescription());
    }

    public void printInfoValues() {
        System.out.printf("     %-5d    Title: %-25s", getId(), getTitle());
        System.out.println("            Short description: " + getShortDescription());
        System.out.println("            Message: " + getMessage());
        System.out.println("            Type: " + getType());
        DateUtils dateUtils = new DateUtils();
        System.out.println("            Register date: " + dateUtils.localDateToString_dd_MM_YYYY_HMS(getRegisterDate()));
        System.out.println("            Modification date: " + dateUtils.localDateToString_dd_MM_YYYY_HMS(getModificationDate()));
    }

    public void printNewInfoValues() {
        System.out.println("     Title: " + getTitle());
        System.out.println("    Short description: " + getShortDescription());
        System.out.println("    Message: " + getMessage());
        System.out.println("    Type: " + getType());
    }


    @Override
    public String getshippingType() {
        return "";
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public boolean send() {
        return true;
    }
}