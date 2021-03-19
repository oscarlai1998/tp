package seedu.igraduate.exception;

/**
 * The exception is thrown if the pre-requisite module cannot be matched.
 */
public class PrerequisiteNotFoundException extends Exception {
    public static final String PREREQUISITE_NOT_FOUND_ERROR_MESSAGE = "The pre-requisite module you have entered"
            + " does not exists. \nPlease double check and try again.";

    public PrerequisiteNotFoundException() {
        super(PREREQUISITE_NOT_FOUND_ERROR_MESSAGE);
    }
}
