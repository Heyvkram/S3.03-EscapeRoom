package entities;

import utils.EnumConstants;
import lombok.Data;

@Data

public class RoomFactory {
    public Room createRoom(EnumConstants.ROOM_THEME theme, EnumConstants.GAME_LEVEL difficulty) {
        switch (theme) {
            case FICTION:
                return new FictionRoom(difficulty);
            case TERROR:
                return new TerrorRoom(difficulty);
            case FANTASY:
                return new FantasyRoom(difficulty);
            default:
                throw new IllegalArgumentException("Unsupported theme: " + theme);
        }
    }
}