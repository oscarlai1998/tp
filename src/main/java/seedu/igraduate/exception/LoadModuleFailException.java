package seedu.igraduate.exception;

/**
 * The exception is thrown if module cannot be loaded properly.
 */
public class LoadModuleFailException extends Exception {
    private static final String LOAD_MODULE_FAIL_ERROR_MESSAGE = "Oops! An error occur when"
            + " trying to import module data from file :(.";

    public LoadModuleFailException() {
        super(LOAD_MODULE_FAIL_ERROR_MESSAGE);
    }
}

