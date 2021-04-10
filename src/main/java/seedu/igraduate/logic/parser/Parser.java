package seedu.igraduate.logic.parser;

import seedu.igraduate.exception.IncorrectParameterCountException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.InvalidModuleTypeException;
import seedu.igraduate.exception.InvalidModuleGradeException;
import seedu.igraduate.exception.InvalidModuleCodeException;
import seedu.igraduate.exception.InvalidModularCreditException;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.InvalidListTypeException;
import seedu.igraduate.exception.IllegalParametersException;

import seedu.igraduate.logic.command.Command;
import seedu.igraduate.logic.command.AddCommand;
import seedu.igraduate.logic.command.DeleteCommand;
import seedu.igraduate.logic.command.DoneCommand;
import seedu.igraduate.logic.command.ExitCommand;
import seedu.igraduate.logic.command.InfoCommand;
import seedu.igraduate.logic.command.ListCommand;
import seedu.igraduate.logic.command.ProgressCommand;
import seedu.igraduate.logic.command.UpdateCommand;
import seedu.igraduate.logic.command.CapCommand;
import seedu.igraduate.logic.command.HelpCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.logging.Level;

/**
 * Represents an instance of a parser. A parser object corresponds to the
 * processing of one input by the user.
 */
public class Parser {
    // Constants for command words
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_INFO = "info";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_PROGRESS = "progress";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_UPDATE = "update";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_CAP = "cap";
    private static final String COMMAND_HELP = "help";

    // Constants for the expected number of parameters for a given command
    private static final int COMMAND_ADD_FLAG_LENGTH = 6;
    private static final int COMMAND_ADD_WITH_PREREQ_FLAG_LENGTH = 8;
    private static final int COMMAND_ADD_PARAMETER_LENGTH = 2;
    private static final int COMMAND_DELETE_LENGTH = 2;
    private static final int COMMAND_INFO_LENGTH = 2;
    private static final int COMMAND_LIST_LENGTH = 2;
    private static final int COMMAND_PROGRESS_LENGTH = 1;
    private static final int COMMAND_DONE_FLAG_LENGTH = 2;
    private static final int COMMAND_DONE_PARAMETER_LENGTH = 2;
    private static final int COMMAND_UPDATE_PARAMETER_LENGTH = 2;
    private static final int COMMAND_UPDATE_FLAG_LENGTH = 2;
    private static final int COMMAND_CAP_LENGTH = 1;
    private static final int COMMAND_HELP_LENGTH = 2;
    private static final int COMMAND_EXIT_LENGTH = 1;

    // Constants for standard array index number
    private static final int DEFAULT_INDEX = -1;
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;

    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());

    /**
     * Parses user input and identifies the command to be executed.
     * 
     * @param line User input directly from the input stream.
     * @return An object of the respective command class (e.g. deleteCommand,
     *         addCommand, etc.)
     * @throws InvalidCommandException          If input does not contain a valid command.
     * @throws IncorrectParameterCountException If the command input does not contain the right parameters.
     * @throws IllegalParametersException       If the parameter includes -t or -c, which are illegal
     *                                          parameters.
     * @throws InputNotNumberException          If the expected integer input is not a number.
     * @throws InvalidModuleTypeException       If the module type entered is not valid.
     * @throws InvalidListTypeException         If the option for command is invalid.
     * @throws InvalidModularCreditException    If the modular credit is not between 0 and 32 inclusive.
     * @throws InvalidModuleGradeException      If the module grade provided is incorrect.
     * @throws InvalidModuleCodeException       If the module code does not follow NUS standard.
     */
    public static Command parseCommand(String line) throws InvalidCommandException, IncorrectParameterCountException,
            InputNotNumberException, InvalidModuleTypeException, InvalidListTypeException,
            InvalidModularCreditException, IllegalParametersException, InvalidModuleGradeException,
            InvalidModuleCodeException {
        if (line.trim().length() == 0) {
            throw new InvalidCommandException("You may type \"help\" to view manual for our available commands.");
        }
        LOGGER.log(Level.INFO, String.format("User input: %s", line));

        // Splits into 2 String elements:
        // 1. command + first parameter
        // 2. command flags (if any)
        ArrayList<String> commands = getCommand(line);
        ArrayList<String> commandParameters = getCommandParameters(commands);
        String command = commandParameters.get(FIRST_INDEX).toLowerCase();
        ArrayList<String> commandFlags = getCommandFlag(commands);

        assert commands.size() <= 2 : "Limit of split is 2";
        assert commandParameters.size() <= 2 : "Limit of split is 2";

        switch (command) {
        case COMMAND_ADD:
            LOGGER.log(Level.INFO, "Input parsed to add command.");
            return createAddCommand(commandParameters, commandFlags);
        case COMMAND_DELETE:
            LOGGER.log(Level.INFO, "Input parsed to delete command.");
            return createDeleteCommand(commandParameters, commandFlags);
        case COMMAND_INFO:
            LOGGER.log(Level.INFO, "Input parsed to info command.");
            return createInfoCommand(commandParameters, commandFlags);
        case COMMAND_LIST:
            LOGGER.log(Level.INFO, "Input parsed to list command.");
            return createListCommand(commandParameters, commandFlags);
        case COMMAND_PROGRESS:
            LOGGER.log(Level.INFO, "Input parsed to progress command.");
            return createProgressCommand(commandParameters, commandFlags);
        case COMMAND_DONE:
            LOGGER.log(Level.INFO, "Input parsed to done command.");
            return createDoneCommand(commandParameters, commandFlags);
        case COMMAND_UPDATE:
            LOGGER.log(Level.INFO, "Input parsed to udpate command.");
            return createUpdateCommand(commandParameters, commandFlags);
        case COMMAND_CAP:
            LOGGER.log(Level.INFO, "Input parsed to cap command.");
            return createCapCommand(commandParameters, commandFlags);
        case COMMAND_HELP:
            LOGGER.log(Level.INFO, "Input parsed to help command");
            return createHelpCommand(commandParameters, commandFlags);
        case COMMAND_EXIT:
            LOGGER.log(Level.INFO, "Input parsed to exit command.");
            return createExitCommand(commandParameters, commandFlags);
        default:
            LOGGER.warning("Invalid command detected.");
            throw new InvalidCommandException("You may type \"help\" to view manual for our available commands.");
        }
    }

    /**
     * Split the user input into maximum of 2 parts, with '-' as delimiter.
     *
     * @param line user input.
     * @return String array of user input split up.
     */
    protected static ArrayList<String> getCommand(String line) {
        String regex = "^\\s+";
        String trimmedLine = line.replaceAll(regex, "");
        return new ArrayList<>(Arrays.asList(trimmedLine.split("\\s+(?=-)", 2)));
    }

    /**
     * Obtain a string array of length 2, with the first index containing name of
     * command and second index containing the first parameter.
     *
     * @param commands User input split up using getCommand().
     * @return String array of command and first parameter separated.
     */
    protected static ArrayList<String> getCommandParameters(ArrayList<String> commands) {
        return new ArrayList<>(Arrays.asList(commands.get(FIRST_INDEX).split("\\s+", 2)));
    }

    /**
     * Obtains the flags and their respective values.
     * 
     * @param commands Array separating command name and parameters with command
     *                 flags and values.
     * @return Array containing the flags and values split with the delimiter (" ").
     */
    protected static ArrayList<String> getCommandFlag(ArrayList<String> commands) {
        if (commands.size() < 2) {
            return new ArrayList<>(Arrays.asList(new String[] { null }));
        }
        return new ArrayList<>(Arrays.asList(commands.get(SECOND_INDEX).split("\\s+")));
    }

    /**
     * Extracts relevant parameters and creates new instance of AddCommand class to
     * execute. Format: "Add [module name] -c [module code] -t [module type] -mc
     * [modular credits] -p [pre-requisites]"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @param commandFlags      flags of commands from user input.
     * @return new instance of AddCommand class.
     * @throws InvalidCommandException          If input does not contain a valid
     *                                          command.
     * @throws IncorrectParameterCountException If the command input does not
     *                                          contain the right parameters.
     * @throws InputNotNumberException          If the expected input is not number.
     * @throws InvalidModuleTypeException       If the specified module type is not
     *                                          valid.
     */
    public static Command createAddCommand(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws InvalidCommandException, IncorrectParameterCountException, InputNotNumberException,
            InvalidModuleTypeException, InvalidModularCreditException, InvalidModuleCodeException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_ADD_PARAMETER_LENGTH);
        boolean isInvalidFlag = (commandFlags.size() != COMMAND_ADD_FLAG_LENGTH);
        boolean isInvalidPrereqFlag = (commandFlags.size() != COMMAND_ADD_WITH_PREREQ_FLAG_LENGTH);

        if (isInvalidPara || (isInvalidFlag && isInvalidPrereqFlag)) {
            LOGGER.warning("Invalid number of parameters");
            throw new IncorrectParameterCountException();
        }

        assert commandParameters.size() == 2 : "Input for add should have 2 parameters (excluding flags)";
        assert commandFlags.size() == 6 || commandFlags.size() == 8 : "COMMAND_ADD_LENGTH should be 6 or 8.";

        String moduleCode = extractModuleCode(commandFlags);
        String moduleName = commandParameters.get(SECOND_INDEX);
        assert moduleName.trim().length() > 0 : "Name of module should not be empty.";
        String moduleType = extractModuleType(commandFlags);
        double modularCredit = extractModularCredit(commandFlags);
        ArrayList<String> preRequisites = extractPrerequisites(commandFlags);
        ArrayList<String> untakenPreRequisites = extractPrerequisites(commandFlags);
        LOGGER.log(Level.INFO, "Valid parameters for add command.");

        return new AddCommand(moduleCode, moduleName, moduleType, modularCredit, preRequisites, untakenPreRequisites);
    }

    /**
     * Extracts relevant parameters and creates new instance of DeleteCommand class
     * to execute. Format: "Delete [module code]"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @return new instance of DeleteCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command createDeleteCommand(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_DELETE_LENGTH);
        boolean isInvalidFlag = (commandFlags.get(FIRST_INDEX) != null);

        if (isInvalidPara || isInvalidFlag) {
            LOGGER.warning("Invalid number of parameters.");
            throw new IncorrectParameterCountException();
        }

        assert commandParameters.size() == 2 : "COMMAND_DELETE_LENGTH should be 2";
        String moduleCode = commandParameters.get(SECOND_INDEX);
        LOGGER.log(Level.INFO, "Valid parameters for delete command.");

        return new DeleteCommand(moduleCode);
    }

    /**
     * Extracts relevant parameters and creates new instance of InfoCommand class
     * to execute. Format: "Info [module code]"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @return new instance of InfoCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command createInfoCommand(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_INFO_LENGTH);
        boolean isInvalidFlag = (commandFlags.get(FIRST_INDEX) != null);

        if (isInvalidPara || isInvalidFlag) {
            LOGGER.warning("Invalid number of parameters.");
            throw new IncorrectParameterCountException();
        }

        assert commandParameters.size() == 2 : "COMMAND_INFO_LENGTH should be 2";
        String moduleCode = commandParameters.get(SECOND_INDEX);
        LOGGER.log(Level.INFO, "Valid parameters for info command.");

        return new InfoCommand(moduleCode);
    }

    /**
     * Extracts relevant parameters and creates new instance of ListCommand class to
     * execute. Format: "List"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @return new instance of ListCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command createListCommand(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException, InvalidListTypeException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_LIST_LENGTH);
        boolean isInvalidFlag = (commandFlags.get(FIRST_INDEX) != null);

        if (isInvalidPara || isInvalidFlag) {
            LOGGER.warning("Invalid number of parameters.");
            throw new IncorrectParameterCountException();
        }
        String scope = extractListScope(commandParameters);
        LOGGER.log(Level.INFO, "Valid parameters for list command.");

        return new ListCommand(scope);
    }

    /**
     * Creates new instance of ProgressCommand class to execute. Format: "Progress"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @return new instance of ProgressCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command createProgressCommand(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_PROGRESS_LENGTH);
        boolean isInvalidFlag = (commandFlags.get(FIRST_INDEX) != null);

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
     * @throws IncorrectParameterCountException If parameter count is not correct.
     * @throws InvalidCommandException          If the command input does not
     *                                          contain the right parameters.
     * @throws InvalidModuleGradeException      If the module grade provided is not valid.
     */
    public static Command createDoneCommand(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException, InvalidCommandException, InvalidModuleGradeException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_DONE_PARAMETER_LENGTH);
        boolean isInvalidFlag = (commandFlags.size() != COMMAND_DONE_FLAG_LENGTH);

        if (isInvalidPara || isInvalidFlag) {
            LOGGER.warning("Invalid number of parameters.");
            throw new IncorrectParameterCountException();
        }
        assert commandFlags.size() == 2 : "COMMAND_DONE_LENGTH should be 2.";

        String moduleGrade = extractModuleGrade(commandFlags);
        LOGGER.log(Level.INFO, "Valid parameters for done command.");

        return new DoneCommand(commandParameters.get(SECOND_INDEX), moduleGrade);
    }

    /**
     * Extracts relevant parameters and creates an instance of UpdateCommand to
     * execute. Format: "update [module code] [-g|-mc|-n|-p] [value]"
     * 
     * @param commandParameters parameters of user input, excluding command flags.
     * @param commandFlags      flags of commands from user input.
     * @return new instance of UpdateCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     * @throws IllegalParametersException       if the parameter includes -t or -c,
     *                                          which are illegal parameters.
     */
    public static Command createUpdateCommand(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException, IllegalParametersException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_UPDATE_PARAMETER_LENGTH);
        boolean isInvalidFlag = (commandFlags.size() < COMMAND_UPDATE_FLAG_LENGTH);
        boolean isIllegalFlag = (commandFlags.contains("-t") || commandFlags.contains("-c"));

        if (isInvalidPara || isInvalidFlag) {
            LOGGER.warning("Invalid number of parameters.");
            throw new IncorrectParameterCountException();
        } else if (isIllegalFlag) {
            LOGGER.warning("Illegal parameters detected. ");
            throw new IllegalParametersException();
        }

        return new UpdateCommand(commandParameters.get(SECOND_INDEX), commandFlags);
    }

    /**
     * Extracts relevant parameters and creates an instance of CapCommand to
     * execute. Format: "Cap"
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @param commandFlags      flags of commands from user input.
     * @return new instance of CapCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command createCapCommand(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_CAP_LENGTH);
        boolean isInvalidFlag = (commandFlags.get(FIRST_INDEX) != null);

        if (isInvalidPara || isInvalidFlag) {
            LOGGER.warning("Invalid number of parameters.");
            throw new IncorrectParameterCountException();
        }
        LOGGER.log(Level.INFO, "Valid parameters for cap command.");

        return new CapCommand();
    }

    /**
     * Creates new instance of HelpCommand class to execute.
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @param commandFlags      flags of commands from user input.
     * @return new instance of HelpCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command createHelpCommand(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.size() > COMMAND_HELP_LENGTH);
        boolean isInvalidFlag = (commandFlags.get(FIRST_INDEX) != null);

        if (isInvalidPara || isInvalidFlag) {
            LOGGER.warning("Invalid number of parameters.");
            throw new IncorrectParameterCountException();
        }
        LOGGER.log(Level.INFO, "Valid parameters for help command");
        if (commandParameters.size() < 2) {
            return new HelpCommand("no params");
        }
        return new HelpCommand(commandParameters.get(1));
    }

    /**
     * Creates new instance of ExitCommand class to execute.
     *
     * @param commandParameters parameters of user input, excluding command flags.
     * @return new instance of ExitCommand class.
     * @throws IncorrectParameterCountException if parameter count is not correct.
     */
    public static Command createExitCommand(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_EXIT_LENGTH);
        boolean isInvalidFlag = (commandFlags.get(FIRST_INDEX) != null);

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
     * @throws IncorrectParameterCountException If parameter count is not correct.
     * @throws InvalidModuleCodeException       If module code does not follow NUS standard.
     * @throws InvalidCommandException          If module code parameter is not provided.
     */
    public static String extractModuleCode(ArrayList<String> commands) throws IncorrectParameterCountException,
            InvalidModuleCodeException, InvalidCommandException {
        assert commands.size() == COMMAND_ADD_FLAG_LENGTH || commands.size() == COMMAND_ADD_WITH_PREREQ_FLAG_LENGTH
                : "extractModuleCode should only be called for add";
        int index = commands.indexOf("-c");
        if (index == DEFAULT_INDEX) {
            LOGGER.warning("Missing module code parameter.");
            throw new InvalidCommandException("Module code parameter is missing.");
        }

        String moduleCode = commands.get(index + 1).toUpperCase().trim();
        if (!isModuleCodeValid(moduleCode)) {
            throw new InvalidModuleCodeException();
        }

        assert commands.get(index + 1).length() > 0 : "Module code should not be empty";
        return commands.get(index + 1).toUpperCase().trim();
    }

    /**
     * Extracts module type from user input. Method is called if user runs "Add"
     * command.
     *
     * @param commandFlags flags of commands from user input.
     * @return module type.
     * @throws InvalidModuleTypeException if command format is not recognised.
     * @throws InvalidCommandException    if -t flag is not found.
     */
    public static String extractModuleType(ArrayList<String> commandFlags)
            throws InvalidModuleTypeException, InvalidCommandException {
        assert commandFlags.size() == COMMAND_ADD_FLAG_LENGTH
                || commandFlags.size() == COMMAND_ADD_WITH_PREREQ_FLAG_LENGTH
                : "extractModuleType should only be called for add";
        int index = commandFlags.indexOf("-t");
        if (index == DEFAULT_INDEX) {
            LOGGER.warning("Missing module type parameter.");
            throw new InvalidCommandException("Module type parameter is missing.");
        }

        String type = commandFlags.get(index + 1).toLowerCase().trim();
        assert type.length() > 0 : "Module type should not be empty.";
        switch (type) {
        case "ue": // fallthrough
        case "ge": // fallthrough
        case "math": // fallthrough
        case "core":
            return type;
        default:
            LOGGER.warning("Invalid module type detected.");
            throw new InvalidModuleTypeException();
        }
    }

    /**
     * Extracts modular credits from user input.
     *
     * @param commandFlags flags of commands from user input.
     * @return number of modular credits.
     * @throws NumberFormatException         if number is not given as modular
     *                                       credits.
     * @throws InvalidCommandException       if -mc flag is not found.
     * @throws InvalidModularCreditException if modular credit is not positive
     *                                       number.
     */
    public static double extractModularCredit(ArrayList<String> commandFlags)
            throws InputNotNumberException, InvalidCommandException, InvalidModularCreditException {
        int index = commandFlags.indexOf("-mc");

        if (index == DEFAULT_INDEX) {
            LOGGER.warning("Missing modular credits parameter.");
            throw new InvalidCommandException("Modular credit parameter is missing.");
        }

        assert commandFlags.get(index + 1).trim().length() > 0 : "Modular credit field should not be empty.";
        try {
            double modularCredit = Double.parseDouble(commandFlags.get(index + 1));
            if (!isModularCreditValid(modularCredit)) {
                throw new InvalidModularCreditException();
            }
            return modularCredit;
        } catch (NumberFormatException e) {
            LOGGER.warning("Invalid module credits detected.");
            throw new InputNotNumberException("Modular credits : -mc");
        }
    }

    /**
     * Extracts module grade from user input.
     *
     * @param commandFlags flags of commands from user input.
     * @return module grade.
     * @throws InvalidCommandException If -g flag is not found.
     * @throws InvalidModuleGradeException If module grade provided is not valid.
     */
    public static String extractModuleGrade(ArrayList<String> commandFlags) throws InvalidCommandException,
            InvalidModuleGradeException {
        int index = commandFlags.indexOf("-g");
        if (index == DEFAULT_INDEX) {
            LOGGER.warning("Missing module grade parameter.");
            throw new InvalidCommandException("Module grade parameter is missing.");
        }

        String moduleGrade = commandFlags.get(index + 1);

        if (!isModuleGradeValid(moduleGrade)) {
            throw new InvalidModuleGradeException();
        }

        assert moduleGrade.length() > 0 : "Grade should not be empty.";
        return moduleGrade;
    }

    public static String extractModuleName(ArrayList<String> commandFlags) throws InvalidCommandException {

        int startIndex = commandFlags.indexOf("-n");
        if (startIndex == DEFAULT_INDEX) {
            LOGGER.warning("Missing module name parameter.");
            throw new InvalidCommandException("Module name parameter is missing.");
        }

        int endIndex = DEFAULT_INDEX;

        for (int i = 0; i < commandFlags.size(); i++) {
            if (commandFlags.get(i).matches("-[^n]{1,2}")) {
                endIndex = i;
                break;
            }
        }

        if (endIndex == DEFAULT_INDEX) {
            endIndex = commandFlags.size();
        }

        String moduleName = "";
        for (int i = startIndex + 1; i < endIndex; i++) {
            moduleName = moduleName + " " + commandFlags.get(i);
        }

        return moduleName.trim();
    }

    /**
     * Determines the option user selects if "List" command is run. Options are: 1.
     * List all modules 2. List modules taken 3. List modules not taken
     *
     * @param commandFlags flags of commands from user input.
     * @return the option user selects.
     * @throws InvalidListTypeException if list type given is invalid.
     */
    public static String extractListScope(ArrayList<String> commandFlags) throws InvalidListTypeException {
        String scope = commandFlags.get(1).trim().toLowerCase();
        switch (scope) {
        case "all": // fallthrough
        case "complete": // fallthrough
        case "incomplete": // fallthrough
        case "available": // fallthrough
        case "core": // fallthrough
        case "elec": // fallthrough
        case "ge": // fallthrough
        case "math":
            return scope;
        default:
            throw new InvalidListTypeException();
        }
    }

    /**
     * Extracts pre-requisite module codes from user input.
     *
     * @param commandFlags flags of commands from user input.
     * @return ArrayList containing extracted prerequisite module codes.
     */
    public static ArrayList<String> extractPrerequisites(ArrayList<String> commandFlags) {
        ArrayList<String> prerequisites = new ArrayList<>();
        int index = commandFlags.indexOf("-p");
        if (index >= 0) {
            String trimmedCommandFlag = commandFlags.get(index + 1).trim();
            ArrayList<String> moduleCodes = new ArrayList<>(Arrays.asList(trimmedCommandFlag.split(",")));
            for (String moduleCode : moduleCodes) {
                prerequisites.add(moduleCode.toUpperCase());
            }
        }

        return prerequisites;
    }

    /**
     * Checks if the module code is valid according to school codes.
     * 
     * @param moduleCode module code to be checked.
     * @return True if the code is valid, false otherwise.
     */
    public static boolean isModuleCodeValid(String moduleCode) {
        return Pattern.matches("[a-zA-Z]{2,3}[0-9]{4}[a-zA-Z]{0,2}", moduleCode);
    }

    /**
     * Checks if the module code is valid according to school codes.
     * 
     * @param preRequisites list of all the module codes to be checked.
     * @return True if all the codes are valid, false otherwise.
     */
    protected static boolean isModuleCodeValid(ArrayList<String> preRequisites) {
        for (String preRequisite : preRequisites) {
            if (!Pattern.matches("[a-zA-Z]{2,3}[0-9]{4}[a-zA-Z]{0,2}", preRequisite)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the module grade is valid according to school module grade standard.
     *
     * @param moduleGrade Module grade entered by user.
     * @return True if the module grade is valid, false otherwise.
     */
    public static boolean isModuleGradeValid(String moduleGrade) {
        switch (moduleGrade.toLowerCase()) {
        case "a+": // fallthrough
        case "a":  // fallthrough
        case "a-": // fallthrough
        case "b+": // fallthrough
        case "b":  // fallthrough
        case "b-": // fallthrough
        case "c+": // fallthrough
        case "c":  // fallthrough
        case "d+": // fallthrough
        case "f":  // fallthrough
        case "s":  // fallthrough
        case "u":  // fallthrough
        case "cs": // fallthrough
        case "cu": // fallthrough
        case "nil":
            return true;
        default:
            return false;
        }
    }

    /**
     * Checks if the modular credit is valid according to school modular credit range.
     *
     * @param modularCredit Modular credit value entered by user.
     * @return True if the modular credit falls in valid range, false otherwise.
     */
    public static boolean isModularCreditValid(double modularCredit) {
        boolean isPositive = modularCredit >= 0;
        boolean isSmallerThanLimit = modularCredit < 33;

        if (isPositive && isSmallerThanLimit) {
            return true;
        }
        return false;
    }
}