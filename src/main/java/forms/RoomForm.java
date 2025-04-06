package forms;


import daos.RoomDAO;
import entities.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EntryUtils;


import java.util.Optional;
import java.util.List;
import java.sql.SQLException;
import java.util.Scanner;

public class RoomForm {

    private static final Logger log = LogManager.getLogger(RoomForm.class);

    public void menuRoom(Scanner scanner) {
        int option;
        RoomDAO roomDao = new RoomDAO();
        // RoomFactory factory = new RoomFactory();
        do {
            System.out.println("\n");
            System.out.println("    -----------------------------------------");
            System.out.println("    Room menu:");
            System.out.println("    -----------------------------------------");
            System.out.println("    1. New room");
            System.out.println("    2. List all rooms");
            System.out.println("    3. Delete room");
            System.out.println("    4. Back");

            System.out.print("\n>>> Choose option > ");
            //option = scanner.nextInt();
            option = EntryUtils.llegirInt(scanner, null);
            scanner.nextLine();

            switch (option) {
                case 1:
                    Room newRoom = newRoomForm(scanner);
                    if(newRoom!=null && !roomDao.saveOrUpdateRoom(newRoom)){
                        System.out.println("\n   Error: Unable to establish connection to the database.");
                        System.out.println("     (Please contact your system administrator)\n");
                        if(!EntryUtils.readYesNo(scanner," Type 'Y' for continue or 'N' for scape.")){
                            option=4;
                        }
                    }
                    break;
                case 2:
                    try {
                        roomDao.printAllRooms();
                    } catch (SQLException | ClassNotFoundException e) {
                        System.out.println("error(e)");
                    }
                    break;
                case 3:
                    try {
                        Optional<Room> roomOpt = roomDao.getRoomById(EntryUtils.readStringLikeLong(scanner, "Type the room to delete id : ", false));
                        if (roomOpt.isPresent()) {
                            System.out.println("\n");
                            roomOpt.get().printBasicInfoValues();
                            if (EntryUtils.readYesNo(scanner, "\nDelete this room (y/n)? ")) {
                                if (roomDao.deleteById(roomOpt.get().getRoomId())) {
                                    System.out.println("\n>>> User deleted.");
                                }
                            }
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Wrong option.");
            }
        } while (option != 3);
    }

    private Room newRoomForm(Scanner scanner) {
        Room room = new Room();
        String roomName = EntryUtils.llegirString(scanner, "Type the room name: ", true);
        room.setRoomName(roomName);
        return room;
    }
}