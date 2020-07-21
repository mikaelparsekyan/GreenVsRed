package exceptions;

public class InvalidInputArgumentsException extends Exception{
    public InvalidInputArgumentsException() {
        super("Invalid number of arguments!");
    }
}
