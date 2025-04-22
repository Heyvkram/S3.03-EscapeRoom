package daos;

import entities.NotificationGeneric;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EnumConstants;

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
        getAllNotifications().forEach(NotificationGeneric::printBasicInfoValues);
    }

    public List<NotificationGeneric> getAllNotifications() throws ClassNotFoundException, SQLException {
        String sqlSelectAllUsers = "SELECT * FROM " + TABLE_NAME;
        List<NotificationGeneric> notificationList = new ArrayList<>();
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

    public boolean insertNewNotification(NotificationGeneric notif) {
        if (notif == null) return false;
        String sqlStr = "INSERT INTO `notifications`(`notification_id`, `notification_title`, `notification_short_description`, `notification_message`, `notification_type`, `notification_shipping_type`) " +
                " VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlStr)) {
            ps.setLong(1, notif.getId());
            ps.setString(2, notif.getTitle());
            ps.setString(3, notif.getShortDescription());
            ps.setString(4, notif.getMessage());
            ps.setString(5, notif.getType());
            ps.setString(6, notif.getShippingType());

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

    public NotificationGeneric resultSetToNotificationObject(ResultSet rs) throws SQLException {
        NotificationGeneric notif = new NotificationGeneric();
        notif.setId(rs.getLong("notification_id"));
        notif.setTitle(rs.getString("notification_title"));
        notif.setShortDescription(rs.getString("notification_short_description"));
        notif.setMessage(rs.getString("notification_message"));
        notif.setType(rs.getString("notification_type"));
        notif.setShippingType(rs.getString("notification_shipping_type"));
        notif.setRegisterDate(rs.getTimestamp("notification_date_reg").toLocalDateTime());
        notif.setModificationDate((rs.getTimestamp("notification_date_modify")!=null)?rs.getTimestamp("notification_date_modify").toLocalDateTime():null);
        return notif;
    }

    public Optional<NotificationGeneric> getNotificationById(Long id) throws SQLException, ClassNotFoundException {
        return getNotificationBy(id, ID_FIELD_NAME);
    }

    public Optional<NotificationGeneric> getNotificationBy(Object object, String fieldName) throws SQLException, ClassNotFoundException {
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

    public boolean sendSmsNotification(NotificationGeneric notification, String phoneNumber){
        // To do
        System.out.println("    > Notification id:"+notification.getId()+" "+phoneNumber+" : SMS sended");
        return true;
    }

    public boolean sendEmailNotification(NotificationGeneric notification, String email){
        // To do
        System.out.println("    > Notification id:"+notification.getId()+" "+email+" : Email sended");
        return true;
    }

    public void publishNotification(NotificationGeneric notification, List<User> usersList) {
        for(User user : usersList){
            publishNotificationToUser( notification, user);
        }
    }

    public void publishNotificationToUser(NotificationGeneric notification, User user) {
        NotificationDAO notificationDAO = new NotificationDAO();
        if(EnumConstants.NOTIFICATION_SHIPPING_TYPE.SMS.getDescription().equals(notification.getShippingType())){
            if(user.getPhoneNumber().isEmpty()){
                System.out.println(" ERROR: The user does not have a registered their phone number. User id : "+ user.getId() );
            }else{
                notificationDAO.sendSmsNotification( notification, user.getPhoneNumber());
            }
        }else{
            if(user.getMail().isEmpty()){
                System.out.println(" ERROR: The user does not have a registered their email. User id : "+ user.getId() );
            }else{
                notificationDAO.sendEmailNotification( notification, user.getMail());
            }
        }
    }

    @Override
    String getTableName(){ return TABLE_NAME; }

    @Override
    String getIdFieldName(){  return ID_FIELD_NAME;  }

}