package entities;

import utils.Constants;
import lombok.Data;

@Data

public class RoomFactory {
    public Room createRoom(Constants.ROOM_THEME theme) {
        switch (theme) {
            case FICTION :
                return new FictionRoom();
            case TERROR :
                return new TerrorRoom();
            case FANTASY :
                return new FantasyRoom();
            default :
                throw new IllegalArgumentException("Unsupported theme: " + theme);
        }
    }
}


