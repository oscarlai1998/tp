package seedu.igraduate.exception;

/**
 * The exception is thrown if the command input was invalid. 
 */
public class InvalidCommandException extends Exception {
    public static final String INVALID_COMMAND_ERROR_MESSAGE = "The command you have entered"
            + " is incorrect.\n%s";

    //@@author kewenlok
    public InvalidCommandException(String errorMessage) {
        super(String.format(INVALID_COMMAND_ERROR_MESSAGE, errorMessage));
    }
}
