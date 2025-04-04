package forms;

import daos.UserDAO;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EntryUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserForm {

    private static final Logger log = LogManager.getLogger(UserForm.class);

    public void menuUser(Scanner scanner) {
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
            System.out.println("    5. Find user by surname");
            System.out.println("    6. Back");

            System.out.print("\n>>> Choose option > ");
            option = EntryUtils.llegirInt(scanner, null);
            switch (option) {
                case 1:
                    User user = newUserForm(scanner);
                    if (user != null && !userDao.insertNewUser(user)) {
                        System.out.println("\n   Error: Unable to establish connection to the database.");
                        System.out.println("     (Please contact your system administrator)\n");
                        if (!EntryUtils.readYesNo(scanner, " Type 'y' for continue or 'n' for escape.")) {
                            option = 4;
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
                        Optional<User> userOpt = userDao.getUserById(EntryUtils.readStringLikeLong(scanner, "Type the user id: ", false));
                        if (userOpt.isPresent()) {
                            userOpt.get().printBasicInfoValues();
                            User usrUpdated = editUserForm(userOpt.get(), scanner);
                            if (usrUpdated != null) {
                                userDao.updateUser(usrUpdated);
                            }

                            userOpt = userDao.getUserById(usrUpdated.getId());
                            userOpt.get().printBasicInfoValues();

                        } else {
                            System.out.println("\n>>> No user with this id was found.");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }

                    break;
                case 4:
                    try {
                        Optional<User> userOpt = userDao.getUserById(EntryUtils.readStringLikeLong(scanner, "Type the user to delete id : ", false));
                        if (userOpt.isPresent()) {
                            System.out.println("\n");
                            userOpt.get().printBasicInfoValues();
                            if (EntryUtils.readYesNo(scanner, "\nDelete this user (y/n)? ")) {
                                if (userDao.deleteById(userOpt.get().getId())) {
                                    System.out.println("\n>>> User deleted.");
                                }
                            }
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 5:

                    try {

                        List<User> userList = userDao.getUsersLikeSurName(EntryUtils.llegirString(scanner, "Type the user surname to find : ", true));
                        if (!userList.isEmpty()) {
                            userDao.printUsersList(userList);
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }

                    break;
                case 6:
                    break;
                default:
                    System.out.println(">>> Wrong option.");
            }
        } while (option != 6);
        scanner.nextLine();
    }

    public User newUserForm(Scanner scanner) {
        System.out.println("\n");

        User user = new User();
        user.setName(EntryUtils.llegirString(scanner, "*Name: ", false, 30));
        user.setSurname(EntryUtils.llegirString(scanner, "*Surname: ", false, 100));

        String nickName = null;
        do {
            nickName = EntryUtils.llegirString(scanner, "*Nickname: ", false, 20);
            if (existField(nickName, "user_nick_name", true)) {
                System.out.println("ERROR: This nickname exist");
                nickName = null;
            }
        } while (nickName == null);
        user.setNickName(nickName);

        String email = null;
        do {
            email = EntryUtils.llegirString(scanner, "*email: ", false, 50, EntryUtils.EMAIL_REGEX);
            if (existField(email, "user_mail", true)) {
                System.out.println("ERROR: This user email exist");
                email = null;
            }
        } while (email == null);
        user.setMail(email);

        String idCard = null;
        do {
            idCard = EntryUtils.llegirString(scanner, "idCard: ", true, 100);
            if (existField(idCard, "user_idCard", true)) {
                System.out.println("ERROR: This user identification card exist");
                idCard = null;
            }
        } while (idCard == null);
        user.setIdCard(idCard);

        user.setAddressStreet(EntryUtils.llegirString(scanner, "Street: ", true, 100));
        user.setAddressNumber(EntryUtils.readStringLikeInt(scanner, "number: ", true, 20000));

        user.setAddressFloor(EntryUtils.llegirString(scanner, "Floor: ", true, 10));
        user.setAddressDoor(EntryUtils.llegirString(scanner, "Door: ", true, 10));
        user.setCity(EntryUtils.llegirString(scanner, "City: ", true, 30));
        user.setZipCode(EntryUtils.llegirString(scanner, "Zip code: ", true, 20));
        user.setCountry(EntryUtils.llegirString(scanner, "Country: ", true, 150));
        user.setPhoneNumber(EntryUtils.llegirString(scanner, "Phone number: ", true, 50));

        if (!EntryUtils.readYesNo(scanner, "\nSave this user (y/n)? ")) {
            user = null;
        }

        return user;
    }

    public User editUserForm(User user, Scanner scanner) {

        UserDAO userDao = new UserDAO();

        System.out.print("\n*Name: " + user.getName());
        user.setName(EntryUtils.llegirString(scanner, ", new value: > ", true, 30));

        System.out.print("\n*Surname: " + user.getSurname());
        user.setSurname(EntryUtils.llegirString(scanner, ", new value: > ", true, 100));

        String nickName = null;
        System.out.print("\n*Nickname: " + user.getNickName());
        do {
            nickName = EntryUtils.llegirString(scanner, ", new value: > ", true, 20);
            if (!nickName.isEmpty() && existField(nickName, "user_nick_name", true)) {
                nickName = null;
            }
        } while (nickName == null);
        user.setNickName(nickName);

        System.out.print("\n*Mail: " + user.getMail());
        user.setMail(EntryUtils.llegirString(scanner, ", new value: > ", true, 50));

        System.out.print("\nStreet: " + user.getAddressStreet());
        user.setAddressStreet(EntryUtils.llegirString(scanner, ", new value: > ", true, 100));

        System.out.print("\nNumber: " + user.getAddressNumber());
        user.setAddressNumber(EntryUtils.readStringLikeInt(scanner, ", new value: > ", true, 20000));

        System.out.print("\nFloor: " + user.getAddressFloor());
        user.setAddressFloor(EntryUtils.llegirString(scanner, ", new value: > ", true, 10));

        System.out.print("\nDoor: " + user.getAddressDoor());
        user.setAddressDoor(EntryUtils.llegirString(scanner, ", new value: > ", true, 10));

        System.out.print("\nCity: " + user.getCity());
        user.setCity(EntryUtils.llegirString(scanner, ", new value: > ", true, 30));

        System.out.print("\nZipCode: " + user.getZipCode());
        user.setZipCode(EntryUtils.llegirString(scanner, ", new value: > ", true, 20));

        System.out.print("\nCountry: " + user.getCountry());
        user.setCountry(EntryUtils.llegirString(scanner, ", new value: > ", true, 150));

        System.out.print("\nPhoneNumber: " + user.getPhoneNumber());
        user.setPhoneNumber(EntryUtils.llegirString(scanner, ", new value: > ", true, 50));

        if (!EntryUtils.readYesNo(scanner, "\nSave this changes (y/n)? ")) {
            user = null;
        }

        return user;
    }

    public boolean existField(String entrada, String fieldName, boolean ignoreCase) {
        UserDAO userDao = new UserDAO();
        boolean result = false;
        try {
            result = userDao.existUserBy(entrada, fieldName, ignoreCase);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println((">>> No s'ha pogut comprovar el camp " + fieldName));
            // log.error(new RuntimeException(e));
        }
        return result;
    }

}
