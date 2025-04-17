package forms;

import daos.RoomDAO;
import daos.UserDAO;
import daos.GameSessionDAO;
import entities.GameSession;
import entities.Room;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EntryUtils;
import utils.EnumConstants;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SessionForm {

    static final Logger log = LogManager.getLogger(SessionForm.class);

    public void menuSession(Scanner scanner) {
        int option;
        UserDAO userDao = new UserDAO();
        RoomDAO roomDao = new RoomDAO();
        GameSessionDAO gsDao = new GameSessionDAO();
        do {
            System.out.println("\n");
            System.out.println("    -----------------------------------------");
            System.out.println("    Session menu:");
            System.out.println("    -----------------------------------------");
            System.out.println("    1. New user session");
            System.out.println("    2. List all sessions");
            System.out.println("    3. List open sessions");
            System.out.println("    4. List close sessions");
            System.out.println("    5. Delete user session");
            System.out.println("    6. Open / close user session");
            System.out.println("    7. Back");

            option = EntryUtils.readStringLikeInt(scanner, "\n>>> Choose option > ", false);
            switch (option) {
                case 1:
                    GameSession gameSession = new GameSession();
                    Optional<User> userOpt = userDao.getUserById(EntryUtils.readStringLikeLong(scanner, "Type the user id: ", false));
                    if(userOpt.isPresent()){
                        System.out.println("\nUser : ");
                        userOpt.ifPresent(User::printBasicInfoValues);
                        gameSession.setUserId(userOpt.get().getId());
                        gameSession.setUserNickname(userOpt.get().getNickName());
                        System.out.println("\n");
                    }else{
                        System.out.println("\n>>> No user with this id was found.");
                        break;
                    }
                    Optional<Room> roomOpt = Optional.empty();
                    try {
                        roomOpt = roomDao.getRoomById(EntryUtils.readStringLikeLong(scanner, "Type the room id: ", false));
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    if(roomOpt.isPresent()){
                        System.out.println("\nRoom : ");
                        roomOpt.ifPresent(Room::printBasicInfoValues);
                        gameSession.setRoomId(roomOpt.get().getRoomId());
                        gameSession.setRoomName(roomOpt.get().getRoomName());
                        gameSession.setPrice(roomOpt.get().getPrice());
                    }else{
                        System.out.println("\n>>> No Room with this id was found.");
                        break;
                    }
                    System.out.println("\n");
                    Integer notePaymentType = null;
                    do {
                        System.out.println("Choose payment type - " + EnumConstants.PAYMENT_TYPE.getMenuOptions());
                        notePaymentType = EntryUtils.readStringLikeInt(scanner, "*Payment type: ", false, EnumConstants.NOTIFICATION_SHIPPING_TYPE.getNumberMaxLevelValue());
                        gameSession.setPaymentType(EnumConstants.PAYMENT_TYPE.getDescriptionFromLevelCode(notePaymentType));
                    } while (!EnumConstants.PAYMENT_TYPE.getLevelCodes().contains(notePaymentType));

                    if (EntryUtils.readYesNo(scanner, "\nOpen session (y/n)? ")) {
                        gameSession.setAccepted(EnumConstants.ITEM_STATUS.AVAILABLE.getDescription());
                    }else{
                        gameSession.setAccepted(EnumConstants.ITEM_STATUS.NOT_AVAILABLE.getDescription());
                    }

                    if (EntryUtils.readYesNo(scanner, "\nSave this session (y/n)? ")) {
                        gsDao.insertNewUserSession(gameSession);
                    }
                    break;

                case 2:
                    try {
                        gsDao.printAllSessions();
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;

                case 3:

                    Optional<User> userOptEdit = userDao.getUserById(EntryUtils.readStringLikeLong(scanner, "Type the user id: ", false));
                    if (userOptEdit.isPresent()) {
                        userOptEdit.get().printBasicInfoValues();
                        User usrUpdated = editUserForm(userOptEdit.get(), scanner);
                        if (usrUpdated != null) {
                            userDao.updateUser(usrUpdated);
                        }

                        userOptEdit = userDao.getUserById(usrUpdated.getId());
                        userOptEdit.get().printBasicInfoValues();

                    } else {
                        System.out.println("\n>>> No user with this id was found.");
                    }
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    break;

                case 8:
                    break;

                default:
                    System.out.println(">>> Wrong option.");
            }
        } while (option != 8);
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