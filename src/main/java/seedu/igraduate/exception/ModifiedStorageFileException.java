package seedu.igraduate.exception;

public class ModifiedStorageFileException extends Exception {
    public static final String MODIFIED_STORAGE_FILE_EXCEPTION = "Unauthorised changes in "
            + "storage file detected, using new storage file." + System.lineSeparator()
            + "Note: If you wish to attempt to fix the configuration, exit program immediately. "
            + System.lineSeparator() + "Do not perform any commands or you will lose the original storage file. ";

    public ModifiedStorageFileException() {
        super(MODIFIED_STORAGE_FILE_EXCEPTION);
    }
}