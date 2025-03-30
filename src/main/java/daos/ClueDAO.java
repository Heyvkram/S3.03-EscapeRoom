package daos;

import entities.Clues;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClueDAO extends GenericDAO {

    private static final Logger log = LogManager.getLogger(ClueDAO.class);
    final String TABLE_NAME = "clues";

    public Optional<Clues> getClueBy(Object object, String fieldName, boolean isLikeSearch) throws SQLException, ClassNotFoundException {
        if (object == null) {
            return Optional.empty();
        }
        final String sqlSelectClue = "SELECT * FROM clues WHERE " + fieldName + "=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sqlSelectClue)) {

            if (object instanceof Integer) {
                ps.setInt(1, (Integer) object);
            } else if (object instanceof String) {
                ps.setString(1, (String) object);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(resultSetToCluesObject(rs));
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public Optional<Clues> getClueByTitle(String s, String title, boolean b) throws SQLException, ClassNotFoundException {
        return getClueByTitle(title, "clue_title", false);
    }

    public Optional<Clues> getClueById(Long id, String clueId, boolean b) throws SQLException, ClassNotFoundException {
        return getClueById(id, "clue_id", false);
    }

    public boolean saveOrUpdateClue(Clues clue) {
        if (clue == null) return false;
        String resultMsg = "Clue updated";
        String sqlStr = "UPDATE clues SET clue_id = ?, clue_title = ?,  clue_description_user = ?, clue_description_admin = ?, clue_theme = ?, clue_level = ?, clue_game_phase = ?, clue_date = ?, clue_price = ?, clue_value = ?";
        if (clue.getId() == null) {
            sqlStr = "INSERT INTO clues (clue_id, clue_title, clue_description_user, clue_description_admin, clue_theme, clue_level, clue_game_phase, clue_date, clue_price, clue_value" + "VALUES (?, ?, ?, ?, ?)";
            resultMsg = "\nClue inserted";
        }

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sqlStr)) {
            ps.setString(1, clue.getTitle());
            ps.setLong(2, clue.getId());
            ps.setDouble(3, clue.getPrice());
            ps.setInt(4, clue.getLevel());
            ps.setString(5, clue.getDescriptionAdmin());
            ps.setString(5, clue.getDescriptionUser());
            // ps.setDate(5, clue.getDate());
            ps.setInt(5, clue.getGamePhase());
            ps.setDouble(5, clue.getValue());
            ps.setObject(5, clue.getTheme());  //El Theme quedará así????

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(resultMsg);
            }
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            log.info("Can't save the information\n");
            log.error("Can't save the information\n", e);
        }

        return false;
    }

    public void printAllClues() throws ClassNotFoundException, SQLException {
        System.out.println("Clues list ____________________");
        getAllClues().forEach(Clues::printBasicInfoValues);
    }

    public List<Clues> getAllClues() throws ClassNotFoundException, SQLException {
        String sqlSelectAllClues = "SELECT * FROM " + TABLE_NAME;
        List<Clues> cluesList = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sqlSelectAllClues); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cluesList.add(resultSetToCluesObject(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return cluesList;
    }

    public Clues resultSetToCluesObject(ResultSet rs) throws SQLException {
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

    public boolean deleteClueById(Long id) throws SQLException, ClassNotFoundException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("There is no clue with this id");
        }
        final String sqlDelete = "DELETE FROM " + TABLE_NAME + " WHERE clue_id=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
            ps.setLong(1, id);
            ps.execute();
            return true;
        }
    }

}
