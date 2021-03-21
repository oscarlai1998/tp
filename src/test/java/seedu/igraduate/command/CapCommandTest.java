package seedu.igraduate.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.igraduate.ModuleList;
import seedu.igraduate.Parser;
import seedu.igraduate.Storage;
import seedu.igraduate.Ui;

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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;

public class CapCommandTest {

    private static final File FILEPATH = Paths.get("./commandteststorage/deleteCommandData.json").toFile();

    private Storage storage = Storage.getStorage(FILEPATH);
    private Ui ui = new Ui();
    private ModuleList moduleList = new ModuleList();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void populateList()
        throws InvalidCommandException, InvalidModuleTypeException, InputNotNumberException,
        IncorrectParameterCountException, ExistingModuleException, ModularCreditExceedsLimitException,
        ModuleNotCompleteException, SaveModuleFailException, InvalidModuleGradeException,
        UnableToDeletePrereqModuleException, PrerequisiteNotFoundException,
        ModuleNotFoundException, InvalidListTypeException, PrerequisiteNotMetException {
        String firstModule = "add Programming Methodology -mc 4 -t core -c cs1010";
        String secondModule = "add Computer Org -mc 4 -t core -c cs2100";
        Command addFirst = Parser.parseCommand(firstModule);
        addFirst.execute(moduleList, ui, storage);
        Command addSecond = Parser.parseCommand(secondModule);
        addSecond.execute(moduleList, ui, storage);
    }

    @Test
    void executeCapCommand_validParameters_success()
        throws InvalidCommandException, InvalidModuleTypeException, InputNotNumberException,
        IncorrectParameterCountException, ExistingModuleException, ModularCreditExceedsLimitException,
        ModuleNotCompleteException, SaveModuleFailException, InvalidModuleGradeException,
        UnableToDeletePrereqModuleException, PrerequisiteNotFoundException,
        ModuleNotFoundException, InvalidListTypeException, PrerequisiteNotMetException {

        String setFirstToDone = "done cs1010 -g A+";
        String setSecondToDone = "done cs2100 -g A-";
        Command doneFirst = Parser.parseCommand(setFirstToDone);
        Command doneSecond = Parser.parseCommand(setSecondToDone);
        doneFirst.execute(moduleList, ui, storage);
        doneSecond.execute(moduleList, ui, storage);

        String calcCap = "cap";
        Command capCommand = Parser.parseCommand(calcCap);
        System.setOut(new PrintStream(outContent));
        capCommand.execute(moduleList, ui, storage);
        assertEquals(String.format(Ui.CAP_MESSAGE, 4.75, "Honours (Highest Distinction)")
                + System.lineSeparator(), outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeCapCommand_invalidGrade_exceptionThrown()
        throws InvalidCommandException, InvalidModuleTypeException, InputNotNumberException,
        IncorrectParameterCountException, ExistingModuleException, ModularCreditExceedsLimitException,
        ModuleNotCompleteException, SaveModuleFailException, InvalidModuleGradeException,
        UnableToDeletePrereqModuleException, PrerequisiteNotFoundException,
        ModuleNotFoundException, InvalidListTypeException, PrerequisiteNotMetException {

        String setFirstToDone = "done cs1010 -g A+";
        String setSecondToDone = "done cs2100 -g B-";
        Command doneFirst = Parser.parseCommand(setFirstToDone);
        Command doneSecond = Parser.parseCommand(setSecondToDone);
        doneFirst.execute(moduleList, ui, storage);
        doneSecond.execute(moduleList, ui, storage);

        String calcCap = "cap";
        Command capCommand = Parser.parseCommand(calcCap);
        capCommand.execute(moduleList, ui, storage);
    }

    @AfterEach
    void tearDownList() throws InvalidCommandException, InvalidModuleTypeException, InputNotNumberException,
        IncorrectParameterCountException, ExistingModuleException, ModularCreditExceedsLimitException,
        ModuleNotCompleteException, SaveModuleFailException, InvalidModuleGradeException,
        UnableToDeletePrereqModuleException, PrerequisiteNotFoundException,
        ModuleNotFoundException, InvalidListTypeException, PrerequisiteNotMetException {
        String firstModule = "Delete cs1010";
        String secondModule = "Delete cs2100";
        Command deleteFirst = Parser.parseCommand(firstModule);
        Command deleteSecond = Parser.parseCommand(secondModule);
        deleteFirst.execute(moduleList, ui, storage);
        deleteSecond.execute(moduleList, ui, storage);
    }
}
