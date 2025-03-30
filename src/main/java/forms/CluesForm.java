package forms;

import daos.ClueDAO;
import entities.Clues;
import entities.User;
import utils.EntryUtils;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

import static forms.UserForm.editUserForm;
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
            System.out.println("    3. Edit clue");
            System.out.println("    4. Delete clue");
            System.out.println("    5. Back");

            System.out.print("\nChoose option > ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    scanner.nextLine();
                    if (!ClueDAO.saveOrUpdateClue(newClueForm(scanner))) {
                        System.out.println("\n   Error: Unable to establish connection to the database.");
                        System.out.println("     (Please contact your system administrator)\n");
                        if (!readYesNo(scanner, " Type 'Y' for continue or 'N' for scape.")) {
                            option = 4;
                        }
                    }


//                    try {
//                        for (Clues x : clueDao.getAllClues()) {
//                            x.printBasicInfoValues();
//                        }
//                    } catch (ClassNotFoundException | SQLException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                    //Hacer lambda para que imprima

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
                        Optional<Clues> clueOpt = clueDao.getClueById(EntryUtils.llegirLong(scanner, "Type the clue id: ", false));
                        scanner.nextLine();
                        if (clueOpt.isPresent()) {
                            clueOpt.get().printBasicInfoValues();
                            editCluesForm(clueOpt.get(), scanner);
                        } else {
                            System.out.println("No clue found with this ID");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
//
                    break;
                case 4:
                    try {
                        clueDao.deleteById(llegirInt(scanner, "Type the user id: ", false));
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 5:
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
        clue.setDescriptionAdmin(llegirString(scanner, "Admin description: ", false));
        //clue.setTheme(EntryUtils.llegirString(scanner, "Theme: ", false));
        clue.setLevel(llegirInt(scanner, "Level: ", false));
        clue.setGamePhase(llegirInt(scanner, "Game phase: ", false));
        scanner.nextLine();
        // clue.setDate(EntryUtils.llegirDate(scanner, "Date: ", false));
        clue.setPrice(llegirDouble(scanner, "Price: ", false));
        clue.setValue(llegirDouble(scanner, "Value: ", false));

        return clue;
    }
    private static void editCluesForm(Clues clue, Scanner scanner) {
        System.out.print("\n*Title: "+ clue.getTitle());
        clue.setTitle(llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*Surname: "+ user.getSurname());
        llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Nickname: "+ user.getNickName());
        llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Street: "+ user.getAddressStreet());
        llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Number: "+ user.getAddressNumber());
        llegirInt(scanner, ", new value: > ", true);
        scanner.nextLine();

        System.out.print("\n*Floor: "+ user.getAddressFloor());
        llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Door: "+ user.getAddressDoor());
        llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*City: "+ user.getCity());
        llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*ZipCode: "+ user.getZipCode());
        llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Country: "+ user.getCountry());
        llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*PhoneNumber: "+ user.getPhoneNumber());
        llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Mail: "+ user.getMail());
        llegirString(scanner, ", new value: > ", true);

    }
    }

}

