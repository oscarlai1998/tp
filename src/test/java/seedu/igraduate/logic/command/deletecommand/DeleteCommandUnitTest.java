package seedu.igraduate.logic.command.deletecommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.igraduate.logic.command.Command;
import seedu.igraduate.logic.command.DeleteCommand;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.logic.parser.Parser;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;

import seedu.igraduate.exception.InvalidModuleGradeException;
import seedu.igraduate.exception.UnableToDeletePrereqModuleException;
import seedu.igraduate.exception.PrerequisiteNotFoundException;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.PrereqIncompleteException;
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
import seedu.igraduate.exception.InvalidModularCreditException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;

public class DeleteCommandUnitTest {

    private static final File FILEPATH = Paths.get("./commandteststorage/deleteCommandData.json").toFile();

    private Storage storage = Storage.getStorage(FILEPATH);
    private Ui ui = new Ui();
    private ModuleList moduleList = new ModuleList();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() throws InvalidCommandException, InvalidModuleTypeException, InputNotNumberException,
            IncorrectParameterCountException, ExistingModuleException, InvalidModularCreditException,
            ModuleNotCompleteException, SaveModuleFailException, InvalidModuleGradeException,
            UnableToDeletePrereqModuleException, PrerequisiteNotFoundException, ModuleNotFoundException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException, NumberFormatException,
            PrereqIncompleteException {
        String firstModule = "add Programming Methodology -mc 4 -t core -c CS1010";
        String secondModule = "add Computer Org -mc 4 -t core -c CS2100 -p CS1010";
        Command addFirst = Parser.parseCommand(firstModule);
        addFirst.execute(moduleList, ui, storage);
        Command addSecond = Parser.parseCommand(secondModule);
        addSecond.execute(moduleList, ui, storage);
    }

    @Test
    void executeDeleteCommand_nonexistentModule_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("Pigs (Three Different Ones)");
        Exception exception = assertThrows(ModuleNotFoundException.class,
                () -> deleteCommand.execute(moduleList, ui, storage));
        assertEquals(ModuleNotFoundException.MODULE_NOT_FOUND_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void executeDeleteCommand_deleteIncompletePrerequisite_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("CS1010");
        Exception exception = assertThrows(UnableToDeletePrereqModuleException.class,
                () -> deleteCommand.execute(moduleList, ui, storage));
        assertEquals(UnableToDeletePrereqModuleException.UNABLE_TO_DELETE_PREREQ_MODULE_ERROR_MESSAGE + "[CS2100]",
                exception.getMessage());
    }

    @Test
    void executeDeleteCommand_moduleInList_success() throws SaveModuleFailException, ModuleNotFoundException,
            PrerequisiteNotFoundException, UnableToDeletePrereqModuleException {
        DeleteCommand deleteCommand = new DeleteCommand("CS2100");
        System.setOut(new PrintStream(outContent));
        deleteCommand.execute(moduleList, ui, storage);
        assertEquals(String.format(Ui.MODULE_DELETED_MESSAGE, "Core", "CS2100") + System.lineSeparator(),
                outContent.toString());
        System.setOut(originalOut);
    }

}
