package seedu.igraduate.exception;

/**
 * The exception is thrown if the prerequisite module of a completed module is incomplete. 
 */
public class PrereqIncompleteException extends Exception {
    public static final String PREREQ_INCOMPLETE_ERROR_MESSAGE = "The prerequisites you have entered"
            + " has not been completed. \nPlease ensure that the prerequisites are completed before adding to"
            + " a completed module.";

    public PrereqIncompleteException() {
        super(PREREQ_INCOMPLETE_ERROR_MESSAGE);
    }
}