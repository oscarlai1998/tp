package seedu.igraduate.exception;

public class IllegalParametersException extends Exception {
    public static final String ILLEGAL_PARAMETERS_ERROR_MESSAGE = "The module code and type cannot be changed. "
            + "Please consider deleting the module and " + System.lineSeparator() + "recreating instead. ";

    public IllegalParametersException() {
        super(ILLEGAL_PARAMETERS_ERROR_MESSAGE);
    }
}
