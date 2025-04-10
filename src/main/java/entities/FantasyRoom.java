package entities;

import daos.ClueDAO;
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
    /*@Override
    public List<Clues> getCluesByTheme(EnumConstants.ROOM_THEME theme) {
        ClueDAO clueDAO = new ClueDAO();
        try {
            return clueDAO.getClueByTheme(theme);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public List<Clues> getClueByTheme(EnumConstants.ROOM_THEME theme) {
        return List.of();
    }
}


    /*
     * AÑADIR LISTA DE DECORATION ITEMS PARA "FANTASY"
     * }
     */


