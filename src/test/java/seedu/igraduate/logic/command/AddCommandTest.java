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
import seedu.igraduate.exception.InvalidModularCreditException;

import seedu.igraduate.model.module.Module;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AddCommandTest {

    private static final File FILEPATH = Paths.get("./commandteststorage/deleteCommandData.json").toFile();

    private Storage storage = Storage.getStorage(FILEPATH);
    private Ui ui = new Ui();
    private ModuleList moduleList = new ModuleList();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void executeAddCommand_ExistingModule_exceptionThrown()
        throws InvalidModuleTypeException, SaveModuleFailException, ExistingModuleException,
        ModuleNotFoundException, PrerequisiteNotFoundException, InvalidModularCreditException {
        ArrayList<String> preRequisites = new ArrayList<>();
        ArrayList<String> untakenPreRequisites = new ArrayList<>();
        AddCommand addCommand = new AddCommand("cs1010", "Programming", "core", 4.0,
                preRequisites, untakenPreRequisites);
        addCommand.execute(moduleList, ui, storage);
        AddCommand duplicateAddCommand = new AddCommand("cs1010", "Programming", "core", 4.0,
                preRequisites, untakenPreRequisites);
        Exception exception = assertThrows(ExistingModuleException.class,
            () -> duplicateAddCommand.execute(moduleList, ui, storage));
        assertEquals(ExistingModuleException.EXISTING_MODULE_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void executeAddCommand_validParameters_success()
        throws  InvalidModuleTypeException, ModuleNotFoundException, SaveModuleFailException,
        ExistingModuleException, PrerequisiteNotFoundException, InvalidModularCreditException {

        ArrayList<String> cs1010PreRequisites = new ArrayList<>();
        ArrayList<String> cs1010UntakenPreRequisites = new ArrayList<>();
        AddCommand cs1010AddCommand = new AddCommand("cs1010", "Programming", "core", 4.0,
                cs1010PreRequisites, cs1010UntakenPreRequisites);
        cs1010AddCommand.execute(moduleList, ui, storage);

        ArrayList<String> preRequisites = new ArrayList<>();
        ArrayList<String> untakenPreRequisites = new ArrayList<>();
        preRequisites.add("CS1010");
        untakenPreRequisites.add("CS1010");
        AddCommand addCommand = new AddCommand("cs2100", "Computer Org", "core", 4.0,
                preRequisites, untakenPreRequisites);
        addCommand.execute(moduleList, ui, storage);
    }

    /*------------- Integration test with Ui -----------------*/
    @Test
    void executeAddCommand_validParametersWithUi_success()
            throws InvalidCommandException, InvalidModuleTypeException,
            InputNotNumberException, IncorrectParameterCountException, ModuleNotFoundException,
            SaveModuleFailException, ExistingModuleException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, ModularCreditExceedsLimitException, UnableToDeletePrereqModuleException,
            InvalidModuleGradeException, InvalidListTypeException, PrerequisiteNotMetException,
            AddSelfToPrereqException, InvalidModularCreditException {
        String line = "add Computer Org -mc 4 -t core -c cs2100";
        Command addCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        addCommand.execute(moduleList, ui, storage);
        Module module = moduleList.getByCode("cs2100");
        assertEquals(String.format(Ui.MODULE_ADDED_MESSAGE, "CS2100", "Computer Org", "4.0")
                + System.lineSeparator()
                + module + System.lineSeparator(), outContent.toString());
        System.setOut(originalOut);
    }
}
