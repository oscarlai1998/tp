package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;

/**
 * Handles exit command.
 */
public class ExitCommand extends Command {


    public ExitCommand(Storage storage, ModuleList moduleList, Ui ui) {
        super(storage, moduleList, ui);
    }

    /**
     * Prints exit message.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) {
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
