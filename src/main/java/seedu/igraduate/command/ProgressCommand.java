package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;

import seedu.igraduate.exception.ModularCreditExceedsLimitException;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles progress command.
 */
public class ProgressCommand extends Command {
    private static final Logger logger = Logger.getLogger(ProgressCommand.class.getName());

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
        double completedMCs = moduleList.getTotalCompletedMCs();
        logger.log(Level.INFO, "Completed MCs success.");
        double percentageDone = (completedMCs / 160) * 100;
        String stringPercentageDone = df.format(percentageDone);
        if (percentageDone > 100) {
            logger.log(Level.WARNING, "Execution failed! Percentage done exceeds 100%.");
            throw new ModularCreditExceedsLimitException();
        }
        ui.printProgressBar(completedMCs, stringPercentageDone);
        logger.log(Level.INFO, "Print Progress Bar success.");
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
