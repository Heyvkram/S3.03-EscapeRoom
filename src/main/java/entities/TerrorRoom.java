package entities;

import daos.DecorationItemDAO;
import utils.EnumConstants;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    /* public List<String> getTerrorDecorationItems() {
     * AÑADIR LISTA DE DECORATION ITEMS PARA "TERROR"
     * }
     */

    public List<String> getTerrorDecorationItems() {
        DecorationItemDAO decorationItemDAO = new DecorationItemDAO();
        Optional<DecorationItem> decorationItems = null;
        try {
            decorationItems = decorationItemDAO.getDecorationItemsByTheme(EnumConstants.ROOM_THEME.TERROR.getDescription());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
