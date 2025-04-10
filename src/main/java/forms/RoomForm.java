package forms;

import daos.RoomDAO;
import entities.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EntryUtils;
import utils.EnumConstants;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class RoomForm {
    private static final Logger log = LogManager.getLogger(RoomForm.class);
    RoomDAO roomDao = new RoomDAO();
    int option;

    public void menuRoom(Scanner scanner) {
        do {
            System.out.println("\n");
            System.out.println("-----------------------------------------");
            System.out.println("Room menu:");
            System.out.println("-----------------------------------------");
            System.out.println("    1. New room");
            System.out.println("    2. List rooms");
            System.out.println("    3. Delete room");
            System.out.println("    4. Back");

            System.out.print("\n>>> Choose option > ");
            option = EntryUtils.llegirInt(scanner, null);
            scanner.nextLine();

            switch (option) {
                case 1:
                    createCustomRoom(scanner);
                    break;
                case 2:
                    listRooms(scanner);
                    break;

                    /*do {
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
                                break;
                            case 2:
                                try {
                                    RoomByThemeForm roomByThemeForm = new RoomByThemeForm();
                                    roomByThemeForm.menuRoomByTheme(scanner);
                                } catch (SQLException | ClassNotFoundException e) {
                                    System.out.println("error(e)");
                                }
                                break;
                            case 3:
                                try {
                                    roomDao.printRoomsByLevel();
                                } catch (SQLException | ClassNotFoundException e) {
                                    System.out.println("error(e)");
                                }
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println(">>> Wrong option.");
                        }
                    } while (option != 4);
                     */


                case 3:
                    //deleteRoom();
                    /*
                    try {
                        Long roomId = Long.valueOf(EntryUtils.llegirInt(scanner, "Type the room id: "));
                        if (roomDao.deleteRoomById(roomId)) {
                            System.out.println("Room deleted successfully.");
                        } else {
                            System.out.println("Error deleting room.");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                     */
                case 4:
                    break;
                default:
                    System.out.println("Wrong option.");
            }
        }   while (option != 4);
    }

    private void createCustomRoom(Scanner scanner) {
        Room room = new Room();
        System.out.print("Enter room name: ");
        room.setRoomName(scanner.nextLine());

        EnumConstants.ROOM_THEME theme = menuRoomByTheme(scanner);
        room.setRoomTheme(theme);
        EnumConstants.GAME_LEVEL roomLevel = menuRoomByLevel(scanner);
        room.setRoomLevel(roomLevel);

        System.out.print("Enter room status (Available/Not available): ");
        room.setRoomStatus(EnumConstants.ROOM_STATUS.valueOf(scanner.nextLine().toUpperCase()));
        System.out.print("Enter maximum number of players: ");
        room.setRoomMaxPlayers(scanner.nextInt());
        scanner.nextLine();
        room.setRoomDate(LocalDateTime.now());

        RoomDAO roomDao = new RoomDAO();
        if (roomDao.createRoom(room)) {
            System.out.println("Room created successfully!");
        } else {
            System.out.println("Error creating room.");
        }
    }

    private EnumConstants.ROOM_THEME menuRoomByTheme(Scanner scanner) {
        System.out.println("\n");
        System.out.println("-----------------------------------------");
        System.out.println("Choose a theme for the room:");
        System.out.println("-----------------------------------------");
        System.out.println("    1. Terror");
        System.out.println("    2. Fiction");
        System.out.println("    3. Fantasy");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                return EnumConstants.ROOM_THEME.TERROR;
            case 2:
                return EnumConstants.ROOM_THEME.FICTION;
            case 3:
                return EnumConstants.ROOM_THEME.FANTASY;
            default:
                System.out.println("Wrong option.");
        }
        return null;
    }

    private EnumConstants.GAME_LEVEL menuRoomByLevel(Scanner scanner) {
        System.out.println("\n");
        System.out.println("-----------------------------------------");
        System.out.println("Choose a difficulty for the room:");
        System.out.println("-----------------------------------------");
        System.out.println("    1. Easy (5 clues)");
        System.out.println("    2. Intermediate (3 clues)");
        System.out.println("    3. Hard (1 clue)");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                return EnumConstants.GAME_LEVEL.EASY;
            case 2:
                return EnumConstants.GAME_LEVEL.INTERMEDIATE;
            case 3:
                return EnumConstants.GAME_LEVEL.HARD;
            default:
                System.out.println("Wrong option.");
        }
        return null;
    }

    private void listRooms(Scanner scanner) {
        System.out.println("-----------------------------------------");
        System.out.println("Choose listing method:");
        System.out.println("-----------------------------------------");
        System.out.println("    1. List all rooms");
        System.out.println("    2. List by theme");
        System.out.println("    3. List by difficulty level");
        System.out.println("    4. Back");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                try {
                    roomDao.printAllRooms();
                } catch (SQLException | ClassNotFoundException e) {
                    System.out.println("Error printing all rooms: " + e.getMessage());
                }
                break;
            case 2:
                listRoomsByTheme(scanner);
                break;
            case 3:
                try {
                    roomDao.printRoomsByLevel();
                } catch (SQLException | ClassNotFoundException e) {
                    System.out.println("error(e)");
                }
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid option. Please choose again.");
                listRooms(scanner);
        }
    }

    private EnumConstants.ROOM_THEME listRoomsByTheme(Scanner scanner) {
        while (true) {
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
            scanner.nextLine();

            try {
                if (option == 4) {
                    return null;
            }
                EnumConstants.ROOM_THEME theme = null;
                switch (option) {
                    case 1:
                        theme = EnumConstants.ROOM_THEME.TERROR;
                        break;
                    case 2:
                        theme = EnumConstants.ROOM_THEME.FICTION;
                        break;
                    case 3:
                        theme = EnumConstants.ROOM_THEME.FANTASY;
                        break;
                    case 4:
                        return null;
                    default:
                        System.out.println("Invalid option. Please choose again.");
                        listRoomsByTheme(scanner);
                }

                if (option != 4) {
                    try {
                        RoomDAO roomDAO = new RoomDAO();
                        List<Room> rooms = roomDAO.getRoomByTheme(theme);

                        System.out.println("\n");
                        System.out.println("-----------------------------------------");
                        System.out.println("Rooms with theme: " + theme);
                        System.out.println("-----------------------------------------");
                        for (Room room : rooms) {
                            System.out.println(room.toString());
                        }
                    } catch (ClassNotFoundException e) {
                        System.out.println("Error: Class not found - " + e.getMessage());
                    } catch (SQLException e) {
                        System.out.println("Error: SQL exception - " + e.getMessage());
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input - " + e.getMessage());
            }
            while (true);
        }
    }
}
























    /* public class RoomByThemeForm {
        private RoomDAO roomDao = new RoomDAO();

        public void menuRoomByTheme(Scanner scanner) throws SQLException, ClassNotFoundException {
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
                themeListOption = EntryUtils.llegirInt(scanner, null);
                EnumConstants.ROOM_THEME theme = getThemeFromOption(themeListOption);

                if (theme != null) {
                    try {
                        roomDao.getRoomByTheme(theme).forEach(Room::printBasicInfoValues);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Invalid theme option.");
                }
            } while (themeListOption != 4);
        }
        private static EnumConstants.ROOM_THEME getThemeFromOption(int themeOption) {
            switch (themeOption) {
                case 1:
                    return EnumConstants.ROOM_THEME.TERROR;
                case 2:
                    return EnumConstants.ROOM_THEME.FICTION;
                case 3:
                    return EnumConstants.ROOM_THEME.FANTASY;
                case 4:
                    break;
                default:
                    return null;
            }
            return null;
        }
    }



    /*private Room newRoomForm(Scanner scanner) {
        Room room = new Room();
        room = chooseTheme(scanner, room);
        room = chooseLevel(scanner, room);
        return room;
    }


    public Room chooseTheme(Scanner scanner, Room room) {

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

    private Room chooseLevel(Scanner scanner, Room room) {
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

    public Room CreateCustomRoomForm(Scanner scanner) {
        Room room = new Room();

        System.out.println("\n");
        System.out.println("-----------------------------------------");
        System.out.println("Create a custom room:");
        System.out.println("-----------------------------------------");

        System.out.print("Enter room name: ");
        room.setRoomName(scanner.nextLine());

        System.out.println("\n");
        System.out.println("-----------------------------------------");
        System.out.println("Choose a theme for the room:");
        System.out.println("-----------------------------------------");
        System.out.println("    1. Terror");
        System.out.println("    2. Fiction");
        System.out.println("    3. Fantasy");

        int themeOption = EntryUtils.llegirInt(scanner, null);
        switch (themeOption) {
            case 1:
                room.setRoomTheme(EnumConstants.ROOM_THEME.TERROR);
                break;
            case 2:
                room.setRoomTheme(EnumConstants.ROOM_THEME.FICTION);
                break;
            case 3:
                room.setRoomTheme(EnumConstants.ROOM_THEME.FANTASY);
                break;
            default:
                System.out.println("Invalid theme option.");
                return null;
        }

        System.out.println("\n");
        System.out.println("-----------------------------------------");
        System.out.println("Choose a difficulty for the room:");
        System.out.println("-----------------------------------------");
        System.out.println("    1. Easy");
        System.out.println("    2. Intermediate");
        System.out.println("    3. Hard");

        int difficultyOption = EntryUtils.llegirInt(scanner, null);
        switch (difficultyOption) {
            case 1:
                room.setRoomLevel(EnumConstants.GAME_LEVEL.EASY);
                break;
            case 2:
                room.setRoomLevel(EnumConstants.GAME_LEVEL.INTERMEDIATE);
                break;
            case 3:
                room.setRoomLevel(EnumConstants.GAME_LEVEL.HARD);
                break;
            default:
                System.out.println("Invalid difficulty option.");
                return null;
        }

        return room;


    }
}

 */
