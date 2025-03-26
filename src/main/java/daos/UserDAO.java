package daos;

import entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO extends GenericDAO{

    private static final Logger log = LogManager.getLogger(UserDAO.class);
    final String TABLE_NAME="users";

    public Optional<User> getUserById(Integer id) throws SQLException, ClassNotFoundException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("No user");
        }
        final String sqlSelectUser = "SELECT * FROM users WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectUser)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(resultSetToUserObject(rs));
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public List<User> getAllUsers() throws ClassNotFoundException, SQLException {
        String sqlSelectAllUsers = "SELECT * FROM "+TABLE_NAME;
        List<User> usersList = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllUsers);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usersList.add(resultSetToUserObject(rs));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return usersList;
    }

    public boolean saveOrUpdateUser(User user) {
        if(user == null) return false;
        String resultMsg = "User updated";
        String sqlStr = "UPDATE users SET name = ?, first_lastName = ?, seccond_lastName = ?, adress_street = ?, adress_number = ?, adress_floor = ?, adress_door = ?, city = ?, zip_code = ?, country = ?, phone = ?, mail = ? WHERE id = ?";
        if (user.getId() == null) {
            sqlStr = "INSERT INTO users (name,first_lastName,seccond_lastName, adress_street, adress_number, adress_floor, adress_door, city, zip_code, country, phone, mail) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            resultMsg = "User inserted";
        }

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlStr)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastName1());
            ps.setString(3, user.getLastName2());
            ps.setString(4, user.getAdress_street());
            ps.setInt(5, user.getAdress_number());
            ps.setString(6, user.getAdress_floor());
            ps.setString(7, user.getAdress_door());
            ps.setString(8, user.getCity());
            ps.setString(9, user.getZip_code());
            ps.setString(10, user.getCountry());
            ps.setString(11, user.getPhone());
            ps.setString(12, user.getMail());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0){
                System.out.println(resultMsg);
            }
            return rowsAffected > 0;

        } catch (SQLException e) {
            log.info("Can't save the information\n");
            log.error("Can't save the information\n",e);
        } catch (ClassNotFoundException e) {
            log.info("Can't save the information\n");
            log.error("Can't save the information\n",e);
        }

        return false;
    }

    public User resultSetToUserObject(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setLastName1(rs.getString("first_lastName"));
        user.setLastName2(rs.getString("seccond_lastName"));
        user.setAdress_street(rs.getString("adress_street"));
        user.setAdress_number(rs.getInt("adress_number"));
        user.setAdress_floor(rs.getString("adress_floor"));
        user.setAdress_door(rs.getString("adress_door"));
        user.setCity(rs.getString("city"));
        user.setZip_code(rs.getString("zip_code"));
        user.setCountry(rs.getString("country"));
        user.setCity(rs.getString("city"));
        user.setPhone(rs.getString("phone"));
        user.setMail(rs.getString("mail"));
        return user;
    }

}



