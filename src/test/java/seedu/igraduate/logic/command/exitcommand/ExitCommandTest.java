package seedu.igraduate.logic.command.exitcommand;

import org.junit.jupiter.api.Test;

import seedu.igraduate.exception.InvalidModuleGradeException;
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
import seedu.igraduate.exception.InvalidModularCreditException;
import seedu.igraduate.exception.InvalidModuleTypeException;
import seedu.igraduate.exception.InvalidModuleCodeException;
import seedu.igraduate.exception.InvalidListTypeException;
import seedu.igraduate.exception.PrerequisiteNotMetException;
import seedu.igraduate.exception.AddSelfToPrereqException;
import seedu.igraduate.exception.MarkCompletedModuleException;

import seedu.igraduate.logic.command.Command;
import seedu.igraduate.logic.command.ExitCommand;
import seedu.igraduate.logic.parser.Parser;
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

    /*------------- Integration Test with Parser -------------*/
    @Test
    void executeExitCommandWithParser_validInput_success() throws InputNotNumberException, InvalidCommandException,
            IncorrectParameterCountException, InvalidModuleTypeException, IllegalParametersException,
            InvalidModuleCodeException, InvalidListTypeException, InvalidModuleGradeException,
            InvalidModularCreditException, PrerequisiteNotMetException, UnableToDeletePrereqModuleException,
            SaveModuleFailException, PrerequisiteNotFoundException, ExistingModuleException,
            AddSelfToPrereqException, MarkCompletedModuleException, ModuleNotCompleteException,
            ModuleNotFoundException {
        String line = "exit";
        Command exitCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(output));
        exitCommand.execute(moduleList, ui, storage);
        String expectedExitMessage = ui.GOODBYE_MESSAGE + System.lineSeparator();
        assertEquals(expectedExitMessage, output.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeExitCommandWithParser_validInputWithWhitespace_success() throws InputNotNumberException,
            InvalidCommandException, IncorrectParameterCountException, InvalidModuleTypeException,
            IllegalParametersException, InvalidModuleCodeException, InvalidListTypeException,
            InvalidModuleGradeException, InvalidModularCreditException, PrerequisiteNotMetException,
            UnableToDeletePrereqModuleException, SaveModuleFailException, PrerequisiteNotFoundException,
            ExistingModuleException, AddSelfToPrereqException, MarkCompletedModuleException,
            ModuleNotCompleteException, ModuleNotFoundException {
        String line = "          exit                               ";
        Command exitCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(output));
        exitCommand.execute(moduleList, ui, storage);
        String expectedExitMessage = ui.GOODBYE_MESSAGE + System.lineSeparator();
        assertEquals(expectedExitMessage, output.toString());
        System.setOut(originalOut);
    }
}