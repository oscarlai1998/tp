package seedu.igraduate.command;

import seedu.igraduate.ModuleList;
import seedu.igraduate.Storage;
import seedu.igraduate.Ui;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.SaveModuleFailException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CapCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(DoneCommand.class.getName());

    private static float cap;

    public void execute(ModuleList moduleList, Ui ui, Storage storage)
            throws SaveModuleFailException, ModuleNotFoundException {
        LOGGER.log(Level.INFO, "Executing cap command...");

    }

    public static float getCap() {
        return cap;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
