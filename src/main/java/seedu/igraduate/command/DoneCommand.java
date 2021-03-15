package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;

import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.SaveModuleFailException;

import seedu.igraduate.module.Module;

import java.util.logging.*;

/**
 * Handles done command.
 */
public class DoneCommand extends Command {
    protected String moduleCode;
    protected String moduleGrade;

    private static final Logger logger = Logger.getLogger(DoneCommand.class.getName());

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
     * Mark the provided module as done and set the corresponding grade.
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
        try {
            Module module = moduleList.getByCode(getModuleCode());
            logger.log(Level.INFO, "moduleList.getByCode success");
            moduleList.markAsTaken(module);
            logger.log(Level.INFO, "moduleList.markAsDone success");
            moduleList.setGrade(module, getModuleGrade());
            logger.log(Level.INFO, "moduleList.setGrade success");
            storage.saveModulesToFile(moduleList);
            logger.log(Level.INFO, "storage.saveModulesToFile success");
            ui.printMarkAsTakenMessage(module);
        } catch (ModuleNotFoundException e) {
            logger.log(Level.WARNING, "DoneCommand execution failed. Check which of the following"
                    + " success log messages are missing: getByCode, markAsDone, setGrade, saveModulesToFile");
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
