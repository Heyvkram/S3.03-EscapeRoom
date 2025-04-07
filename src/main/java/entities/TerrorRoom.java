package entities;

import utils.EnumConstants;

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

}
