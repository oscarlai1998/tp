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

public class UpdateCommand extends Command {
    private String moduleCode;
    private String[] commandFlags;

    private Module targetModule;
    private double moduleCredit;
    private String moduleGrade;
    private String moduleName;

    private static final Logger LOGGER = Logger.getLogger(AddCommand.class.getName());

    public UpdateCommand(String moduleCode, String[] commandFlags) {
        this.moduleCode = moduleCode;
        this.commandFlags = commandFlags;
    }

    @Override
    public void execute(ModuleList modules, Ui ui, Storage storage) throws ModuleNotFoundException,
            NumberFormatException, InputNotNumberException, ModuleNotCompleteException, SaveModuleFailException {
        this.targetModule = modules.getModule(moduleCode);
        extractModuleName(this.commandFlags);
        extractModuleCredits(this.commandFlags);
        extractModuleGrade(this.commandFlags);
        storage.saveModulesToFile(modules);
        ui.printUpdateSuccess(targetModule);
    }

    private void extractModuleName(String[] commandFlags) {
        try {
            moduleName = Parser.extractModuleName(commandFlags);
            targetModule.setName(moduleName);
        } catch (InvalidCommandException exception) {
            LOGGER.info("No name field found, not updates to name done. ");
        }
    }

    private void extractModuleCredits(String[] commandFlags) throws NumberFormatException, InputNotNumberException {
        try {
            moduleCredit = Parser.extractModuleCredits(commandFlags);
            targetModule.setCredit(moduleCredit);
        } catch (InvalidCommandException exception) {
            LOGGER.info("No credit field found, no updates to credit done. ");
        }
    }

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
