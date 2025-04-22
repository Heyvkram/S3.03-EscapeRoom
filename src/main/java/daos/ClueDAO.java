package daos;

import entities.CalculablePriceInterface;
import entities.Clues;
import entities.DecorationItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DateUtils;
import utils.UtilsEscape;

import java.sql.*;
import java.util.*;

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

    public Clues getClueById(Long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM clues WHERE clue_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return resultSetToCluesObject(rs);
                }
            }
        }
        return null;
    }

    public List<Clues> getCluesByTheme(String theme) throws SQLException, ClassNotFoundException {
        return getClueBy(theme, "clue_theme", true);
    }

    public List<Clues> getCluesByThemeAndLevel(String theme, String level) throws SQLException, ClassNotFoundException {
        List<Clues> clueList = getCluesByTheme(theme);
        return clueList.stream().filter(item -> item.getLevel().equalsIgnoreCase(level)).toList();
    }

    public boolean saveClueRoomRelation(List<Clues> clueList, long roomId) {
        boolean result = false;
        String resultMsg = "Clues room relation created ";
        String sqlStr = "INSERT INTO relation_clue_room (clue_id, room_id,) " + "VALUES (?, ?);";

        for(Clues clue : clueList){
            try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sqlStr)) {
                ps.setLong(1, clue.getId());
                ps.setLong(2, roomId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println(resultMsg + clue.getId() + roomId );
                }

            } catch (SQLException | ClassNotFoundException e) {
                log.info("Can't save the information\n");
                log.error("Can't save the information\n", e);
            }
        }
        return result;
    }


    public boolean saveClue(Clues clue) {
        if (clue == null) return false;
        String resultMsg = "Clue inserted";
        String sqlStr = "INSERT INTO clues (clue_title, clue_description_user, clue_description_admin, clue_theme, clue_level, clue_game_phase, clue_date_reg, clue_price, clue_value) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sqlStr)) {
            ps.setString(1, clue.getTitle());
            ps.setString(2, clue.getDescriptionUser());
            ps.setString(3, clue.getDescriptionAdmin());
            ps.setObject(4, clue.getTheme());
            ps.setString(5, clue.getLevel());
            ps.setString(6, clue.getGamePhase());
            ps.setDate(7, null); //cuando viene null  hacer un if
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

    private Clues resultSetToCluesObject(ResultSet rs) throws SQLException {
        Clues clue = new Clues();
        clue.setId(rs.getLong("clue_id"));
        clue.setTitle(rs.getString("clue_title"));
        clue.setDescriptionUser(rs.getString("clue_description_user"));
        clue.setDescriptionAdmin(rs.getString("clue_description_admin"));
        clue.setTheme(rs.getString("clue_theme"));
        clue.setLevel(rs.getString("clue_level"));
        clue.setGamePhase(rs.getString("clue_game_phase"));
        Timestamp timedate = rs.getTimestamp("clue_date_reg");
        if (timedate != null) {
            clue.setClue_date_reg(DateUtils.parseToLocalDate(timedate.toString()));
        }
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

    public double getClueItemsPrices(List<Clues> itemsList){
        List<CalculablePriceInterface> priceInterfaceItems = new ArrayList<>(itemsList);
        return UtilsEscape.sumAllPrices(priceInterfaceItems);
    }

    public void printAllClueItemsPrices() throws ClassNotFoundException, SQLException {
        System.out.println("Clue Items Price list ____________________");
        List<Clues> allItems = getAllClues();

        if (allItems.isEmpty()) {
            System.out.println("No clue items found.");
            return;
        }

        allItems.forEach(Clues::printPriceInfoValues);

        List<CalculablePriceInterface> priceInterfaceItems = new ArrayList<>(allItems);
        double totalPrice = UtilsEscape.sumAllPrices(priceInterfaceItems);

        System.out.println("------------------------------------------");
        System.out.println("Total price for all clue items: $" + String.format("%.2f", totalPrice));
    }

    public Map<String, Double> getTotalCluePriceByTheme() throws SQLException, ClassNotFoundException {
        Map<String, Double> themePrices = new HashMap<>();
        String sql = "SELECT clue_theme, SUM(clue_price) AS total_price FROM clues GROUP BY clue_theme";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String theme = rs.getString("clue_theme");
                double price = rs.getDouble("total_price");
                themePrices.put(theme, price);
            }
        }
        return themePrices;
    }

    public List<Clues> getCluesOfRoom(long roomID) throws ClassNotFoundException, SQLException  {
        List<Clues> resultList = new ArrayList<>();
        final String sqlSelectDecorationItem = "select * from clues where " +
                "clue_id in (SELECT clue_id FROM relation_clue_room where room_id=?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectDecorationItem)) {
            ps.setLong(1, (Long) roomID);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    resultList.add(resultSetToCluesObject(rs));
                }
            }
        }
        return resultList;
    }

    public boolean saveCluesRoomRelations(long roomID, List<Clues> relations) {
        if (relations == null || relations.isEmpty()) {
            log.warn("Lista de relaciones nula o vacía. No se realiza ninguna inserción.");
            return true; // Consideramos que no hay error si no hay nada que insertar
        }

        String sqlStr = "INSERT INTO relation_clue_room (room_id, clue_id) VALUES (?, ?)";
        int totalRelations = relations.size();
        int successfulInsertions = 0;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlStr)) {

            conn.setAutoCommit(false); // Iniciamos una transacción para hacer la operación atómica

            for (Clues clue : relations) {
                ps.setLong(1, roomID);
                ps.setLong(2, clue.getId());
                ps.addBatch(); // Añadimos la inserción al batch
            }

            int[] results = ps.executeBatch(); // Ejecutamos el batch completo

            for (int result : results) {
                if (result == 1 || result == 0) { // Verifica si la inserción fue exitosa (1) o no hubo error (0 en algunos drivers)
                    successfulInsertions++;
                } else {
                    log.error("Error al insertar una relación. Resultado: {}", result);
                    conn.rollback(); // Si una inserción falla, hacemos rollback de toda la transacción
                    return false; // Devolvemos false para indicar el fallo
                }
            }

            conn.commit(); // Si todas las inserciones fueron exitosas, hacemos commit de la transacción
            log.info("Se insertaron {} de {} relaciones entre pistas y habitaciones.", successfulInsertions, totalRelations);
            return successfulInsertions == totalRelations; // Devolvemos true si todas fueron exitosas

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Error al guardar las relaciones entre pistas y habitaciones", e);
            return false; // Devolvemos false en caso de excepción
        }
    }


}
