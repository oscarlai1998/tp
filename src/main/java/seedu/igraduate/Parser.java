package seedu.igraduate;

import seedu.igraduate.command.Command;
import seedu.igraduate.command.AddCommand;
import seedu.igraduate.command.DeleteCommand;
import seedu.igraduate.command.ExitCommand;
import seedu.igraduate.command.ListCommand;
import seedu.igraduate.command.ProgressCommand;

import seedu.igraduate.exception.InvalidCommandException;

public class Parser {
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_PROGRESS = "progress";
    private static final String COMMAND_EXIT = "exit";
    private static final int COMMAND_ADD_LENGTH = 6;
    private static final int COMMAND_DELETE_LENGTH = 2;
    private static final int COMMAND_LIST_LENGTH = 2;
    private static final int COMMAND_PROGRESS_LENGTH = 1;
    private static final int COMMAND_EXIT_LENGTH = 1;
    
    /**
     * Parses user input and identifies the command to be executed.
     *
     * @param line user input.
     */
    public static Command parseCommand(String line) 
            throws InvalidCommandException { 
        if (line.trim().length() == 0) { 
            throw new InvalidCommandException();
        }
        String[] commands = line.split("\\s+");
        String command = commands[0].toLowerCase();

        switch (command) { 
        case COMMAND_ADD:
            return executeAddCommand(commands);
        case COMMAND_DELETE:
            return executeDeleteCommand(commands);
        case COMMAND_LIST:
            return executeListCommand(commands);
        case COMMAND_PROGRESS:
            return executeProgressCommand(commands);
        case COMMAND_EXIT:
            // Fallthrough
            return executeExitCommand(commands);
        default:
            throw new InvalidCommandException();
        }
    }

    public static Command executeAddCommand(String[] commands) 
            throws InvalidCommandException { 
        if (commands.length != COMMAND_ADD_LENGTH) { 
            throw new InvalidCommandException();
        }

        String code = extractCodePerimeter(commands); 
        String type = extractTypePerimeter(commands);
        int credit = extractCreditPerimeter(commands);
        return new AddCommand();
    }

    public static Command executeDeleteCommand(String[] commands)
            throws InvalidCommandException { 
        if (commands.length != COMMAND_DELETE_LENGTH) { 
            throw new InvalidCommandException();
        }
        String code = extractCodePerimeter(commands);
        return new DeleteCommand();
    }

    public static Command executeListCommand(String[] commands) 
            throws InvalidCommandException { 
        if (commands.length != COMMAND_LIST_LENGTH) { 
            throw new InvalidCommandException();
        }
        String scope = extractScopePerimeter(commands);
        return new ListCommand(); 
    }

    public static Command executeProgressCommand(String[] commands) 
            throws InvalidCommandException { 
        if (commands.length != COMMAND_PROGRESS_LENGTH) { 
            throw new InvalidCommandException();
        }
        return new ProgressCommand();
    }

    public static Command executeExitCommand(String[] commands) 
            throws InvalidCommandException { 
        if (commands.length != COMMAND_EXIT_LENGTH) { 
            throw new InvalidCommandException();
        }
        return new ExitCommand();
    }


    public static String extractCodePerimeter(String[] commands) 
            throws InvalidCommandException { 
        return commands[1].toUpperCase().trim();
    }

    public static String extractTypePerimeter(String[] commands) 
            throws InvalidCommandException { 
        for (int i = 0; i < commands.length; i++) { 
            if (commands[i].equals("-t")) { 
                String type = commands[i + 1].toLowerCase().trim();
                switch (type) {
                case "core":
                case "ue":
                case "math":
                case "ge":
                    return type;
                default: 
                    throw new InvalidCommandException();
                }
            }
        }
        throw new InvalidCommandException();
    }

    public static int extractCreditPerimeter(String[] commands) 
            throws NumberFormatException, InvalidCommandException {
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].equals("-c")) {
                return Integer.parseInt(commands[i + 1]);
            }
        }
        throw new InvalidCommandException();
    }

    public static String extractScopePerimeter(String[] commands) 
        throws InvalidCommandException {
        String scope = commands[1].trim().toLowerCase();
        switch (scope) {
        case "all":
        case "left":
        case "taken":
            return scope;
        default:
            throw new InvalidCommandException();
        }
    } 
}