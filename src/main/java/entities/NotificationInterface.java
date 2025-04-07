package entities;

public interface NotificationInterface {
    boolean send();

    void setTitle(String s);

    void setShortDescription(String s);

    void setMessage(String s);

    void setType(String descriptionFromLevelCode);

    void printNewInfoValues();

    long getId();

    String getTitle();

    String getShortDescription();

    String getMessage();

    String getType();

    String getshippingType();

    User getUser();
}
