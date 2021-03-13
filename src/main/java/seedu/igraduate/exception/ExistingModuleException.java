package seedu.igraduate.exception;

/**
 * The exception is thrown if the module code input already exists. 
 */
public class ExistingModuleException extends Exception {
    private static final String EXISTING_MODULE_ERROR_MESSAGE = "The module code"
            + " already exists. \nPlease double check and try again.";

    public ExistingModuleException() {
        super(EXISTING_MODULE_ERROR_MESSAGE);
    }
}
