package daos;

import entities.Clues;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DateUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.getConnection;

public class ClueDAO extends GenericDAO {

    private static final Logger log = LogManager.getLogger(ClueDAO.class);
    final String TABLE_NAME = "clues";
    final String ID_FIELD_NAME = "clue_id";

    public List<Clues> getClueBy(Object object, String fieldName, boolean isLikeSearch) throws SQLException, ClassNotFoundException {
        if (object == null) {
            return Collections.emptyList();
        }
        String param = "";
        if (object instanceof Integer) {
            param = ((Integer) object).toString();
        } else if (object instanceof String) {
            param = (String) object;
        }
        final String sqlSelectClue = "SELECT * FROM clues WHERE " + fieldName + "='" + param + "'";
        List<Clues> cluesList = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sqlSelectClue)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cluesList.add(resultSetToCluesObject(rs));
                }
            } catch (SQLException e) {
                log.error(e);
            }

            return cluesList;
        }
    }

    public Optional<Clues> getClueById(Long id) {
        Optional<Clues> clue = getClueById(id);
        return clue;
    }
//    public Clues getClueById(Long aLong) throws SQLException, ClassNotFoundException {
//        return getClueBy(aLong, "clue_theme", true);
//    }

    public List<Clues> getClueByTheme(String theme) throws SQLException, ClassNotFoundException {
        return getClueBy(theme, "clue_theme", true);
    }


//        public void printCluesByTheme () throws ClassNotFoundException, SQLException {
//            System.out.println("Clues list by theme ____________________");
//            getClueByTheme().forEach(Clues::printBasicInfoValues);
//        }
//

    public boolean saveOrUpdateClue(Clues clue) {
        if (clue == null) return false;
        String resultMsg = "Clue updated";
        String sqlStr = "UPDATE clues SET clue_id = ?, clue_title = ?,  clue_description_user = ?, clue_description_admin = ?, clue_theme = ?, clue_level = ?, clue_game_phase = ?, clue_date = ?, clue_price = ?, clue_value = ?";
        if (clue.getId() == null) {
            sqlStr = "INSERT INTO clues (clue_title, clue_description_user, clue_theme, clue_level, clue_game_phase, clue_date_reg, clue_price, clue_value" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            resultMsg = "\nClue inserted";
        }

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sqlStr)) {
            ps.setString(1, clue.getTitle());
            ps.setString(2, clue.getDescriptionUser());
            ps.setString(3, clue.getDescriptionAdmin());
            ps.setObject(4, clue.getTheme());
            ps.setString(5, clue.getLevel());
            ps.setString(6, clue.getGamePhase());
            ps.setDate(7, null);
            ps.setDouble(8, clue.getPrice());
            ps.setDouble(9, clue.getValue());


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
        clue.setTheme(rs.getString("clue_theme"));
        clue.setLevel(rs.getString("clue_level"));
        clue.setGamePhase(rs.getString("clue_game_phase"));
        clue.setClue_date_reg(DateUtils.parseToLocalDate(rs.getTimestamp("clue_date_reg").toString()));
        clue.setPrice(rs.getDouble("clue_price"));
        clue.setValue(rs.getDouble("clue_value"));

        return clue;
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

    @Override
    String getTableName() {
        return TABLE_NAME;
    }

    @Override
    String getIdFieldName() {
        return ID_FIELD_NAME;
    }

}
