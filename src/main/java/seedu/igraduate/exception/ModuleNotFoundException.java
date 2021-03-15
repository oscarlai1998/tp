package seedu.igraduate.exception;

/**
 * The exception is thrown if the module cannot be matched. 
 */
public class ModuleNotFoundException extends Exception {
    public static final String MODULE_NOT_FOUND_ERROR_MESSAGE = "The module code you have entered"
            + " does not exists. \nPlease double check and try again.";

    public ModuleNotFoundException() {
        super(MODULE_NOT_FOUND_ERROR_MESSAGE);
    }
}
