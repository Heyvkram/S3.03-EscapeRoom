package entities;

public class FantasyRoom extends Room implements RoomThemeInterface{
    // @Override
    public String getDescription() {
        return "Fantasy room with magical elements"; }

    // @Override
    public void enterRoom() {
        System.out.println("Starting fantasy room... Enter a magical world!");
    }

    /* public List<String> getFantasyDecorationItems() {
     * AÑADIR LISTA DE DECORATION ITEMS PARA "FANTASY"
     * }
     */
}

