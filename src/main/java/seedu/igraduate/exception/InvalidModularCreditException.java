package seedu.igraduate.exception;

/**
 * Exception is thrown if modular credit input is negative.
 */
public class InvalidModularCreditException extends Exception {
    public static final String INVALID_MODULAR_CREDIT_ERROR_MESSAGE = "Invalid modular credit. "
        + "A valid modular credit should be between 0 and 32 inclusive.";

    public InvalidModularCreditException() {
        super(INVALID_MODULAR_CREDIT_ERROR_MESSAGE);
    }
}
