package seedu.igraduate.exception;

public class ExistingModuleException extends Exception {
    private static final String EXISTING_MODULE_ERROR_MESSAGE = "The module code"
            + " already exists. \n";

    public ExistingModuleException() {
        super(EXISTING_MODULE_ERROR_MESSAGE);
    }
}
