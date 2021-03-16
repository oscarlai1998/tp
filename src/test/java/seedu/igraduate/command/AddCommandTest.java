package seedu.igraduate.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.igraduate.ModuleList;
import seedu.igraduate.Parser;
import seedu.igraduate.Storage;
import seedu.igraduate.Ui;

import seedu.igraduate.exception.IncorrectParameterCountException;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.InvalidModuleTypeException;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.ExistingModuleException;
import seedu.igraduate.exception.SaveModuleFailException;
import seedu.igraduate.exception.ModularCreditExceedsLimitException;
import seedu.igraduate.module.Module;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;

public class AddCommandTest {

    private static final File FILEPATH = Paths.get("./commandteststorage/deleteCommandData.json").toFile();

    private Storage storage = new Storage(FILEPATH);
    private Ui ui = new Ui();
    private ModuleList moduleList = new ModuleList();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void executeAddCommand_ExistingModule_exceptionThrown()
            throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException,
            SaveModuleFailException, ExistingModuleException {
        AddCommand addCommand = new AddCommand("cs1010", "Programming", "core", 4.0);
        addCommand.execute(moduleList, ui, storage);
        String line = "add Programming -mc 4 -t core -c cs1010";
        Command testAddCommand = Parser.parseCommand(line);
        Exception exception = assertThrows(ExistingModuleException.class,
            () -> testAddCommand.execute(moduleList, ui, storage));
        assertEquals(ExistingModuleException.EXISTING_MODULE_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void executeAddCommand_validParameters_success()
            throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException,
            ModuleNotFoundException, SaveModuleFailException, ExistingModuleException,
            ModularCreditExceedsLimitException {
        String line = "add Computer Org -mc 4 -t core -c cs2100";
        Command addCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        addCommand.execute(moduleList, ui, storage);
        Module module = moduleList.getByCode("cs2100");
        assertEquals(String.format(Ui.MODULE_ADDED_MESSAGE, "CS2100", "Computer Org", "4.0")
                + "\r\n"
                + module + "\r\n", outContent.toString());
        System.setOut(originalOut);
    }
}
