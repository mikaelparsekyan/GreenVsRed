import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertTrue;

public class ApplicationTest {
    @Test
    public void assertIfInvalidGridSizeThrowsError() {
        //Assert true if invalid grid size is entered.
        assertTrue(isInvalidGridThrowsError());
    }

    private boolean isInvalidGridThrowsError() {
        Engine engine = new Engine();
        int[][] invalidGrid = new int[0][0];
        try {
            Method checkIfGridSizeIsValidMethod = engine
                    .getClass()
                    .getDeclaredMethod("checkIfGridSizeIsValid", int[][].class);

            checkIfGridSizeIsValidMethod.setAccessible(true);

            checkIfGridSizeIsValidMethod.invoke(engine, new Object[]{invalidGrid});
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return true;
        }
        return false;
    }

    @Test
    public void assertIfInvalidCellCoordinatesThrowsError() {
        //Assert true if cell has invalid coordinates.
        assertTrue(isInvalidCellCoordinatesThrowsError());
    }

    private boolean isInvalidCellCoordinatesThrowsError() {
        Engine engine = new Engine();
        int[][] grid = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Cell invalidCell = new Cell(-1, -1);

        try {
            Method checkIfGridSizeIsValidMethod = engine
                    .getClass()
                    .getDeclaredMethod("checkIfCellShouldChangeValue", int[][].class, Cell.class);

            checkIfGridSizeIsValidMethod.setAccessible(true);

            checkIfGridSizeIsValidMethod.invoke(engine, grid, invalidCell);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return true;
        }
        return false;
    }
}
