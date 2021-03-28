package seedu.igraduate.logic.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.logic.Parser;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;

import seedu.igraduate.exception.InvalidModuleGradeException;
import seedu.igraduate.exception.UnableToDeletePrereqModuleException;
import seedu.igraduate.exception.ModularCreditExceedsLimitException;
import seedu.igraduate.exception.PrerequisiteNotFoundException;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.SaveModuleFailException;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.ExistingModuleException;
import seedu.igraduate.exception.ModuleNotCompleteException;
import seedu.igraduate.exception.IncorrectParameterCountException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.InvalidModuleTypeException;
import seedu.igraduate.exception.InvalidListTypeException;
import seedu.igraduate.exception.PrerequisiteNotMetException;
import seedu.igraduate.exception.AddSelfToPrereqException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DeleteCommandTest {

    private static final File FILEPATH = Paths.get("./commandteststorage/deleteCommandData.json").toFile();

    private Storage storage = Storage.getStorage(FILEPATH);
    private Ui ui = new Ui();
    private ModuleList moduleList = new ModuleList();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void executeDeleteCommand_nonexistentModule_exceptionThrown()
        throws InvalidCommandException, InvalidModuleTypeException,
        InputNotNumberException, IncorrectParameterCountException, InvalidListTypeException {
        String line = "Delete Pigs (Three Different Ones)";
        Command deleteCommand = Parser.parseCommand(line);
        Exception exception = assertThrows(ModuleNotFoundException.class,
            () -> deleteCommand.execute(moduleList, ui, storage));
        assertEquals(ModuleNotFoundException.MODULE_NOT_FOUND_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void executeDeleteCommand_moduleInList_success()
            throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            ModularCreditExceedsLimitException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException {
        ArrayList<String> preRequisites = new ArrayList<>();
        ArrayList<String> untakenPreRequisites = new ArrayList<>();
        AddCommand addCommand = new AddCommand("cs1010", "Programming", "core", 4.0,
                preRequisites, untakenPreRequisites);
        addCommand.execute(moduleList, ui, storage);
        String line = "Delete cs1010";
        Command deleteCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        deleteCommand.execute(moduleList, ui, storage);
        assertEquals(String.format(Ui.MODULE_DELETED_MESSAGE, "Core", "cs1010")
                + System.lineSeparator(), outContent.toString());
        System.setOut(originalOut);
    }
}
