package seedu.igraduate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.logic.parser.Parser;

class IGraduateTest {
    private Parser parser = new Parser();

    @Test
    void parseCommand_emptyCommand_exceptionThrown() {
        String line = "";
        Exception exception = assertThrows(InvalidCommandException.class, () -> parser.parseCommand(line));
        String expectedMessage = String.format(InvalidCommandException.INVALID_COMMAND_ERROR_MESSAGE,
                "You may type \"help\" to view manual for our available commands.");
        assertEquals(expectedMessage, exception.getMessage());
    }
}
