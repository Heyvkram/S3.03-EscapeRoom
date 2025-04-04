package forms;

import daos.NotificationDAO;
import daos.UserDAO;
import entities.NotificationFactory;
import entities.NotificationInterface;
import entities.NotificationSMS;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EnumConstants;
import utils.EntryUtils;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class NotificationForm {

    private static final Logger log = LogManager.getLogger(NotificationForm.class);

    public void menuNotification(Scanner scanner) {
        int option;
        NotificationDAO notifDao = new NotificationDAO();
        do {
            System.out.println("\n");
            System.out.println("    -----------------------------------------");
            System.out.println("    Notification menu:");
            System.out.println("    -----------------------------------------");
            System.out.println("    1. New notification");
            System.out.println("    2. List notifications");
            System.out.println("    3. Delete notification");
            System.out.println("    4. Send notification");
            System.out.println("    5. Show notification");
            System.out.println("    6. Back");

            System.out.print("\n>>> Choose option > ");
            // option = scanner.nextInt();
            option = EntryUtils.llegirInt(scanner,null);
            scanner.nextLine();
            switch (option) {
                case 1:
                    NotificationInterface newNotification = newNotificationForm(scanner);
                    if(newNotification!=null && !notifDao.insertNewNotification(newNotification)){
                        System.out.println("\n   Error: Unable to establish connection to the database.");
                        System.out.println("     (Please contact your system administrator)\n");
                        if(!EntryUtils.readYesNo(scanner," Type 'y' for continue or 'n' for escape.")){
                            option=4;
                        }
                    }
                    break;
                case 2:
                    try {
                        notifDao.printAllNotifications();
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 3:
                    try {
                        Optional<NotificationSMS>  notifOpt =  notifDao.getNotificationById(EntryUtils.readStringLikeLong(scanner, "Type the user to delete id : ", false));
                        if(notifOpt.isPresent()){
                            System.out.println("\n");
                            notifOpt.get().printBasicInfoValues();
                            if(EntryUtils.readYesNo(scanner, "\nDelete this notification (y/n)? ")){
                                if(notifDao.deleteById(notifOpt.get().getId())){
                                    System.out.println("\n>>> Notification deleted.");
                                }
                            }
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 4:
                    try {
                        Optional<NotificationSMS>  notifOpt =  notifDao.getNotificationById(EntryUtils.readStringLikeLong(scanner, "Type the user to delete id : ", false));
                        if(notifOpt.isPresent()){
                            System.out.println("\n");
                            notifOpt.get().printBasicInfoValues();
                            if(EntryUtils.readYesNo(scanner, "\nSend this notification (y/n)? ")){
                                if(notifDao.sendNotification(notifOpt.get())){
                                    System.out.println("\n>>> Notification sended.");
                                }
                            }
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 5:
                    try{
                        Optional<NotificationSMS>  notifOpt =  notifDao.getNotificationById(EntryUtils.readStringLikeLong(scanner, "Type the notification id : ", false));
                        if(notifOpt.isPresent()){
                            notifOpt.get().printInfoValues();
                        }else{
                            System.out.println(">>> Notification not found.");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        System.out.println(">>> ERROR: "+e);
                        log.error(e);
                    }
                    break;
                default:
                    System.out.println(">>> Wrong option.");
            }
        } while (option != 6);
        scanner.nextLine();
    }

    public NotificationInterface newNotificationForm(Scanner scanner) {
        System.out.println("\n");
        NotificationInterface notif =null;

        Integer noteShippingTypeValue = null;
        do{
            System.out.println("Choose option : " + EnumConstants.NOTIFICATION_SHIPPING_TYPE.getMenuOptions());
            noteShippingTypeValue = EntryUtils.llegirInt(scanner, "*Shipping ype: ", false, EnumConstants.NOTIFICATION_SHIPPING_TYPE.getNumberMaxLevelValue() );
            scanner.nextLine();

        }while(!EnumConstants.NOTIFICATION_SHIPPING_TYPE.getLevelCodes().contains(noteShippingTypeValue));

        notif = NotificationFactory.getNotification(EnumConstants.NOTIFICATION_SHIPPING_TYPE.getDescriptionFromLevelCode(noteShippingTypeValue));

        notif.setTitle(EntryUtils.llegirString(scanner, "*Title: ", false, 50));
        notif.setShortDescription(EntryUtils.llegirString(scanner, "*Short description: ", false, 100));
        notif.setMessage(EntryUtils.llegirString(scanner, "*Message: ", false, 500));

        Integer noteTypeValue =null;
        do{
            System.out.println("Choose option : " + EnumConstants.NOTIFICATIONS_TYPE.getMenuOptions());
            noteTypeValue = EntryUtils.llegirInt(scanner, "*Type: ", false, EnumConstants.NOTIFICATIONS_TYPE.getNumberMaxLevelValue() );
            scanner.nextLine();

        }while(!EnumConstants.NOTIFICATIONS_TYPE.getLevelCodes().contains(noteTypeValue));

        notif.setType(EnumConstants.NOTIFICATIONS_TYPE.getDescriptionFromLevelCode(noteTypeValue));
        notif.printNewInfoValues();

        if(!EntryUtils.readYesNo(scanner, "\nSave this notification (y/n)? ")){
            notif=null;
        }

        if(noteShippingTypeValue.equals(EnumConstants.NOTIFICATION_SHIPPING_TYPE.EMPTY.getLevelCode())){
            if(!EntryUtils.readYesNo(scanner, "\nDo you want to add a user? (y/n)? ")){
                UserDAO userDao = new UserDAO();
                Optional<User> userOpt = userDao.getUserById(EntryUtils.readStringLikeLong(scanner, "Type the user id: ", true));
            }
        }

        return notif;
    }

}
