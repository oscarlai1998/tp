package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;

import seedu.igraduate.exception.*;

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
     * @throws SaveModuleFailException If storage fail to save module data to disk.
     * @throws ModuleNotFoundException If the module indicated does not exists in module list.
     * @throws InvalidModuleTypeException If the module type is invalid.
     * @throws ModularCreditExceedsLimitException If the total completed modular credits exceed 160.
     * @throws ExistingModuleException If the module to be added already exists in module list.
     * @throws PrerequisiteNotFoundException If any of the pre-requisite module does not exists.
     * @throws UnableToDeletePrereqModuleException If module is a pre-requisite module for other modules.
     * @throws InvalidModuleGradeException If the module grade is invalid.
     */
    public abstract void execute(ModuleList moduleList, Ui ui, Storage storage)
        throws SaveModuleFailException, ModuleNotFoundException, InvalidModuleTypeException,
            ModularCreditExceedsLimitException, ExistingModuleException, PrerequisiteNotFoundException,
            UnableToDeletePrereqModuleException, InvalidModuleGradeException;

    /**
     * Returns a flag indicating whether the program should terminate after execution of current command.
     *
     * @return True if the program should be terminated after executing current command, False otherwise.
     */
    public abstract boolean isExit();
}
