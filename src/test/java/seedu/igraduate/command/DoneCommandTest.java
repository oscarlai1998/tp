package seedu.igraduate.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.igraduate.ModuleList;
import seedu.igraduate.Parser;
import seedu.igraduate.Storage;
import seedu.igraduate.Ui;

import seedu.igraduate.exception.InvalidModuleTypeException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.IncorrectParameterCountException;
import seedu.igraduate.exception.SaveModuleFailException;
import seedu.igraduate.exception.ExistingModuleException;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.PrerequisiteNotFoundException;
import seedu.igraduate.exception.ModularCreditExceedsLimitException;

import seedu.igraduate.module.Module;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DoneCommandTest {

    private static final File FILEPATH = Paths.get("./commandteststorage/deleteCommandData.json").toFile();

    private static final String MODULE_MARKEDASDONE_MESSAGE = "Nice! I've marked this module as done:"
            + System.lineSeparator()
            + "  %s"
            + System.lineSeparator();
    private Storage storage = new Storage(FILEPATH);
    private Ui ui = new Ui();
    private ModuleList moduleList = new ModuleList();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void executeDoneCommand_nonexistentModule_exceptionThrown()
            throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException {
        String line = "Done GES1036 -g A+";
        Command doneCommand = Parser.parseCommand(line);
        Exception exception = assertThrows(ModuleNotFoundException.class,
            () -> doneCommand.execute(moduleList, ui, storage));
        assertEquals(ModuleNotFoundException.MODULE_NOT_FOUND_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void executeDoneCommand_moduleInList_success()
            throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException,
            InvalidCommandException, InputNotNumberException,
            ModularCreditExceedsLimitException, ModuleNotFoundException,
            PrerequisiteNotFoundException  {
        ArrayList<String> preRequisites = new ArrayList<>();
        AddCommand addCommand = new AddCommand("cs1010", "Programming", "core", 4.0, preRequisites);
        addCommand.execute(moduleList, ui, storage);
        String line = "Done CS1010 -g A";
        Command doneCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        Module module = moduleList.getByCode("cs1010");
        doneCommand.execute(moduleList, ui, storage);
        assertEquals(String.format(MODULE_MARKEDASDONE_MESSAGE, module), outContent.toString());
        System.setOut(originalOut);
    }
}
