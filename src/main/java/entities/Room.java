package entities;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Room {
    private Long roomId;
    private String roomName;
    private String roomTheme;
    private String roomLevel;
    private String roomStatus;
    private int roomMaxPlayers;
    private LocalDateTime roomDate;
    private double price;
    private double totalCostValue;

    private List<Clues> clues;
    private List<DecorationItem> decorationItems;

    public void printBasicInfoValues() {
        System.out.printf("     %-5d %-30s  |  Title: %-30s  |  Level: %-15s  |  Status: %-15s  |  Max Players: %-10d   |   Price: %.2f   |   Cost: %.2f   |   Date: %-10s%n",
                getRoomId(), getRoomName(), getRoomTheme(), getRoomLevel(), getRoomStatus(), getRoomMaxPlayers(), getPrice(), getTotalCostValue(), getRoomDate());
    }

}
