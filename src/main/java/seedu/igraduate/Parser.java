package seedu.igraduate;

import seedu.igraduate.command.Command;
import seedu.igraduate.command.AddCommand;
import seedu.igraduate.command.DeleteCommand;
import seedu.igraduate.command.DoneCommand;
import seedu.igraduate.command.ExitCommand;
import seedu.igraduate.command.ListCommand;
import seedu.igraduate.command.ProgressCommand;

import seedu.igraduate.exception.IncorrectParameterCountException;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.InvalidModuleTypeException;

/**
 * Represents an instance of a parser.
 * A parse object corresponds to the processing of one input by the user.
 */
public class Parser {
    // Constants for command words
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_PROGRESS = "progress";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_EXIT = "exit";

    // Constants for the expected number of parameters for a given command
    private static final int COMMAND_ADD_LENGTH = 6;
    private static final int COMMAND_DELETE_LENGTH = 2;
    private static final int COMMAND_LIST_LENGTH = 1;
    private static final int COMMAND_PROGRESS_LENGTH = 1;
    private static final int COMMAND_DONE_LENGTH = 2;
    private static final int COMMAND_EXIT_LENGTH = 1;

    /**
     * Parses user input and identifies the command to be executed.
     * 
     * @param line User input directly from the input stream. 
     * @return An object of the respective command class (e.g. deleteCommand, addCommand, etc.)
     * @throws InvalidCommandException If input does not contain a valid command. 
     * @throws IncorrectParameterCountException If the command input does not contain the right parameters. 
     */
    public static Command parseCommand(String line) 
            throws InvalidCommandException, IncorrectParameterCountException,
            InputNotNumberException, InvalidModuleTypeException {
        if (line.trim().length() == 0) {
            throw new InvalidCommandException();
        }

        // Splits into 2 String elements:
        // 1. command + first parameter
        // 2. command flags (if any)
        String[] commands = line.split("\\s+(?=-)", 2);
        assert commands.length <= 2 : "Limit of split is 2";
        String[] commandParameters = commands[0].split("\\s+", 2);
        assert commandParameters.length <= 2 : "Limit of split is 2";
        String command = commandParameters[0].toLowerCase();

        switch (command) {
        case COMMAND_ADD:
            String[] addCommandFlags = getCommandFlag(commands);
            return createAddCommand(commandParameters, addCommandFlags);
        case COMMAND_DELETE:
            return createDeleteCommand(commandParameters);
        case COMMAND_LIST:
            return createListCommand(commandParameters);
        case COMMAND_PROGRESS:
            return createProgressCommand(commandParameters);
        case COMMAND_DONE:
            String[] doneCommandFlags = getCommandFlag(commands);
            return createDoneCommand(commandParameters, doneCommandFlags);
        case COMMAND_EXIT:
            return createExitCommand(commandParameters);
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Obtains the flags and their respective values. 
     * 
     * @param commands Array separating command name and parameters with command flags and values.
     * @return Array containing the flags and values split with the delimiter (" ").
     */
    private static String[] getCommandFlag(String[] commands) {
        if (commands.length < 2) {
            return new String[] { null };
        }
        return commands[1].split("\\s+");
    }

    /**
     * Extracts relevant parameters and creates new instance of AddCommand class to
     * execute.
     * Format: "Add [module name] -c [module code] -t [module type] -mc [modular credits]"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @param commandFlags flags of commands from user input. 
     * @return new instance of AddCommand class.
     * @throws InvalidCommandException If input does not contain a valid command. 
     * @throws IncorrectParameterCountException If the command input does not contain the right parameters. 
     */
    public static Command createAddCommand(String[] commandParameters, String[] commandFlags)
            throws InvalidCommandException, IncorrectParameterCountException,
            InputNotNumberException, InvalidModuleTypeException {
        if (commandFlags.length != COMMAND_ADD_LENGTH) {
            throw new IncorrectParameterCountException();
        }
        assert commandParameters.length == 2 : "Input for add should have 2 parameters (excluding flags)";
        assert commandFlags.length == 6 : "COMMAND_ADD_LENGTH should be 6.";
        String moduleCode = extractModuleCode(commandFlags);
        String moduleName = commandParameters[1];
        String moduleType = extractModuleType(commandFlags);
        double moduleCredits = extractModuleCredits(commandFlags);

        return new AddCommand(moduleCode, moduleName, moduleType, moduleCredits);
    }

    /**
     * Extracts relevant parameters and creates new instance of DeleteCommand class to execute.
     * Format: "Delete [module code]"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @return new instance of DeleteCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command createDeleteCommand(String[] commandParameters) 
            throws IncorrectParameterCountException {
        if (commandParameters.length != COMMAND_DELETE_LENGTH) {
            throw new IncorrectParameterCountException();
        }
        assert commandParameters.length == 2 : "Input for delete should have 2 parameters.";
        String moduleCode = commandParameters[1];

        return new DeleteCommand(moduleCode);
    }

    /**
     * Extracts relevant parameters and creates new instance of ListCommand class to execute.
     * Format: "List"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @return new instance of ListCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command createListCommand(String[] commandParameters)
            throws IncorrectParameterCountException {
        if (commandParameters.length != COMMAND_LIST_LENGTH) {
            throw new IncorrectParameterCountException();
        }
        assert commandParameters.length == 1 : "Input for list should only have 1 parameter.";
        return new ListCommand();
    }

    /**
     * Creates new instance of ProgressCommand class to execute.
     * Format: "Progress"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @return new instance of ProgressCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command createProgressCommand(String[] commandParameters) 
            throws IncorrectParameterCountException {
        if (commandParameters.length != COMMAND_PROGRESS_LENGTH) {
            throw new IncorrectParameterCountException();
        }
        assert commandParameters.length == 1 : "Input for progress should only have 1 parameter.";
        return new ProgressCommand();
    }

    /**
     * Extracts relevant parameters and creates an instance of DoneCommand to execute.
     * Format: "done [module code] -g [grade]"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @param commandFlags flags of commands from user input. 
     * @return new instance of DoneCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     * @throws InvalidCommandException If the command input does not contain the right parameters. 
     */
    public static Command createDoneCommand(String[] commandParameters, String[] commandFlags)
            throws IncorrectParameterCountException, InvalidCommandException {
        if (commandFlags.length != COMMAND_DONE_LENGTH) {
            throw new IncorrectParameterCountException();
        }
        assert commandParameters.length == 2 : "Input for done should have 2 parameters (excluding flags)";
        assert commandFlags.length == 2 : "COMMAND_DONE_LENGTH should be 2.";
        String moduleGrade = extractModuleGrade(commandFlags);
        return new DoneCommand(commandParameters[1], moduleGrade);
    }

    /**
     * Creates new instance of ExitCommand class to execute.
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @return new instance of ExitCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command createExitCommand(String[] commandParameters) 
            throws IncorrectParameterCountException {
        if (commandParameters.length != COMMAND_EXIT_LENGTH) {
            throw new IncorrectParameterCountException();
        }
        assert commandParameters.length == 1 : "Input for exit should only have 1 parameter.";
        return new ExitCommand();
    }

    /**
     * Extracts module code from user input. Method is called if user runs "Add" or
     * "Delete" command.
     *
     * @param commands parameters of user input, excluding command flags.
     * @return module code.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static String extractModuleCode(String[] commands)
            throws IncorrectParameterCountException {
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].equals("-c")) {
                assert commands[i + 1].length() > 0 : "Module code should not be empty";
                return commands[i + 1].toLowerCase().trim();
            }
        }
        throw new IncorrectParameterCountException();
    }

    /**
     * Extracts module type from user input. Method is called if user runs "Add" command.
     *
     * @param commandFlags flags of commands from user input. 
     * @return module type.
     * @throws InvalidModuleTypeException if command format is not recognised.
     */
    public static String extractModuleType(String[] commandFlags) 
            throws InvalidModuleTypeException {
        for (int i = 0; i < commandFlags.length; i++) {
            if (commandFlags[i].equals("-t")) {
                String type = commandFlags[i + 1].toLowerCase().trim();
                assert type.length() > 0 : "Module type should not be empty.";
                switch (type) {
                case "core":
                case "ue":
                case "math":
                case "ge":
                    return type;
                default:
                    throw new InvalidModuleTypeException();
                }
            }
        }
        throw new InvalidModuleTypeException();
    }

    /**
     * Extracts module credits from user input.
     *
     * @param commandFlags flags of commands from user input. 
     * @return number of modular credits.
     * @throws NumberFormatException   if number is not given as modular credits.
     * @throws InvalidCommandException if command format is not recognised.
     */
    public static double extractModuleCredits(String[] commandFlags)
            throws InputNotNumberException, InvalidCommandException {
        for (int i = 0; i < commandFlags.length; i++) {
            if (commandFlags[i].equals("-mc")) {
                try {
                    return Double.parseDouble(commandFlags[i + 1]);
                } catch (NumberFormatException e) {
                    throw new InputNotNumberException("Modular credits : -mc");
                }
            }
        }
        throw new InvalidCommandException();
    }

    /**
     * Extracts module grade from user input.
     *
     * @param commandFlags flags of commands from user input. 
     * @return module grade.
     * @throws InvalidCommandException if command format is not recognised.
     */
    public static String extractModuleGrade(String[] commandFlags) throws InvalidCommandException {
        for (int i = 0; i < commandFlags.length; i++) {
            if (commandFlags[i].equals("-g")) {
                assert commandFlags[i + 1].length() > 0 : "Grade should not be empty.";
                return commandFlags[i + 1];
            }
        }
        throw new InvalidCommandException();
    }
}