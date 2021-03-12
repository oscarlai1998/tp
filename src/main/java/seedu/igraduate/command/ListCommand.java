package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;
import seedu.igraduate.module.Module;

import java.util.ArrayList;

/**
 * Handles list command.
 */
public class ListCommand extends Command {

    /**
     * Todo: Add comments here.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) {
        ArrayList<Module> modules = moduleList.getModules();
        if (modules.isEmpty()) {
            ui.printListEmptyMessage();
            return;
        }
        ui.printEntireList(moduleList.getModules());
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
