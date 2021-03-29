package seedu.igraduate.exception;

/**
 * Exception is thrown if modular credit input is negative.
 */
public class InvalidModularCreditException extends Exception {
    public static final String INVALID_MODULAR_CREDIT_ERROR_MESSAGE = "Invalid modular credit input. " +
        "Please enter a positive number.";

    public InvalidModularCreditException() {
        super(INVALID_MODULAR_CREDIT_ERROR_MESSAGE);
    }
}
