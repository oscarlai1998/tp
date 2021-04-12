package seedu.igraduate.logic.command;

import seedu.igraduate.storage.Storage;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.ui.Ui;

import seedu.igraduate.exception.ModuleNotFoundException;

import seedu.igraduate.model.module.Module;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Handles info command.
 */
public class InfoCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(InfoCommand.class.getName());

    /**
     * Module code of the module to show detailed information.
     */
    protected String moduleCode;

    //@@author kewenlok
    /**
     * Constructs a new InfoCommand object.
     *
     * @param moduleCode Module code specified by the user.
     */
    public InfoCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Prints information of specified module from moduleList.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     * @throws ModuleNotFoundException If the module specified does not exists.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws ModuleNotFoundException {
        LOGGER.log(Level.INFO, "Executing info command...");
        try {
            Module module = moduleList.getModuleByCode(moduleCode);
            ui.printModuleInfo(module, moduleList);
            LOGGER.log(Level.INFO, String.format("Successfully printed %s module information.", moduleCode));
        } catch (ModuleNotFoundException e) {
            LOGGER.log(Level.WARNING, "Failed to print information of non-existence module.", e);
            throw new ModuleNotFoundException();
        } finally {
            LOGGER.log(Level.INFO, "End of info command execution.");
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
