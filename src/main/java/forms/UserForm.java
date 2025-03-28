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

    private static final Logger log = LogManager.getLogger(UserForm.class);

    public static void menuUser(Scanner scanner) {
        int option;
        UserDAO userDao = new UserDAO();
        do {
            System.out.println("\n");
            System.out.println("    -----------------------------------------");
            System.out.println("    User menu:");
            System.out.println("    -----------------------------------------");
            System.out.println("    1. New user");
            System.out.println("    2. List users");
            System.out.println("    3. Edit user");
            System.out.println("    4. Delete user");
            System.out.println("    5. Back");

            System.out.print("\n>>> Choose option > ");
            // option = scanner.nextInt();
            option = EntryUtils.llegirInt(scanner,null);
            scanner.nextLine();
            switch (option) {
                case 1:
                        if(!userDao.insertNewUser(newUserForm(scanner))){
                           System.out.println("\n   Error: Unable to establish connection to the database.");
                           System.out.println("     (Please contact your system administrator)\n");
                           if(!EntryUtils.readYesNo(scanner," Type 'y' for continue or 'n' for escape.")){
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
                        Optional<User>  userOpt =  userDao.getUserById(EntryUtils.readStringLikeLong(scanner, "Type the user id: ", false));
                        if(userOpt.isPresent()){
                            userOpt.get().printBasicInfoValues();
                            User usrUpdated = editUserForm(userOpt.get(), scanner);
                            if(usrUpdated!=null) {
                                userDao.updateUser(usrUpdated);
                            }

                            userOpt =  userDao.getUserById(usrUpdated.getId());
                            userOpt.get().printBasicInfoValues();

                        }else{
                            System.out.println("\n>>> No user with this id was found.");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }

                    break;
                case 4:
                    try {
                        Optional<User>  userOpt =  userDao.getUserById(EntryUtils.readStringLikeLong(scanner, "Type the user to delete id : ", false));
                        if(userOpt.isPresent()){
                            System.out.println("\n");
                            userOpt.get().printBasicInfoValues();
                            if(EntryUtils.readYesNo(scanner, "\nDelete this user (y/n)? ")){
                                if(userDao.deleteById(userOpt.get().getId())){
                                    System.out.println("\n>>> User deleted.");
                                }
                            }
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println(">>> Wrong option.");
            }
        } while (option != 5);
        scanner.nextLine();
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

    public static User editUserForm(User user, Scanner scanner) {

        System.out.print("\n*Name: "+ user.getName());
        user.setName(EntryUtils.llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*Surname: "+ user.getSurname());
        user.setSurname(EntryUtils.llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*Nickname: "+ user.getNickName());
        user.setNickName(EntryUtils.llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*Street: "+ user.getAddressStreet());
        EntryUtils.llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Number: "+ user.getAddressNumber());
        EntryUtils.readStringLikeInt(scanner, ", new value: > ", true);

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


        if(!EntryUtils.readYesNo(scanner, "\nSave this changes (y/n)? ")){
            user=null;
        }

        return user;
    }

}
