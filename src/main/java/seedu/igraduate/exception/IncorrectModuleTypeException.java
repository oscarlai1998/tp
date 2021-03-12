package seedu.igraduate.exception;

public class IncorrectModuleTypeException extends Exception {
    private static final String INCORRECT_MODULE_TYPE_ERROR_MESSAGE = "The module type indicated"
            + " is incorrect. \nPlease double check and try again.";

    public IncorrectModuleTypeException() {
        super(INCORRECT_MODULE_TYPE_ERROR_MESSAGE);
    }
}
