package forms;


import daos.RoomDAO;
import entities.Room;
import forms.RoomForm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EntryUtils;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class RoomForm {

    private static final Logger log = LogManager.getLogger(RoomForm.class);

    public static void menuRoom(Scanner scanner) {
        int option;
        RoomDAO roomDao = new RoomDAO();
        System.out.println("\n");
        do {
            System.out.println("\n-----------------------------------------");
            System.out.println("Room menu:");
            System.out.println("-----------------------------------------");
            System.out.println("    1. New room");
            System.out.println("    2. List all rooms");
            System.out.println("    3. Select theme");
            System.out.println("    4. Choose difficulty");
            System.out.println("    5. Back");

            System.out.print("\nChoose option > ");
            option = scanner.nextInt();
            option = EntryUtils.llegirInt(scanner, null);

            switch (option) {
                case 1:
                    scanner.nextLine();
                    if(!roomDao.saveOrUpdateRoom(newRoomForm(scanner))){
                        System.out.println("\n   Error: Unable to establish connection to the database.");
                        System.out.println("     (Please contact your system administrator)\n");
                        if(!EntryUtils.readYesNo(scanner," Type 'Y' for continue or 'N' for scape.")){
                            option=5;
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
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Wrong option.");
            }
        } while (option != 3);
    }
}