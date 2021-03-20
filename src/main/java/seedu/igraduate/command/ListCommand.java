package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles list command.
 */
public class ListCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ListCommand.class.getName());
    private String scope;

    public ListCommand(String scope) {
        this.scope = scope;
    }

    /**
     * Prints list of all modules.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) {
        LOGGER.log(Level.INFO, "Executing list command...");
        if (moduleList.isEmpty()) {
            assert moduleList.isEmpty() : "List should be empty";
            LOGGER.log(Level.INFO, "List is empty.");
            ui.printListEmptyMessage();
        } else {
            ui.printEntireList(moduleList.getModules());
            LOGGER.log(Level.INFO, "Successfully printed module list.");
        }
        LOGGER.log(Level.INFO, "End of list command execution.");
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
