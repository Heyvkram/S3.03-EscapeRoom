package entities;

public class FictionRoom extends Room implements RoomThemeInterface {
    // @Override
    public String getDescription() {
        return "Sci-Fi room with futuristic technology"; }

    // @Override
    public void enterRoom() {
        System.out.println("Starting Sci-Fi room... Welcome to the future!");
    }

    /* public List<String> getFictionDecorationItems() {
     * AÑADIR LISTA DE DECORATION ITEMS PARA "FICTION / SCI-FI"
     * }
    */
}



