package seedu.igraduate.logic.command;

import seedu.igraduate.storage.Storage;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.ui.Ui;

import seedu.igraduate.exception.InvalidListTypeException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles list command.
 */
public class ListCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(ListCommand.class.getName());

    /**
     * Scope of the module list to be printed.
     */
    private String scope;

    //@@author oscarlai1998
    /**
     * Constructs a new ListCommand object.
     *
     * @param scope The scope of list command.
     */
    public ListCommand(String scope) {
        this.scope = scope;
    }

    /**
     * Prints list of all modules based on the scope.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     * @throws InvalidListTypeException If the list option provided is invalid.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws InvalidListTypeException {
        LOGGER.log(Level.INFO, "Executing list command...");
        switch (scope) {
        case "all" :
            if (moduleList.isEmpty()) {
                assert moduleList.isEmpty() : "List should be empty";
                ui.printListEmptyMessage();
                LOGGER.log(Level.INFO, "No module found.");
            } else {
                ui.printEntireList(moduleList.getModules());
                LOGGER.log(Level.INFO, "Printed Entire List.");
            }
            break;
        case "complete" :
            if (moduleList.isCompletedModulesEmpty()) {
                ui.printCompleteListEmptyMessage();
                LOGGER.log(Level.INFO, "No completed modules.");
            } else {
                ui.printCompletedList(moduleList.getModules());
                LOGGER.log(Level.INFO, "Printed completed modules.");
            }
            break;
        case "incomplete":
            if (moduleList.isIncompletedModulesEmpty()) {
                ui.printIncompleteListEmptyMessage();
                LOGGER.log(Level.INFO, "No incomplete modules.");
            } else {
                ui.printIncompleteList(moduleList.getModules());
                LOGGER.log(Level.INFO, "Printed incomplete modules.");
            }
            break;
        //@@author kewenlok
        case "available":
            if (moduleList.isModuleAvailable()) {
                ui.printAvailableList(moduleList.getModules());
                LOGGER.log(Level.INFO, "Printed available modules.");
            } else {
                ui.printAvailableListEmptyMessage();
                LOGGER.log(Level.INFO, "No available modules.");
            }
            break;
        case "core":
            if (moduleList.isCoreModulesEmpty()) {
                ui.printCoreModuleListEmptyMessage();
                LOGGER.log(Level.INFO, "No core modules.");
            } else {
                ui.printCoreModuleList(moduleList.getModules());
                LOGGER.log(Level.INFO, "Printed core modules.");
            }
            break;
        case "elec":
            if (moduleList.isElectiveModulesEmpty()) {
                ui.printElectiveModuleListEmptyMessage();
                LOGGER.log(Level.INFO, "No elective modules.");
            } else {
                ui.printElectiveModuleList(moduleList.getModules());
                LOGGER.log(Level.INFO, "Printed elective modules.");
            }
            break;
        case "ge":
            if (moduleList.isGeModulesEmpty()) {
                ui.printGeModuleListEmptyMessage();
                LOGGER.log(Level.INFO, "No ge modules.");
            } else {
                ui.printGeModuleList(moduleList.getModules());
                LOGGER.log(Level.INFO, "Printed ge modules.");
            }
            break;
        case "math":
            if (moduleList.isMathModulesEmpty()) {
                ui.printMathModuleListEmptyMessage();
                LOGGER.log(Level.INFO, "No math modules.");
            } else {
                ui.printMathModuleList(moduleList.getModules());
                LOGGER.log(Level.INFO, "Printed math modules.");
            }
            break;
        default:
            LOGGER.log(Level.INFO, "Failed to print a valid list");
        }
        LOGGER.log(Level.INFO, "End of list command execution.");
    }

    /**
     * {@inheritDoc}
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
