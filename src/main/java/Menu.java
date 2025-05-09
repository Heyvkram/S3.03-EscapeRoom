import daos.ClueDAO;
import forms.*;
import utils.EntryUtils;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public static void principalMenu(Scanner scanner ) {
        int option;
        do {
            System.out.println("\n");
            System.out.println("-----------------------------------------");
            System.out.println("Principal menu :");
            System.out.println("-----------------------------------------");
            System.out.println("    1. Decoration Item menu");
            System.out.println("    2. User menu");
            System.out.println("    3. Clue menu");
            System.out.println("    4. Room menu");
            System.out.println("    5. Notification menu");
            System.out.println("    6. Session menu");
            System.out.println("    7. Exit");

            System.out.print("\n>>> Choose option : ");
            option = EntryUtils.readStringLikeInt(scanner,null, false);

            switch (option) {
                case 1:
                    try {
                        DecorationItemsForm.menuDecorationItem(scanner);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    UserForm userForm = new UserForm();
                    userForm.menuUser(scanner);
                    break;
                case 3:
                    try {
                        CluesForm.menuClues(scanner);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    RoomForm roomForm = new RoomForm();
                    roomForm.menuRoom(scanner);
                    break;
                case 5:
                    NotificationForm notificationForm = new NotificationForm();
                    notificationForm.menuNotification(scanner);
                    break;
                case 6:
                    SessionForm sessionForm = new SessionForm();
                    sessionForm.menuSession(scanner);
                    break;

                case 7:
                    break;

                default:
                    System.out.println(">>> Wrong option.");
            }
        } while (option != 7);
    }

}