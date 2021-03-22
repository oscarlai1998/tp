package seedu.igraduate.logic.command;

import java.util.logging.Logger;
import seedu.igraduate.model.ModuleList;
import seedu.igraduate.logic.Parser;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.ModuleNotCompleteException;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.SaveModuleFailException;
import seedu.igraduate.exception.InvalidModuleGradeException;
import seedu.igraduate.model.module.Module;

/**
 * Handles update command. 
 */
public class UpdateCommand extends Command {
    private String moduleCode;
    private String[] commandFlags;

    private Module targetModule;
    private double moduleCredit;
    private String moduleGrade;
    private String moduleName;

    private static final Logger LOGGER = Logger.getLogger(AddCommand.class.getName());

    /**
     * Child class of the command class that contains the module code and command flags. 
     * 
     * @param moduleCode module code. 
     * @param commandFlags flags containing all the information to update. 
     */
    public UpdateCommand(String moduleCode, String[] commandFlags) {
        this.moduleCode = moduleCode;
        this.commandFlags = commandFlags;
    }

    /**
     * Executes the updateCommand function. 
     * 
     * @param modules Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     * @throws ModuleNotFoundException If the module code is not found in module list. 
     * @throws NumberFormatException If module credit is not an integer (or double). 
     * @throws InputNotNumberException If module credit is not an integer (or double). 
     * @throws ModuleNotCompleteException If the module has not been marked as completed. 
     * @throws SaveModuleFailException If the program fails to save changes. 
     */
    @Override
    public void execute(ModuleList modules, Ui ui, Storage storage) 
            throws ModuleNotFoundException,
            NumberFormatException, InputNotNumberException, ModuleNotCompleteException,
            InvalidModuleGradeException, SaveModuleFailException {
        this.targetModule = modules.getModule(moduleCode);
        
        try {
            updateModuleGrade(this.commandFlags);
        } catch (ModuleNotCompleteException exception) {
            throw exception;
        } finally {
            updateModuleName(this.commandFlags);
            updateModuleCredits(this.commandFlags);
            storage.saveModulesToFile(modules);
        }

        ui.printUpdateSuccess(targetModule);
    }

    /**
     * Updates the module name from commandFlags. 
     * 
     * @param commandFlags List containing all flags and values. 
     */
    private void updateModuleName(String[] commandFlags) {
        try {
            moduleName = Parser.extractModuleName(commandFlags);
            targetModule.setName(moduleName);
        } catch (InvalidCommandException exception) {
            LOGGER.info("No name field found, not updates to name done. ");
        }
    }

    /**
     * Updates the module credit from commandFlags. 
     * 
     * @param commandFlags List containing all flags and values. 
     * @throws NumberFormatException If module credit is not an integer (or double). 
     * @throws InputNotNumberException If module credit is not an integer (or double). 
     */
    private void updateModuleCredits(String[] commandFlags) throws NumberFormatException, InputNotNumberException {
        try {
            moduleCredit = Parser.extractModuleCredits(commandFlags);
            targetModule.setCredit(moduleCredit);
        } catch (InvalidCommandException exception) {
            LOGGER.info("No credit field found, no updates to credit done. ");
        }
    }

    /**
     * Updates module grade from commandFlags. 
     * If the module is incomplete, no updates is done on the module grade.  
     * 
     * @param commandFlags List containing all flags and values. 
     * @throws ModuleNotCompleteException If the module has not been marked as completed. 
     */
    private void updateModuleGrade(String[] commandFlags) throws ModuleNotCompleteException,
        InvalidModuleGradeException {
        try {
            moduleGrade = Parser.extractModuleGrade(commandFlags);
            if (!targetModule.isDone()) {
                LOGGER.warning("Module has not been completed, no grade update is permitted. ");
                throw new ModuleNotCompleteException();
            }
            if (!targetModule.isGradeValid(moduleGrade)) {
                throw new InvalidModuleGradeException();
            }
            targetModule.setGrade(moduleGrade);
        } catch (InvalidCommandException invalidCommandException) {
            LOGGER.info("No grade field found, no updates to grade done. ");
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
