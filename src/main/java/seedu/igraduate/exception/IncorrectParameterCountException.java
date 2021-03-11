package seedu.igraduate.exception;

public class IncorrectParameterCountException extends Exception {

    private static final String INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE = "The number of parameters" +
            "+ provided is incorrect. \nPlease double check and try again.";

    public IncorrectParameterCountException() {
        super(INCORRECT_PARAMETER_COUNT_ERROR_MESSAGE);
    }
}
