package forms;

import daos.ClueDAO;
import entities.Clues;
import utils.EntryUtils;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

import static forms.UserForm.log;
import static utils.EntryUtils.*;

public class CluesForm {

    public static void menuClues(Scanner scanner) {
        int option;
        ClueDAO clueDao = new ClueDAO();
        System.out.println("\n");
        do {
            System.out.println("-----------------------------------------");
            System.out.println("Clue menu:");
            System.out.println("-----------------------------------------");
            System.out.println("    1. New clue");
            System.out.println("    2. List clues");
            System.out.println("    3. Find clue by theme");
            System.out.println("    4. Delete clue");
            System.out.println("    5. Back");

            System.out.print("\nChoose option > ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    scanner.nextLine();
                    if (!clueDao.saveOrUpdateClue(newClueForm(scanner))) {
                        System.out.println("\n   Error: Unable to establish connection to the database.");
                        System.out.println("     (Please contact your system administrator)\n");
                        if (!EntryUtils.readYesNo(scanner, " Type 'Y' for continue or 'N' for scape.")) {
                            option = 5;
                        }
                    }

                    break;
                case 2:
                    try {
                        clueDao.printAllClues();
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 3:
                    try {
                        Optional<Clues> clueOpt = clueDao.getClueByTheme(EntryUtils.llegirString(scanner, "Type clue theme: ", true));
                        scanner.nextLine();
                        if (clueOpt.isPresent()) {
                            clueOpt.get().printBasicInfoValues();
                        } else {
                            System.out.println("No clue found with this theme");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }

                    break;

//                public Optional<Clues> getClueByTheme(String theme) throws SQLException, ClassNotFoundException {
//                    String sql = "SELECT * FROM clues WHERE clue_theme = ?";
//                    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
//                        ps.setString(1, theme);
//                        try (ResultSet rs = ps.executeQuery()) {
//                            if (rs.next()) {
//                                return Optional.of(resultSetToCluesObject(rs));
//                            }
//                        }
//                    }
//                    return Optional.empty();
                case 4:
                    try {
                        clueDao.deleteById(llegirInt(scanner, "Type the user id: ", false));
                    } catch (SQLException | ClassNotFoundException e) {
                        log.error(e);
                    }
                    break;
                case 5:
                    break;

                default:
                    System.out.println("Wrong option.");
            }
        } while (option != 4);
    }


    private static Clues newClueForm(Scanner scanner) {
        System.out.println("\n");
        Clues clue = new Clues();
        clue.setTitle(llegirString(scanner, "*Title: ", false));
        clue.setDescriptionUser(llegirString(scanner, "*User description: ", false));
       // clue.setDescriptionAdmin(llegirString(scanner, "Admin description: ", false));
        //clue.setTheme(EntryUtils.llegirString(scanner, "Theme: ", false));
        //clue.setLevel(llegirInt(scanner, "Level: ", false));
        clue.setGamePhase(llegirString(scanner, "Game phase: ", false));
//        scanner.nextLine();
        // clue.setDate(EntryUtils.llegirDate(scanner, "Date: ", false));
//        clue.setPrice(llegirDouble(scanner, "Price: ", false));
//        clue.setValue(llegirDouble(scanner, "Value: ", false));

        return clue;
    }

    private static void editCluesForm(Clues clue, Scanner scanner) {
        System.out.print("\n*Title: " + clue.getTitle());
        clue.setTitle(llegirString(scanner, ", new value: > ", true));

        System.out.print("\n*User description: " + clue.getDescriptionUser());
        llegirString(scanner, ", new value: > ", true);

        System.out.print("\n*Admin description: " + clue.getDescriptionAdmin());
        llegirString(scanner, ", new value: > ", true);

//        System.out.print("\n*Theme: "+ clue.getTheme());
//        llegirEn(scanner, ", new value: > ", true);

        System.out.print("\n*Level: " + clue.getLevel());
        llegirInt(scanner, ", new value: > ", true);
        scanner.nextLine();

        System.out.print("\n*Game phase: " + clue.getGamePhase());
        llegirInt(scanner, ", new value: > ", true);


//        System.out.print("\n*Date: "+ clue.getDate());
//        llegirDate(scanner, ", new value: > ", true);

        System.out.print("\n*Price: " + clue.getPrice());
        llegirDouble(scanner, ", new value: > ", true);

        System.out.print("\n*Value: " + clue.getValue());
        llegirDouble(scanner, ", new value: > ", true);

    }

}

