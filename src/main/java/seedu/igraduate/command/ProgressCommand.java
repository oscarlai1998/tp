package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;
import seedu.igraduate.exception.ModularCreditExceedsLimitException;
import seedu.igraduate.module.Module;
import java.text.DecimalFormat;

/**
 * Handles progress command.
 */
public class ProgressCommand extends Command {
    /**
     * Prints progress in percentage of MCs completed out of 160MCs.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage)
            throws ModularCreditExceedsLimitException {
        DecimalFormat df = new DecimalFormat("0.00");
        double completedMCs = Module.getTotalCompletedMCs();
        double percentageDone = (completedMCs / 160 ) * 100;
        String stringPercentageDone = df.format(percentageDone);
        if (percentageDone > 100) {
            throw new ModularCreditExceedsLimitException();
        }
        ui.printProgressBar(completedMCs, stringPercentageDone);
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
