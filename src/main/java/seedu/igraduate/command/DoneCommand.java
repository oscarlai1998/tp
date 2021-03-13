package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;

import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.SaveModuleFailException;

import seedu.igraduate.module.Module;

/**
 * Handles done command.
 */
public class DoneCommand extends Command {
    protected String moduleCode;
    protected String moduleGrade;

    public DoneCommand(String moduleCode, String moduleGrade) {
        this.moduleCode = moduleCode;
        this.moduleGrade = moduleGrade;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleGrade() {
        return moduleGrade;
    }

    /**
     * Mark the provided module as done and set the corresponding grade.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     * @throws SaveModuleFailException If fail to save module data to file.
     * @throws ModuleNotFoundException If module is not found in module list.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage)
            throws SaveModuleFailException, ModuleNotFoundException {
        try {
            Module module = moduleList.getByCode(getModuleCode());
            moduleList.markAsTaken(module);
            moduleList.setGrade(module, getModuleGrade());
            storage.saveModulesToFile(moduleList);
            ui.printMarkAsTakenMessage(module);
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
