package forms;

import daos.ClueDAO;
import entities.Clues;
import utils.EntryUtils;
import utils.EnumConstants;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static forms.UserForm.log;
import static utils.EntryUtils.*;

public class CluesForm {

    public static void menuClues(Scanner scanner) {
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
            System.out.println("    5. Back");

            System.out.print("\nChoose option > ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    scanner.nextLine();
                    if (!clueDao.saveOrUpdateClue(newClueForm(scanner))) {
                        System.out.println("\n   Error: Unable to establish connection to the database.");
                        System.out.println("     (Please contact your system administrator)\n");
                        if (!EntryUtils.readYesNo(scanner, " Type 'Y' for continue or 'N' for scape.")) {
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
//                    try {
//                        clueDao.deleteById(Long.valueOf(llegirInt(scanner, "Type clue id: ", false)));
//                    } catch (SQLException | ClassNotFoundException e) {
//                        log.error(e);
//                    }
//                    break;

                try {
                    Long aLong= EntryUtils.readStringLikeLong(scanner, "Type clue id to delete it: ", false);
                    Optional<Clues> clue = clueDao.getClueById(aLong);

                            System.out.println("\n");
                            clue.get().printBasicInfoValues();

                            if (EntryUtils.readYesNo(scanner, "\nDelete this clue (y/n)? ")) {
                                if (clueDao.deleteById(clue.get().getId())) {
                                    System.out.println("\n>>> Clue deleted.");
                                }
                    } else {
                        System.out.println("No clue found with this ID.");
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    log.error(e);
                }

//                try {
//                    List<Clues> userOpt = clueDao.getClueById(EntryUtils.readStringLikeLong(scanner, "Type the user to delete id : ", false));
//                    if (userOpt.isPresent()) {
//                        System.out.println("\n");
//                        userOpt.get().printBasicInfoValues();
//                        if (EntryUtils.readYesNo(scanner, "\nDelete this user (y/n)? ")) {
//                            if (userDao.deleteById(userOpt.get().getId())) {
//                                System.out.println("\n>>> User deleted.");
//                            }
//                        }
//                    }
//                } catch (SQLException | ClassNotFoundException e) {
//                    log.error(e);
//                }
                break;
                default:
                    System.out.println("Wrong option.");
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
        int xx=EntryUtils.llegirInt(scanner, "*Type: ", false, EnumConstants.ROOM_THEME.getNumberMaxLevelValue());
        clue.setTheme(EnumConstants.ROOM_THEME.getDescriptionFromLevelCode(xx));
        scanner.nextLine();

        System.out.println("*Choose game level: " + EnumConstants.GAME_LEVEL.getMenuOptions());
        int xy=EntryUtils.llegirInt(scanner, "*Type: ", false, EnumConstants.GAME_LEVEL.getNumberMaxLevelValue());
        clue.setLevel(EnumConstants.GAME_LEVEL.getDescriptionFromLevelCode(xy));
        scanner.nextLine();
        //clue.setLevel(llegirInt(scanner, "Level: ", false));

        System.out.println("*Choose game phase: " + EnumConstants.GAME_LEVEL.getMenuOptions());
        int xr=EntryUtils.llegirInt(scanner, "*Type: ", false, EnumConstants.GAME_LEVEL.getNumberMaxLevelValue());
        clue.setLevel(EnumConstants.GAME_LEVEL.getDescriptionFromLevelCode(xr));
        scanner.nextLine();

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

}

