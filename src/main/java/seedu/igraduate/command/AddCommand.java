package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;

/**
 * Handles add command.
 */
public class AddCommand extends Command {
    protected String moduleCode;
    protected String moduleType;
    protected int moduleCredits;

    public AddCommand(Storage storage, ModuleList moduleList, Ui ui) {
        super(storage, moduleList, ui);
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public void setModuleCredits(int moduleCredits) {
        this.moduleCredits = moduleCredits;
    }

    /**
     * Todo: Add comments here.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) {
        // Todo: Command action
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
