package seedu.igraduate.logic.command;

import seedu.igraduate.exception.*;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.ui.Ui;

/**
 * Parent class for more specific command child class. Contains basic
 * information applicable to all command-related classes.
 */
public abstract class Command {
    /**
     * Executes command based on the corresponding command type. To be overriden.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui         User interface for printing result.
     * @param storage    Storage for storing module list data.
     * @throws SaveModuleFailException             If storage fail to save module
     *                                             data to disk.
     * @throws ModuleNotFoundException             If the module indicated does not
     *                                             exists in module list.
     * @throws InvalidModuleTypeException          If the module type is invalid.
     * @throws ModularCreditExceedsLimitException  If the total completed modular
     *                                             credits exceed 160.
     * @throws ExistingModuleException             If the module to be added already
     *                                             exists in module list.
     * @throws InputNotNumberException             If the module credit input is not
     *                                             an integer.
     * @throws NumberFormatException               If the module credit failed to
     *                                             convert into integer.
     * @throws ModuleNotCompleteException          If the module is incomplete.
     * @throws InvalidModuleGradeException         If the module grade is invalid.
     * @throws UnableToDeletePrereqModuleException If the module is a pre-requisite
     *                                             of other modules.
     */
    public abstract void execute(ModuleList moduleList, Ui ui, Storage storage) throws SaveModuleFailException,
            ModuleNotFoundException, InvalidModuleTypeException, ModularCreditExceedsLimitException,
            ExistingModuleException, PrerequisiteNotFoundException, NumberFormatException,
            InputNotNumberException, ModuleNotCompleteException, InvalidModuleGradeException,
            UnableToDeletePrereqModuleException, InvalidListTypeException, PrerequisiteNotMetException,
            AddSelfToPrereqException;

    /**
     * Returns a flag indicating whether the program should terminate after
     * execution of current command.
     *
     * @return True if the program should be terminated after executing current
     *         command, False otherwise.
     */
    public abstract boolean isExit();
}
