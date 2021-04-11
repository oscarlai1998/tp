package seedu.igraduate.logic.command.helpcommand;

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
import seedu.igraduate.logic.command.HelpCommand;
import seedu.igraduate.logic.parser.Parser;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HelpCommandTest {

    private static final File FILEPATH = Paths.get("./commandteststorage/infoCommandData.json").toFile();

    private static final String INVALID_HELP_OPTION_MESSAGE = "You may type \"help\" to view manual for our available "
            + "commands.";

    private Storage storage = Storage.getStorage(FILEPATH);
    private Ui ui = new Ui();
    private ModuleList moduleList = new ModuleList();
    private Parser parser = new Parser();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /*---------- Unit tests ----------------*/
    @Test
    void executeHelpCommand_noParams_success() throws InvalidCommandException {
        System.setOut(new PrintStream(outContent));
        HelpCommand helpCommand = new HelpCommand("no params");
        helpCommand.execute(moduleList, ui, storage);
        String expectedOutput = Ui.HELP_INTRO + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeHelpCommand_infoParam_success() throws InvalidCommandException {
        System.setOut(new PrintStream(outContent));
        HelpCommand helpCommand = new HelpCommand("info");
        helpCommand.execute(moduleList, ui, storage);
        String expectedOutput = Ui.HELP_INFO + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeHelpCommand_badParam_exceptionThrown() {
        HelpCommand helpCommand = new HelpCommand("mai");
        Exception exception = assertThrows(InvalidCommandException.class,
            () -> helpCommand.execute(moduleList, ui, storage));
        assertEquals(String.format(InvalidCommandException.INVALID_COMMAND_ERROR_MESSAGE, INVALID_HELP_OPTION_MESSAGE),
                exception.getMessage());
    }

    /*-------------- Integration tests ---------------------*/
    @Test
    void executeAddCommandWithParser_listParam_success() throws InputNotNumberException, InvalidCommandException,
            IncorrectParameterCountException, InvalidModuleTypeException, IllegalParametersException,
            InvalidModuleCodeException, InvalidListTypeException, InvalidModuleGradeException,
            InvalidModularCreditException, PrerequisiteNotMetException, UnableToDeletePrereqModuleException,
            SaveModuleFailException, PrerequisiteNotFoundException, ExistingModuleException, AddSelfToPrereqException,
            MarkCompletedModuleException, ModuleNotCompleteException, ModuleNotFoundException {
        String line = "help add";
        Command helpCommand = parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        helpCommand.execute(moduleList, ui, storage);
        String expectedOutput = Ui.HELP_ADD + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeAddCommandWithParser_listParamWithWhitespace_success() throws InputNotNumberException,
            InvalidCommandException, IncorrectParameterCountException, InvalidModuleTypeException,
            IllegalParametersException, InvalidModuleCodeException, InvalidListTypeException,
            InvalidModuleGradeException, InvalidModularCreditException, PrerequisiteNotMetException,
            UnableToDeletePrereqModuleException, SaveModuleFailException, PrerequisiteNotFoundException,
            ExistingModuleException, AddSelfToPrereqException, MarkCompletedModuleException,
            ModuleNotCompleteException, ModuleNotFoundException {
        String line = "         help        add    ";
        Command helpCommand = parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        helpCommand.execute(moduleList, ui, storage);
        String expectedOutput = Ui.HELP_ADD + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(originalOut);
    }
}
