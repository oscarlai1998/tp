package seedu.igraduate.exception;

/**
 * The exception is thrown if module is already marked completed.
 */
public class MarkCompletedModuleException extends Exception {
    public static final String MARK_COMPLETED_MODULE_ERROR_MESSAGE = "Cannot complete a module that is already marked "
            + "as done!";

    //@@author fupernova
    public MarkCompletedModuleException() {
        super(MARK_COMPLETED_MODULE_ERROR_MESSAGE);
    }
}
