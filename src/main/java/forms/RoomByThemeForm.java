package forms;

import daos.RoomDAO;
import entities.Room;
import utils.EntryUtils;
import utils.EnumConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;
import java.util.Optional;


public class RoomByThemeForm {
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