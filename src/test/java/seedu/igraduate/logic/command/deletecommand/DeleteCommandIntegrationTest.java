package seedu.igraduate.logic.command.deletecommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import seedu.igraduate.logic.command.AddCommand;
import seedu.igraduate.logic.command.Command;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.logic.parser.Parser;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;
import seedu.igraduate.model.module.Module;

import seedu.igraduate.exception.InvalidModuleGradeException;
import seedu.igraduate.exception.InvalidModularCreditException;
import seedu.igraduate.exception.UnableToDeletePrereqModuleException;
import seedu.igraduate.exception.PrerequisiteNotFoundException;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.SaveModuleFailException;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.ExistingModuleException;
import seedu.igraduate.exception.IllegalParametersException;
import seedu.igraduate.exception.ModuleNotCompleteException;
import seedu.igraduate.exception.IncorrectParameterCountException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.InvalidModuleTypeException;
import seedu.igraduate.exception.InvalidModuleCodeException;
import seedu.igraduate.exception.InvalidListTypeException;
import seedu.igraduate.exception.PrerequisiteNotMetException;
import seedu.igraduate.exception.AddSelfToPrereqException;
import seedu.igraduate.exception.MarkCompletedModuleException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeleteCommandIntegrationTest {

    private static final File FILEPATH = Paths.get("./commandteststorage/deleteCommandData.json").toFile();

    private Storage storage = Storage.getStorage(FILEPATH);
    private Ui ui = new Ui();
    private ModuleList moduleList = new ModuleList();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() throws SaveModuleFailException, InvalidModuleTypeException, ExistingModuleException,
            ModuleNotFoundException, PrerequisiteNotFoundException, InvalidModularCreditException {
        ArrayList<String> preRequisites = new ArrayList<>();
        ArrayList<String> untakenPreRequisites = new ArrayList<>();
        ArrayList<String> requiredByModules = new ArrayList<>();
        AddCommand firstModuleAddCommand = new AddCommand("cs1010", "Programming Methodology", "core", 4.0,
                preRequisites, untakenPreRequisites);
        firstModuleAddCommand.execute(moduleList, ui, storage);
        requiredByModules.add("CS2100");
        Module module = moduleList.getModuleByCode("cs1010");
        module.setRequiredByModules(requiredByModules);
        ArrayList<String> secondModulePreRequisites = new ArrayList<>();
        ArrayList<String> secondModuleUntakenPreRequisites = new ArrayList<>();
        secondModulePreRequisites.add("CS1010");
        secondModuleUntakenPreRequisites.add("CS1010");
        AddCommand secondModuleAddCommand = new AddCommand("cs2100", "Introduction to Computer Organisation", "core",
                4.0, secondModulePreRequisites, secondModuleUntakenPreRequisites);
        secondModuleAddCommand.execute(moduleList, ui, storage);
    }

    @Test
    void executeDeleteCommand_nonexistentModule_exceptionThrown() throws InvalidCommandException,
            InvalidModuleTypeException, InputNotNumberException, IncorrectParameterCountException,
            InvalidListTypeException, InvalidModularCreditException, IllegalParametersException,
            InvalidModuleGradeException, InvalidModuleCodeException {
        String line = "Delete Pigs (Three Different Ones)";
        Command deleteCommand = Parser.parseCommand(line);
        Exception exception = assertThrows(ModuleNotFoundException.class,
            () -> deleteCommand.execute(moduleList, ui, storage));
        String exceptionMessage = ModuleNotFoundException.MODULE_NOT_FOUND_ERROR_MESSAGE;
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void executeDeleteCommand_preRequisiteModule_exceptionThrown() throws InvalidCommandException,
            InvalidModuleTypeException, InputNotNumberException, IncorrectParameterCountException,
            InvalidListTypeException, InvalidModularCreditException, IllegalParametersException,
            InvalidModuleGradeException, InvalidModuleCodeException {
        String line = "Delete CS1010";
        Command deleteCommand = Parser.parseCommand(line);
        Exception exception = assertThrows(UnableToDeletePrereqModuleException.class,
            () -> deleteCommand.execute(moduleList, ui, storage));
        String exceptionMessage = UnableToDeletePrereqModuleException.UNABLE_TO_DELETE_PREREQ_MODULE_ERROR_MESSAGE
                + "[CS2100]";
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void executeDeleteCommand_moduleInList_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException, InvalidModuleCodeException {
        String line = "Delete cs2100";
        Command deleteCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        deleteCommand.execute(moduleList, ui, storage);
        String successMessage = String.format(Ui.MODULE_DELETED_MESSAGE, "Core", "cs2100") + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @AfterEach
    void tearDown() {
        moduleList = new ModuleList();
    }
}