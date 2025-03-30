package daos;

import entities.DecorationItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DecorationItemDAO extends GenericDAO{
    private static final Logger log = LogManager.getLogger(DecorationItemDAO.class);
    final String TABLE_NAME = "decoration_items";

    public Optional<DecorationItem> getDecorationItemBy(Object object, String fieldName, boolean isLikeSearch) throws SQLException, ClassNotFoundException {
        if(object == null){
            return Optional.empty();
        }
        final String sqlSelectDecorationItem = "SELECT * FROM decoration_items WHERE " + fieldName + "=?";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlSelectDecorationItem)) {
            if (object instanceof Integer) {
                ps.setInt(1, (Integer) object);
            } else if (object instanceof String) {
                ps.setString(1, (String) object);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(resultSetToDecorationItemObject(rs));
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public Optional<DecorationItem> getDecorationItemByName(String name) throws SQLException, ClassNotFoundException {
        return getDecorationItemBy( name, "decoration_item_name", false);
    }

    public Optional<DecorationItem> getDecorationIemById(Long id) throws SQLException, ClassNotFoundException {
        return getDecorationItemBy( id, "user_id", false);
    }

    public boolean saveOrUpdateDecorationItem(DecorationItem decorationItem) {
        if(decorationItem == null) return false;
        String resultMsg = "Decoration item updated";
        String sqlStr = "UPDATE decoration_items SET decoration_item_name = ?, decoration_item_description = ?,  decoration_item_price = ?, decoration_item_clue_valor = ?, decoration_item_img = ?";
        if (decorationItem.getId() == null) {
            sqlStr = "INSERT INTO decoration_items (decoration_item_name, decoration_item_description, decoration_item_price, decoration_item_clue_valor, decoration_item_img" +
                    "VALUES (?, ?, ?, ?, ?)";
            resultMsg = "\nUser inserted";
        }

        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlStr)) {
            ps.setString(1, decorationItem.getName());
            ps.setString(2, decorationItem.getDescription());
            ps.setDouble(3, decorationItem.getPrice());
            ps.setInt(4, decorationItem.getClueValor());
            ps.setString(5, decorationItem.getImg());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0){
                System.out.println(resultMsg);
            }
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            log.info("Can't save the information\n");
            log.error("Can't save the information\n",e);
        }

        return false;
    }

    public DecorationItem resultSetToDecorationItemObject(ResultSet rs) throws SQLException {
        DecorationItem decorationItem = new DecorationItem();
        decorationItem.setId(rs.getLong("decoration_item_id"));
        decorationItem.setName(rs.getString("decoration_item_name"));
        decorationItem.setDescription(rs.getString("decoration_item_description"));
        //decorationItem.setTheme(rs.getTheme("decoration_item_theme"));
        decorationItem.setPrice(rs.getDouble("decoration_item_price"));
        decorationItem.setClueValor(rs.getInt("decoration_item_clue_valor"));
        decorationItem.setImg(rs.getString("decoration_item_img"));
        return decorationItem;
    }

    public List<DecorationItem> getAllDecorationItems() throws ClassNotFoundException, SQLException {
        String sqlSelectAllDecorationItems = "SELECT * FROM "+TABLE_NAME;
        List<DecorationItem> decorationItemsList = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllDecorationItems);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                decorationItemsList.add(resultSetToDecorationItemObject(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return decorationItemsList;
    }

    public void printAllDecorationItems() throws ClassNotFoundException, SQLException {
        System.out.println("Decoration Items list ____________________");
        getAllDecorationItems().forEach(DecorationItem::printBasicInfoValues);
    }

    public boolean deleteDecorationItemsById(Long id) throws SQLException, ClassNotFoundException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("There is no decoration item with this id");
        }
        final String sqlDelete = "DELETE FROM "+TABLE_NAME+" WHERE decoration_item_id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
            ps.setLong(1, id);
            ps.execute();
            return true;
        }
    }

}
