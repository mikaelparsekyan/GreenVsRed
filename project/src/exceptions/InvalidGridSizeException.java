package exceptions;

public class InvalidGridSizeException extends Exception {
    public InvalidGridSizeException() {
        super("Invalid grid size! Enter valid grid length!");
    }
}
