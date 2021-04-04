package seedu.igraduate.logic.command.donecommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

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
import seedu.igraduate.exception.InvalidListTypeException;
import seedu.igraduate.exception.PrerequisiteNotMetException;
import seedu.igraduate.exception.AddSelfToPrereqException;
import seedu.igraduate.exception.MarkCompletedModuleException;

import seedu.igraduate.logic.command.AddCommand;
import seedu.igraduate.logic.command.Command;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.logic.parser.Parser;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;

import seedu.igraduate.model.module.Module;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DoneCommandTest {

    private static final File FILEPATH = Paths.get("./commandteststorage/doneCommandData.json").toFile();

    private static final String MODULE_MARKEDASDONE_MESSAGE = "Nice! I've marked this module as done:"
            + System.lineSeparator() + "  %s" + System.lineSeparator();
    private Storage storage = Storage.getStorage(FILEPATH);
    private Ui ui = new Ui();
    private ModuleList moduleList = new ModuleList();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void executeDoneCommand_nonexistentModule_exceptionThrown() throws InvalidCommandException,
            InvalidModuleTypeException, InputNotNumberException, IncorrectParameterCountException,
            InvalidListTypeException, InvalidModularCreditException, IllegalParametersException {
        String line = "Done GES1036 -g A+";
        Command doneCommand = Parser.parseCommand(line);
        Exception exception = assertThrows(ModuleNotFoundException.class,
                () -> doneCommand.execute(moduleList, ui, storage));
        assertEquals(ModuleNotFoundException.MODULE_NOT_FOUND_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    void executeDoneCommand_moduleInList_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {
        ArrayList<String> preRequisites = new ArrayList<>();
        ArrayList<String> untakenPreRequisites = new ArrayList<>();
        AddCommand addCommand = new AddCommand("cs1010", "Programming", "core", 4.0, preRequisites,
                untakenPreRequisites);
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
