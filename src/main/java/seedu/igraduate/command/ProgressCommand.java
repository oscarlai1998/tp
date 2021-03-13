package seedu.igraduate.command;

import seedu.igraduate.Storage;
import seedu.igraduate.ModuleList;
import seedu.igraduate.Ui;
import seedu.igraduate.exception.ModularCreditExceedsLimit;
import seedu.igraduate.module.Module;
import java.text.DecimalFormat;

import java.util.ArrayList;

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
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws ModularCreditExceedsLimit {
        DecimalFormat df = new DecimalFormat("0.00");
        ArrayList<Module> modules = moduleList.getModules();
        float completedMCs = 0;
        for (Module module : modules) {
            if (module.isDone()) {
                completedMCs += module.getCredit();
            }
        }
        float percentageDone = (completedMCs / 160) * 100;
        String stringPercentageDone = df.format(percentageDone);
        if (percentageDone > 100) {
            throw new ModularCreditExceedsLimit();
        }
        ui.printProgressBar(completedMCs, percentageDone, stringPercentageDone);
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
