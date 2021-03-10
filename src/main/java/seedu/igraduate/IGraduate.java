package seedu.igraduate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import seedu.igraduate.exception.InvalidCommandException;

public class IGraduate {
    private Storage storage;
    private ModuleList modules;

    /**
     * Main entry-point for the java.duke.IGraduate application.
     */
    public static void main(String[] args) {
        while(true) {
            String commandString = Parser.getCommand();
            try {
                Parser.parseCommand(commandString);
            } catch (InvalidCommandException exception) {
                System.out.println("Invalid input!");
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number!");
            }
        }
    }
}
