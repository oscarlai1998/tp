package seedu.igraduate.logic.command;

import seedu.igraduate.storage.Storage;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.ui.Ui;

import java.text.DecimalFormat;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles progress command.
 */
public class ProgressCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ProgressCommand.class.getName());

    /**
     * Prints progress in percentage of MCs completed out of 160MCs.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) {
        LOGGER.log(Level.INFO, "Executing progress command...");
        DecimalFormat df = new DecimalFormat("0.00");
        double completedMCs = moduleList.getTotalCompletedMCs();
        LOGGER.log(Level.INFO, "Completed MCs success.");
        double percentageDone = (completedMCs / 160) * 100;
        if (percentageDone > 100) {
            percentageDone = 100;
        }
        String stringPercentageDone = df.format(percentageDone);
        ui.printProgressBar(completedMCs, stringPercentageDone);
        LOGGER.log(Level.INFO, "Print Progress Bar success.");
        LOGGER.log(Level.INFO, "End of progress command execution.");
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
