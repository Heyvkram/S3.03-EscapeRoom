package entities;

import daos.DecorationItemDAO;
import utils.EnumConstants;

import java.sql.SQLException;
import java.util.List;

public interface RoomThemeInterface {

    EnumConstants.ROOM_THEME getTheme();

    void setTheme(EnumConstants.ROOM_THEME theme);

    String getDescription();

    void enterRoom();

    List<DecorationItem> getDecorationItemsByTheme(EnumConstants.ROOM_THEME theme);

    List<Clues> getClueByTheme(EnumConstants.ROOM_THEME theme);
}

