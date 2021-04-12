package seedu.igraduate.exception;

/**
 * The exception is thrown if the parameters given is incorrect. 
 */
public class IncorrectParameterCountException extends Exception {
    public static final String INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE = "The number of parameters"
            + " provided is incorrect. \nPlease double check and try again.";

    //@@author kewenlok
    public IncorrectParameterCountException() {
        super(INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE);
    }
}
