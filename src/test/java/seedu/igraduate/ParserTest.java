package seedu.igraduate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.igraduate.command.AddCommand;
import seedu.igraduate.command.DeleteCommand;
import seedu.igraduate.command.DoneCommand;
import seedu.igraduate.command.ListCommand;
import seedu.igraduate.command.ProgressCommand;
import seedu.igraduate.command.CapCommand;

import seedu.igraduate.exception.IncorrectParameterCountException;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.InvalidModuleTypeException;

public class ParserTest {

    @Test
    void parseCommand_emptyCommand_exceptionThrown() {
        String line = "";
        Exception exception = assertThrows(InvalidCommandException.class, () -> Parser.parseCommand(line));
        assertEquals(InvalidCommandException.INVALID_COMMAND_ERROR_MESSAGE, exception.getMessage());
    }

    /****************************************** AddCommand tests. *************************************************/
    @Test
    void createAddCommand_appropriateParameters_success() 
            throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException {
        String line = "add Programming Methodology -t core -mc 4 -c CS1010";
        assertEquals(AddCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createAddCommand_parametersWithSpacing_success() 
            throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException {
        String line = "add Programming Methodology    -t      core       -mc      4      -c            CS1010";
        assertEquals(AddCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createAddCommand_tooManyParameters_exceptionThrown() {
        String line = "Add Introduction to Information Security -t core -mc 4 -c CS2107 -n prefab sprout";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, 
                exception.getMessage());
    }

    @Test
    void createAddCommand_tooFewParameters_exceptionThrown() {
        String line = "Add Introduction to Information Security -t core -mc  -c CS2107";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, 
                exception.getMessage());
    }

    @Test
    void createAddCommand_invalidCredits_exceptionThrown() {
        String line = "Add Introduction to Information Security -t core -mc n -c CS2107";
        Exception exception = assertThrows(InputNotNumberException.class,
            () -> Parser.parseCommand(line));
        assertEquals(InputNotNumberException.INPUT_NOT_NUMBER_ERROR_MESSAGE + "Modular credits : -mc", 
                exception.getMessage());
    }

    /****************************************** DeleteCommand tests. *************************************************/
    @Test
    void createDeleteCommand_appropriateParameters_success()
            throws InvalidCommandException, InvalidModuleTypeException, 
            InputNotNumberException, IncorrectParameterCountException {
        String line = "Delete CS2107";
        assertEquals(DeleteCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createDeleteCommand_tooFewParameters_exceptionThrown() {
        String line = "Delete";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, 
                exception.getMessage());
    }

    @Test
    void createDeleteCommand_extraFlag_exceptionThrown() {
        String line = "Delete CS2106 -mc 4";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, 
                exception.getMessage());
    }

    /****************************************** DoneCommand tests. *************************************************/
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
    void createDoneCommand_invalidParameter_exceptionThrown() {
        String line = "done";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE,
                exception.getMessage());
    }

    @Test
    void createDoneCommand_tooFewParameters_exceptionThrown() {
        String line = "done CS2107";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, 
                exception.getMessage());
    }

    /****************************************** ProgressCommand tests. ************************************************/
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
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, 
                exception.getMessage());
    }

    @Test
    void createProgressCommand_tooManyParameters_exceptionThrown() {
        String line = "progress testing";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, 
                exception.getMessage());
    }

    /****************************************** ListCommand tests. *************************************************/
    @Test
    void createListCommand_appropriateParameters_success() 
            throws InvalidCommandException, InvalidModuleTypeException, 
            InputNotNumberException, IncorrectParameterCountException {
        String line = "list complete";
        assertEquals(ListCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createListCommand_tooManyParameters_exception() {
        String line = "list all hoi";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, 
                exception.getMessage());
    }

    @Test
    void createListCommand_extraFlag_exceptionThrown() {
        String line = "list -mc";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, 
                exception.getMessage());
    }

    /****************************************** CapCommand tests. *************************************************/
    @Test
    void createCapCommand_appropriateParameters_success()
            throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException {
        String line = "cap";
        assertEquals(CapCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createCapCommand_tooManyParameters_exceptionThrow() {
        String line = "cap now";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE,
                exception.getMessage());
    }

    @Test
    void createCapCommand_extraFlag_exceptionThrown() {
        String line = "cap -t";
        Exception exception = assertThrows(IncorrectParameterCountException.class,
            () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE,
                exception.getMessage());
    }
}