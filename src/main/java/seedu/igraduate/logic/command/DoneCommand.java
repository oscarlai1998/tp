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
    protected String moduleCode;
    protected String moduleGrade;

    private static final Logger LOGGER = Logger.getLogger(DoneCommand.class.getName());

    /**
     * Child class of the command class that contains the module code and grade. 
     * Marks a module as done with the grade obtained. 
     * 
     * @param moduleCode module code. 
     * @param moduleGrade grade obtained in the module. 
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
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     * @throws SaveModuleFailException If module data fails to save to file.
     * @throws ModuleNotFoundException If module is not found in module list.
     * @throws InvalidModuleGradeException If grade from user input is invalid.
     * @throws PrerequisiteNotMetException If module's prerequistes have not been marked as done.
     * @throws MarkCompletedModuleException If module is already marked as done.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage)
        throws SaveModuleFailException, ModuleNotFoundException, InvalidModuleGradeException,
        PrerequisiteNotMetException, PrerequisiteNotFoundException, MarkCompletedModuleException {
        LOGGER.log(Level.INFO, "Executing done command...");
        try {
            Module module = moduleList.getByCode(getModuleCode());
            if (!Module.isGradeValid(moduleGrade)) {
                LOGGER.log(Level.INFO, "Invalid grade input.");
                throw new InvalidModuleGradeException();
            }
            if (!moduleList.isModuleValid(module)) {
                LOGGER.log(Level.INFO, "Prerequisites check failed.");
                throw new PrerequisiteNotMetException(module.getCode(), module.getUntakenPreRequisites());
            }
            if (module.isDone()) {
                LOGGER.log(Level.INFO, "Trying to mark an already done module as done.");
                throw new MarkCompletedModuleException();
            }
            moduleList.markAsTaken(module);
            moduleList.setGrade(module, getModuleGrade());
            storage.saveModulesToFile(moduleList); //update json list
            ui.printMarkAsTakenMessage(module); //done message
            LOGGER.log(Level.INFO, String.format("Successfully marked %s module as taken.", getModuleCode()));
        } catch (ModuleNotFoundException | PrerequisiteNotMetException exception) {
            LOGGER.log(Level.WARNING, "Failed to mark non-existence module as taken.", exception);
            throw exception;
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
