package seedu.igraduate.exception;

/**
 * The exception is thrown if module grade input is invalid.
 */
public class InvalidModuleGradeException extends Exception {
    public static final String INVALID_MODULE_GRADE_ERROR_MESSAGE = "The module grade you have"
            + " entered is invalid. \nThe supported module types for add are: "
        + "A+, A, A-, B+, B, B-, C+, C, D+, D, F, S, U, CS, CU.";

    //@@author LJ-37
    public InvalidModuleGradeException() {
        super(INVALID_MODULE_GRADE_ERROR_MESSAGE);
    }
}
