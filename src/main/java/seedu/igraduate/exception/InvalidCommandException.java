package seedu.igraduate.exception;

/**
 * The exception is thrown if the command input was invalid. 
 */
public class InvalidCommandException extends Exception {
    private static final String INVALID_COMMAND_ERROR_MESSAGE = "The command you have entered"
            + " is incorrect. \nPlease double check and try again.";

    public InvalidCommandException() {
        super(INVALID_COMMAND_ERROR_MESSAGE);
    }
}
