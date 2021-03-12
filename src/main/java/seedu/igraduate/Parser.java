package seedu.igraduate;

import seedu.igraduate.command.Command;
import seedu.igraduate.command.AddCommand;
import seedu.igraduate.command.DeleteCommand;
import seedu.igraduate.command.DoneCommand;
import seedu.igraduate.command.ExitCommand;
import seedu.igraduate.command.ListCommand;
import seedu.igraduate.command.ProgressCommand;

import seedu.igraduate.exception.IncorrectParameterCountException;
import seedu.igraduate.exception.InvalidCommandException;

public class Parser {
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_PROGRESS = "progress";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_EXIT = "exit";

    private static final int COMMAND_ADD_LENGTH = 6;
    private static final int COMMAND_DELETE_LENGTH = 2;
    private static final int COMMAND_LIST_LENGTH = 2;
    private static final int COMMAND_PROGRESS_LENGTH = 1;
    private static final int COMMAND_DONE_LENGTH = 2;
    private static final int COMMAND_EXIT_LENGTH = 1;

    /**
     * Parses user input and identifies the command to be executed.
     *
     * @param line user input.
     */
    public static Command parseCommand(String line) 
            throws InvalidCommandException, IncorrectParameterCountException {
        if (line.trim().length() == 0) {
            throw new InvalidCommandException();
        }

        // Splits into command + name, command flags
        String[] commands = line.split("\\s+(?=-)", 2);
        String[] commandParameters = commands[0].split("\\s+", 2);
        String command = commandParameters[0].toLowerCase();

        switch (command) {
        case COMMAND_ADD:
            String[] addCommandFlags = getCommandFlag(commands);
            return executeAddCommand(commandParameters, addCommandFlags);
        case COMMAND_DELETE:
            return executeDeleteCommand(commandParameters);
        case COMMAND_LIST:
            return executeListCommand(commandParameters);
        case COMMAND_PROGRESS:
            return executeProgressCommand(commandParameters);
        case COMMAND_DONE:
            String[] doneCommandFlags = getCommandFlag(commands);
            return executeDoneCommand(doneCommandFlags);
        case COMMAND_EXIT:
            return executeExitCommand(commandParameters);
        default:
            throw new InvalidCommandException();
        }
    }

    private static String[] getCommandFlag(String[] commands) {
        if (commands.length < 2) {
            return new String[] { null };
        }
        return commands[1].split("\\s+");
    }

    /**
     * Extracts relevant parameters and creates new instance of AddCommand class to
     * execute.
     *
     * @param commands user input split into substrings with " " as delimiter.
     * @return new instance of AddCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command executeAddCommand(String[] commandParameters, String[] commandFlags)
            throws InvalidCommandException, IncorrectParameterCountException {
        if (commandFlags.length != COMMAND_ADD_LENGTH) {
            throw new IncorrectParameterCountException();
        }
        String moduleCode = extractModuleCode(commandFlags);
        String moduleName = extractModuleName(commandParameters);
        String moduleType = extractModuleType(commandFlags);
        double moduleCredits = extractModuleCredits(commandFlags);

        return new AddCommand(moduleCode, moduleName, moduleType, moduleCredits);
    }

    /**
     * Extracts relevant parameters and creates new instance of DeleteCommand class
     * to execute.
     *
     * @param commands user input split into substrings with " " as delimiter.
     * @return new instance of DeleteCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command executeDeleteCommand(String[] commandParameters) 
            throws IncorrectParameterCountException {
        if (commandParameters.length != COMMAND_DELETE_LENGTH) {
            throw new IncorrectParameterCountException();
        }
        String code = extractModuleCode(commandParameters);

        return new DeleteCommand(code);
    }

    /**
     * Extracts relevant parameters and creates new instance of ListCommand class to
     * execute.
     *
     * @param commands user input split into substrings with " " as delimiter.
     * @return new instance of ListCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command executeListCommand(String[] commandParameters)
            throws InvalidCommandException, IncorrectParameterCountException {
        if (commandParameters.length != COMMAND_LIST_LENGTH) {
            throw new IncorrectParameterCountException();
        }
        String scope = extractListScope(commandParameters);

        return new ListCommand(scope);
    }

    /**
     * Creates new instance of ProgressCommand class to execute.
     *
     * @param commands user input split into substrings with " " as delimiter.
     * @return new instance of ProgressCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command executeProgressCommand(String[] commandParameters) 
            throws IncorrectParameterCountException {
        if (commandParameters.length != COMMAND_PROGRESS_LENGTH) {
            throw new IncorrectParameterCountException();
        }
        return new ProgressCommand();
    }

    /**
     * Extracts relevant parameters and creates an instance of DoneCommand to
     * execute.
     *
     * @param commands user input split into substrings with " " as delimiter.
     * @return new instance of DoneCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command executeDoneCommand(String[] commandFlags)
            throws IncorrectParameterCountException, InvalidCommandException {
        if (commandFlags.length != COMMAND_DONE_LENGTH) {
            throw new IncorrectParameterCountException();
        }
        String moduleCode = extractModuleCode(commandFlags);
        return new DoneCommand(moduleCode);
    }

    /**
     * Creates new instance of ExitCommand class to execute.
     *
     * @param commands user input split into substrings with " " as delimiter.
     * @return new instance of ExitCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command executeExitCommand(String[] commandParameters) 
            throws IncorrectParameterCountException {
        if (commandParameters.length != COMMAND_EXIT_LENGTH) {
            throw new IncorrectParameterCountException();
        }
        return new ExitCommand();
    }

    /**
     * Extracts module code from user input. Method is called if user runs "Add" or
     * "Delete" command.
     *
     * @param commandFlags user input split into substrings with " " as delimiter.
     * @return module code.
     */
    public static String extractModuleCode(String[] commandFlags) 
            throws IncorrectParameterCountException {
        for (int i = 0; i < commandFlags.length; i++) {
            if (commandFlags[i].equals("-code")) {
                return commandFlags[i + 1].toLowerCase().trim();
            }
        }
        throw new IncorrectParameterCountException();
    }

    public static String extractModuleName(String[] commandParameters) 
            throws IncorrectParameterCountException {
        return commandParameters[1];
    }

    /**
     * Extracts module type from user input. Method is called if user runs "Add"
     * command.
     *
     * @param commands user input split into substrings with " " as delimiter.
     * @return module type.
     * @throws InvalidCommandException if command format is not recognised.
     */
    public static String extractModuleType(String[] commandFlags) 
            throws InvalidCommandException {
        for (int i = 0; i < commandFlags.length; i++) {
            if (commandFlags[i].equals("-t")) {
                String type = commandFlags[i + 1].toLowerCase().trim();
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

    /**
     * Extracts module credits from user input.
     *
     * @param commands user input split into substrings with " " as delimiter.
     * @return number of modular credits.
     * @throws NumberFormatException   if number is not given as modular credits.
     * @throws InvalidCommandException if command format is not recognised.
     */
    public static double extractModuleCredits(String[] commandFlags)
            throws NumberFormatException, InvalidCommandException {
        for (int i = 0; i < commandFlags.length; i++) {
            if (commandFlags[i].equals("-c")) {
                return Double.parseDouble(commandFlags[i + 1]);
            }
        }
        throw new InvalidCommandException();
    }

    /**
     * Determines the option user selects if "List" command is run. Options are: 1.
     * List all modules 2. List modules taken 3. List modules not taken
     *
     * @param commands user input split into substrings with " " as delimiter.
     * @return the option user selects.
     * @throws InvalidCommandException if command format is not recognised.
     */
    public static String extractListScope(String[] commandFlags) 
            throws InvalidCommandException {
        String scope = commandFlags[1].trim().toLowerCase();
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