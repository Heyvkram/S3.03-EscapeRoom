package entities;

import lombok.Data;
import utils.DateUtils;

import java.time.LocalDateTime;

@Data
public class NotificationEmail extends NotificationGeneric {

    private final String shippingType = "Email";
    private User user = null;

    public NotificationEmail() {

    }

    public NotificationEmail(User user) {
        this.user = user;
    }

    public void printBasicInfoValues() {
        System.out.printf("     %-5d %-25s | Title: %-30s  Short description: %-25s Shipping type: %-25s%n", getId(), getTitle(), getType(), getShortDescription(), getshippingType());
    }

    public void printInfoValues() {
        System.out.printf("     %-5d    Title: %-25s", getId(), getTitle());
        System.out.println("            Short description: " + getShortDescription());
        System.out.println("            Message: " + getMessage());
        System.out.println("            Type: " + getType());
        if (user != null && !user.getMail().isEmpty()) {
            System.out.println("            User Email: " + user.getMail());
        } else {
            System.out.println("            User Email: User not informed");
        }
        DateUtils dateUtils = new DateUtils();
        System.out.println("            Register date: " + dateUtils.localDateToString_dd_MM_YYYY_HMS(getRegisterDate()));
        System.out.println("            Modification date: " + dateUtils.localDateToString_dd_MM_YYYY_HMS(getModificationDate()));
    }

    public void printNewInfoValues() {
        System.out.println("    Title: " + getTitle());
        System.out.println("    Short description: " + getShortDescription());
        System.out.println("    Message: " + getMessage());
        System.out.println("    Type: " + getType());
    }

    @Override
    public boolean send() {
        if (user != null && !user.getMail().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getshippingType() {
        return shippingType;
    }

}


