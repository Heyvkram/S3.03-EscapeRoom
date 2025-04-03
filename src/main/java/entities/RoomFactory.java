package entities;

import utils.Themes;
import lombok.Data;

@Data

public class RoomFactory {
    public Room createRoom(Themes theme) {
        switch (theme) {
            case FICTION :
                return new FictionRoom();
            case TERROR :
                return new TerrorRoom();
            case FANTASY :
                return new FantasyRoom();
            default :
                throw new IllegalArgumentException("Not valid theme: " + theme);
        }
    }
}


