import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Engine implements Runnable {
    private BufferedReader bufferedReader;

    public Engine() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        int[][] grid;
        try {
            String inputLine = this.bufferedReader.readLine();
            String[] gridSizeArray = inputLine.split(", ");
            int gridXSize = Integer.parseInt(gridSizeArray[0]);
            int gridYSize = Integer.parseInt(gridSizeArray[1]);

            grid = new int[gridXSize][gridYSize];

            this.readInput(grid, gridYSize);

            String[] coordinatesInput = bufferedReader.readLine().split(", ");
            int[] cellInput = new int[3];
            for (int i = 0; i < cellInput.length; i++) {
                cellInput[i] = Integer.parseInt(coordinatesInput[i]);
            }

            int[] cellCoordinates = new int[]{cellInput[0], cellInput[1]};
            int iterations = cellInput[2];

            for (int i = 0; i < iterations; i++) {

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Cannot parse numbers! Enter valid numbers!");
        }
    }

    private void readInput(int[][] grid, int gridYSize) throws IOException {
        for (int row = 0; row < gridYSize; row++) {
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

        int cell = grid[cellX][cellY];

        if (cell == 0) {
            //red color cell

        } else if (cell == 1) {
            //green color cell
        }
        return false;
    }

    private int getCountOfCellsByColor(int[][] grid, int[] cellCoordinates, char color) {
        int count = 0;

        

        return count;
    }
}
