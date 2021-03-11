package seedu.igraduate.exception;

/**
 * The exception is thrown if module cannot be imported properly.
 */
public class ImportModuleFailException extends Exception {
    private static final String IMPORT_MODULE_FAIL_ERROR_MESSAGE = "Oops! An error occur when"
            + " trying to import module data from file :(.";

    public ImportModuleFailException() {
        super(IMPORT_MODULE_FAIL_ERROR_MESSAGE);
    }
}

