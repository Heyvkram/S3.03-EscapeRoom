package entities;

import lombok.Data;
import utils.EntryUtils;
import utils.Themes;


import java.util.Date;

@Data
public class Room {
    private Long id;
    private String name;
    // private String theme;            ES UN ENUM
    // private String level;            ES UN ENUM
    // private boolean status;          ORIGINALMENTE ENUM, PUEDE SER UN BOOLEAN?
    private int maxPlayers;
    // private Date roomDate;           ES UNA FECHA

    public String getRoomName(){
        return name;
    }

    public void printBasicInfoValues() {
        System.out.printf("     %-5d  %-25s   ,Description: %-25s%n", getId(), getName(),
                getTheme(), getLevel(), getMaxPlayers(), getRoomDate());
    }

    /* public String getRoomStatus(){
        return status ? "Not available" : "Available";
    }
     */
}
