package seedu.igraduate.exception;

/**
 * The exception is thrown if the json file has been corrupted.
 */
public class CorruptedStorageFileException extends Exception {
    public static final String MODIFIED_STORAGE_FILE_EXCEPTION = "Unsupported changes in "
            + "storage file detected, using new storage file." + System.lineSeparator()
            + "Note: If you wish to attempt to fix the configuration, exit program immediately. "
            + System.lineSeparator() + "Do not perform any commands or you will lose the original storage file. ";

    //@@author xseh
    public CorruptedStorageFileException() {
        super(MODIFIED_STORAGE_FILE_EXCEPTION);
    }
}