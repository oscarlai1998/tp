package seedu.igraduate.exception;

/**
 * The exception is thrown if the module code is invalid.
 */
public class InvalidModuleCodeException extends Exception {
    private static final String INVALID_MODULE_CODE_ERROR_MESSAGE = "The module code you have"
            + " entered is invalid.\nPlease make sure the module code follows NUS standard.";

    public InvalidModuleCodeException() {
        super(INVALID_MODULE_CODE_ERROR_MESSAGE);
    }
}
