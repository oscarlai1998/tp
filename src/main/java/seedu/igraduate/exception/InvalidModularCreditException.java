package seedu.igraduate.exception;

/**
 * Exception is thrown if modular credit input is not in valid range.
 */
public class InvalidModularCreditException extends Exception {
    public static final String INVALID_MODULAR_CREDIT_ERROR_MESSAGE = "Invalid modular credit. "
        + "A modular credit must be an integer between 1 and 32 inclusive.";

    //@@author oscarlai1998
    public InvalidModularCreditException() {
        super(INVALID_MODULAR_CREDIT_ERROR_MESSAGE);
    }
}
