package seedu.igraduate.logic.command.exitcommand;

import org.junit.jupiter.api.Test;

import seedu.igraduate.logic.command.ExitCommand;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExitCommandTest {

    private static final File FILEPATH = Paths.get("./commandteststorage/exitCommandData.json").toFile();

    private ExitCommand exitCommand = new ExitCommand();
    private Ui ui = new Ui();
    private ModuleList moduleList = new ModuleList();
    private Storage storage = Storage.getStorage(FILEPATH);

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void executeExitCommand_success() {
        System.setOut(new PrintStream(output));
        exitCommand.execute(moduleList, ui, storage);
        String expectedExitMessage = ui.GOODBYE_MESSAGE + System.lineSeparator();
        assertEquals(expectedExitMessage, output.toString());
        System.setOut(originalOut);
    }

    @Test
    void isExit_returnedTrue_success() {
        assertEquals(true, exitCommand.isExit());
    }
}