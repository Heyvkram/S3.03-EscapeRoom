package entities;

import lombok.Data;
import utils.EnumConstants;
import java.time.LocalDateTime;

@Data

public class Room {
    private Long roomId;
    private String roomName;
    private String roomTheme;
    private String roomLevel;
    private String roomStatus;
    private int roomMaxPlayers;
    private LocalDateTime roomDate;

    public String getRoomName(){
        return roomName;
    }

    public void printBasicInfoValues() {
        System.out.printf("     %-5d %-30s  |  Title: %-30s  |  Level: %-15s  |  Status: %-15s  |  Max Players: %-10d  |  Date: %-10s%n",
                getRoomId(), getRoomName(), getRoomTheme(), getRoomLevel(), getRoomStatus(), getRoomMaxPlayers(), getRoomDate());
    }
}
