package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;

import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.SaveModuleFailException;

import seedu.igraduate.module.Module;

/**
 * Handles delete command.
 */
public class DeleteCommand extends Command {
    protected String moduleCode;

    public DeleteCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Deletes a module from moduleList.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     * @throws ModuleNotFoundException If the module specified does not exists.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage)
            throws ModuleNotFoundException, SaveModuleFailException {
        try {
            Module module = moduleList.getByCode(moduleCode);
            String moduleType = moduleList.getModuleType(module);
            moduleList.delete(module);
            storage.saveModulesToFile(moduleList);
            ui.printDeletedModuleSuccess(moduleCode, moduleType);
        } catch (ModuleNotFoundException e) {
            throw new ModuleNotFoundException();
        }
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
