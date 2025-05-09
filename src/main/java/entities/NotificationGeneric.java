package entities;

import lombok.Data;
import utils.DateUtils;

import java.time.LocalDateTime;

@Data
public class NotificationGeneric {
    private long id;
    private String title;
    private String shortDescription;
    private String message;
    private String type;
    private String shippingType;
    private LocalDateTime registerDate;
    private LocalDateTime  modificationDate;
    private User  user;

    public void printBasicInfoValues() {
        System.out.printf("     %-5d  Shipping type: %-10s   Type: %-10s   Title: %-30s    Short description: %-25s%n", getId(), getShippingType(), getType(), getTitle(),  getShortDescription());
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

    public NotificationGeneric() {
    }

    public NotificationGeneric(String message, User user, String shippingType) {
        this.message = message;
        this.user = user;
        this.shippingType = shippingType;
    }
}