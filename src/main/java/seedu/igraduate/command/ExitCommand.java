package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Handles exit command.
 */
public class ExitCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ListCommand.class.getName());

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
