import exceptions.InvalidCellCoordinatesException;
import exceptions.InvalidGridSizeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine implements Runnable {
    private final Input input;

    public Engine() {
        this.input = new Input(new BufferedReader(new InputStreamReader(System.in)));
    }

    @Override
    public void run() {
        int[][] grid;
        try {
            //Split the input by ", " and parse it to integer.
            //@throws NumberFormatException if we enter invalid number.
            String[] gridSizeArray = input.readLineAsArray(", ");
            int gridWidth = Integer.parseInt(gridSizeArray[0]);
            int gridHeight = Integer.parseInt(gridSizeArray[1]);

            //Create a new instance of two dimension array, with the entered size.
            grid = new int[gridHeight][gridWidth];

            //Throw exception if entered grid size is invalid
            checkIfGridSizeIsValid(grid);

            //Fill the grid with the entered values.
            this.fillGrid(grid);

            String[] coordinatesInput = input.readLineAsArray(", ");

            int[] cellInput = new int[3];
            for (int i = 0; i < cellInput.length; i++) {
                //Parse the last line from the input.
                cellInput[i] = Integer.parseInt(coordinatesInput[i]);
            }

            //  Assign the cell coordinates in array.
            //  [0] is X coordinate length,
            //  [1] is Y coordinate length
            Cell cell = new Cell(cellInput[1], cellInput[0]);
            int iterations = cellInput[2];
            int greenCellCounter = 0;

            //Throw exception if entered cell coordinates are invalid.
            checkIfCellIsInBounds(grid, cell);

            //First check if the cell in zero generation is green
            if (isCellGreen(grid, cell)) {
                greenCellCounter++;
            }

            //Iterate as many times as we entered in the console.
            for (int i = 0; i < iterations; i++) {
                int[][] newGrid = new int[gridHeight][gridWidth];
                for (int r = 0; r < grid.length; r++) {
                    for (int j = 0; j < grid[0].length; j++) {
                        //Check if cell value should be changed to the opposite.
                        if (checkIfCellShouldChangeValue(grid, new Cell(r, j))) {
                            //Get current cell color.
                            int gridColorCell = grid[r][j];
                            //If cell color is red, make it green,
                            //or else make it red.
                            if (gridColorCell == 0) {
                                newGrid[r][j] = 1;
                            } else {
                                newGrid[r][j] = 0;
                            }
                        } else {
                            //If the cell value should not be changed, just save the current value.
                            newGrid[r][j] = grid[r][j];
                        }
                    }
                }
                //Assign the new generation in the grid.
                grid = newGrid;
                printGrid(grid);
                System.out.println("---------------------");

                //If the cell in new generation is green, we increment the counter.
                if (isCellGreen(grid, cell)) {
                    greenCellCounter++;
                }
            }
            //Print the count of green cells.
            System.out.println(greenCellCounter);

        } catch (IOException e) {
            //Handle IOException and print error message.
            System.err.println("Something went wrong!");
        } catch (NumberFormatException e) {
            //Handle NumberFormatException and print number exception message.
            System.err.println("Cannot parse numbers! Enter valid numbers!");
        } catch (InvalidCellCoordinatesException | InvalidGridSizeException e) {
            //Print invalid input errors.
            System.err.println(e.getMessage());
        }
    }

    private void checkIfGridSizeIsValid(int[][] grid) throws InvalidGridSizeException {
        //This method throws exception if grid's size is valid.
        boolean isValid = grid.length > 0 && grid[0].length > 0;

        if (!isValid) {
            throw new InvalidGridSizeException();
        }
    }

    private void checkIfCellIsInBounds(int[][] grid, Cell cell) throws InvalidCellCoordinatesException {
        //This method throws exception if cell is not in the current grid's bounds.
        boolean isValid = (cell.getX() >= 0 && cell.getX() < grid.length) ||
                (cell.getY() >= 0 && cell.getY() < grid[0].length);

        if (!isValid) {
            throw new InvalidCellCoordinatesException();
        }
    }

    private boolean isCellGreen(int[][] grid, Cell cell) {
        //This method returns if cell's color is 1(green).
        int cellX = cell.getX();
        int cellY = cell.getY();

        return grid[cellX][cellY] == 1;
    }

    private void fillGrid(int[][] grid) throws IOException {
        //This method fills the grid with the input values.

        for (int row = 0; row < grid.length; row++) {
            //Split the grid's row by "" and assign the values in array.
            String[] rowArray = input.readLineAsArray("");

            for (int col = 0; col < rowArray.length; col++) {
                //Parse the string values to integer.
                grid[row][col] = Integer.parseInt(rowArray[col]);
            }
        }
    }

    private void printGrid(int[][] grid) {
        //Prints the grid for testing.
        for (int[] row : grid) {
            for (int element : row) {
                System.out.print(element);
            }
            System.out.println();
        }
    }

    private boolean checkIfCellShouldChangeValue(int[][] grid, Cell cell) {
        /* This method returns if current cell should change the value to the opposite.
            Example:
            0 => 1,
            1 => 0
         */
        int cellX = cell.getX();
        int cellY = cell.getY();

        int countOfNeighboursGreenCells = getCountOfNeighboursGreenCells(grid, cell);

        int cellColor = grid[cellX][cellY];
        //Returns true if cell's color is red, and the count of green neighbour cells is 3 or 6.
        if (cellColor == 0 && (countOfNeighboursGreenCells == 3 || countOfNeighboursGreenCells == 6)) {
            return true;
        }
        //Returns if the green cell's color should be changed.
        return cellColor == 1 && (countOfNeighboursGreenCells != 3 &&
                countOfNeighboursGreenCells != 6 && countOfNeighboursGreenCells != 2);
    }

    private int getCountOfNeighboursGreenCells(int[][] grid, Cell cell) {
        int cellX = cell.getX();
        int cellY = cell.getY();

        //This method returns the number of green neighbour cells.
        int greenCellCounter = 0;

        //This loop iterates the neighbour cells
        for (int x = cellX - 1; x <= cellX + 1; x++) {
            for (int y = cellY - 1; y <= cellY + 1; y++) {
                if ((x >= 0 && y >= 0 && x < grid.length && y < grid[0].length) &&
                        (x != cellX || y != cellY)) {
                    if (grid[x][y] == 1) {
                        //Increment the greenCellCounter if cell's neighbour is green colored.
                        greenCellCounter++;
                    }
                }
            }
        }
        return greenCellCounter;
    }
}
