package forms;

import daos.DecorationItemDAO;
import entities.DecorationItem;
import utils.EntryUtils;
import utils.EnumConstants;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class DecorationItemsForm {

    public static void menuDecorationItem(Scanner scanner) throws SQLException, ClassNotFoundException{
        int option;
        DecorationItemDAO decorationItem = new DecorationItemDAO();
        System.out.println("\n");
        do {
            System.out.println("-----------------------------------------");
            System.out.println("Decoration items menu:");
            System.out.println("-----------------------------------------");
            System.out.println("    1. New decoration item");
            System.out.println("    2. List decoration items");
            System.out.println("    3. List decoration items by theme");
            System.out.println("    4. List of prices");
            System.out.println("    5. Delete decoration item");
            System.out.println("    6. Back");

            System.out.print("\nChoose option > ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    scanner.nextLine();
                    if(!decorationItem.saveDecorationItem(newDecorationItemForm(scanner))){
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
                    System.out.println("Choose the theme: " + EnumConstants.ROOM_THEME.getMenuOptions());
                    int xx=EntryUtils.llegirInt(scanner, "*Type: ", false, EnumConstants.ROOM_THEME.getNumberMaxLevelValue());
                    String theme = EnumConstants.ROOM_THEME.getDescriptionFromLevelCode(xx);
                    scanner.nextLine();
                    try {
                        decorationItem.printDecorationItemsByTheme(theme);
                    } catch (SQLException | ClassNotFoundException e) {
                        System.out.println("error(e)");
                    }
                    break;
                case 4:
                    try {
                        decorationItem.printAllDecorationItemsPrices();
                    } catch (SQLException | ClassNotFoundException e) {
                        System.out.println("error(e)");
                    }
                    break;
                case 5:
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
                case 6:
                    break;
                default:
                    System.out.println("Wrong option.");
            }
        } while (option != 6);
    }

    public static DecorationItem newDecorationItemForm(Scanner scanner) {
        System.out.println("\n");
        DecorationItem decorationItem = new DecorationItem();
        decorationItem.setName(EntryUtils.llegirString(scanner, "*Name: ", false));
        decorationItem.setDescription(EntryUtils.llegirString(scanner, "*Description: ", false));
        System.out.println("*Choose clue theme: " + EnumConstants.ROOM_THEME.getMenuOptions());
        int xx=EntryUtils.llegirInt(scanner, "*Type: ", false, EnumConstants.ROOM_THEME.getNumberMaxLevelValue());
        decorationItem.setTheme(EnumConstants.ROOM_THEME.getDescriptionFromLevelCode(xx));
        scanner.nextLine();
        decorationItem.setPrice(EntryUtils.llegirDouble(scanner, "*Price: "));
        decorationItem.setClueValor(EntryUtils.llegirInt(scanner, "ClueValor: "));
        scanner.nextLine();
        decorationItem.setImg(EntryUtils.llegirString(scanner, "Img: ", true));
        return decorationItem;
    }
}
