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

    public User newUserForm(Scanner scanner) {
        System.out.println("\n");
        User user = new User();
        user.setName(EntryUtils.llegirString(scanner, "*Name: ", false));
        user.setSurname(EntryUtils.llegirString(scanner, "*Surname: ", false));
        /// user.setNickName(EntryUtils.llegirString(scanner, "Nickname: ", false));

        String nickName = null;
        do{
            nickName = EntryUtils.llegirString(scanner, "*Nickname: ", false);
            if(existField(nickName, "user_nick_name", true)){
                System.out.println("ERROR: This nickname exist");
                nickName = null;
            }
        }while(nickName==null);
        user.setSurname(nickName);

        String idCard = null;
        do{
            idCard = EntryUtils.llegirString(scanner, "*idCard: ", true);
            if(existField(idCard, "user_idCard", true)){
                System.out.println("ERROR: This user identification card exist");
                idCard = null;
            }
        }while(idCard==null);
        user.setIdCard(idCard);

        user.setAddressStreet(EntryUtils.llegirString(scanner, "Street: ", true));
        user.setAddressNumber(EntryUtils.llegirInt(scanner, "number: ", true));
        scanner.nextLine();
        user.setAddressFloor(EntryUtils.llegirString(scanner, "Floor: ", true));
        user.setAddressDoor(EntryUtils.llegirString(scanner, "Door: ", true));
        user.setCity(EntryUtils.llegirString(scanner, "City: ", true));
        user.setZipCode(EntryUtils.llegirString(scanner, "Zip code: ", true));
        user.setCountry(EntryUtils.llegirString(scanner, "Country: ", true));
        user.setPhoneNumber(EntryUtils.llegirString(scanner, "Phone number: ", true));

        String email = null;
        do{
            email = EntryUtils.llegirString(scanner, "*email: ", false);
            if(existField(email, "user_mail", true)){
                System.out.println("ERROR: This user email exist");
                email = null;
            }
        }while(email==null);
        user.setMail(email);

        return user;
    }

    public User editUserForm(User user, Scanner scanner) {

        UserDAO userDao = new UserDAO();

        System.out.print("\n*Name: "+ user.getName());
        user.setName(EntryUtils.llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*Surname: "+ user.getSurname());
        user.setSurname(EntryUtils.llegirString(scanner, ", new value: > ", true));

        String nickName = null;
        System.out.print("\n*Nickname: "+ user.getNickName());
        do{
            nickName = EntryUtils.llegirString(scanner, ", new value: > ", true);
            if(nickName.isEmpty() || existField(nickName, "user_nick_name", true)){
                nickName = null;
            }
        }while(nickName==null);
        user.setNickName(nickName);
        // System.out.print("\n*Nickname: "+ user.getNickName());

        System.out.print("\n*Street: "+ user.getAddressStreet());
        user.setAddressStreet(EntryUtils.llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*Number: "+ user.getAddressNumber());
        user.setAddressNumber(EntryUtils.readStringLikeInt(scanner, ", new value: > ", true));

        System.out.print("\n*Floor: "+ user.getAddressFloor());
        user.setAddressFloor(EntryUtils.llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*Door: "+ user.getAddressDoor());
        user.setAddressDoor(EntryUtils.llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*City: "+ user.getCity());
        user.setCity(EntryUtils.llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*ZipCode: "+ user.getZipCode());
        user.setZipCode(EntryUtils.llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*Country: "+ user.getCountry());
        user.setCountry(EntryUtils.llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*PhoneNumber: "+ user.getPhoneNumber());
        user.setPhoneNumber(EntryUtils.llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*Mail: "+ user.getMail());
        user.setMail(EntryUtils.llegirString(scanner, ", new value: > ", true));


        if(!EntryUtils.readYesNo(scanner, "\nSave this changes (y/n)? ")){
            user=null;
        }

        return user;
    }

    public boolean existField(String entrada, String fieldName, boolean ignoreCase){
        UserDAO userDao = new UserDAO();
        boolean result = false;
        try {
            result = userDao.existUserBy(entrada, fieldName, ignoreCase);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println((">>> No s'ha pogut comprovar el camp "+fieldName));
            // log.error(new RuntimeException(e));
        }
        return result;
    }

}
