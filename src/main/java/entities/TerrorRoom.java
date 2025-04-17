package entities;

import daos.DecorationItemDAO;
import utils.EnumConstants;

import java.sql.SQLException;
import java.util.List;

public class TerrorRoom extends Room implements RoomThemeInterface {

    private EnumConstants.GAME_LEVEL difficulty;

    public TerrorRoom(EnumConstants.GAME_LEVEL difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public EnumConstants.ROOM_THEME getTheme() {
        return null;
    }

    @Override
    public void setTheme(EnumConstants.ROOM_THEME theme) {
    }

    public EnumConstants.GAME_LEVEL getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(EnumConstants.GAME_LEVEL difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String getDescription() {
        return "Terror room with a dark and mysterious atmosphere.\n Level: " + difficulty;
    }

    @Override
    public void enterRoom() {
        System.out.println("Starting terror room... Prepare to be scared!");
    }

}
