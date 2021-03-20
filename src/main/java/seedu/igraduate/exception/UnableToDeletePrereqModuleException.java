package seedu.igraduate.exception;

import java.util.ArrayList;

/**
 * This exception is thrown when user tries to delete a pre-requisite module.
 */
public class UnableToDeletePrereqModuleException extends Exception {
    public static final String UNABLE_TO_DELETE_PREREQ_MODULE_ERROR_MESSAGE = "Deletion failed...The " +
            "module you are trying to delete is a pre-requisite module of: \n";

    public UnableToDeletePrereqModuleException(ArrayList<String> modules) {
        super(UNABLE_TO_DELETE_PREREQ_MODULE_ERROR_MESSAGE + modules);
    }
}
