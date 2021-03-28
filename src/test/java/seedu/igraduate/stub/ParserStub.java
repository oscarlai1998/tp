package seedu.igraduate.stub;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.igraduate.logic.Parser;
import seedu.igraduate.exception.IncorrectParameterCountException;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.InvalidListTypeException;
import seedu.igraduate.exception.InvalidModuleTypeException;

public class ParserStub extends Parser {
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_PROGRESS = "progress";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_UPDATE = "update";
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
    private static final int COMMAND_UPDATE_PARAMETER_LENGTH = 2;
    private static final int COMMAND_UPDATE_FLAG_LENGTH = 2;
    private static final int COMMAND_CAP_LENGTH = 1;
    private static final int COMMAND_EXIT_LENGTH = 1;

    public static String createAddCommandStub(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws InvalidCommandException, IncorrectParameterCountException, InputNotNumberException,
            InvalidModuleTypeException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_ADD_PARAMETER_LENGTH);
        boolean isInvalidFlag = (commandFlags.size() != COMMAND_ADD_FLAG_LENGTH);
        boolean isInvalidPrereqFlag = (commandFlags.size() != COMMAND_ADD_WITH_PREREQ_FLAG_LENGTH);

        if (isInvalidPara || (isInvalidFlag && isInvalidPrereqFlag)) {
            throw new IncorrectParameterCountException();
        }

        String moduleCode = extractModuleCode(commandFlags);
        String moduleName = commandParameters.get(1);
        String moduleType = extractModuleType(commandFlags);
        double moduleCredits = extractModuleCredits(commandFlags);
        ArrayList<String> preRequisites = extractPreRequisites(commandFlags);
        ArrayList<String> untakenPreRequisites = extractPreRequisites(commandFlags);

        if (!isModuleCodeValid(moduleCode) || !isModuleCodeValid(preRequisites)) {
            throw new InvalidCommandException();
        }

        return String.format("new AddCommand(%s, %s, %s, %.2f, %s, %s)", moduleCode, moduleName, moduleType,
                moduleCredits, preRequisites, untakenPreRequisites);
    }

    public static String createDeleteCommandStub(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_DELETE_LENGTH);
        boolean isInvalidFlag = (commandFlags.get(0) != null);

        if (isInvalidPara || isInvalidFlag) {
            throw new IncorrectParameterCountException();
        }

        String moduleCode = commandParameters.get(1);

        return "new DeleteCommand(" + moduleCode + ")";
    }

    public static String createListCommandStub(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException, InvalidListTypeException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_LIST_LENGTH);
        boolean isInvalidFlag = (commandFlags.get(0) != null);

        if (isInvalidPara || isInvalidFlag) {
            throw new IncorrectParameterCountException();
        }
        String scope = extractListScope(commandParameters);

        return String.format("new ListCommand(%s)", scope);
    }

    public static String createProgressCommandStub(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_PROGRESS_LENGTH);
        boolean isInvalidFlag = (commandFlags.get(0) != null);

        if (isInvalidPara || isInvalidFlag) {
            throw new IncorrectParameterCountException();
        }

        return "new ProgressCommand()";
    }

    public static String createDoneCommandStub(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException, InvalidCommandException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_DONE_PARAMETER_LENGTH);
        boolean isInvalidFlag = (commandFlags.size() != COMMAND_DONE_FLAG_LENGTH);

        if (isInvalidPara || isInvalidFlag) {
            throw new IncorrectParameterCountException();
        }

        String moduleGrade = extractModuleGrade(commandFlags);

        return String.format("new DoneCommand(%s, %s)", commandParameters.get(1), moduleGrade);
    }

    public static String createUpdateCommandStub(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_UPDATE_PARAMETER_LENGTH);
        boolean isInvalidFlag = (commandFlags.size() < COMMAND_UPDATE_FLAG_LENGTH);

        if (isInvalidPara || isInvalidFlag) {
            throw new IncorrectParameterCountException();
        }

        return String.format("new UpdateCommand(%s, %s)", commandParameters.get(1), commandFlags);
    }

    public static String createCapCommandStub(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_CAP_LENGTH);
        boolean isInvalidFlag = (commandFlags.get(0) != null);

        if (isInvalidPara || isInvalidFlag) {
            throw new IncorrectParameterCountException();
        }

        return "new CapCommand()";
    }

    public static String createExitCommandStub(ArrayList<String> commandParameters, ArrayList<String> commandFlags)
            throws IncorrectParameterCountException {
        boolean isInvalidPara = (commandParameters.size() != COMMAND_EXIT_LENGTH);
        boolean isInvalidFlag = (commandFlags.get(0) != null);

        if (isInvalidPara || isInvalidFlag) {
            throw new IncorrectParameterCountException();
        }

        return "new ExitCommand()";
    }

    public static String parseCommandStub(String line) throws InvalidCommandException, IncorrectParameterCountException,
            InvalidModuleTypeException, InputNotNumberException, InvalidListTypeException {
        if (line.trim().length() == 0) {
            throw new InvalidCommandException();
        }
        ArrayList<String> commands = getCommand(line);
        ArrayList<String> commandParameters = getCommandParameters(commands);
        String command = commandParameters.get(0).toLowerCase();
        ArrayList<String> commandFlags = getCommandFlag(commands);

        switch (command) {
        case COMMAND_ADD:
            return createAddCommandStub(commandParameters, commandFlags);
        case COMMAND_DELETE:
            return createDeleteCommandStub(commandParameters, commandFlags);
        case COMMAND_LIST:
            return createListCommandStub(commandParameters, commandFlags);
        case COMMAND_PROGRESS:
            return createProgressCommandStub(commandParameters, commandFlags);
        case COMMAND_UPDATE:
            return createUpdateCommandStub(commandParameters, commandFlags);
        case COMMAND_CAP:
            return createCapCommandStub(commandParameters, commandFlags);
        case COMMAND_DONE:
            return createDoneCommandStub(commandParameters, commandFlags);
        case COMMAND_EXIT:
            return createExitCommandStub(commandParameters, commandFlags);
        default:
            throw new InvalidCommandException();
        }
    }
}