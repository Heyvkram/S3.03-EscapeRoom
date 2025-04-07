package entities;

import utils.EnumConstants;

public interface RoomThemeInterface {

    EnumConstants.ROOM_THEME getTheme();

    void setTheme(EnumConstants.ROOM_THEME theme);

    String getDescription();
    void enterRoom();
}
