package daos;

import entities.NotificationSMS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotificationDAO extends GenericDAO {

    private static final Logger log = LogManager.getLogger(NotificationDAO.class);
    final String TABLE_NAME = "notifications";
    final String ID_FIELD_NAME="notification_id";

    public void printAllNotifications() throws ClassNotFoundException, SQLException {
        System.out.println("\n    Users list............................");
        getAllNotifications().forEach(NotificationSMS::printBasicInfoValues);
    }

    public List<NotificationSMS> getAllNotifications() throws ClassNotFoundException, SQLException {
        String sqlSelectAllUsers = "SELECT * FROM " + TABLE_NAME;
        List<NotificationSMS> notificationList = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllUsers);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                notificationList.add(resultSetToNotificationObject(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return notificationList;
    }

    public boolean insertNewNotification(NotificationSMS notif) {
        if (notif == null) return false;
        String sqlStr = "INSERT INTO `notifications`(`notification_id`, `notification_title`, `notification_short_description`, `notification_message`, `notification_type`) " +
                " VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlStr)) {
            ps.setLong(1, notif.getId());
            ps.setString(2, notif.getTitle());
            ps.setString(3, notif.getShortDescription());
            ps.setString(4, notif.getMessage());
            ps.setString(5, notif.getType());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(">>>New comunication inserted\n");
            }
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            log.info("Can't save the information\n");
            log.error("Can't save the information\n", e);
            return false;
        }
    }

    public NotificationSMS resultSetToNotificationObject(ResultSet rs) throws SQLException {
        NotificationSMS notif = new NotificationSMS();
        notif.setId(rs.getLong("notification_id"));
        notif.setTitle(rs.getString("notification_title"));
        notif.setShortDescription(rs.getString("notification_short_description"));
        notif.setMessage(rs.getString("notification_message"));
        notif.setType(rs.getString("notification_type"));
        notif.setRegisterDate(rs.getTimestamp("notification_date_reg").toLocalDateTime());
        notif.setModificationDate((rs.getTimestamp("notification_date_modify")!=null)?rs.getTimestamp("notification_date_modify").toLocalDateTime():null);
        return notif;
    }

    public Optional<NotificationSMS> getNotificationById(Long id) throws SQLException, ClassNotFoundException {
        return getNotificationBy(id, ID_FIELD_NAME);
    }

    public Optional<NotificationSMS> getNotificationBy(Object object, String fieldName) throws SQLException, ClassNotFoundException {
        if (object == null) {
            return Optional.empty();
        }
        final String sqlSelectUser = "SELECT * FROM notifications WHERE " + fieldName + "=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectUser)) {

            if (object instanceof Integer) {
                ps.setInt(1, (Integer) object);
            } else if (object instanceof Long) {
                ps.setLong(1, (Long) object);
            } else if (object instanceof String) {
                ps.setString(1, (String) object);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(resultSetToNotificationObject(rs));
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public boolean sendNotification(NotificationSMS notification){
        // To do
        return true;
    }

    @Override
    String getTableName(){ return TABLE_NAME; }

    @Override
    String getIdFieldName(){  return ID_FIELD_NAME;  }

}
