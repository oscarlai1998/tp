package seedu.igraduate;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;

import seedu.igraduate.command.Command;

import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;

/**
 * IGraduate program.
 */
public class IGraduate {
    private Storage storage;
    private ModuleList modules;
    private Ui ui;

    private static final Logger logger = Logger.getLogger(IGraduate.class.getName());

    static {
        // Initialize logger configurations at the moment the program is run
        try {
            InputStream inputStream = IGraduate.class.getClassLoader().getResourceAsStream("logger.properties");
            LogManager.getLogManager().readConfiguration(inputStream);
        } catch (Exception e) {
            logger.log(Level.WARNING, "An error occur when trying to read logger configuration file.", e);
        }
    }

    /**
     * Instantiates Storage, ModuleList and Ui components of the program.
     *
     * @param filePath The file path at which module data file is located, if exists.
     */
    public IGraduate(File filePath) {
        logger.info("Initializing iGraduate components...");
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            modules = new ModuleList(storage.loadModulesFromFile());
            logger.info("All components loaded successfully.");
        } catch (Exception e) {
            ui.printErrorMessage(e);
            modules = new ModuleList();
            logger.log(Level.WARNING, "Failed to load modules from file.", e);
            logger.info("A new module list is created.");
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
                logger.log(Level.WARNING, "An exception occur when handling user command", e);
            } finally {
                ui.printBorderLine();
            }
        }
    }

    public static void main(String[] args) {
        logger.info("iGraduate starts.");
        File filePath = Paths.get("data/modules.json").toFile();
        new IGraduate(filePath).run();
        logger.info("iGraduate exits.");
    }
}
