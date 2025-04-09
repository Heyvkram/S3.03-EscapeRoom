package forms;

import daos.ClueDAO;
import entities.Clues;
import utils.EntryUtils;
import utils.EnumConstants;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import static forms.UserForm.log;
import static utils.EntryUtils.*;

public class CluesForm {

    public static void menuClues(Scanner scanner) throws SQLException, ClassNotFoundException {
        int option;
        ClueDAO clueDao = new ClueDAO();
        System.out.println("\n");
        do {
            System.out.println("-----------------------------------------");
            System.out.println("Clue menu:");
            System.out.println("-----------------------------------------");
            System.out.println("    1. New clue");
            System.out.println("    2. List clues");
            System.out.println("    3. Find clue by theme");
            System.out.println("    4. Delete clue");
            System.out.println("    5. Calculate price");
            System.out.println("    6. Back");

            System.out.print("\nChoose option > ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    scanner.nextLine();
                    if (!clueDao.saveOrUpdateClue(newClueForm(scanner))) {
                        System.out.println("\n   Error: Unable to establish connection to the database.");
                        System.out.println("     (Please contact your system administrator)\n");
                        if (!readYesNo(scanner, " Type 'Y' for continue or 'N' for scape.")) {
                            option = 5;
                        }
                    }

                    break;
                case 2:
                    try {
                        clueDao.printAllClues();
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 3:
                    try {
                        System.out.println("-----------------------------------------");
                        System.out.println("Clue Themes:");
                        System.out.println("-----------------------------------------");
                        System.out.println("    1. " + EnumConstants.ROOM_THEME.TERROR.getDescription());
                        System.out.println("    2. " + EnumConstants.ROOM_THEME.FICTION.getDescription());
                        System.out.println("    3. " + EnumConstants.ROOM_THEME.FANTASY.getDescription());
                        System.out.println("Type clue theme:");
                        option = scanner.nextInt();
                        String themeName = "";
                        switch (option) {
                            case 1:
                                themeName = EnumConstants.ROOM_THEME.TERROR.getDescription();
                                break;
                            case 2:
                                themeName = EnumConstants.ROOM_THEME.FICTION.getDescription();
                                break;
                            case 3:
                                themeName = EnumConstants.ROOM_THEME.FANTASY.getDescription();
                                break;
                            default:
                                System.out.println("Wrong option.");
                        }
                        List<Clues> clues = clueDao.getClueByTheme(themeName);
                        if (!clues.isEmpty()) {
                            for (Clues clue : clues) {
                                clue.printBasicInfoValues();
                            }
                        } else {
                            System.out.println("No clue found with this theme");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;

                case 4:
                    deleteClueById(scanner, clueDao);
//                    try {
//                        clueDao.deleteById(Long.valueOf(llegirInt(scanner, "Type clue id: ", false)));
//                    } catch (SQLException | ClassNotFoundException e) {
//                        log.error(e);
//                    }
//                    break;

//                    try {
//                        Long aLong = readStringLikeLong(scanner, "Type clue id to delete it: ", false);
//                        Optional<Clues> clue = clueDao.getClueById(aLong);
//
//                        System.out.println("\n");
//                        clue.get().printBasicInfoValues();
//
//                        if (readYesNo(scanner, "\nDelete this clue (y/n)? ")) {
//                            if (clueDao.deleteById(clue.get().getId())) {
//                                System.out.println("\n>>> Clue deleted.");
//                            }
//                        } else {
//                            System.out.println("No clue found with this ID.");
//                        }
//                    } catch (SQLException | ClassNotFoundException e) {
//                        log.error(e);
//                    }

                    break;
                case 5:

                    System.out.println("-----------------------------------------");
                    System.out.println("Choose one of this options:");
                    System.out.println("-----------------------------------------");
                    System.out.println("    1. " + "Clues total price: ");
                    System.out.println("    2. " + "Clues prices order by theme: ");

                    option = scanner.nextInt();
                    int option2;
                    switch (option) {
                        case 1:
                            try {
                                double total = clueDao.getTotalCluesPrice();
                                System.out.println("\n>>> Total price of all clues: €" + total);
                            } catch (Exception e) {
                                log.error("Error fetching total clue price", e);
                            }

                            break;
                        case 2:
                            try {
                                Map<String, Double> prices = clueDao.getTotalCluePriceByTheme();
                                System.out.println("\n>>> Clue price by theme:");
                                for (Map.Entry<String, Double> entry : prices.entrySet()) {
                                    System.out.println("- " + entry.getKey() + ": €" + entry.getValue());
                                }
                            } catch (Exception e) {
                                log.error("Error fetching clue price by theme", e);
                            }
                            break;
                        default:
                            System.out.println("Wrong option.");
                    }
            }
        } while (option != 4);
    }


    private static Clues newClueForm(Scanner scanner) {
        System.out.println("\n");
        Clues clue = new Clues();
        clue.setTitle(llegirString(scanner, "*Title: ", false));
        clue.setDescriptionUser(llegirString(scanner, "*User description: ", false));
        // clue.setDescriptionAdmin(llegirString(scanner, "Admin description: ", false));

        System.out.println("*Choose clue theme: " + EnumConstants.ROOM_THEME.getMenuOptions());
        int xx = llegirInt(scanner, "*Type: ", false, EnumConstants.ROOM_THEME.getNumberMaxLevelValue());
        clue.setTheme(EnumConstants.ROOM_THEME.getDescriptionFromLevelCode(xx));
        scanner.nextLine();

        System.out.println("*Choose game level: " + EnumConstants.GAME_LEVEL.getMenuOptions());
        int xy = llegirInt(scanner, "*Type: ", false, EnumConstants.GAME_LEVEL.getNumberMaxLevelValue());
        clue.setLevel(EnumConstants.GAME_LEVEL.getDescriptionFromLevelCode(xy));
        scanner.nextLine();
        //clue.setLevel(llegirInt(scanner, "Level: ", false));

//        System.out.println("*Choose game phase: " + EnumConstants.GAME_LEVEL.getMenuOptions());
//        int xr = llegirInt(scanner, "*Type: ", false, EnumConstants.GAME_LEVEL.getNumberMaxLevelValue());
//        clue.setLevel(EnumConstants.GAME_LEVEL.getDescriptionFromLevelCode(xr));
//        scanner.nextLine();

        //  clue.setGamePhase(llegirString(scanner, "Game phase: ", false));
//        scanner.nextLine();
        // clue.setDate(EntryUtils.llegirDate(scanner, "Date: ", false));
        clue.setPrice(llegirDouble(scanner, "Price: "));
//        clue.setValue(llegirDouble(scanner, "Value: ", false));

        return clue;
    }

    private static void editCluesForm(Clues clue, Scanner scanner) {
        System.out.print("\n*Title: " + clue.getTitle());
        clue.setTitle(llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*User description: " + clue.getDescriptionUser());
        llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Admin description: " + clue.getDescriptionAdmin());
        llegirString(scanner, ", new value: > ", true);

//        System.out.print("\n*Theme: "+ clue.getTheme());
//        llegirEn(scanner, ", new value: > ", true);

        System.out.print("\n*Level: " + clue.getLevel());
        llegirInt(scanner, ", new value: > ", true);
        scanner.nextLine();

        System.out.print("\n*Game phase: " + clue.getGamePhase());
        llegirInt(scanner, ", new value: > ", true);


//        System.out.print("\n*Date: "+ clue.getDate());
//        llegirDate(scanner, ", new value: > ", true);

        System.out.print("\n*Price: " + clue.getPrice());
        llegirDouble(scanner, ", new value: > ");

        System.out.print("\n*Value: " + clue.getValue());
        llegirDouble(scanner, ", new value: > ");

    }
    private static void deleteClueById(Scanner scanner, ClueDAO clueDao) {
        try {
            System.out.println("Type a clue ID you want to delete:");
            Long clueId = scanner.nextLong();
            Clues clue = clueDao.getClueById(clueId);

            if (clue != null) {
                clue.printBasicInfoValues();
                boolean confirm = readYesNo(scanner, "Are you sure you want to delete this clue? (y/n): ");
                if (confirm) {
                    boolean deleted = clueDao.deleteById(clueId);
                    if (deleted) {
                        System.out.println(">>> Clue successfully deleted.");
                    } else {
                        System.out.println(">>> No clue found with this ID.");
                    }
                }
            } else {
                System.out.println(">>> No clue found with this ID.");
            }
        } catch (Exception e) {
            log.error("Error deleting clue", e);
        }
    }

}

