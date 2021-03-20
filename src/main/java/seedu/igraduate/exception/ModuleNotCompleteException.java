package seedu.igraduate.exception;

public class ModuleNotCompleteException extends Exception {
    public static final String MODULE_NOT_COMPLETED_EXCEPTION = "Module has not been completed, "
            + "no changes made. \n";

    public ModuleNotCompleteException() {
        super(MODULE_NOT_COMPLETED_EXCEPTION);
    }
}
