package forms;

import daos.NotificationDAO;
import daos.UserDAO;
import entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EnumConstants;
import utils.EntryUtils;
import utils.PublisherAgent;

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
            System.out.println("    1. Create notification");
            System.out.println("    2. List notifications templates");
            System.out.println("    3. Show notification template");
            System.out.println("    4. Delete notification template");
            System.out.println("    5. Send notification to subscribers");
            System.out.println("    6. Back");

            System.out.print("\n>>> Choose option > ");
            // option = scanner.nextInt();
            option = EntryUtils.readStringLikeInt(scanner, null, false);
            switch (option) {
                case 1:
                    NotificationGeneric newNotification = newNotificationForm(scanner);
                    if (newNotification != null && !notifDao.insertNewNotification(newNotification)) {
                        System.out.println("\n   Error: Unable to establish connection to the database.");
                        System.out.println("     (Please contact your system administrator)\n");
                        if (!EntryUtils.readYesNo(scanner, " Type 'y' for continue or 'n' for escape.")) {
                            option = 4;
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
                        Optional<NotificationGeneric> notifOpt = notifDao.getNotificationById(EntryUtils.readStringLikeLong(scanner, "Type the notification id : ", false));
                        if (notifOpt.isPresent()) {
                            notifOpt.get().printInfoValues();
                        } else {
                            System.out.println("\n>>> Notification not found.");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        System.out.println(">>> ERROR: " + e);
                        log.error(e);
                    }
                    break;
                case 4:
                    try {
                        Optional<NotificationGeneric> notifOpt = notifDao.getNotificationById(EntryUtils.readStringLikeLong(scanner, "Type the user to delete id : ", false));
                        if (notifOpt.isPresent()) {
                            System.out.println("\n");
                            notifOpt.get().printBasicInfoValues();
                            if (EntryUtils.readYesNo(scanner, "\nDelete this notification (y/n)? ")) {
                                if (notifDao.deleteById(notifOpt.get().getId())) {
                                    System.out.println("\n>>> Notification deleted.");
                                }
                            }
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 5:
                    try {
                        Optional<NotificationGeneric> notifOpt = notifDao.getNotificationById(EntryUtils.readStringLikeLong(scanner, "Type the notification id : ", false));
                        if (notifOpt.isPresent()) {
                            System.out.println("\n");
                            notifOpt.get().printBasicInfoValues();
                            if (EntryUtils.readYesNo(scanner, "\nSend this notification to subscribers (y/n)? ")) {
                                if (PublisherAgent.sendNotificationToSubscribers(notifOpt.get())) {
                                    System.out.println(">>> Notification sended.");
                                }
                            }
                        } else {
                            System.out.println("\n>>> Notification not found.");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 6:
                    break;
                default:
                    System.out.println(">>> Wrong option.");
            }
        } while (option != 6);
    }

    public NotificationGeneric newNotificationForm(Scanner scanner) {
        System.out.println("\n");
        NotificationGeneric notif = new NotificationGeneric();
        notif.setTitle(EntryUtils.llegirString(scanner, "*Title: ", false, 50));
        notif.setShortDescription(EntryUtils.llegirString(scanner, "*Short description: ", false, 100));
        notif.setMessage(EntryUtils.llegirString(scanner, "*Message: ", false, 500));

        Integer noteShippingTypeValue = null;
        do {
            System.out.println("Choose Shipping type - " + EnumConstants.NOTIFICATION_SHIPPING_TYPE.getMenuOptions());
            noteShippingTypeValue = EntryUtils.readStringLikeInt(scanner, "*Shipping type: ", false, EnumConstants.NOTIFICATION_SHIPPING_TYPE.getNumberMaxLevelValue());
            notif.setShippingType(EnumConstants.NOTIFICATION_SHIPPING_TYPE.getDescriptionFromLevelCode(noteShippingTypeValue));
        } while (!EnumConstants.NOTIFICATION_SHIPPING_TYPE.getLevelCodes().contains(noteShippingTypeValue));

        Integer noteTypeValue = null;
        do {
            System.out.println("Choose option : " + EnumConstants.NOTIFICATIONS_TYPE.getMenuOptions());
            noteTypeValue = EntryUtils.llegirInt(scanner, "*Type: ", false, EnumConstants.NOTIFICATIONS_TYPE.getNumberMaxLevelValue());
            scanner.nextLine();

        } while (!EnumConstants.NOTIFICATIONS_TYPE.getLevelCodes().contains(noteTypeValue));

        notif.setType(EnumConstants.NOTIFICATIONS_TYPE.getDescriptionFromLevelCode(noteTypeValue));
        notif.printNewInfoValues();

        if ((EnumConstants.NOTIFICATIONS_TYPE.GENERAL.getDescription().equals(notif.getType())) && (EntryUtils.readYesNo(scanner, "\nPublish this notification now (y/n)? "))) {
            PublisherAgent.sendNotificationToSubscribers(notif);
        }

        if (!EntryUtils.readYesNo(scanner, "\nSave this notification as a template (y/n)? ")) {
            notif = null;
        }

        return notif;
    }

}