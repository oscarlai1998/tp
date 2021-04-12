package seedu.igraduate.logic.command;

import seedu.igraduate.storage.Storage;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles exit command.
 */
public class ExitCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ExitCommand.class.getName());

    //@@author oscarlai1998
    /**
     * Prints exit message.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) {
        LOGGER.log(Level.INFO, "Exit Command executed!");
        ui.printExitMessage();
    }

    //@@author kewenlok
    /**
     * {@inheritDoc}
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
