package seedu.igraduate;

import java.io.File;
import java.nio.file.Paths;
import seedu.igraduate.command.Command;

import seedu.igraduate.exception.InvalidCommandException;

/**
 * IGraduate program.
 */
public class IGraduate {
    private Storage storage;
    private ModuleList modules;
    private Ui ui;
    private Parser parser;

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
        } catch (Exception e) {
            ui.printErrorMessage(1); // Todo: Change to exception
            modules = new ModuleList();
        }
        parser = new Parser(storage, modules, ui);
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
                Command c = parser.parseCommand(fullCommand);
                c.execute(modules, ui, storage);
                ui.printBorderLine();
                isExit = c.isExit();
            } catch (InvalidCommandException exception) {
                System.out.println("Invalid input!");
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number!");
            }
        }
    }

    public static void main(String[] args) {
        File filePath = Paths.get("data/modules.json").toFile();
        new IGraduate(filePath).run();
    }
}
