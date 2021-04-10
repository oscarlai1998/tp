package seedu.igraduate.logic.command;

import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.SaveModuleFailException;
import seedu.igraduate.exception.InvalidModuleGradeException;
import seedu.igraduate.exception.PrerequisiteNotMetException;
import seedu.igraduate.exception.PrerequisiteNotFoundException;
import seedu.igraduate.exception.MarkCompletedModuleException;

import seedu.igraduate.storage.Storage;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.ui.Ui;

import seedu.igraduate.model.module.Module;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Handles done command.
 */
public class DoneCommand extends Command {
    /**
     * Module code of the module to be marked as taken.
     */
    protected String moduleCode;
    /**
     * Module grade of the module to be marked as taken.
     */
    protected String moduleGrade;

    private static final Logger LOGGER = Logger.getLogger(DoneCommand.class.getName());

    /**
     * Constructs a new DoneCommand object.
     * 
     * @param moduleCode Module code of the module to be marked as done.
     * @param moduleGrade Module grade of the module.
     */
    public DoneCommand(String moduleCode, String moduleGrade) {
        this.moduleCode = moduleCode;
        this.moduleGrade = moduleGrade;
    }

    /**
     * Retrieves the module code given in the user input. 
     * 
     * @return module code. 
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Retrieves module grade given in the user input. 
     * 
     * @return module grade. 
     */
    public String getModuleGrade() {
        return moduleGrade;
    }

    /**
     * Marks the provided module as done and set the corresponding grade.
     * Prints mark as taken message after module is marked as taken and grade is assigned.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     * @throws SaveModuleFailException If module data fails to save to file.
     * @throws ModuleNotFoundException If module is not found in module list.
     * @throws InvalidModuleGradeException If grade from user input is invalid.
     * @throws PrerequisiteNotMetException If module's prerequisites have not been marked as done.
     * @throws PrerequisiteNotFoundException If the module's prerequisite is not found.
     * @throws MarkCompletedModuleException If module is already marked as done.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage)
        throws SaveModuleFailException, ModuleNotFoundException, InvalidModuleGradeException,
        PrerequisiteNotMetException, PrerequisiteNotFoundException, MarkCompletedModuleException {
        LOGGER.log(Level.INFO, "Executing done command...");
        try {
            Module module = moduleList.getModuleByCode(getModuleCode());
            moduleList.markAsTaken(module);
            moduleList.setGrade(module, getModuleGrade());
            storage.saveModulesToFile(moduleList);
            ui.printMarkAsTakenMessage(module);
            LOGGER.log(Level.INFO, String.format("Successfully marked %s module as taken.", getModuleCode()));
        } catch (ModuleNotFoundException e) {
            LOGGER.log(Level.WARNING, "Failed to mark non-existence module as taken.", e);
            throw new ModuleNotFoundException();
        } finally {
            LOGGER.log(Level.INFO, "End of done command execution.");
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
