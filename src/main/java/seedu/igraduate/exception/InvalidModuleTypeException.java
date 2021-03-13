package seedu.igraduate.exception;

public class InvalidModuleTypeException extends Exception {
    private static final String INVALID_MODULE_TYPE_ERROR_MESSAGE = "The module type you have"
            + " entered is invalid. \nThe supported module types are: core, ge, math, ue.";

    public InvalidModuleTypeException() {
        super(INVALID_MODULE_TYPE_ERROR_MESSAGE);
    }
}
