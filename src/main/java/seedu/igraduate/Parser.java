package seedu.igraduate;

import java.util.Scanner;

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

    public static String getCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Parses user input and identifies the command to be executed.
     *
     * @param line user input.
     */
    public static void parseCommand(String line) 
            throws InvalidCommandException {
        if (line.trim().length() == 0) {
            throw new InvalidCommandException();
        }
        String[] commands = line.split("\\s+");
        String command = commands[0].toLowerCase();

        switch(command) {
        case COMMAND_ADD:
            executeAddCommand(commands);
            break;
        case COMMAND_DELETE:
            executeDeleteCommand(commands);
            break;
        case COMMAND_LIST:
            executeListCommand(commands);
            break;
        case COMMAND_PROGRESS:
            executeProgressCommand(commands);
            break;
        case COMMAND_EXIT:
        // Fallthrough
            executeExitCommand(commands);
        default:
            throw new InvalidCommandException();
        }
    }

    public static void executeAddCommand(String[] commands) 
            throws InvalidCommandException {
        if (commands.length != COMMAND_ADD_LENGTH) {
            throw new InvalidCommandException();
        }

        String code = extractCodePerimeter(commands); 
        String type = extractTypePerimeter(commands);
        int credit = extractCreditPerimeter(commands);
        System.out.println(String.format("Code: %s, type: %s, credit: %d", code, type, credit));
    };

    public static void executeDeleteCommand(String[] commands) 
            throws InvalidCommandException{
        if (commands.length != COMMAND_DELETE_LENGTH) {
            throw new InvalidCommandException();
        }
        String code = extractCodePerimeter(commands);
        System.out.println(String.format("Code: %s", code));
    }; 

    public static void executeListCommand(String[] commands) 
            throws InvalidCommandException {
        if (commands.length != COMMAND_LIST_LENGTH) {
            throw new InvalidCommandException();
        }
        String scope = extractScopePerimeter(commands);
        System.out.println(String.format("Scope: %s", scope));
    };

    public static void executeProgressCommand(String[] commands) 
            throws InvalidCommandException {
        if (commands.length != COMMAND_PROGRESS_LENGTH) {
            throw new InvalidCommandException();
        }
        System.out.println("Progress");
    };

    public static void executeExitCommand(String[] commands) 
            throws InvalidCommandException {
        if (commands.length != COMMAND_EXIT_LENGTH) {
            throw new InvalidCommandException();
        }
        System.out.println("Exit");
    };


    public static String extractCodePerimeter(String[] commands) 
            throws InvalidCommandException {
        return commands[1].toUpperCase().trim();
    }

    public static String extractTypePerimeter(String[] commands) 
            throws InvalidCommandException {
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].equals("-t")) {
                String type = commands[i+1].toLowerCase().trim();
                switch(type) {
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
                return Integer.parseInt(commands[i+1]);
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