package seedu.igraduate.logic.command;

import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HelpCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(HelpCommand.class.getName());
    private String helpType;

    public HelpCommand(String helpType) {
        this.helpType = helpType.trim();
    }

    /**
     * Prints the relevant help section based on the user input.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui         User interface for printing result.
     * @param storage    Storage for storing module list data.
     * @throws InvalidCommandException if help type is invalid.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws InvalidCommandException {
        LOGGER.log(Level.INFO, "Executing help command...");
        switch (helpType.toLowerCase()) {
        case "no params":
            ui.printIntroHelp();
            break;
        case "add":
            ui.printAddHelp();
            break;
        case "delete":
            ui.printDeleteHelp();
            break;
        case "update":
            ui.printUpdateHelp();
            break;
        case "done":
            ui.printDoneHelp();
            break;
        case "info":
            ui.printInfoHelp();
            break;
        case "list":
            ui.printListHelp();
            break;
        case "progress":
            ui.printProgressHelp();
            break;
        case "cap":
            ui.printCapHelp();
            break;
        case "exit":
            ui.printExitHelp();
            break;
        default:
            LOGGER.log(Level.WARNING, "Invalid help option...");
            throw new InvalidCommandException();
        }
        LOGGER.log(Level.INFO, "End of help command execution.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
