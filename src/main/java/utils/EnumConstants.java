package utils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class EnumConstants {

    public static enum NOTIFICATIONS_TYPE {
        GENERIC(0, "Generic"),
        PERSONAL(1, "Personal"),
        SERVICE(2, "Service"),
        PAYMENT(3, "Payment"),
        GAME(4, "Game");

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
            return NOTIFICATIONS_TYPE.values().length - 1;
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
            return null; // O podríeu llençar una excepció IllegalArgumentException
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
        TERROR(0, "Terror"),
        FICTION(1, "Fiction"),
        FANTASY(2, "Fantasy");

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
            return NOTIFICATIONS_TYPE.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return NOTIFICATIONS_TYPE.values().length - 1;
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
            return null; // O podríeu llençar una excepció IllegalArgumentException
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (NOTIFICATIONS_TYPE item : EnumSet.allOf(NOTIFICATIONS_TYPE.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }

    }

    public static enum PAYMENT_TYPE {
        CREDIT_CARD(0, "Credit card"),
        BIZUM(1, "Bizum"),
        PAYPAL(2, "Paypal");

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
            return NOTIFICATIONS_TYPE.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return NOTIFICATIONS_TYPE.values().length - 1;
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
            return null; // O podríeu llençar una excepció IllegalArgumentException
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (NOTIFICATIONS_TYPE item : EnumSet.allOf(NOTIFICATIONS_TYPE.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }
    }

    public static enum GAME_LEVEL {
        EASY(0, "Easy"),
        INTERMEDIATE(1, "Intermediate"),
        HARD(2, "Hard");

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
            return NOTIFICATIONS_TYPE.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return NOTIFICATIONS_TYPE.values().length - 1;
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
            return null; // O podríeu llençar una excepció IllegalArgumentException
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (NOTIFICATIONS_TYPE item : EnumSet.allOf(NOTIFICATIONS_TYPE.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }
    }

    public static enum ROOM_STATUS {
        AVAILABLE(0, "Available"),
        NOT_AVAILABLE(1, "Not available");

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
            return NOTIFICATIONS_TYPE.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return NOTIFICATIONS_TYPE.values().length - 1;
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
            return null; // O podríeu llençar una excepció IllegalArgumentException
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (NOTIFICATIONS_TYPE item : EnumSet.allOf(NOTIFICATIONS_TYPE.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }
    }

    public static enum NOTIFICATIONS_STATUS {
        SENDED(0, "Sended"),
        CANCELED(1, "Canceled"),
        PENDING(2, "Pending");

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
            return NOTIFICATIONS_TYPE.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return NOTIFICATIONS_TYPE.values().length - 1;
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
            return null; // O podríeu llençar una excepció IllegalArgumentException
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (NOTIFICATIONS_TYPE item : EnumSet.allOf(NOTIFICATIONS_TYPE.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }
    }

    public static enum NOTIFICATION_SHIPPING_TYPE {
        SMS(0, "Sms"),
        EMAIL(1, "Email");

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
            return NOTIFICATIONS_TYPE.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return NOTIFICATIONS_TYPE.values().length - 1;
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
            return null; // O podríeu llençar una excepció IllegalArgumentException
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (NOTIFICATIONS_TYPE item : EnumSet.allOf(NOTIFICATIONS_TYPE.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }
    }

    public static enum NOTIFICATION_LEVEL {
        INFORMATIVE(0, "Informative"),
        IMPORTANT(1, "Important"),
        CRITICAL(2, "Critical"),;

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
            return NOTIFICATIONS_TYPE.values().length;
        }

        public static int getNumberMaxLevelValue() {
            return NOTIFICATIONS_TYPE.values().length - 1;
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
            return null; // O podríeu llençar una excepció IllegalArgumentException
        }

        public static String getMenuOptions() {
            StringBuilder strB = new StringBuilder();
            for (NOTIFICATIONS_TYPE item : EnumSet.allOf(NOTIFICATIONS_TYPE.class)) {
                strB.append(item.getLevelCode() + " : " + item.getDescription() + " / ");
            }
            return strB.toString();
        }
    }
}
