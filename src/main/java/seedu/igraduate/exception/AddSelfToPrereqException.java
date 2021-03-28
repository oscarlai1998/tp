package seedu.igraduate.exception;

/**
 * This exception is thrown when user updates a module's list of prerequisites to include the module itself.
 */
public class AddSelfToPrereqException extends Exception {
    public static final String ADD_TO_SELF_MESSAGE = "Cannot add a module to its own list of prerequisites!!";

    public AddSelfToPrereqException() {
        super(ADD_TO_SELF_MESSAGE);
    }
}
