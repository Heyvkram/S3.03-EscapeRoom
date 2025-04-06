package entities;
import utils.EnumConstants;


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

    /* public List<String> getFictionDecorationItems() {
     * AÑADIR LISTA DE DECORATION ITEMS PARA "FICTION / SCI-FI"
     * }
    */
}



