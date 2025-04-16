package entities;

import daos.DecorationItemDAO;
import utils.EnumConstants;

import java.sql.SQLException;
import java.util.List;

public class FantasyRoom extends Room implements RoomThemeInterface{

    private EnumConstants.GAME_LEVEL difficulty;

    public FantasyRoom(EnumConstants.GAME_LEVEL difficulty) {
        this.difficulty = difficulty;
    }
    @Override
    public String getDescription() {
        return "Fantasy room with magical elements.\n Level: " + difficulty;
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
    public void enterRoom() {
        System.out.println("Starting fantasy room... Enter a magical world!");
    }

    public List<DecorationItem> getTerrorDecorationItems() {
        DecorationItemDAO decorationItemDAO = new DecorationItemDAO();
        List<DecorationItem> decorationItems = null;
        try {
            decorationItems = decorationItemDAO.getDecorationsItemsBy(EnumConstants.ROOM_THEME.FANTASY.getDescription(),"decoration_item_theme");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return decorationItems;
    }

}

