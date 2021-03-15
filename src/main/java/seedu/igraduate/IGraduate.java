package seedu.igraduate;

import java.io.File;
import java.nio.file.Paths;

import java.util.logging.Logger;

import seedu.igraduate.command.Command;
import seedu.igraduate.exception.LoadModuleFailException;

/**
 * IGraduate program.
 */
public class IGraduate {
    private Storage storage;
    private ModuleList modules;
    private Ui ui;
    private static final Logger LOGGER = Logger.getLogger(IGraduate.class.getName());

    /**
     * Instantiates Storage, ModuleList and Ui components of the program.
     *
     * @param filePath The file path at which module data file is located, if exists.
     */
    public IGraduate(File filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            modules = new ModuleList(storage.loadModulesFromFile());
            LOGGER.info("File loaded successfullly. ");
        } catch (LoadModuleFailException exception) {
            ui.printErrorMessage(exception);
            modules = new ModuleList();
        }
    }

    /**
     * Runs IGraduate program.
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getCommand();
                ui.printBorderLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(modules, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.printErrorMessage(e);
            } finally {
                ui.printBorderLine();
            }
        }
    }

    public static void main(String[] args) {
        File filePath = Paths.get("data/modules.json").toFile();
        new IGraduate(filePath).run();
    }
}
