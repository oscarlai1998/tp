package seedu.igraduate.command;

import java.util.logging.Logger;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Parser;
import seedu.igraduate.Storage;
import seedu.igraduate.Ui;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.ModuleNotCompleteException;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.SaveModuleFailException;
import seedu.igraduate.module.Module;

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
     * @param moduleList Module list consisting of all modules.
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
            NumberFormatException, InputNotNumberException, ModuleNotCompleteException, SaveModuleFailException {
        this.targetModule = modules.getModule(moduleCode);
        extractModuleName(this.commandFlags);
        extractModuleCredits(this.commandFlags);
        extractModuleGrade(this.commandFlags);
        storage.saveModulesToFile(modules);
        ui.printUpdateSuccess(targetModule);
    }

    /**
     * Extracts the module name from commandFlags. 
     * 
     * @param commandFlags List containing all flags and values. 
     */
    private void extractModuleName(String[] commandFlags) {
        try {
            moduleName = Parser.extractModuleName(commandFlags);
            targetModule.setName(moduleName);
        } catch (InvalidCommandException exception) {
            LOGGER.info("No name field found, not updates to name done. ");
        }
    }

    /**
     * Extracts the module credit from commandFlags. 
     * 
     * @param commandFlags List containing all flags and values. 
     * @throws NumberFormatException If module credit is not an integer (or double). 
     * @throws InputNotNumberException If module credit is not an integer (or double). 
     */
    private void extractModuleCredits(String[] commandFlags) throws NumberFormatException, InputNotNumberException {
        try {
            moduleCredit = Parser.extractModuleCredits(commandFlags);
            targetModule.setCredit(moduleCredit);
        } catch (InvalidCommandException exception) {
            LOGGER.info("No credit field found, no updates to credit done. ");
        }
    }

    /**
     * Extract module grade from commandFlags. 
     * 
     * @param commandFlags List containing all flags and values. 
     * @throws ModuleNotCompleteException If the module has not been marked as completed. 
     */
    private void extractModuleGrade(String[] commandFlags) throws ModuleNotCompleteException {
        try {
            moduleGrade = Parser.extractModuleGrade(commandFlags);
            if (!targetModule.isDone()) {
                LOGGER.warning("Module has not been completed, no grade update is permitted. ");
                throw new ModuleNotCompleteException();
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
