package seedu.igraduate.exception;

/**
 * The exception is thrown if module data file is not found.
 */
public class DataFileNotFoundException extends Exception {
    private static final String DATA_FILE_NOT_FOUND_ERROR_MESSAGE = "Starting without existing"
            + " module data...\nInitializing new module list...";

    //@@author kewenlok
    public DataFileNotFoundException() {
        super(DATA_FILE_NOT_FOUND_ERROR_MESSAGE);
    }
}

