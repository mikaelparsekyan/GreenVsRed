import java.io.BufferedReader;
import java.io.IOException;

public class Input {
    private final BufferedReader reader;

    public Input(BufferedReader reader) {
        this.reader = reader;
    }

    public String[] readLineAsArray(String split) throws IOException {
        return reader.readLine().split(split);
    }
}
