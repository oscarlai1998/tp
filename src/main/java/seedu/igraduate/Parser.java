package seedu.igraduate;

import java.util.Scanner;

public class Parser {
    private static final int COMMAND_ADD = 1;
    private static final int COMMAND_DELETE = 2;
    private static final int COMMAND_LIST = 3;
    private static final int COMMAND_PROGRESS = 4;
    private static final int COMMAND_EXIT = 5;

    /**
     * Parses user input and creates new instance of command to be executed.
     *
     * @param line user input.
     */
    public void parseCommand(String line) {
        int command = identifyCommand(line);
        switch (command) {
        case COMMAND_ADD:
            return; // Eventually return addcommand object
        case COMMAND_DELETE:
            return; //Eventually return deletecommand object
        case COMMAND_LIST:
            return; // Eventually return listcommand object
        case COMMAND_PROGRESS:
            return; // Eventually return progresscommand object
        default:
            return; // Eventually return exitcommand object
        }
    }

    /**
     * Parses user input and identifies the command to be executed.
     *
     * @param line user input.
     * @return integer corresponding to command type.
     */
    public int identifyCommand(String line) {
        if (line.trim().length() == 0) {
            System.out.println("Empty input!"); // Add exception class later
        }
        String[] substrings = line.split(" ");
        String command = substrings[0];
        if (command.equalsIgnoreCase("add")) {
            return COMMAND_ADD;
        }
        if (command.equalsIgnoreCase("delete")) {
            return COMMAND_DELETE;
        }
        if (command.equalsIgnoreCase("list")) {
            return COMMAND_LIST;
        }
        if (command.equalsIgnoreCase("progress")) {
            return COMMAND_PROGRESS;
        }
        // Only left with exit
        return COMMAND_EXIT;
    }
}
