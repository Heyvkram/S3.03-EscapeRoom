package forms;

import daos.RoomDAO;
import entities.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EntryUtils;
import utils.EnumConstants;

import java.util.List;
import java.util.Optional;
import java.sql.SQLException;
import java.util.Scanner;

public class RoomForm {

    private static final Logger log = LogManager.getLogger(RoomForm.class);

    public void menuRoom(Scanner scanner) {
        int option;
        RoomDAO roomDao = new RoomDAO();
        do {
            System.out.println("\n");
            System.out.println("    -----------------------------------------");
            System.out.println("    Room menu:");
            System.out.println("    -----------------------------------------");
            System.out.println("    1. New room");
            System.out.println("    2. List rooms");
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
                    do {
                        System.out.println("\n");
                        System.out.println("-----------------------------------------");
                        System.out.println("Choose listing method:");
                        System.out.println("-----------------------------------------");
                        System.out.println("    1. List all rooms");
                        System.out.println("    2. List by theme");
                        System.out.println("    3. List by difficulty");
                        System.out.println("    4. Back");

                        System.out.print("\n>>> Choose option > ");
                        option = EntryUtils.llegirInt(scanner, null);

                        switch (option) {
                            case 1:
                                try {
                                    roomDao.printAllRooms();
                                } catch (SQLException | ClassNotFoundException e) {
                                    System.out.println("error(e)");
                                }
                            case 2:
                                int themeListOption = 0;
                                do {
                                    System.out.println("\n");
                                    System.out.println("-----------------------------------------");
                                    System.out.println("Choose a theme:");
                                    System.out.println("-----------------------------------------");
                                    System.out.println("    1. Terror");
                                    System.out.println("    2. Fiction");
                                    System.out.println("    3. Fantasy");
                                    System.out.println("    4. Back");

                                    System.out.print("\n>>> Choose option > ");
                                    option = EntryUtils.llegirInt(scanner, null);
                                    switch (themeListOption) {
                                        case 1:
                                            try {
                                                List<Room> terrorRooms = roomDao.getRoomByTheme(EnumConstants.ROOM_THEME.TERROR);
                                                terrorRooms.forEach(Room::printBasicInfoValues);
                                            } catch (SQLException | ClassNotFoundException e) {
                                                System.out.println("error(e)");
                                            }
                                            break;
                                        case 2:
                                            try {
                                                List<Room> fictionRooms = roomDao.getRoomByTheme(EnumConstants.ROOM_THEME.FICTION);
                                                fictionRooms.forEach(Room::printBasicInfoValues);
                                            } catch (SQLException | ClassNotFoundException e) {
                                                System.out.println("error(e)");
                                            }
                                            break;
                                        case 3:
                                            try {
                                                List<Room> fantasyRooms = roomDao.getRoomByTheme(EnumConstants.ROOM_THEME.FANTASY);
                                                fantasyRooms.forEach(Room::printBasicInfoValues);
                                            } catch (SQLException | ClassNotFoundException e) {
                                                System.out.println("error(e)");
                                            }
                                            break;
                                        case 4:
                                            break;
                                        default:
                                            System.out.println(">>> Wrong option.");
                                    }
                                } while (true);

                            case 3:
                                try {
                                    roomDao.printRoomsByLevel();
                                } catch (SQLException | ClassNotFoundException e) {
                                    System.out.println("error(e)");
                                }
                            case 4:
                                break;
                            default:
                                System.out.println(">>> Wrong option.");
                        }
                    } while (true);

                case 3:
                    try {
                        Optional<Room> roomOpt = roomDao.getRoomById(EntryUtils.readStringLikeLong(scanner,
                                "Type the room to delete id : ", false));
                        if (roomOpt.isPresent()) {
                            System.out.println("\n");
                            roomOpt.get().printBasicInfoValues();
                            if (EntryUtils.readYesNo(scanner, "\nDelete this room (y/n)? ")) {
                                if (roomDao.deleteById(roomOpt.get().getRoomId())) {
                                    System.out.println("\n>>> Room deleted.");
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
        room = chooseTheme(scanner, room);
        room = chooseDifficulty(scanner, room);
        return room;
    }

    private Room chooseTheme(Scanner scanner, Room room) {
        int option;
        do {
            System.out.println("\n");
            System.out.println("-----------------------------------------");
            System.out.println("Choose a theme for the room:");
            System.out.println("-----------------------------------------");
            System.out.println("    1. Terror");
            System.out.println("    2. Fiction");
            System.out.println("    3. Fantasy");
            System.out.println("    4. Back");

            System.out.print("\n>>> Choose option > ");
            option = EntryUtils.llegirInt(scanner, null);

            switch (option) {
                case 1:
                    room.setRoomTheme(EnumConstants.ROOM_THEME.TERROR);
                    return room;
                case 2:
                    room.setRoomTheme(EnumConstants.ROOM_THEME.FICTION);
                    return room;
                case 3:
                    room.setRoomTheme(EnumConstants.ROOM_THEME.FANTASY);
                    return room;
                case 4:
                    return null;
                default:
                    System.out.println(">>> Wrong option.");
            }
        } while (true);
    }

    private Room chooseDifficulty(Scanner scanner, Room room) {
        int option;
        do {
            System.out.println("\n");
            System.out.println("-----------------------------------------");
            System.out.println("Choose a difficulty for the room:");
            System.out.println("-----------------------------------------");
            System.out.println("    1. Easy");
            System.out.println("    2. Intermediate");
            System.out.println("    3. Hard");
            System.out.println("    4. Back");

            System.out.print("\n>>> Choose option > ");
            option = EntryUtils.llegirInt(scanner, null);

            switch (option) {
                case 1:
                    room.setRoomLevel(EnumConstants.GAME_LEVEL.EASY);
                    return room;
                case 2:
                    room.setRoomLevel(EnumConstants.GAME_LEVEL.INTERMEDIATE);
                    return room;
                case 3:
                    room.setRoomLevel(EnumConstants.GAME_LEVEL.HARD);
                    return room;
                case 4:
                    return null;
                default:
                    System.out.println(">>> Wrong option.");
            }
        } while (true);
    }
}
