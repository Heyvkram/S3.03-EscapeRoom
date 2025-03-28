package daos;

import com.sun.jdi.LongValue;
import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DdBbUtils;

import java.sql.*;
import java.util.*;

public class UserDAO extends GenericDAO {

    private static final Logger log = LogManager.getLogger(UserDAO.class);
    final String TABLE_NAME = "users";

    public Optional<User> getUserBy(Object object, String fieldName, boolean isLikeSearch) throws SQLException, ClassNotFoundException {
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

    public Optional<User> getUserById(Long id) throws SQLException, ClassNotFoundException {
        return getUserBy(id, "user_id", false);
    }

    public Optional<User> getUserByIdCard(String idCard) throws SQLException, ClassNotFoundException {
        return getUserBy(idCard, "user_idCard", false);
    }

    public Optional<User> getUserByNickName(String nickName) throws SQLException, ClassNotFoundException {
        return getUserBy(nickName, "nick_name", false);
    }

    public List<User> getUsersLikeSurName(String pLastname) throws ClassNotFoundException, SQLException {
        String sqlSelectAllUsers = "SELECT * FROM users WHERE first_lastName LIKE ?";
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
                    "user_address_door,user_city,user_zip_code,user_country,user_phone,user_mail) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlStr)) {
            ps.setString(1, user.getNickName());
            ps.setString(2, user.getName());
            ps.setString(3, user.getSurname());
            ps.setString(4, user.getAddressStreet());
            ps.setString(5, user.getIdCard());
            ps.setInt(6, user.getAddressNumber());
            ps.setString(7, user.getAddressFloor());
            ps.setString(8, user.getAddressDoor());
            ps.setString(9, user.getCity());
            ps.setString(10, user.getZipCode());
            ps.setString(11, user.getCountry());
            ps.setString(12, user.getPhoneNumber());
            ps.setString(13, user.getMail());

            if (user.getId() != null) {
                ps.setLong(14, user.getId());
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

        if(user.getNickName()!=null) {
            arrayQueryParams.add(" user_nick_name = ?");
            parameterList.add(user.getNickName());
        }
        if(user.getName()!=null) {
            arrayQueryParams.add(" user_name = ?");
            parameterList.add(user.getName());
        }
        if(user.getSurname()!=null) {
            arrayQueryParams.add(" user_surname = ?");
            parameterList.add(user.getSurname());
        }

        if(arrayQueryParams.isEmpty()){
            System.out.println("\n>>> No changes in user\n");
            return false;
        }

        String updatedParams = String.join(",", arrayQueryParams);
        String sqlUpdate = "UPDATE users SET " + updatedParams + " WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlUpdate);
        ) {
            DdBbUtils.preparareUpdate(ps, parameterList);
            ps.setLong(parameterList.size()+1, user.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(">>> User updated\n");
            }
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            log.info("!!!! Can't save the information\n");
            log.error("!!!! Can't save the information\n", e);
            return false;
        }
    }

    public boolean deleteById(Long id) throws SQLException, ClassNotFoundException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("No user");
        }
        final String sqlDelete = "DELETE FROM "+TABLE_NAME+" WHERE user_id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
            ps.setLong(1, id);
            ps.execute();
            return true;
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
        return user;
    }

}



