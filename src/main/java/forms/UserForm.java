package forms;

import daos.UserDAO;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EntryUtils;

import java.sql.SQLException;
import java.util.Scanner;

public class UserForm {

    private static final Logger log = LogManager.getLogger(UserForm.class);

    public static void menuUser(Scanner scanner) {
        int option;
        System.out.println("\n");
        UserDAO userDao = new UserDAO();
        do {
            System.out.println("-----------------------------------------");
            System.out.println("User menu:");
            System.out.println("-----------------------------------------");
            System.out.println("    1. New user");
            System.out.println("    2. Edit user");
            System.out.println("    3. Delete user");
            System.out.println("    4. Back");

            System.out.print("\nChoose option > ");
            // option = scanner.nextInt();
            option = EntryUtils.llegirInt(scanner,null);
            switch (option) {
                case 1:
                       if(!userDao.saveOrUpdateUser(newUserForm(scanner))){
                           System.out.println("\n   Error: Unable to establish connection to the database.");
                           System.out.println("     (Please contact your system administrator)\n");
                           if(!EntryUtils.readYesNo(scanner," Type 'Y' for continue or 'N' for scape.")){
                               option=4;
                           }
                       }
                    break;
                case 2:
                    // ObjectDAO.edit(scanner);
                    break;
                case 3:
                    try {
                        userDao.deleteUserById(EntryUtils.llegirInt(scanner, "Type the user id: ", false));
                    } catch (SQLException e) {
                        log.error(e);
                    } catch (ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Wrong option.");
            }
        } while (option != 4);
    }

    public static User newUserForm(Scanner scanner) {
        User user = new User();
        user.setName(EntryUtils.llegirString(scanner, "User name: ", false));
        user.setLastName1(EntryUtils.llegirString(scanner, "First last name: ", false));
        user.setLastName2(EntryUtils.llegirString(scanner, "Second last name: ", false));
        user.setAdress_street(EntryUtils.llegirString(scanner, "Street: ", false));
        user.setAdress_number(EntryUtils.llegirInt(scanner, "number: ", false));
        user.setAdress_floor(EntryUtils.llegirString(scanner, "Floor: ", false));
        user.setAdress_door(EntryUtils.llegirString(scanner, "Door: ", false));
        user.setCity(EntryUtils.llegirString(scanner, "City: ", false));
        user.setZip_code(EntryUtils.llegirString(scanner, "Zip code: ", false));
        user.setCountry(EntryUtils.llegirString(scanner, "Country: ", false));
        user.setPhone(EntryUtils.llegirString(scanner, "Phone number: ", false));
        user.setMail(EntryUtils.llegirString(scanner, "email: ", false));
        return user;
    }

}
