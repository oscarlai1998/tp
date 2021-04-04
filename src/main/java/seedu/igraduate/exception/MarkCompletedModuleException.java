package seedu.igraduate.exception;

public class MarkCompletedModuleException extends Exception {
    public static final String MARK_COMPLETED_MODULE_ERROR_MESSAGE = "Cannot complete a module that is already marked "
            + "as done!";

    public MarkCompletedModuleException() {
        super(MARK_COMPLETED_MODULE_ERROR_MESSAGE);
    }
}
