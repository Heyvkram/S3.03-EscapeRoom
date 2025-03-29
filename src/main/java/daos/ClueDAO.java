package daos;

import entities.Clues;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClueDAO extends GenericDAO {

    private static final Logger log = LogManager.getLogger(ClueDAO.class);
    final String TABLE_NAME="clues";

    public void printAllClues() throws ClassNotFoundException, SQLException {
        System.out.println("Clues list ____________________");
        getAllClues().forEach(Clues::printBasicInfoValues);
    }

    public List<Clues> getAllClues() throws ClassNotFoundException, SQLException {
        String sqlSelectAllUsers = "SELECT * FROM "+TABLE_NAME;
        List<Clues> cluesList = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllUsers);
             ResultSet rs = ps.executeQuery()) {
//

        } catch (SQLException e) {
            log.error(e);
        }
        return cluesList;
    }

    public User resultSetToClues(ResultSet rs) throws SQLException {
        Clues clue = new Clues();
        clue.setId(rs.getLong("clue_id"));
        clue.setTitle(rs.getString("clue_title"));
        clue.setDescriptionUser(rs.getString("clue_description_user"));
        clue.setDescriptionAdmin(rs.getString("clue_description_admin"));
        // clue.setTheme(rs.get("clue_theme"));                      //como pongo al enum????
        clue.setLevel(rs.getInt("clue_level"));
        clue.setGamePhase(rs.getInt("clue_game_phase"));
        System.out.println(rs.getDate("clue_date"));
      //  clue.setDate(rs.getDate("clue_date"));//como pongo la fecha????
        clue.setPrice(rs.getDouble("clue_price"));
        clue.setValue(rs.getDouble("clue_value"));

        return null;
    }


}
