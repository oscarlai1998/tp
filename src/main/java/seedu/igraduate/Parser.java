package seedu.igraduate;

import seedu.igraduate.command.Command;
import seedu.igraduate.command.AddCommand;
import seedu.igraduate.command.DeleteCommand;
import seedu.igraduate.command.DoneCommand;
import seedu.igraduate.command.ExitCommand;
import seedu.igraduate.command.ListCommand;
import seedu.igraduate.command.ProgressCommand;
import seedu.igraduate.command.CapCommand;

import seedu.igraduate.exception.IncorrectParameterCountException;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.InvalidModuleTypeException;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Represents an instance of a parser. 
 * A parser object corresponds to the processing of one input by the user.
 */
public class Parser {
    // Constants for command words
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_PROGRESS = "progress";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_CAP = "cap";

    // Constants for the expected number of parameters for a given command
    private static final int COMMAND_ADD_FLAG_LENGTH = 6;
    private static final int COMMAND_ADD_WITH_PREREQ_FLAG_LENGTH = 8;
    private static final int COMMAND_ADD_PARAMETER_LENGTH = 2;
    private static final int COMMAND_DELETE_LENGTH = 2;
    private static final int COMMAND_LIST_LENGTH = 2;
    private static final int COMMAND_PROGRESS_LENGTH = 1;
    private static final int COMMAND_DONE_FLAG_LENGTH = 2;
    private static final int COMMAND_DONE_PARAMETER_LENGTH = 2;
    private static final int COMMAND_CAP_LENGTH = 1;
    private static final int COMMAND_EXIT_LENGTH = 1;

    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());

    /**
     * Parses user input and identifies the command to be executed.
     * 
     * @param line User input directly from the input stream.
     * @return An object of the respective command class (e.g. deleteCommand,
     *         addCommand, etc.)
     * @throws InvalidCommandException          If input does not contain a valid
     *                                          command.
     * @throws IncorrectParameterCountException If the command input does not
     *                                          contain the right parameters.
     */
    public static Command parseCommand(String line) 
            throws InvalidCommandException, IncorrectParameterCountException,
            InputNotNumberException, InvalidModuleTypeException {
        if (line.trim().length() == 0) {
            throw new InvalidCommandException();
        }

        LOGGER.log(Level.INFO, String.format("User input: %s", line));

        // Splits into 2 String elements:
        // 1. command + first parameter
        // 2. command flags (if any)
        String[] commands = line.split("\\s+(?=-)", 2);
        assert commands.length <= 2 : "Limit of split is 2";
        String[] commandParameters = commands[0].split("\\s+", 2);
        assert commandParameters.length <= 2 : "Limit of split is 2";
        String command = commandParameters[0].toLowerCase();
        String[] commandFlags = getCommandFlag(commands);

        switch (command) {
        case COMMAND_ADD:
            LOGGER.log(Level.INFO, "Input parsed to add command.");
            return createAddCommand(commandParameters, commandFlags);
        case COMMAND_DELETE:
            LOGGER.log(Level.INFO, "Input parsed to delete command.");
            return createDeleteCommand(commandParameters, commandFlags);
        case COMMAND_LIST:
            LOGGER.log(Level.INFO, "Input parsed to list command.");
            return createListCommand(commandParameters, commandFlags);
        case COMMAND_PROGRESS:
            LOGGER.log(Level.INFO, "Input parsed to progress command.");
            return createProgressCommand(commandParameters, commandFlags);
        case COMMAND_DONE:
            LOGGER.log(Level.INFO, "Input parsed to done command.");
            return createDoneCommand(commandParameters, commandFlags);
        case COMMAND_CAP:
            LOGGER.log(Level.INFO, "Input parsed to cap command.");
            return createCapCommand(commandParameters, commandFlags);
        case COMMAND_EXIT:
            LOGGER.log(Level.INFO, "Input parsed to exit command.");
            return createExitCommand(commandParameters, commandFlags);
        default:
            LOGGER.warning("Invalid command detected.");
            throw new InvalidCommandException();
        }
    }

    /**
     * Obtains the flags and their respective values.
     * 
     * @param commands Array separating command name and parameters with command
     *                 flags and values.
     * @return Array containing the flags and values split with the delimiter (" ").
     */
    private static String[] getCommandFlag(String[] commands) {
        if (commands.length < 2) {
            return new String[] { null };
        }
        return commands[1].split("\\s+");
    }

    /**
     * Extracts relevant parameters and creates new instance of AddCommand class to execute.
     * Format: "Add [module name] -c [module code] -t [module type] -mc [modular credits]"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @param commandFlags      flags of commands from user input.
     * @return new instance of AddCommand class.
     * @throws InvalidCommandException          If input does not contain a valid
     *                                          command.
     * @throws IncorrectParameterCountException If the command input does not
     *                                          contain the right parameters.
     */
    public static Command createAddCommand(String[] commandParameters, String[] commandFlags)
            throws InvalidCommandException, IncorrectParameterCountException, InputNotNumberException,
            InvalidModuleTypeException {
        boolean isInvalidPara = (commandParameters.length != COMMAND_ADD_PARAMETER_LENGTH);
        boolean isInvalidFlag = (commandFlags.length != COMMAND_ADD_FLAG_LENGTH);
        boolean isInvalidPrereqFlag = (commandFlags.length != COMMAND_ADD_WITH_PREREQ_FLAG_LENGTH);

        if (isInvalidPara || (isInvalidFlag && isInvalidPrereqFlag)) {
            LOGGER.warning("Invalid number of parameters");
            throw new IncorrectParameterCountException();
        }

        assert commandParameters.length == 2 : "Input for add should have 2 parameters (excluding flags)";
        assert commandFlags.length == 6 || commandFlags.length == 8 : "COMMAND_ADD_LENGTH should be 6 or 8.";

        String moduleCode = extractModuleCode(commandFlags);
        String moduleName = commandParameters[1];
        assert moduleName.trim().length() > 0 : "Name of module should not be empty.";
        String moduleType = extractModuleType(commandFlags);
        double moduleCredits = extractModuleCredits(commandFlags);
        ArrayList<String> preRequisites = extractPreRequisites(commandFlags);
        LOGGER.log(Level.INFO, "Valid parameters for add command.");

        return new AddCommand(moduleCode, moduleName, moduleType, moduleCredits, preRequisites);
    }

    /**
     * Extracts relevant parameters and creates new instance of DeleteCommand class to execute.
     * Format: "Delete [module code]"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @return new instance of DeleteCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command createDeleteCommand(String[] commandParameters, String[] commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.length != COMMAND_DELETE_LENGTH);
        boolean isInvalidFlag = (commandFlags[0] != null);

        if (isInvalidPara || isInvalidFlag) {
            LOGGER.warning("Invalid number of parameters.");
            throw new IncorrectParameterCountException();
        }

        assert commandParameters.length == 2 : "COMMAND_DELETE_LENGTH should be 2";
        String moduleCode = commandParameters[1];
        LOGGER.log(Level.INFO, "Valid parameters for delete command.");

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
    public static Command createListCommand(String[] commandParameters, String[] commandFlags)
            throws IncorrectParameterCountException, InvalidCommandException {
        boolean isInvalidPara = (commandParameters.length != COMMAND_LIST_LENGTH);
        boolean isInvalidFlag = (commandFlags[0] != null);

        if (isInvalidPara || isInvalidFlag) {
            LOGGER.warning("Invalid number of parameters.");
            throw new IncorrectParameterCountException();
        }
        String scope = extractListScope(commandParameters);
        LOGGER.log(Level.INFO, "Valid parameters for list command.");

        return new ListCommand(scope);
    }

    /**
     * Creates new instance of ProgressCommand class to execute.
     * Format: "Progress"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @return new instance of ProgressCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command createProgressCommand(String[] commandParameters, String[] commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.length != COMMAND_PROGRESS_LENGTH);
        boolean isInvalidFlag = (commandFlags[0] != null);

        if (isInvalidPara || isInvalidFlag) {
            LOGGER.warning("Invalid number of parameters.");
            throw new IncorrectParameterCountException();
        }
        LOGGER.log(Level.INFO, "Valid parameters for progress command.");

        return new ProgressCommand();
    }

    /**
     * Extracts relevant parameters and creates an instance of DoneCommand to
     * execute. Format: "done [module code] -g [grade]"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @param commandFlags      flags of commands from user input.
     * @return new instance of DoneCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     * @throws InvalidCommandException          If the command input does not
     *                                          contain the right parameters.
     */
    public static Command createDoneCommand(String[] commandParameters, String[] commandFlags)
            throws IncorrectParameterCountException, InvalidCommandException {
        boolean isInvalidPara = (commandParameters.length != COMMAND_DONE_PARAMETER_LENGTH);
        boolean isInvalidFlag = (commandFlags.length != COMMAND_DONE_FLAG_LENGTH);

        if (isInvalidPara || isInvalidFlag) {
            LOGGER.warning("Invalid number of parameters.");
            throw new IncorrectParameterCountException();
        }
        assert commandFlags.length == 2 : "COMMAND_DONE_LENGTH should be 2.";

        String moduleGrade = extractModuleGrade(commandFlags);
        LOGGER.log(Level.INFO, "Valid parameters for done command.");

        return new DoneCommand(commandParameters[1], moduleGrade);
    }

    public static Command createCapCommand(String[] commandParameters, String[] commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.length != COMMAND_CAP_LENGTH);
        boolean isInvalidFlag = (commandFlags[0] != null);

        if (isInvalidPara || isInvalidFlag) {
            LOGGER.warning("Invalid number of parameters.");
            throw new IncorrectParameterCountException();
        }
        LOGGER.log(Level.INFO, "Valid parameters for cap command.");

        return new CapCommand();
    }
    /**
     * Creates new instance of ExitCommand class to execute.
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @return new instance of ExitCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command createExitCommand(String[] commandParameters, String[] commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.length != COMMAND_EXIT_LENGTH);
        boolean isInvalidFlag = (commandFlags[0] != null);

        if (isInvalidPara || isInvalidFlag) {
            LOGGER.warning("Invalid number of parameters.");
            throw new IncorrectParameterCountException();
        }
        LOGGER.log(Level.INFO, "Valid parameters for exit command.");
        
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
        assert commands.length == COMMAND_ADD_FLAG_LENGTH
                || commands.length == COMMAND_ADD_WITH_PREREQ_FLAG_LENGTH
                : "extractModuleCode should only be called for add";
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].equals("-c")) {
                assert commands[i + 1].length() > 0 : "Module code should not be empty";
                return commands[i + 1].toUpperCase().trim();
            }
        }
        LOGGER.warning("Missing module code parameter.");
        throw new IncorrectParameterCountException();
    }

    /**
     * Extracts module type from user input. Method is called if user runs "Add"
     * command.
     *
     * @param commandFlags flags of commands from user input.
     * @return module type.
     * @throws InvalidModuleTypeException if command format is not recognised.
     * @throws InvalidCommandException if -t flag is not found. 
     */
    public static String extractModuleType(String[] commandFlags) 
            throws InvalidModuleTypeException, InvalidCommandException {
        assert commandFlags.length == COMMAND_ADD_FLAG_LENGTH
                || commandFlags.length == COMMAND_ADD_WITH_PREREQ_FLAG_LENGTH
                : "extractModuleType should only be called for add";
        for (int i = 0; i < commandFlags.length; i++) {
            if (commandFlags[i].equals("-t")) {
                String type = commandFlags[i + 1].toLowerCase().trim();
                assert type.length() > 0 : "Module type should not be empty.";
                switch (type) {
                case "ue":
                case "ge":
                case "math":
                case "core":
                    return type;
                default:
                    LOGGER.warning("Invalid module type detected.");
                    throw new InvalidModuleTypeException();
                }
            }
        }
        LOGGER.warning("Missing module type parameter.");
        throw new InvalidCommandException();
    }

    /**
     * Extracts module credits from user input.
     *
     * @param commandFlags flags of commands from user input.
     * @return number of modular credits.
     * @throws NumberFormatException   if number is not given as modular credits.
     * @throws InvalidCommandException if -mc flag is not found.
     */
    public static double extractModuleCredits(String[] commandFlags)
            throws InputNotNumberException, InvalidCommandException {
        assert commandFlags.length == COMMAND_ADD_FLAG_LENGTH
                || commandFlags.length == COMMAND_ADD_WITH_PREREQ_FLAG_LENGTH
                : "extractModuleCredits should only be called for add command.";
        for (int i = 0; i < commandFlags.length; i++) {
            if (commandFlags[i].equals("-mc")) {
                assert commandFlags[i + 1].trim().length() > 0 : "Modular credits field should not be empty.";
                try {
                    return Double.parseDouble(commandFlags[i + 1]);
                } catch (NumberFormatException e) {
                    LOGGER.warning("Invalid module credits detected.");
                    throw new InputNotNumberException("Modular credits : -mc");
                }
            }
        }
        LOGGER.warning("Missing module credits parameter.");
        throw new InvalidCommandException();
    }

    /**
     * Extracts module grade from user input.
     *
     * @param commandFlags flags of commands from user input.
     * @return module grade.
     * @throws InvalidCommandException if -g flag is not found.
     */
    public static String extractModuleGrade(String[] commandFlags) 
            throws InvalidCommandException {
        assert commandFlags.length == COMMAND_DONE_FLAG_LENGTH : "extractModuleGrade should only be "
                + "called for done command.";
        for (int i = 0; i < commandFlags.length; i++) {
            if (commandFlags[i].equals("-g")) {
                assert commandFlags[i + 1].length() > 0 : "Grade should not be empty.";
                return commandFlags[i + 1];
            }
        }
        LOGGER.warning("Missing module grade parameter.");
        throw new InvalidCommandException();
    }

    /**
     * Determines the option user selects if "List" command is run. Options are: 1.
     * List all modules 2. List modules taken 3. List modules not taken
     *
     * @param commandFlags flags of commands from user input.
     * @return the option user selects.
     * @throws InvalidCommandException if command format is not recognised.
     */
    public static String extractListScope(String[] commandFlags)
            throws InvalidCommandException {
        String scope = commandFlags[1].trim().toLowerCase();
        switch (scope) {
        case "all":
        case "complete":
        case "incomplete":
            return scope;
        default:
            throw new InvalidCommandException();
        }
    }

    public static ArrayList<String> extractPreRequisites(String[] commandFlags) {
        ArrayList<String> preRequisites = new ArrayList<>();
        List<String> moduleCodes;
        for (int i = 0; i < commandFlags.length; i++) {
            if (commandFlags[i].equals("-p")) {
                String trimmedCommandFlag = commandFlags[i + 1].trim();
                moduleCodes = Arrays.asList(trimmedCommandFlag.split(","));
                for (String moduleCode : moduleCodes) {
                    preRequisites.add(moduleCode.toUpperCase());
                }
                break;
            }
        }
        return preRequisites;
    }
}