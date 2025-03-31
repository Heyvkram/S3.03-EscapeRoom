package forms;

import daos.DecorationItemDAO;
import entities.DecorationItem;
import utils.EntryUtils;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class DecorationItemsForm {

    public static void menuDecorationItem(Scanner scanner) {
        int option;
        DecorationItemDAO decorationItem = new DecorationItemDAO();
        System.out.println("\n");
        do {
            System.out.println("-----------------------------------------");
            System.out.println("Decoration items menu:");
            System.out.println("-----------------------------------------");
            System.out.println("    1. New decoration item");
            System.out.println("    2. List decoration items");
            System.out.println("    3. Edit decoration item");
            System.out.println("    4. Delete decoration item");
            System.out.println("    5. Back");

            System.out.print("\nChoose option > ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    scanner.nextLine();
                    if(!decorationItem.saveOrUpdateDecorationItem(newDecorationItemForm(scanner))){
                        System.out.println("\n   Error: Unable to establish connection to the database.");
                        System.out.println("     (Please contact your system administrator)\n");
                        if(!EntryUtils.readYesNo(scanner," Type 'Y' for continue or 'N' for scape.")){
                            option=5;
                        }
                    }
                    break;
                case 2:
                    try {
                        decorationItem.printAllDecorationItems();
                    } catch (SQLException | ClassNotFoundException e) {
                        System.out.println("error(e)");
                    }
                    break;
                case 3:
                    // ObjectDAO.edit(scanner);
                    break;
                case 4:
                    scanner.nextLine();
                    try {
                        Optional<DecorationItem>  decorationOpt =  decorationItem.getDecorationIemById(EntryUtils.readStringLikeLong(scanner, "Type the decoration item id : ", false));
                        if(decorationOpt.isPresent()){
                            System.out.println("\n");
                            decorationOpt.get().printBasicInfoValues();
                            if(EntryUtils.readYesNo(scanner, "\nDelete this decoration item (y/n)? ")){
                                if(decorationItem.deleteDecorationItemsById(decorationOpt.get().getId())){
                                    System.out.println("\n>>> Decoration item deleted.");
                                }
                            }
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        System.out.println(e);
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Wrong option.");
            }
        } while (option != 5);
    }

    public static DecorationItem newDecorationItemForm(Scanner scanner) {
        System.out.println("\n");
        DecorationItem decorationItem = new DecorationItem();
        decorationItem.setName(EntryUtils.llegirString(scanner, "*Name: ", false));
        decorationItem.setDescription(EntryUtils.llegirString(scanner, "Description: ", false));
        //decorationItem.setTheme(EntryUtils.llegirString(scanner, "Theme: ", false));
        decorationItem.setPrice(EntryUtils.llegirDouble(scanner, "Price: "));
        decorationItem.setClueValor(EntryUtils.llegirInt(scanner, "ClueValor: ", false));
        scanner.nextLine();
        decorationItem.setImg(EntryUtils.llegirString(scanner, "Img: ", false));
        return decorationItem;
    }
}
