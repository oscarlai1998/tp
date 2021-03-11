package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.SaveModuleFailException;

/**
 * Parent class for more specific command child class.
 * Contains basic information applicable to all command-related classes.
 */
public abstract class Command {
    /**
     * Executes command based on the corresponding command type. To be overriden.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     */
    public abstract void execute(ModuleList moduleList, Ui ui, Storage storage)
            throws SaveModuleFailException, ModuleNotFoundException;

    /**
     * Returns a flag indicating whether the program should terminate after execution of current command.
     *
     * @return True if the program should be terminated after executing current command, False otherwise.
     */
    public abstract boolean isExit();
}
