package seedu.igraduate.exception;

public class ModularCreditExceedsLimit extends Exception {
    private static final String MC_EXCEED_LIMIT_ERROR_MESSAGE = "Modular Credit exceeds"
            + " limit of 160MCs. \nPlease dobule check and try again.";
    public ModularCreditExceedsLimit() {
        super(MC_EXCEED_LIMIT_ERROR_MESSAGE);
    }
}
