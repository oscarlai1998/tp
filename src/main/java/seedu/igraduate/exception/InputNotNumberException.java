package seedu.igraduate.exception;

/**
 * The exception is thrown if input is not an integer.
 */
public class InputNotNumberException extends Exception {
    public static final String INPUT_NOT_NUMBER_ERROR_MESSAGE = "The input for the following"
            + " parameter provided is not a number: \n";

    public InputNotNumberException(String parameterType) {
        super(INPUT_NOT_NUMBER_ERROR_MESSAGE + parameterType);
    }
}
