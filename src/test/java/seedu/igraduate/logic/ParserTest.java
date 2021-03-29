package seedu.igraduate.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.igraduate.logic.command.AddCommand;
import seedu.igraduate.logic.command.DeleteCommand;
import seedu.igraduate.logic.command.DoneCommand;
import seedu.igraduate.logic.command.ListCommand;
import seedu.igraduate.logic.command.ProgressCommand;
import seedu.igraduate.logic.command.UpdateCommand;
import seedu.igraduate.logic.command.CapCommand;

import seedu.igraduate.exception.IncorrectParameterCountException;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.InvalidModuleTypeException;
import seedu.igraduate.stub.ParserStub;
import seedu.igraduate.exception.InvalidListTypeException;

public class ParserTest {
    /*-------------------- Unit tests --------------------*/
    @Test
    void parseCommand_emptyCommand_exceptionThrown() {
        String line = "";
        Exception exception = assertThrows(InvalidCommandException.class, () -> Parser.parseCommand(line));
        assertEquals(InvalidCommandException.INVALID_COMMAND_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void parseCommand_invalidCommand_exceptionThrown() {
        String line = "search CS1010";
        Exception exception = assertThrows(InvalidCommandException.class, () -> Parser.parseCommand(line));
        assertEquals(InvalidCommandException.INVALID_COMMAND_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void parseAddCommand_appropriateParameters_success()
            throws InvalidCommandException, IncorrectParameterCountException, InvalidModuleTypeException,
            InputNotNumberException, InvalidListTypeException {

        String line = "add Introduction to Operating Systems -t core -mc 4 -c CS2106 -p CS1010,CS2100";
        assertEquals("new AddCommand(CS2106, Introduction to Operating Systems, core, 4.00, [CS1010, CS2100], "
                + "[CS1010, CS2100])", ParserStub.parseCommandStub(line));
    }

    @Test
    void parseDeleteCommand_appropriateParameters_success()
            throws InvalidCommandException, IncorrectParameterCountException, InvalidModuleTypeException,
            InputNotNumberException, InvalidListTypeException {
        String line = "delete CS1010";
        assertEquals("new DeleteCommand(CS1010)", ParserStub.parseCommandStub(line));
    }

    @Test
    void parseListCommand_appropriateParameters_success()
            throws InvalidCommandException, IncorrectParameterCountException, InvalidModuleTypeException,
            InputNotNumberException, InvalidListTypeException {
        String line = "list all";
        assertEquals("new ListCommand(all)", ParserStub.parseCommandStub(line));
    }

    @Test
    void parseProgressCommand_appropriateParameters_success()
            throws InvalidCommandException, IncorrectParameterCountException, InvalidModuleTypeException,
            InputNotNumberException, InvalidListTypeException {
        String line = "progress";
        assertEquals("new ProgressCommand()", ParserStub.parseCommandStub(line));
    }

    @Test
    void parseDoneCommand_appropriateParameters_success()
            throws InvalidCommandException, IncorrectParameterCountException, InvalidModuleTypeException,
            InputNotNumberException, InvalidListTypeException {
        String line = "done CS1010 -g A+";
        assertEquals("new DoneCommand(CS1010, A+)", ParserStub.parseCommandStub(line));
    }

    @Test
    void parseUpdateCommand_appropriateParameters_success()
            throws InvalidCommandException, IncorrectParameterCountException, InvalidModuleTypeException,
            InputNotNumberException, InvalidListTypeException {
        String line = "update CS2106 -n Intro to OS -mc 2 -g A- -p CS1010,CS2100";
        assertEquals("new UpdateCommand(CS2106, [-n, Intro, to, OS, -mc, 2, -g, A-, -p, CS1010,CS2100])",
                ParserStub.parseCommandStub(line));
    }

    @Test
    void parseCapCommand_appropriateParameters_success()
            throws InvalidCommandException, IncorrectParameterCountException, InvalidModuleTypeException,
            InputNotNumberException, InvalidListTypeException {
        String line = "cap";
        assertEquals("new CapCommand()", ParserStub.parseCommandStub(line));
    }

    @Test
    void parseExitCommand_appropriateParameters_success()
            throws InvalidCommandException, IncorrectParameterCountException, InvalidModuleTypeException,
            InputNotNumberException, InvalidListTypeException {
        String line = "exit";
        assertEquals("new ExitCommand()", ParserStub.parseCommandStub(line));
    }

    /*-------------------- Integration tests --------------------*/

    /* Add Command */
    @Test
    void createAddCommand_appropriateParameters_success() throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException, InvalidListTypeException {
        String line = "add Programming Methodology -t core -mc 4 -c CS1010";
        assertEquals(AddCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createAddCommand_parametersWithSpacing_success() throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException, InvalidListTypeException {
        String line = "add Programming Methodology    -t      core       -mc      4      -c            CS1010";
        assertEquals(AddCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createAddCommand_tooManyParameters_exceptionThrown() {
        String line = "Add Introduction to Information Security -t core -mc 4 -c CS2107 -n prefab sprout";
        Exception exception = assertThrows(IncorrectParameterCountException.class, () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void createAddCommand_tooFewParameters_exceptionThrown() {
        String line = "Add Introduction to Information Security -t core -mc  -c CS2107";
        Exception exception = assertThrows(IncorrectParameterCountException.class, () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void createAddCommand_invalidCredits_exceptionThrown() {
        String line = "Add Introduction to Information Security -t core -mc n -c CS2107";
        Exception exception = assertThrows(InputNotNumberException.class, () -> Parser.parseCommand(line));
        assertEquals(InputNotNumberException.INPUT_NOT_NUMBER_ERROR_MESSAGE + "Modular credits : -mc",
                exception.getMessage());
    }

    /* Delete Command */
    @Test
    void createDeleteCommand_appropriateParameters_success() throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException, InvalidListTypeException {
        String line = "Delete CS2107";
        assertEquals(DeleteCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createDeleteCommand_tooFewParameters_exceptionThrown() {
        String line = "Delete";
        Exception exception = assertThrows(IncorrectParameterCountException.class, () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void createDeleteCommand_extraFlag_exceptionThrown() {
        String line = "Delete CS2106 -mc 4";
        Exception exception = assertThrows(IncorrectParameterCountException.class, () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void createDoneCommand_appropriateParameters_success() throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException, InvalidListTypeException {
        String line = "done CS2107 -g A+";
        assertEquals(DoneCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createDoneCommand_parametersWithSpacing_success() throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException, InvalidListTypeException {
        String line = "done      CS2107           -g                             A+";
        assertEquals(DoneCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createDoneCommand_invalidParameter_exceptionThrown() {
        String line = "done";
        Exception exception = assertThrows(IncorrectParameterCountException.class, () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void createDoneCommand_tooFewParameters_exceptionThrown() {
        String line = "done CS2107";
        Exception exception = assertThrows(IncorrectParameterCountException.class, () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, exception.getMessage());
    }

    /* Progress Command */
    @Test
    void createProgressCommand_appropriateParameters_success()
            throws InvalidCommandException, InvalidModuleTypeException, InputNotNumberException,
            IncorrectParameterCountException, InvalidListTypeException {
        String line = "progress";
        assertEquals(ProgressCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createProgressCommand_extraFlag_exceptionThrown() {
        String line = "progress -mc";
        Exception exception = assertThrows(IncorrectParameterCountException.class, () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void createProgressCommand_tooManyParameters_exceptionThrown() {
        String line = "progress testing";
        Exception exception = assertThrows(IncorrectParameterCountException.class, () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, exception.getMessage());
    }

    /* ListCommand */
    @Test
    void createListCommand_appropriateParameters_success() throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException, InvalidListTypeException {
        String line = "list complete";
        assertEquals(ListCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createListCommand_tooManyParameters_exception() {
        String line = "list all hoi";
        Exception exception = assertThrows(InvalidListTypeException.class, () -> Parser.parseCommand(line));
        assertEquals(InvalidListTypeException.INVALID_LIST_TYPE_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void createListCommand_extraFlag_exceptionThrown() {
        String line = "list -mc";
        Exception exception = assertThrows(IncorrectParameterCountException.class, () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, exception.getMessage());
    }

    /* CapCommand tests */
    @Test
    void createCapCommand_appropriateParameters_success() throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException, InvalidListTypeException {
        String line = "cap";
        assertEquals(CapCommand.class, Parser.parseCommand(line).getClass());
    }

    @Test
    void createCapCommand_tooManyParameters_exceptionThrow() {
        String line = "cap now";
        Exception exception = assertThrows(IncorrectParameterCountException.class, () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void createCapCommand_extraFlag_exceptionThrown() {
        String line = "cap -t";
        Exception exception = assertThrows(IncorrectParameterCountException.class, () -> Parser.parseCommand(line));
        assertEquals(IncorrectParameterCountException.INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE, exception.getMessage());
    }

    /* UpdateCommand tests */
    @Test
    void createUpdateCommand_appropriateParameters_success() throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException, InvalidListTypeException {
        String line = "update CS2100 -n Introduction to Computer Organisation -mc 4";
        assertEquals(UpdateCommand.class, Parser.parseCommand(line).getClass());
    }
}