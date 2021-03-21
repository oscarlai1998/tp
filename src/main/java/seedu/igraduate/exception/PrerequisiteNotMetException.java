package seedu.igraduate.exception;

/**
 * Exception is thrown if prerequisite of the module has not been completed.
 */
public class PrerequisiteNotMetException extends Exception {
    public static final String PREREQUISITE_NOT_MET_ERROR_MESSAGE = "You have not completed the prerequisites"
        + " of this module. \nPlease double check and try again.";

    public PrerequisiteNotMetException() {
        super(PREREQUISITE_NOT_MET_ERROR_MESSAGE);
    }
}
