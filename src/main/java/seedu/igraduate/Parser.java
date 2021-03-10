package seedu.igraduate;

import seedu.igraduate.command.Command;
import seedu.igraduate.command.AddCommand;
import seedu.igraduate.command.DeleteCommand;
import seedu.igraduate.command.ListCommand;
import seedu.igraduate.command.ProgressCommand;

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
    public static Command parseCommand(String line) throws Exception {
        int command = identifyCommand(line);
        switch (command) {
        case COMMAND_ADD:
            return new AddCommand(); // Eventually return addcommand object
        case COMMAND_DELETE:
            return new DeleteCommand(); //Eventually return deletecommand object
        case COMMAND_LIST:
            return new ListCommand(); // Eventually return listcommand object
        case COMMAND_PROGRESS:
            return new ProgressCommand(); // Eventually return progresscommand object
        default:
            throw new Exception();
        }
    }

    /**
     * Parses user input and identifies the command to be executed.
     *
     * @param line user input.
     * @return integer corresponding to command type.
     */
    public static int identifyCommand(String line) {
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
