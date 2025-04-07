package utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DdBbUtils {
    public static PreparedStatement preparareUpdate(PreparedStatement ps, ArrayList<Object> parameterList) throws SQLException {
        int indexParam = 1;
        for(Object param : parameterList){
            if(param instanceof String){
                ps.setString(indexParam, String.valueOf(param));
            }else if(param instanceof Long){
                ps.setLong(indexParam, Long.parseLong((String)param));
            }else if(param instanceof Integer){
                ps.setInt(indexParam, Integer.parseInt((String) param));
            }
            indexParam++;
        }
        return null;
    }
}
