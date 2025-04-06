package entities;

import lombok.Data;
import utils.EnumConstants;
import java.time.LocalDateTime;

@Data

public class Room {
    private Long roomId;
    private String roomName;
    private EnumConstants.ROOM_THEME roomTheme;
    private EnumConstants.GAME_LEVEL roomLevel;
    private EnumConstants.ROOM_STATUS roomStatus;      // ORIGINALMENTE ENUM, PUEDE SER UN BOOLEAN?
    private int roomMaxPlayers;
    private LocalDateTime roomDate;

    public String getRoomName(){
        return roomName;
    }

    public void printBasicInfoValues() {
        System.out.printf("Room list: " + getRoomId() + " " + getRoomName()
                + " " + getRoomTheme() + " " +  getRoomLevel()  + " " +  getRoomStatus() + " " +  getRoomMaxPlayers()  + " " +  getRoomDate());
    }

    /* public String getRoomStatus(){
        return status ? "Not available" : "Available";
    }
     */
}
