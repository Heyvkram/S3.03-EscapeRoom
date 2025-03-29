package forms;

import daos.DecorationItemDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class DecorationItemsForm {

    public static void menuDecorationItem(Scanner scanner) {
        int option;
        DecorationItemDAO decorationItem = new DecorationItemDAO();
        System.out.println("\n");
        do {
            System.out.println("-----------------------------------------");
            System.out.println("Object menu:");
            System.out.println("-----------------------------------------");
            System.out.println("    1. List");
            System.out.println("    2. Edit object");
            System.out.println("    3. Delete object");
            System.out.println("    4. Back");

            System.out.print("\nChoose option > ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    try {
                        decorationItem.printAllDecorationItems();
                    } catch (SQLException | ClassNotFoundException e) {
                        System.out.println("error(e)");
                    }
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
