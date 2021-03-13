package seedu.igraduate.exception;

/**
 * This exception is thrown if the program fails to save data to file. 
 */
public class SaveModuleFailException extends Exception {
    private static final String SAVE_MODULE_FAIL_ERROR_MESSAGE = "Oops! An error occur when"
            + " trying to save module data to file :(.";

    public SaveModuleFailException() {
        super(SAVE_MODULE_FAIL_ERROR_MESSAGE);
    }
}
