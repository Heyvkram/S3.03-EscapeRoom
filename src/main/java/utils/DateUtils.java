package utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    static final String dateFormat_dd_MM_YYYY_HMS = "dd/MM/YYYY HH:mm:ss";

    public String localDateToString_dd_MM_YYYY_HMS(LocalDateTime localDate){
        if(localDate==null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat_dd_MM_YYYY_HMS);
        return localDate.format(formatter);
    }

}
