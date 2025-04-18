package escapeRoomExceptions;

public class PrintableException extends Exception{

    public PrintableException(String message) {
        System.out.println(message);
    }
}