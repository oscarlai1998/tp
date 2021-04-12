package seedu.igraduate.exception;

import java.util.ArrayList;

/**
 * Exception is thrown if prerequisite of the module has not been completed.
 */
public class PrerequisiteNotMetException extends Exception {
    public static final String PREREQUISITE_NOT_MET_ERROR_MESSAGE = "You have not completed the prerequisites"
        + " for %s!\nList of prerequisites required for %s:\n" + "%s";

    private final String moduleCode;
    private final ArrayList<String> prerequisites;

    //@@author oscarlai1998
    public PrerequisiteNotMetException(String moduleCode, ArrayList<String> prerequisites) {
        super(String.format(PREREQUISITE_NOT_MET_ERROR_MESSAGE, moduleCode, moduleCode,
                convertPrerequisiteListToString(prerequisites)));
        this.moduleCode = moduleCode;
        this.prerequisites = prerequisites;

    }

    //@@author fupernova
    /**
     * Converts all prerequisites not taken into string form with comma as separator.
     *
     * @param prerequisites Array list of prerequisites not taken.
     * @return String form of prerequisites.
     */
    private static String convertPrerequisiteListToString(ArrayList<String> prerequisites) {
        String prerequisitesNotTaken = "";
        for (int i = 1; i < prerequisites.size(); i++) {
            prerequisitesNotTaken += prerequisites.get(i);
            prerequisitesNotTaken += ", ";
        }
        prerequisitesNotTaken += prerequisites.get(prerequisites.size() - 1);

        return prerequisitesNotTaken;
    }
}
