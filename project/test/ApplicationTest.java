import exceptions.InvalidCellCoordinatesException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ApplicationTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void checkIfGridSizeIsValid() {
        Class<Engine> engineClass = Engine.class;
        int[][] invalidGrid = new int[0][0];
        try {
            Method checkIfGridSizeIsValidMethod = engineClass
                    .getDeclaredMethod("checkIfGridSizeIsValid");

            checkIfGridSizeIsValidMethod.setAccessible(true);

            exception.expect(InvalidCellCoordinatesException.class);
            checkIfGridSizeIsValidMethod.invoke(invalidGrid);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
