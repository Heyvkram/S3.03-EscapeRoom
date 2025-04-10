package daos;

import com.sun.jdi.LongValue;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DdBbUtils;
import utils.EntryUtils;

import java.sql.*;
import java.util.*;

public class UserDAO extends GenericDAO {

    private static final Logger log = LogManager.getLogger(UserDAO.class);
    final String TABLE_NAME = "users";
    final String ID_FIELD_NAME = "user_id";

    @Override
    String getTableName() {
        return TABLE_NAME;
    }

    @Override
    String getIdFieldName() {
        return ID_FIELD_NAME;
    }

    public boolean existUserBy(Object object, String fieldName, boolean ignoreCase) throws SQLException, ClassNotFoundException {

        String finalSqlSelectr = "SELECT EXISTS (SELECT 1 FROM users WHERE ";
        String whereClause;

        if (ignoreCase && "user_nick_name".equals(fieldName) && object instanceof String) {
            whereClause = "LOWER(" + fieldName + ") = LOWER(?)";
        } else {
            whereClause = fieldName + " = ?";
        }

        finalSqlSelectr += whereClause + ")";

        boolean result = false;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(finalSqlSelectr)) {
            if (object instanceof Integer) {
                ps.setInt(1, (Integer) object);
            } else if (object instanceof Long) {
                ps.setLong(1, (Long) object);
            } else if (object instanceof String) {
                ps.setString(1, (String) object);
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getBoolean(1);
                }
            }
            return result;
        }
    }

    public Optional<User> getUserByIdCard(String idCard) throws SQLException, ClassNotFoundException {
        return getUserBy(idCard, "user_idCard");
    }

    public Optional<User> getUserByNickName(String nickName) throws SQLException, ClassNotFoundException {
        return getUserBy(nickName, "nick_name");
    }

    public Optional<User> getUserByEmail(String nickName) throws SQLException, ClassNotFoundException {
        return getUserBy(nickName, "user_mail");
    }

    public Optional<User> getUserById(Long id){
        Optional<User> user = Optional.empty();;
        try{
            user = getUserBy(id, ID_FIELD_NAME);
        }catch (SQLException | ClassNotFoundException e) {
            System.out.println("\n>>> No user with this id was found.");
        }
        return user;
    }

    public Optional<User> getUserBy(Object object, String fieldName) throws SQLException, ClassNotFoundException {
        if (object == null) {
            return Optional.empty();
        }
        final String sqlSelectUser = "SELECT * FROM users WHERE " + fieldName + "=?";
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
                    return Optional.of(resultSetToUserObject(rs));
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public List<User> getUsersNotifiables() throws ClassNotFoundException, SQLException {
        String sqlSelectAllUsers = "SELECT * FROM users WHERE user_notifiable = 'y'";
        List<User> usersList = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllUsers)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usersList.add(resultSetToUserObject(rs));
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Error getting notifiables users : " + e.getMessage(), e);
        }

        return usersList;
    }

    public List<User> getUsersLikeSurName(String pLastname) throws ClassNotFoundException, SQLException {
        String sqlSelectAllUsers = "SELECT * FROM users WHERE user_surname LIKE ?";
        List<User> usersList = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllUsers)) {

            ps.setString(1, "%" + pLastname + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usersList.add(resultSetToUserObject(rs));
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Error getting users by last name: " + e.getMessage(), e);
        }

        return usersList;
    }

    public void printAllUsers() throws ClassNotFoundException, SQLException {
        System.out.println("\n    Users list............................");
        getAllUsers().forEach(User::printBasicInfoValues);
    }

    public void printUsersList(List<User> usersList) throws ClassNotFoundException, SQLException {
        System.out.println("\n    Users list............................");
        usersList.forEach(User::printBasicInfoValues);
    }

    public List<User> getAllUsers() throws ClassNotFoundException, SQLException {
        String sqlSelectAllUsers = "SELECT * FROM " + TABLE_NAME;
        List<User> usersList = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllUsers);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usersList.add(resultSetToUserObject(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return usersList;
    }

    public boolean insertNewUser(User user) {
        if (user == null) return false;
        String sqlStr = "INSERT INTO users (user_nick_name,user_name,user_surname,user_idCard,user_address_street,user_address_number,user_address_floor," +
                "user_address_door,user_city,user_zip_code,user_country,user_phone,user_mail, user_notifiable ) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlStr)) {
            ps.setString(1, user.getNickName());
            ps.setString(2, user.getName());
            ps.setString(3, user.getSurname());
            ps.setString(4, user.getAddressStreet());
            ps.setString(5, user.getIdCard());

            if (user.getAddressNumber() != null) {
                ps.setInt(6, user.getAddressNumber());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }

            ps.setString(7, user.getAddressFloor());
            ps.setString(8, user.getAddressDoor());
            ps.setString(9, user.getCity());
            ps.setString(10, user.getZipCode());
            ps.setString(11, user.getCountry());
            ps.setString(12, user.getPhoneNumber());
            ps.setString(13, user.getMail());
            ps.setString(14, user.getNotifiable());

            if (user.getId() != null) {
                ps.setLong(15, user.getId());
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(">>>New user inserted\n");
            }
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            log.info("Can't save the information\n");
            log.error("Can't save the information\n", e);
            return false;
        }
    }

    public boolean updateUser(User user) {
        if (user == null) return false;

        ArrayList<Object> parameterList = new ArrayList<>();
        ArrayList<String> arrayQueryParams = new ArrayList<>();

        if (EntryUtils.isNotNullOrEmpty(user.getNickName())) {
            arrayQueryParams.add(" user_nick_name = ?");
            parameterList.add(user.getNickName());
        }

        if (EntryUtils.isNotNullOrEmpty(user.getName())) {
            arrayQueryParams.add(" user_name = ?");
            parameterList.add(user.getName());
        }

        if (EntryUtils.isNotNullOrEmpty(user.getSurname())) {
            arrayQueryParams.add(" user_surname = ?");
            parameterList.add(user.getSurname());
        }

        if (EntryUtils.isNotNullOrEmpty(user.getAddressStreet())) {
            arrayQueryParams.add(" user_address_street = ?");
            parameterList.add(user.getAddressStreet());
        }

        if (EntryUtils.isNotNullOrEmpty(user.getIdCard())) {
            arrayQueryParams.add(" user_idCard = ?");
            parameterList.add(user.getIdCard());
        }

        if (user.getAddressNumber() != null) {
            arrayQueryParams.add(" user_address_number = ?");
            parameterList.add(user.getAddressNumber());
        }

        if (EntryUtils.isNotNullOrEmpty(user.getAddressFloor())) {
            arrayQueryParams.add(" user_address_floor = ?");
            parameterList.add(user.getAddressFloor());
        }

        if (EntryUtils.isNotNullOrEmpty(user.getAddressDoor())) {
            arrayQueryParams.add(" user_address_door = ?");
            parameterList.add(user.getAddressDoor());
        }

        if (EntryUtils.isNotNullOrEmpty(user.getCity())) {
            arrayQueryParams.add(" user_city = ?");
            parameterList.add(user.getCity());
        }

        if (EntryUtils.isNotNullOrEmpty(user.getZipCode())) {
            arrayQueryParams.add(" user_zip_code = ?");
            parameterList.add(user.getZipCode());
        }

        if (EntryUtils.isNotNullOrEmpty(user.getCountry())) {
            arrayQueryParams.add(" user_country = ?");
            parameterList.add(user.getCountry());
        }

        if (EntryUtils.isNotNullOrEmpty(user.getPhoneNumber())) {
            arrayQueryParams.add(" user_phone = ?");
            parameterList.add(user.getPhoneNumber());
        }

        if (EntryUtils.isNotNullOrEmpty(user.getMail())) {
            arrayQueryParams.add(" user_mail = ?");
            parameterList.add(user.getMail());
        }

        if (EntryUtils.isNotNullOrEmpty(user.getNotifiable())) {
            arrayQueryParams.add(" user_notifiable = ?");
            parameterList.add(user.getNotifiable());
        }

        if (arrayQueryParams.isEmpty()) {
            System.out.println("\n>>> No changes in user\n");
            return false;
        }

        String updatedParams = String.join(",", arrayQueryParams);
        String sqlUpdate = "UPDATE users SET " + updatedParams + " WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlUpdate);
        ) {
            DdBbUtils.preparareUpdate(ps, parameterList);
            ps.setLong(parameterList.size() + 1, user.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(">>> User updated\n");
            }
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("!!!! Can't save the information\n");
            log.error("!!!! Can't save the information\n", e);
            return false;
        }
    }

    public User resultSetToUserObject(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setName(rs.getString("user_name"));
        user.setSurname(rs.getString("user_surname"));
        user.setNickName(rs.getString("user_nick_name"));
        user.setAddressStreet(rs.getString("user_address_street"));
        user.setAddressNumber(rs.getInt("user_address_number"));
        user.setAddressFloor(rs.getString("user_address_floor"));
        user.setAddressDoor(rs.getString("user_address_door"));
        user.setCity(rs.getString("user_city"));
        user.setZipCode(rs.getString("user_zip_code"));
        user.setCountry(rs.getString("user_country"));
        user.setPhoneNumber(rs.getString("user_phone"));
        user.setMail(rs.getString("user_mail"));
        user.setNotifiable(rs.getString("user_notifiable"));
        return user;
    }

}