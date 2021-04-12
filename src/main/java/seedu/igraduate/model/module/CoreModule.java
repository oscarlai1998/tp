package seedu.igraduate.model.module;

import java.util.ArrayList;

/**
 * Handles core modules. 
 */
public class CoreModule extends Module {
    //@@author kewenlok
    /**
     * Constructs a new CoreModule object.
     *
     * @param code                 Module code of the module object.
     * @param name                 Module name of the module object.
     * @param credit               Modular credit of the module object.
     * @param status               Status of the module, whether it is "taken" or "not taken".
     * @param grade                Default module grade when module is created.
     * @param prerequisites        Prerequisites for the module object.
     * @param untakenPrerequisites Unsatisfied prerequisites for module object.
     */
    public CoreModule(String code, String name, double credit, String status, String grade,
                      ArrayList<String> prerequisites, ArrayList<String> untakenPrerequisites) {
        super(code, name, credit, status, grade, prerequisites, untakenPrerequisites);
    }

    @Override
    public String toString() {
        return "[C]" + super.toString();
    }
}
