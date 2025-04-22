package forms;

import daos.ClueDAO;
import daos.DecorationItemDAO;
import daos.GameSessionDAO;
import daos.RoomDAO;
import entities.Clues;
import entities.DecorationItem;
import entities.GameSession;
import entities.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EntryUtils;
import utils.EnumConstants;

import java.util.List;
import java.sql.SQLException;
import java.util.Optional;
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
            System.out.println("    3. Details room");
            System.out.println("    4. Delete room");
            System.out.println("    5. List of rooms economic results");
            System.out.println("    6. Back");

            option = EntryUtils.readStringLikeInt(scanner, "\n>>> Choose option > ", false);

            switch (option) {
                case 1:
                    Room newRoom = newRoomForm(scanner);
                    newRoom = roomDao.saveNewRoom(newRoom);
                    boolean allOk = true;
                    if (EntryUtils.readYesNo(scanner, "\nSave this room (y/n)? ")) {
                        DecorationItemDAO decoItemDAO = new DecorationItemDAO();
                        allOk = decoItemDAO.saveDecorationItemRoomRelations(newRoom.getRoomId(), newRoom.getDecorationItems());
                        if (allOk) {
                            ClueDAO clueDAO = new ClueDAO();
                            allOk = clueDAO.saveCluesRoomRelations(newRoom.getRoomId(), newRoom.getClues());
                        }
                        if (!allOk) {
                            System.out.println("\n   Error: Unable save information.");
                            System.out.println("     (Please contact your system administrator)\n");
                            if (!EntryUtils.readYesNo(scanner, " Type 'Y' for continue or 'N' for scape.")) {
                                option = 5;
                            }
                        }
                        System.out.println(">>> All relations saved correctly");
                    }else{
                        System.out.println(">>> Exit without saving");
                    }
                    break;
                case 2:
                    int option2;
                    do {
                        System.out.println("\n");
                        System.out.println("-----------------------------------------");
                        System.out.println("Choose listing method:");
                        System.out.println("-----------------------------------------");
                        System.out.println("    1. List all rooms");
                        System.out.println("    2. List by theme");
                        System.out.println("    3. List by difficulty");
                        System.out.println("    4. Back");

                        option2 =  EntryUtils.readStringLikeInt(scanner, "\n>>> Choose option > ", false);

                        switch (option2) {
                            case 1:
                                try {
                                    roomDao.printAllRooms();
                                } catch (SQLException | ClassNotFoundException e) {
                                    log.error(e);
                                }
                                break;
                            case 2:
                                try {
                                    RoomByThemeForm roomByThemeForm = new RoomByThemeForm();
                                    roomByThemeForm.menuRoomByTheme(scanner);
                                } catch (SQLException | ClassNotFoundException e) {
                                    log.error(e);
                                }
                                break;
                            case 3:
                                try {
                                    roomDao.printRoomsByLevel();
                                } catch (SQLException | ClassNotFoundException e) {
                                    log.error(e);
                                }
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println(">>> Wrong option.");
                        }

                    } while (option2 != 4);
                    break;

                case 3:
                    try {
                        Optional<Room> optRoom = roomDao.getRoomById(EntryUtils.readStringLikeLong(scanner, "Type the room id: ",true));
                        if(optRoom.isPresent()){
                            GameSessionDAO gsDAO = new GameSessionDAO();
                            optRoom.get().printBasicInfoValues();
                            System.out.println("    Decoration items:");
                            DecorationItemDAO decorationItemDAO = new DecorationItemDAO();
                            List<DecorationItem> decorationItemList = decorationItemDAO.getDecorationItemOfRoom(optRoom.get().getRoomId());
                            decorationItemList.forEach(DecorationItem::printPriceInfoValues);
                            System.out.println("    Clues: ");
                            ClueDAO clueDAO = new ClueDAO();
                            List<Clues> clueList = clueDAO.getCluesOfRoom(optRoom.get().getRoomId());
                            clueList.forEach(Clues::printPriceInfoValues);
                            List<GameSession> sessionList = gsDAO.getRoomAllSessions(optRoom.get().getRoomId());
                            gsDAO.printRoomGameSessionPrices(optRoom.get(), sessionList);
                        }else{
                            System.out.println(">>> Wrong room id");
                        }

                    } catch (SQLException | ClassNotFoundException e) {
                        System.out.println("\n   Error: Unable to show information.");
                        System.out.println("     (Please contact your system administrator)\n");
                        if (!EntryUtils.readYesNo(scanner, " Type 'Y' for continue or 'N' for scape.")) {
                            option = 5;
                        }
                        log.error(e);
                    }
                    break;

                case 4:
                    try {
                        Long roomId = Long.valueOf(EntryUtils.llegirInt(scanner, "Type the room id: "));
                        if (roomDao.deleteRoomById(roomId)) {
                            System.out.println("Room deleted successfully.");
                        } else {
                            System.out.println("Error deleting room.");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        System.out.println("\n   Error: This action could not be performed..");
                        System.out.println("     (Please contact your system administrator)\n");
                        log.error(e);
                    }
                    break;
                case 5:
                    GameSessionDAO gsDAO = new GameSessionDAO();
                    try {
                        List<Room> roomList = roomDao.getAllRooms();
                        for (Room room:roomList){
                            List<GameSession> sessionList = gsDAO.getRoomAllSessions(room.getRoomId());
                            gsDAO.printRoomGameSessionPrices(room, sessionList);
                        }

                    } catch (ClassNotFoundException | SQLException e) {
                        System.out.println("\n   Error: This action could not be performed..");
                        System.out.println("     (Please contact your system administrator)\n");
                        log.error(e);
                        break;
                    }

                    break;
                case 6:
                    break;
                default:
                    System.out.println("Wrong option.");
            }
        } while (option != 6);
    }

    private Room newRoomForm(Scanner scanner) {
        Room newRoom = new Room();
        newRoom.setRoomName(EntryUtils.llegirString(scanner, "*Name: ", true));

        newRoom = chooseTheme(scanner, newRoom);

        newRoom = chooseDifficulty(scanner, newRoom);

        newRoom = chooseStatus(scanner, newRoom);

        newRoom.setRoomMaxPlayers(EntryUtils.readStringLikeInt(scanner, "*Number of players: ", true));

        DecorationItemDAO decorationItemDAO = new DecorationItemDAO();
        List<entities.DecorationItem> decorationItems = null;
        try {
            decorationItems = decorationItemDAO.getDecorationsItemsBy(newRoom.getRoomTheme(),"decoration_item_theme");
            newRoom.setDecorationItems(decorationItems);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ClueDAO clueDAO = new ClueDAO();
        List<entities.Clues> cluesList = null;
        try {
            cluesList = clueDAO.getCluesByThemeAndLevel(newRoom.getRoomTheme(), newRoom.getRoomLevel());
            newRoom.setClues(cluesList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n");
        newRoom.printBasicInfoValues();
        System.out.println("\n");
        System.out.println("    Decoration items ------------------------------");
        for(entities.DecorationItem item:decorationItems) item.printBasicInfoValues();
        double decoItemsTotalPrice =decorationItemDAO.getDecorationItemsListTotalPrice(decorationItems);
        System.out.printf("Total price: %.2f%n", decoItemsTotalPrice);
        System.out.println("\n");
        System.out.println("    Clues -----------------------------------------");
        for(entities.Clues item:cluesList) item.printBasicInfoValues();
        double clueTotalPrice = clueDAO.getClueItemsPrices(cluesList);
        System.out.printf("Total price cost calculated: %.2f%n", clueTotalPrice);
        System.out.println("\n");
        newRoom.setTotalCostValue(decoItemsTotalPrice + clueTotalPrice);
        newRoom.setPrice(EntryUtils.llegirDouble(scanner, "*Session price: "));

        return newRoom;
    }

    private Room chooseTheme(Scanner scanner, Room room) {
        int option;
        do {
            System.out.println("\n");
            System.out.println("-----------------------------------------");
            System.out.println("Choose a theme for the room:");
            System.out.println("-----------------------------------------");
            System.out.println(EnumConstants.ROOM_THEME.getMenuOptions());
            System.out.println("    1. Terror");
            System.out.println("    2. Fiction");
            System.out.println("    3. Fantasy");
            System.out.println("    4. Back");

            option = EntryUtils.readStringLikeInt(scanner, "\n>>> Choose option > ", false);

            switch (option) {
                case 1:
                    room.setRoomTheme(EnumConstants.ROOM_THEME.TERROR.getDescription());
                    return room;
                case 2:
                    room.setRoomTheme(EnumConstants.ROOM_THEME.FICTION.getDescription());
                    return room;
                case 3:
                    room.setRoomTheme(EnumConstants.ROOM_THEME.FANTASY.getDescription());
                    return room;
                case 4:
                    return null;
                default:
                    System.out.println(">>> Wrong option.");
            }
        } while (option != 4);
        return null;
    }

    private Room chooseDifficulty(Scanner scanner, Room room) {
        int option;
        do {
            System.out.println("\n");
            System.out.println("-----------------------------------------");
            System.out.println("Choose a difficulty for the room:");
            System.out.println("-----------------------------------------");
            System.out.println(EnumConstants.GAME_LEVEL.getMenuOptions());
            System.out.println("    1. Easy");
            System.out.println("    2. Intermediate");
            System.out.println("    3. Hard");
            System.out.println("    4. Back");

            option = EntryUtils.readStringLikeInt(scanner, "\n>>> Choose option > ", false);
/*
            ClueDAO clueDao = new ClueDAO();
            try {
                List<Clues> cluesList = clueDao.getClueByThemeAndLevel(room.getRoomTheme(), EnumConstants.GAME_LEVEL.getDescriptionFromLevelCode(option));
                clueDao.saveClueRoomRelation(clueList, roomId);
            } catch (SQLException | ClassNotFoundException e) {
                log.error(" Clue search error in getClueByThemeAndLevel method : ",e);
            }
*/
            switch (option) {
                case 1:
                    room.setRoomLevel(EnumConstants.GAME_LEVEL.EASY.getDescription());
                    return room;
                case 2:
                    room.setRoomLevel(EnumConstants.GAME_LEVEL.INTERMEDIATE.getDescription());
                    return room;
                case 3:
                    room.setRoomLevel(EnumConstants.GAME_LEVEL.HARD.getDescription());
                    return room;
                default:
                    System.out.println(">>> Wrong option.");
            }
        } while (option != 4);

        return null;
    }


    private Room chooseStatus(Scanner scanner, Room room) {
        Integer option;
        boolean optionOk = false;
        do {
            System.out.println("\n");
            System.out.println("-----------------------------------------");
            System.out.println("Choose a status for the room:");
            System.out.println("-----------------------------------------");
            System.out.println(EnumConstants.ITEM_STATUS.getMenuOptions());

            option = EntryUtils.readStringLikeInt(scanner, "\n>>> Choose option > ", false);

            switch (option) {
                case 0:
                    room.setRoomStatus(EnumConstants.ITEM_STATUS.AVAILABLE.getDescription());
                    return room;
                case 1:
                    room.setRoomStatus(EnumConstants.ITEM_STATUS.NOT_AVAILABLE.getDescription());
                    return room;
                default:
                    option = null;
                    System.out.println(">>> Wrong option.");
            }

        } while (option != null);

        return null;

    }
}
