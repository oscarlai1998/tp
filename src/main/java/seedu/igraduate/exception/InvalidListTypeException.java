package seedu.igraduate.exception;

/**
 * Exception is thrown if list command is not followed by: all, incomplete and complete.
 */
public class InvalidListTypeException  extends Exception {
    public static final String INVALID_LIST_TYPE_ERROR_MESSAGE = "The list type you have"
        + " entered is invalid. \nThe supported list types for list are: all, incomplete, complete"
        + ", available, core, elec, ge and math.";

    //@@author oscarlai1998
    public InvalidListTypeException() {
        super(INVALID_LIST_TYPE_ERROR_MESSAGE);
    }
}
