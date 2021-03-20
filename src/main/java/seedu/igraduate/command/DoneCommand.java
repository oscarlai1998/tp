package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;

import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.SaveModuleFailException;

import seedu.igraduate.module.Module;

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
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage)
            throws SaveModuleFailException, ModuleNotFoundException {
        LOGGER.log(Level.INFO, "Executing done command...");
        try {
            Module module = moduleList.getByCode(getModuleCode());
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
