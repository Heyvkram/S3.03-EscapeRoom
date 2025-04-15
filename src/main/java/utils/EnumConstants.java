package utils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class EnumConstants {

    public static enum NOTIFICATIONS_TYPE {
        GENERAL(1, "General"),
        GAME(2, "Game");

        private final int levelCode;
        private final String description;

        NOTIFICATIONS_TYPE(int levelCode, String description) {
            this.levelCode = levelCode;
            this.description = description;
        }

        public int getLevelCode() {
            return levelCode;
        }

        public String getDescription() {
            return description;
        }

        public static int getNumberOfItems() {
            return NOTIFICATIONS_TYPE.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return NOTIFICATIONS_TYPE.values().length;
        }

        public static List<Integer> getLevelCodes() {
            List<Integer> levelCodes = new ArrayList<>();
            for (NOTIFICATIONS_TYPE type : NOTIFICATIONS_TYPE.values()) {
                levelCodes.add(type.getLevelCode());
            }
            return levelCodes;
        }

        public static String getDescriptionFromLevelCode(int levelCode) {
            for (NOTIFICATIONS_TYPE type : NOTIFICATIONS_TYPE.values()) {
                if (type.getLevelCode() == levelCode) {
                    return type.getDescription();
                }
            }
            return null;
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (NOTIFICATIONS_TYPE item : EnumSet.allOf(NOTIFICATIONS_TYPE.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }

    }

    public static enum ROOM_THEME {
        TERROR(1, "Terror"),
        FICTION(2, "Fiction"),
        FANTASY(3, "Fantasy");

        private final int levelCode;
        private final String description;

        ROOM_THEME(int levelCode, String description) {
            this.levelCode = levelCode;
            this.description = description;
        }

        public int getLevelCode() {
            return levelCode;
        }

        public String getDescription() {
            return description;
        }

        public static int getNumberOfItems() {
            return ROOM_THEME.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return ROOM_THEME.values().length;
        }

        public static List<Integer> getLevelCodes() {
            List<Integer> levelCodes = new ArrayList<>();
            for (ROOM_THEME type : ROOM_THEME.values()) {
                levelCodes.add(type.getLevelCode());
            }
            return levelCodes;
        }

        public static String getDescriptionFromLevelCode(int levelCode) {
            for (ROOM_THEME type : ROOM_THEME.values()) {
                if (type.getLevelCode() == levelCode) {
                    return type.getDescription();
                }
            }
            return null;
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (ROOM_THEME item : EnumSet.allOf(ROOM_THEME.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }

    }

    public static enum PAYMENT_TYPE {
        CREDIT_CARD(1, "Credit card"),
        BIZUM(2, "Bizum"),
        PAYPAL(3, "Paypal");

        private final int levelCode;
        private final String description;

        PAYMENT_TYPE(int levelCode, String description) {
            this.levelCode = levelCode;
            this.description = description;
        }

        public int getLevelCode() {
            return levelCode;
        }

        public String getDescription() {
            return description;
        }

        public static int getNumberOfItems() {
            return PAYMENT_TYPE.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return PAYMENT_TYPE.values().length;
        }

        public static List<Integer> getLevelCodes() {
            List<Integer> levelCodes = new ArrayList<>();
            for (PAYMENT_TYPE type : PAYMENT_TYPE.values()) {
                levelCodes.add(type.getLevelCode());
            }
            return levelCodes;
        }

        public static String getDescriptionFromLevelCode(int levelCode) {
            for (PAYMENT_TYPE type : PAYMENT_TYPE.values()) {
                if (type.getLevelCode() == levelCode) {
                    return type.getDescription();
                }
            }
            return null;
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (PAYMENT_TYPE item : EnumSet.allOf(PAYMENT_TYPE.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }
    }

    public static enum GAME_LEVEL {
        EASY(1, "Easy"),
        INTERMEDIATE(2, "Intermediate"),
        HARD(3, "Hard");

        private final int levelCode;
        private final String description;

        GAME_LEVEL(int levelCode, String description) {
            this.levelCode = levelCode;
            this.description = description;
        }

        public int getLevelCode() {
            return levelCode;
        }

        public String getDescription() {
            return description;
        }

        public static int getNumberOfItems() {
            return GAME_LEVEL.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return GAME_LEVEL.values().length;
        }

        public static List<Integer> getLevelCodes() {
            List<Integer> levelCodes = new ArrayList<>();
            for (GAME_LEVEL type : GAME_LEVEL.values()) {
                levelCodes.add(type.getLevelCode());
            }
            return levelCodes;
        }

        public static String getDescriptionFromLevelCode(int levelCode) {
            for (GAME_LEVEL type : GAME_LEVEL.values()) {
                if (type.getLevelCode() == levelCode) {
                    return type.getDescription();
                }
            }
            return null;
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (GAME_LEVEL item : EnumSet.allOf(GAME_LEVEL.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }
    }

    public static enum ROOM_STATUS {
        AVAILABLE(1, "Available"),
        NOT_AVAILABLE(2, "Not available");

        private final int levelCode;
        private final String description;

        ROOM_STATUS(int levelCode, String description) {
            this.levelCode = levelCode;
            this.description = description;
        }

        public int getLevelCode() {
            return levelCode;
        }

        public String getDescription() {
            return description;
        }

        public static int getNumberOfItems() {
            return ROOM_STATUS.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return ROOM_STATUS.values().length;
        }

        public static List<Integer> getLevelCodes() {
            List<Integer> levelCodes = new ArrayList<>();
            for (ROOM_STATUS type : ROOM_STATUS.values()) {
                levelCodes.add(type.getLevelCode());
            }
            return levelCodes;
        }

        public static String getDescriptionFromLevelCode(int levelCode) {
            for (ROOM_STATUS type : ROOM_STATUS.values()) {
                if (type.getLevelCode() == levelCode) {
                    return type.getDescription();
                }
            }
            return null;
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (ROOM_STATUS item : EnumSet.allOf(ROOM_STATUS.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }
    }

    public static enum NOTIFICATIONS_STATUS {
        SENDED(1, "Sended"),
        CANCELED(2, "Canceled"),
        PENDING(3, "Pending");

        private final int levelCode;
        private final String description;

        NOTIFICATIONS_STATUS(int levelCode, String description) {
            this.levelCode = levelCode;
            this.description = description;
        }

        public int getLevelCode() {
            return levelCode;
        }

        public String getDescription() {
            return description;
        }

        public static int getNumberOfItems() {
            return NOTIFICATIONS_STATUS.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return NOTIFICATIONS_STATUS.values().length;
        }

        public static List<Integer> getLevelCodes() {
            List<Integer> levelCodes = new ArrayList<>();
            for (NOTIFICATIONS_STATUS type : NOTIFICATIONS_STATUS.values()) {
                levelCodes.add(type.getLevelCode());
            }
            return levelCodes;
        }

        public static String getDescriptionFromLevelCode(int levelCode) {
            for (NOTIFICATIONS_STATUS type : NOTIFICATIONS_STATUS.values()) {
                if (type.getLevelCode() == levelCode) {
                    return type.getDescription();
                }
            }
            return null;
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (NOTIFICATIONS_STATUS item : EnumSet.allOf(NOTIFICATIONS_STATUS.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }
    }

    public static enum NOTIFICATION_SHIPPING_TYPE {
        SMS(1, "Sms"),
        EMAIL(2, "Email"),
        EMPTY(3, "-");

        private final int levelCode;
        private final String description;

        NOTIFICATION_SHIPPING_TYPE(int levelCode, String description) {
            this.levelCode = levelCode;
            this.description = description;
        }

        public int getLevelCode() {
            return levelCode;
        }

        public String getDescription() {
            return description;
        }

        public static int getNumberOfItems() {
            return NOTIFICATION_SHIPPING_TYPE.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return NOTIFICATION_SHIPPING_TYPE.values().length;
        }

        public static List<Integer> getLevelCodes() {
            List<Integer> levelCodes = new ArrayList<>();
            for (NOTIFICATION_SHIPPING_TYPE type : NOTIFICATION_SHIPPING_TYPE.values()) {
                levelCodes.add(type.getLevelCode());
            }
            return levelCodes;
        }

        public static String getDescriptionFromLevelCode(int levelCode) {
            for (NOTIFICATION_SHIPPING_TYPE type : NOTIFICATION_SHIPPING_TYPE.values()) {
                if (type.getLevelCode() == levelCode) {
                    return type.getDescription();
                }
            }
            return null;
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (NOTIFICATION_SHIPPING_TYPE item : EnumSet.allOf(NOTIFICATION_SHIPPING_TYPE.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }
    }

    public static enum NOTIFICATION_LEVEL {
        INFORMATIVE(1, "Informative"),
        IMPORTANT(2, "Important"),
        CRITICAL(3, "Critical"),
        ;

        private final int levelCode;
        private final String description;

        NOTIFICATION_LEVEL(int levelCode, String description) {
            this.levelCode = levelCode;
            this.description = description;
        }

        public int getLevelCode() {
            return levelCode;
        }

        public String getDescription() {
            return description;
        }

        public static int getNumberOfItems() {
            return NOTIFICATION_LEVEL.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return NOTIFICATION_LEVEL.values().length;
        }

        public static List<Integer> getLevelCodes() {
            List<Integer> levelCodes = new ArrayList<>();
            for (NOTIFICATION_LEVEL type : NOTIFICATION_LEVEL.values()) {
                levelCodes.add(type.getLevelCode());
            }
            return levelCodes;
        }

        public static String getDescriptionFromLevelCode(int levelCode) {
            for (NOTIFICATION_LEVEL type : NOTIFICATION_LEVEL.values()) {
                if (type.getLevelCode() == levelCode) {
                    return type.getDescription();
                }
            }
            return null;
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (NOTIFICATION_LEVEL item : EnumSet.allOf(NOTIFICATION_LEVEL.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }
    }
}