import forms.DecorationItemsForm;
import forms.RoomForm;
import forms.UserForm;
import utils.EntryUtils;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public static void principalMenu(Scanner scanner ) {
        int option;
        do {
            System.out.println("-----------------------------------------");
            System.out.println("Principal menu :");
            System.out.println("-----------------------------------------");
            System.out.println("    1. Object menu");
            System.out.println("    2. User menu");
            System.out.println("    3. Clue menu");
            System.out.println("    4. Room menu");
            System.out.println("    5. Exit");

            System.out.print("\nChoose option > ");
            option = EntryUtils.llegirInt(scanner,null);

            switch (option) {
                case 1:
                    DecorationItemsForm.menuDecorationItem(scanner);
                    break;
                case 2:
                    UserForm.menuUser(scanner);
                    break;
                case 3:
                    break;
                case 4:
                    RoomForm.menuRoom(scanner);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Wrong option.");
            }
        } while (option != 4);
    }




    public static void menuClue(Scanner scanner) {
        int option;
        System.out.println("\n");
        do {
            System.out.println("-----------------------------------------");
            System.out.println("Object menu:");
            System.out.println("-----------------------------------------");
            System.out.println("    1. New clue");
            System.out.println("    2. Edit clue");
            System.out.println("    3. Delete clue");
            System.out.println("    4. Back");

            System.out.print("\nChoose option > ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    // ObjectDAO.metode1(scanner);
                    break;
                case 2:
                    // ObjectDAO.metode2(scanner);
                    break;
                case 3:
                    // ObjectDAO.metode3(scanner);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Wrong option.");
            }
        } while (option != 4);
    }


    public static void menuRoom(Scanner scanner) {
        int option;
        System.out.println("\n");
        do {
            System.out.println("-----------------------------------------");
            System.out.println("Room menu:");
            System.out.println("-----------------------------------------");
            System.out.println("    1. New room");
            System.out.println("    2. Edit room");
            System.out.println("    3. Delete room");
            System.out.println("    4. Back");

            System.out.print("\nChoose option > ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    // ObjectDAO.metode1(scanner);
                    break;
                case 2:
                    // ObjectDAO.metode2(scanner);
                    break;
                case 3:
                    // ObjectDAO.metode3(scanner);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Wrong option.");
            }
        } while (option != 4);
    }

}
