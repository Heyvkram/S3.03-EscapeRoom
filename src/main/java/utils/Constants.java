package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class Constants {

    public static enum NOTIFICATIONS_TYPE {
        GENERIC (0),
        PERSONAL (1),
        SERVICE (2),
        PAYMENT (3),
        GAME (4);

        private final int levelCode;

        NOTIFICATIONS_TYPE(int levelCode) {
            this.levelCode = levelCode;
        }

        public int getLevelCode() {
            return levelCode;
        }

        public static int getNumberOfItems(){
            return NOTIFICATIONS_TYPE.values().length;
        }

        public static int getNumberMaxLevelValue(){
            return NOTIFICATIONS_TYPE.values().length-1;
        }

        public static List<Integer> getLevelCodes(){
            List<Integer> levelCodes = new ArrayList<>();
            for (NOTIFICATIONS_TYPE type : NOTIFICATIONS_TYPE.values()) {
                levelCodes.add(type.getLevelCode());
            }
            return levelCodes;
        }

        public static String fromLevelCode(int levelCode) {
            for (NOTIFICATIONS_TYPE type : NOTIFICATIONS_TYPE.values()) {
                if (type.getLevelCode() == levelCode) {
                    return type.name();
                }
            }
            return null; // O podríeu llençar una excepció IllegalArgumentException
        }


        public static String getMenuOptions(){
            StringBuilder strB=new StringBuilder();
            for (NOTIFICATIONS_TYPE item : EnumSet.allOf(NOTIFICATIONS_TYPE.class)) {
                strB.append(item.levelCode + " : " + item.name() + " / ");
            }
            return strB.toString();
        }
    }




    public static enum PAYMENT_TYPE {
        CREDIT_CARD,
        BIZUM,
        PAYPAL
    }

    public static enum ROOM_THEME {
        TERROR,
        FICTION,
        FANTASY
    }

    public static enum GAME_LEVEL {
        EASY,
        INTERMEDIATE,
        HARD
    }

    public static enum ROOM_STATUS {
        AVAILABLE,
        NOT_AVAILABLE
    }

}
