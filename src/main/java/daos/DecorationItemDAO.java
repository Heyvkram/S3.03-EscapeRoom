package daos;

import entities.Clues;
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

    public List<DecorationItem> getDecorationsItemsBy(Object object, String fieldName) throws SQLException, ClassNotFoundException {
        if(object == null){
            return null;
        }
        List<DecorationItem> resultList = new ArrayList<>();
        final String sqlSelectDecorationItem = "SELECT * FROM decoration_items WHERE " + fieldName + "=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectDecorationItem)) {
            if (object instanceof Long) {
                ps.setLong(1, (Long) object);
            } else if (object instanceof Integer) {
                ps.setInt(1, (Integer) object);
            } else if (object instanceof String) {
                ps.setString(1, (String) object);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    resultList.add(resultSetToDecorationItemObject(rs));
                }
            }
        }
        return resultList;
    }

    public Optional<DecorationItem> getDecorationItemBy(Object object, String fieldName) throws SQLException, ClassNotFoundException {
        if(object == null){
            return Optional.empty();
        }
        final String sqlSelectDecorationItem = "SELECT * FROM decoration_items WHERE " + fieldName + "=?";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlSelectDecorationItem)) {
            if (object instanceof Long) {
                ps.setLong(1, (Long) object);
            } else if (object instanceof Integer) {
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

    public Optional<DecorationItem> getDecorationIemById(Long id) throws SQLException, ClassNotFoundException {
        return getDecorationItemBy(id, "decoration_item_id");
    }

    public boolean saveDecorationItem(DecorationItem decorationItem) {
        if(decorationItem == null) return false;
        String sqlStr = "INSERT INTO decoration_items (decoration_item_name, decoration_item_description, decoration_item_theme, decoration_item_price, decoration_item_clue_valor, decoration_item_img) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        String resultMsg = "\nDecoration item inserted";

        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlStr)) {
            ps.setString(1, decorationItem.getName());
            ps.setString(2, decorationItem.getDescription());
            ps.setString(3, decorationItem.getTheme());
            ps.setDouble(4, decorationItem.getPrice());
            ps.setInt(5, decorationItem.getClueValor());
            ps.setString(6, decorationItem.getImg());

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
        decorationItem.setTheme(rs.getString("decoration_item_theme"));
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

    public void printDecorationItemsByTheme(String theme) throws ClassNotFoundException, SQLException {
        System.out.println("Decoration Items with theme '" + theme + "' ____________________");
        List<DecorationItem> filteredItems = getAllDecorationItems().stream()
                .filter(item -> item.getTheme().equalsIgnoreCase(theme))
                .toList();

        if (filteredItems.isEmpty()) {
            System.out.println("No decoration items found with the theme: " + theme);
            return;
        }

        filteredItems.forEach(DecorationItem::printThemeValorValues);

        double totalPrice = filteredItems.stream()
                .mapToDouble(DecorationItem::getPrice)
                .sum();

        System.out.println("------------------------------------------");
        System.out.println("Total price for theme '" + theme + "': $" + String.format("%.2f", totalPrice));
    }

    public void printAllDecorationItemsPrices() throws ClassNotFoundException, SQLException {
        System.out.println("Decoration Items Price list ____________________");
        List<DecorationItem> allItems = getAllDecorationItems();

        if (allItems.isEmpty()) {
            System.out.println("No decoration items found.");
            return;
        }

        allItems.forEach(DecorationItem::printPriceInfoValues);

        double totalPrice = allItems.stream()
                .mapToDouble(DecorationItem::getPrice)
                .sum();

        System.out.println("------------------------------------------");
        System.out.println("Total price for all decoration items: $" + String.format("%.2f", totalPrice));
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

    @Override
    String getTableName() {
        return TABLE_NAME;
    }

    @Override
    String getIdFieldName() {
        return "";
    }
}
