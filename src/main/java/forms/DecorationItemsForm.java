package forms;

import java.util.Scanner;

public class DecorationItemsForm {

    public static void menuDecorationItem(Scanner scanner) {
        int option;
        System.out.println("\n");
        do {
            System.out.println("-----------------------------------------");
            System.out.println("Object menu:");
            System.out.println("-----------------------------------------");
            System.out.println("    1. New object");
            System.out.println("    2. Edit object");
            System.out.println("    3. Delete object");
            System.out.println("    4. Back");

            System.out.print("\nChoose option > ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    // ObjectDAO.insert(scanner);
                    break;
                case 2:
                    // ObjectDAO.edit(scanner);
                    break;
                case 3:
                    // ObjectDAO.delete(scanner);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Wrong option.");
            }
        } while (option != 4);
    }

}
