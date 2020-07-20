package exceptions;

public class InvalidCellCoordinatesException extends Exception{
    public InvalidCellCoordinatesException() {
        super("Invalid cell coordinates! The cell must be in the grid bounds!");
    }
}
