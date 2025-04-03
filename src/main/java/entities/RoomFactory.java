package entities;

import utils.Themes;
import lombok.Data;

@Data

public class RoomFactory {
    public static Room createRoom(String roomType, Themes theme) {
        if (roomType.equalsIgnoreCase("Sci-FI Room")) {
            return new FictionRoom(theme);
        } else if (roomType.equalsIgnoreCase("Terror Room")) {
            return new TerrorRoom(theme);
        } else if (roomType.equalsIgnoreCase("Fantasy Room")) {
            return new FantasyRoom(theme);
        } else {
            throw new UnsupportedOperationException("Unsupported room type");
        }
    }
}

