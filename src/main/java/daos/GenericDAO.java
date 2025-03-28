package daos;

import utils.DdBbConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public abstract class GenericDAO {

    String TABLE_NAME = "";

    final Connection getConnection() throws SQLException, ClassNotFoundException {
        DdBbConnection ddBbConnection = new DdBbConnection();
        return ddBbConnection.getConnection();
    }

}
