import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine implements Runnable {
    private final BufferedReader bufferedReader;

    public Engine() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        int[][] grid;
        try {
            //Read the console input.
            String inputLine = this.bufferedReader.readLine();

            //Split the input by ", " and parse it to string.
            //@throws NumberFormatException if we enter invalid number.
            String[] gridSizeArray = inputLine.split(", ");
            int gridXSize = Integer.parseInt(gridSizeArray[0]);
            int gridYSize = Integer.parseInt(gridSizeArray[1]);

            //Create a new instance of two dimension array, with the entered size.
            grid = new int[gridXSize][gridYSize];

            //Fill the grid with the entered values.
            this.fillGrid(grid);

            String[] coordinatesInput = bufferedReader.readLine().split(", ");
            int[] cellInput = new int[3];
            for (int i = 0; i < cellInput.length; i++) {
                cellInput[i] = Integer.parseInt(coordinatesInput[i]);
            }

            //Assign the cell coordinates in array.
            //  [0] is X coordinate,
            //  [1] is Y coordinate
            int[] cellCoordinates = new int[]{cellInput[0], cellInput[1]};
            int iterations = cellInput[2];
            int greenCounter = 0;

            //Iterate as many times as we entered in the console.
            for (int i = 0; i < iterations; i++) {
                int[][] newGrid = new int[gridXSize][gridYSize];
                for (int r = 0; r < grid.length; r++) {
                    for (int j = 0; j < grid[0].length; j++) {
                        if (checkIfCellShouldChangeValue(grid, new int[]{r, j})) {
                            int gridColorCell = grid[r][j];
                            if (gridColorCell == 0) {
                                newGrid[r][j] = 1;
                            } else {
                                newGrid[r][j] = 0;
                            }
                        } else {
                            newGrid[r][j] = grid[r][j];
                        }
                    }
                }
                //Assign the new generation in the grid.
                grid = newGrid;

                //If the cell in new generation is green, we increment the counter.
                if (grid[cellCoordinates[0]][cellCoordinates[1]] == 1) {
                    greenCounter++;
                }
            }
            //Print the counter
            System.out.println(greenCounter);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Cannot parse numbers! Enter valid numbers!");
        }
    }

    private void fillGrid(int[][] grid) throws IOException {
        for (int row = 0; row < grid[0].length; row++) {
            String rowString = bufferedReader.readLine();
            String[] rowArray = rowString.split("");

            for (int col = 0; col < rowArray.length; col++) {
                grid[row][col] = Integer.parseInt(rowArray[col]);
            }
        }
    }

    private void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int element : row) {
                System.out.print(element);
            }
            System.out.println();
        }
    }

    private boolean checkIfCellShouldChangeValue(int[][] grid, int[] cellCoordinates) {
        int cellX = cellCoordinates[0];
        int cellY = cellCoordinates[1];

        int cellColor = grid[cellX][cellY];

        int countOfNeighboursGreenCells = getCountOfNeighboursGreenCells(grid, cellX, cellY);

        if (cellColor == 0 && (countOfNeighboursGreenCells == 3 || countOfNeighboursGreenCells == 6)) {
            return true;
        }
        return cellColor == 1 && (countOfNeighboursGreenCells != 3 &&
                countOfNeighboursGreenCells != 6 && countOfNeighboursGreenCells != 2);
    }

    private int getCountOfNeighboursGreenCells(int[][] grid, int cellX, int cellY) {
        //This method returns the number of green neighbour cells.
        int greenCellCounter = 0;

        for (int x = cellX - 1; x <= cellX + 1; x++) {
            for (int y = cellY - 1; y <= cellY + 1; y++) {
                if ((x >= 0 && y >= 0 && x < grid.length && y < grid[0].length) &&
                        (x != cellX || y != cellY)) {
                    if (grid[x][y] == 1) {
                        greenCellCounter++;
                    }
                }
            }
        }
        return greenCellCounter;
    }
}
