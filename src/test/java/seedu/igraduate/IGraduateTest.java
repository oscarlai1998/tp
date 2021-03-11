package seedu.igraduate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.igraduate.exception.InvalidCommandException;

class IGraduateTest {
    @Test
    void parseCommand_emptyCommand_exceptionThrown() {
        String line = "";
        Exception exception = assertThrows(InvalidCommandException.class, () -> Parser.parseCommand(line));
        assertEquals("The command you have entered" + " is incorrect. \nPlease double check and try again.",
                exception.getMessage());
    }
}
