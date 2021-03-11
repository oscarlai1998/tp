package seedu.igraduate;

import java.io.File;
import java.nio.file.Paths;

import seedu.igraduate.command.Command;
import seedu.igraduate.exception.IncorrectParameterCountException;
import seedu.igraduate.exception.InvalidCommandException;

/**
 * IGraduate program.
 */
public class IGraduate {
    private static final int ERR_INVALID_INPUT = -1;

    private Storage storage;
    private ModuleList modules;
    private Ui ui;

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
            } catch (InvalidCommandException exception) {
                ui.printErrorMessage(ERR_INVALID_INPUT);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number!");
            } catch (IncorrectParameterCountException exception) {
                System.out.println(exception.getMessage());
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
