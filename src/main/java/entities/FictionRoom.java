package entities;
import daos.DecorationItemDAO;
import utils.EnumConstants;

import java.sql.SQLException;
import java.util.List;


public class FictionRoom extends Room implements RoomThemeInterface {

    private EnumConstants.GAME_LEVEL difficulty;

    public FictionRoom(EnumConstants.GAME_LEVEL difficulty) {
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
        return "Sci-Fi room with futuristic technology.\n Level: " + difficulty;
    }
    @Override
    public void enterRoom() {
        System.out.println("Starting Sci-Fi room... Welcome to the future!");
    }

    public List<DecorationItem> getTerrorDecorationItems() {
        DecorationItemDAO decorationItemDAO = new DecorationItemDAO();
        List<DecorationItem> decorationItems = null;
        try {
            decorationItems = decorationItemDAO.getDecorationsItemsBy(EnumConstants.ROOM_THEME.FICTION.getDescription(),"decoration_item_theme");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return decorationItems;
    }

}



