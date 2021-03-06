public class Main {
    public static void main(String[] args) {
        /*
            @Author - Mikael Parsekyan, 2020
            The program has unit tests, written to test different cases.
            @See /tests/ApplicationTest.java
         */

        //@Param "engine" holds the main functionality of the application.
        //The Engine's run() method starts the program.
        //The output of the application is the calculation of how many times the cell was green.
        Engine engine = new Engine();
        engine.run();
    }
}
