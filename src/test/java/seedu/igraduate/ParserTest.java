package seedu.igraduate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.igraduate.command.AddCommand;
import seedu.igraduate.command.ListCommand;
import seedu.igraduate.command.ProgressCommand;
import seedu.igraduate.command.DoneCommand;
import seedu.igraduate.command.DeleteCommand;

import seedu.igraduate.exception.IncorrectParameterCountException;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.InvalidModuleTypeException;

public class ParserTest {

    private static final String INVALID_COMMAND_MESSAGE = "The command you have entered is incorrect. \n"
            + "Please double check and try again.";
    private static final String INCORRECT_PARAMETER_NUMBER_MESSAGE = "The number of parameters"
            + " provided is incorrect. \nPlease double check and try again.";

    @Test
    void parseCommand_emptyCommand_exceptionThrown() {
        String line = "";
        Exception exception = assertThrows(InvalidCommandException.class, () -> Parser.parseCommand(line));
        assertEquals(INVALID_COMMAND_MESSAGE, exception.getMessage());
    }

    @Test
    void createAddCommand_appropriateParameters_success() throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException {
        String line = "add Programming Methodology -t core -mc 4 -c CS1010";
        assertEquals(AddCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createAddCommand_parametersWithSpacing_success() throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException {
        String line = "add Programming Methodology    -t      core       -mc      4      -c            CS1010";
        assertEquals(AddCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createAddCommand_tooManyParameters_exceptionThrown() {
        String line = "Add Introduction to Information Security -t core -mc 4 -c CS2107 -n prefab sprout";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(INCORRECT_PARAMETER_NUMBER_MESSAGE, exception.getMessage());
    }

    @Test
    void createAddCommand_tooFewParameters_exceptionThrown() {
        String line = "Add Introduction to Information Security -t core -mc  -c CS2107";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(INCORRECT_PARAMETER_NUMBER_MESSAGE, exception.getMessage());
    }

    @Test
    void createDeleteCommand_appropriateParameters_success() throws InvalidCommandException,
            InvalidModuleTypeException, InputNotNumberException, IncorrectParameterCountException {
        String line = "Delete CS2107";
        assertEquals(DeleteCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createDeleteCommand_tooFewParameters_exceptionThrown() {
        String line = "Delete";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(INCORRECT_PARAMETER_NUMBER_MESSAGE, exception.getMessage());
    }

    @Test
    void createDeleteCommand_extraFlag_exceptionThrown() {
        String line = "Delete CS2106 -mc 4";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(INCORRECT_PARAMETER_NUMBER_MESSAGE, exception.getMessage());
    }

    @Test
    void createDoneCommand_appropriateParameters_success() throws InvalidCommandException,
            InvalidModuleTypeException, InputNotNumberException, IncorrectParameterCountException {
        String line = "done CS2107 -g A+";
        assertEquals(DoneCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createDoneCommand_parametersWithSpacing_success() throws InvalidCommandException,
            InvalidModuleTypeException, InputNotNumberException, IncorrectParameterCountException {
        String line = "done      CS2107           -g                             A+";
        assertEquals(DoneCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createListCommand_invalidParameter_exceptionThrown() {
        String line = "done";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(INCORRECT_PARAMETER_NUMBER_MESSAGE, exception.getMessage());
    }

    @Test
    void createDoneCommand_tooFewParameters_exceptionThrown() {
        String line = "done CS2107";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(INCORRECT_PARAMETER_NUMBER_MESSAGE, exception.getMessage());
    }

    @Test
    void createProgressCommand_appropriateParameters_success() throws InvalidCommandException,
            InvalidModuleTypeException, InputNotNumberException, IncorrectParameterCountException {
        String line = "progress";
        assertEquals(ProgressCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createProgressCommand_extraFlag_exceptionThrown() {
        String line = "progress -mc";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(INCORRECT_PARAMETER_NUMBER_MESSAGE, exception.getMessage());
    }

    @Test
    void createListCommand_appropriateParameters_success() throws InvalidCommandException,
            InvalidModuleTypeException, InputNotNumberException, IncorrectParameterCountException {
        String line = "list";
        assertEquals(ListCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createListCommand_tooManyParameters_exception() {
        String line = "list all";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(INCORRECT_PARAMETER_NUMBER_MESSAGE, exception.getMessage());
    }
}