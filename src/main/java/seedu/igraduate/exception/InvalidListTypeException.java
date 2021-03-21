package seedu.igraduate.exception;

public class InvalidListTypeException  extends Exception {
    public static final String INVALID_LIST_TYPE_ERROR_MESSAGE = "The list type you have"
        + " entered is invalid. \nThe supported list types for list are: all, incomplete and complete";

    public InvalidListTypeException() {
        super(INVALID_LIST_TYPE_ERROR_MESSAGE);
    }
}
