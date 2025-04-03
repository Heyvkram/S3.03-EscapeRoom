package entities;

public class TerrorRoom extends Room implements RoomThemeInterface {
    @Override
    public String getDescription() {
        return "Terror room with a dark and mysterious atmosphere"; }

    @Override
    public void enterRoom() {
        System.out.println("Starting terror room... Prepare to be scared!");
    }

    /* public List<String> getTerrorDecorationItems() {
     * AÑADIR LISTA DE DECORATION ITEMS PARA "TERROR"
     * }
     */

}
