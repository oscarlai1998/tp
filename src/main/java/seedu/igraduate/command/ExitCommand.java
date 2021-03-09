package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;

/**
 * Handles exit command.
 */
public class ExitCommand extends Command {
    /**
     * Todo: Add comments here.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) {
        // Todo: Print exit message
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
