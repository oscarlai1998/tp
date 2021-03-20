package seedu.igraduate.exception;

/**
 * The exception is thrown if the module being updated (on grade) has not been completed.  
 */
public class ModuleNotCompleteException extends Exception {
    public static final String MODULE_NOT_COMPLETED_EXCEPTION = "Module has not been completed, "
            + "no changes made. \n";

    public ModuleNotCompleteException() {
        super(MODULE_NOT_COMPLETED_EXCEPTION);
    }
}
