package entities;

import utils.EnumConstants;

import java.util.List;

public interface RoomThemeInterface {

    EnumConstants.ROOM_THEME getTheme();

    void setTheme(EnumConstants.ROOM_THEME theme);

    String getDescription();

    void enterRoom();

    List<Clues> clues = List.of();

    List<DecorationItem> decorationItems = List.of();
    
}
