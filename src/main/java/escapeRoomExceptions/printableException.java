package escapeRoomExceptions;

public class printableException extends Exception{

    public printableException(String message) {
        System.out.println(message);
    }
}