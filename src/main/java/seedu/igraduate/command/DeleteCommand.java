package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;

import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.SaveModuleFailException;

import seedu.igraduate.module.Module;

import java.util.logging.*;

/**
 * Handles delete command.
 */
public class DeleteCommand extends Command {
    protected String moduleCode;

    private static final Logger logger = Logger.getLogger(DeleteCommand.class.getName());

    /**
     * Child class of the command class that contains the module code. 
     * 
     * @param moduleCode module code. 
     */
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
     * @throws SaveModuleFailException If module data fails to save to file.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage)
            throws ModuleNotFoundException, SaveModuleFailException {
        try {
            Module module = moduleList.getByCode(moduleCode);
            logger.log(Level.INFO, "moduleList.getByCode success.");
            String moduleType = moduleList.getModuleType(module);
            logger.log(Level.INFO, "moduleList.getModuleType success.");
            moduleList.delete(module);
            storage.saveModulesToFile(moduleList);
            logger.log(Level.INFO, "storage.saveModulesToFile success.");
            ui.printDeletedModuleSuccess(moduleCode, moduleType);
            logger.log(Level.INFO, "DeleteCommand execution success.");
        } catch (ModuleNotFoundException e) {
            logger.log(Level.WARNING, "DeleteCommand execution failed. Check which of the following"
                    + " success messages are missing: getByCode, getModuleType, saveModulesToFile.");
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
