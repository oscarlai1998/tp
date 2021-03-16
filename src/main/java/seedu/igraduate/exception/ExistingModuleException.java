package seedu.igraduate.exception;

/**
 * The exception is thrown if the module code input already exists. 
 */
public class ExistingModuleException extends Exception {
    public static final String EXISTING_MODULE_ERROR_MESSAGE = "The module code"
            + " already exists.";

    public ExistingModuleException() {
        super(EXISTING_MODULE_ERROR_MESSAGE);
    }
}
