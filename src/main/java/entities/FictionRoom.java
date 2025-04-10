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
        EnumConstants.ROOM_THEME theme = getTheme();
        List<DecorationItem> decorationItems = getDecorationItemsByTheme(theme);
        decorationItems.forEach(decorationItem -> System.out.println(decorationItem.getDecorationItemData()));
    }

    @Override
    public List<DecorationItem> getDecorationItemsByTheme(EnumConstants.ROOM_THEME theme) {
        DecorationItemDAO decorationItemDAO = new DecorationItemDAO();
        try {
            return decorationItemDAO.getDecorationItemsByTheme(theme);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Clues> getClueByTheme(EnumConstants.ROOM_THEME theme) {
        return List.of();
    }
}

    /* public List<String> getFictionDecorationItems() {
     * AÑADIR LISTA DE DECORATION ITEMS PARA "FICTION / SCI-FI"
     * }
    */




