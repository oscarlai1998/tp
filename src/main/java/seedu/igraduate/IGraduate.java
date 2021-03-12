package seedu.igraduate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

import com.google.gson.JsonSyntaxException;

import seedu.igraduate.command.Command;

/**
 * IGraduate program.
 */
public class IGraduate {
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
        } catch (FileNotFoundException exception) {
            ui.printErrorMessage(exception);
            modules = new ModuleList();
        } catch (JsonSyntaxException exception) {
            ui.printErrorMessage(exception);
        } catch (IOException exception) {
            ui.printErrorMessage(exception);
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
