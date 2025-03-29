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
//                    try {
//                        Optional<User> userOpt = clueDao.getUserById(EntryUtils.llegirInt(scanner, "Type the user id: ", false));
//                        scanner.nextLine();
//                        if (userOpt.isPresent()) {
//                            userOpt.get().printBasicInfoValues();
//                            editUserForm(userOpt.get(), scanner);
//                        } else {
//                            System.out.println("No user found with this ID");
//                        }
//                    } catch (SQLException | ClassNotFoundException e) {
//                        log.error(e);
//                    }
                    break;
                case 4:
                    try {
                        clueDao.deleteById(EntryUtils.llegirInt(scanner, "Type the user id: ", false));
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

}

