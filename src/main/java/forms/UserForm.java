package forms;

import daos.UserDAO;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EntryUtils;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class UserForm {

    static final Logger log = LogManager.getLogger(UserForm.class);

    public static void menuUser(Scanner scanner) {
        int option;
        UserDAO userDao = new UserDAO();
        do {
            System.out.println("\n-----------------------------------------");
            System.out.println("User menu:");
            System.out.println("-----------------------------------------");
            System.out.println("    1. New user");
            System.out.println("    2. List users");
            System.out.println("    3. Edit user");
            System.out.println("    4. Delete user");
            System.out.println("    5. Back");

            System.out.print("\nChoose option > ");
            // option = scanner.nextInt();
            option = EntryUtils.llegirInt(scanner,null);
            switch (option) {
                case 1:
                        scanner.nextLine();
                        if(!userDao.saveOrUpdateUser(newUserForm(scanner))){
                           System.out.println("\n   Error: Unable to establish connection to the database.");
                           System.out.println("     (Please contact your system administrator)\n");
                           if(!EntryUtils.readYesNo(scanner," Type 'Y' for continue or 'N' for scape.")){
                               option=4;
                           }
                       }
                    break;
                case 2:
                    try {
                        userDao.printAllUsers();
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 3:
                    try {
                        Optional<User>  userOpt =  userDao.getUserById(EntryUtils.llegirInt(scanner, "Type the user id: ", false));
                        scanner.nextLine();
                        if(userOpt.isPresent()){
                            userOpt.get().printBasicInfoValues();
                            editUserForm(userOpt.get(), scanner);
                        }else{
                            System.out.println("No s'ha trobar cap usuari amb aquest id");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 4:
                    try {
                        userDao.deleteById(EntryUtils.llegirInt(scanner, "Type the user id: ", false));
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Wrong option.");
            }
        } while (option != 5);
    }

    public static User newUserForm(Scanner scanner) {
        System.out.println("\n");
        User user = new User();
        user.setName(EntryUtils.llegirString(scanner, "*Name: ", false));
        user.setSurname(EntryUtils.llegirString(scanner, "*Surname: ", false));
        user.setNickName(EntryUtils.llegirString(scanner, "Nickname: ", false));
        user.setAddressStreet(EntryUtils.llegirString(scanner, "Street: ", false));
        user.setAddressNumber(EntryUtils.llegirInt(scanner, "number: ", false));
        scanner.nextLine();
        user.setAddressFloor(EntryUtils.llegirString(scanner, "Floor: ", false));
        user.setAddressDoor(EntryUtils.llegirString(scanner, "Door: ", false));
        user.setCity(EntryUtils.llegirString(scanner, "City: ", false));
        user.setZipCode(EntryUtils.llegirString(scanner, "Zip code: ", false));
        user.setCountry(EntryUtils.llegirString(scanner, "Country: ", false));
        user.setPhoneNumber(EntryUtils.llegirString(scanner, "Phone number: ", false));
        user.setMail(EntryUtils.llegirString(scanner, "*email: ", false));
        return user;
    }

    public static void editUserForm(User user, Scanner scanner) {

        System.out.print("\n*Name: "+ user.getName());
        user.setName(EntryUtils.llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*Surname: "+ user.getSurname());
        EntryUtils.llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Nickname: "+ user.getNickName());
        EntryUtils.llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Street: "+ user.getAddressStreet());
        EntryUtils.llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Number: "+ user.getAddressNumber());
        EntryUtils.llegirInt(scanner, ", new value: > ", true);
        scanner.nextLine();

        System.out.print("\n*Floor: "+ user.getAddressFloor());
        EntryUtils.llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Door: "+ user.getAddressDoor());
        EntryUtils.llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*City: "+ user.getCity());
        EntryUtils.llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*ZipCode: "+ user.getZipCode());
        EntryUtils.llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Country: "+ user.getCountry());
        EntryUtils.llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*PhoneNumber: "+ user.getPhoneNumber());
        EntryUtils.llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Mail: "+ user.getMail());
        EntryUtils.llegirString(scanner, ", new value: > ", true);

    }

}
