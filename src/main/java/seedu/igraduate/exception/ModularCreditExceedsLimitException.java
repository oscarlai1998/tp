package seedu.igraduate.exception;

/**
 * The exception is thrown if the module limit is reached.
 */
public class ModularCreditExceedsLimitException extends Exception {
    private static final String MC_EXCEED_LIMIT_ERROR_MESSAGE = "Modular credit exceeds"
            + " limit of 160MCs. \nPlease double check and try again.";

    public ModularCreditExceedsLimitException() {
        super(MC_EXCEED_LIMIT_ERROR_MESSAGE);
    }
}
