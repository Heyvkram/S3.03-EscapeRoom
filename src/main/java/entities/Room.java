package entities;

import lombok.Data;
import utils.Constants;
import java.time.LocalDateTime;

@Data

public class Room {
    private Long roomId;
    private String roomName;
    private Constants.ROOM_THEME roomTheme;
    private Constants.GAME_LEVEL roomLevel;
    private Constants.ROOM_STATUS roomStatus;      // ORIGINALMENTE ENUM, PUEDE SER UN BOOLEAN?
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
