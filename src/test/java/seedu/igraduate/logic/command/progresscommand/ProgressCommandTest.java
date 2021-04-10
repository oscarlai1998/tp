package seedu.igraduate.logic.command.progresscommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.igraduate.logic.command.Command;
import seedu.igraduate.logic.command.ProgressCommand;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.logic.parser.Parser;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;

import seedu.igraduate.exception.InvalidModuleGradeException;
import seedu.igraduate.exception.InvalidModuleCodeException;
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
import seedu.igraduate.exception.InvalidModularCreditException;
import seedu.igraduate.exception.MarkCompletedModuleException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;

public class ProgressCommandTest {

    private static final File FILEPATH = Paths.get("./commandteststorage/progressCommandData.json").toFile();

    private Storage storage = Storage.getStorage(FILEPATH);
    private Ui ui = new Ui();
    private ModuleList moduleList = new ModuleList();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void populateList() throws InvalidCommandException, InvalidModuleTypeException, InputNotNumberException,
            IncorrectParameterCountException, ExistingModuleException, InvalidModularCreditException,
            ModuleNotCompleteException, SaveModuleFailException, InvalidModuleGradeException,
            UnableToDeletePrereqModuleException, PrerequisiteNotFoundException, ModuleNotFoundException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException, InvalidModuleCodeException {
        String firstModule = "add Programming Methodology -mc 4 -t core -c cs1010";
        String secondModule = "add Algo and Strutures -mc 4 -t core -c cs2040c";
        String thirdModule = "add Discrete Structures -mc 4 -t core -c cs1231s";
        Command addFirst = Parser.parseCommand(firstModule);
        addFirst.execute(moduleList, ui, storage);
        Command addSecond = Parser.parseCommand(secondModule);
        addSecond.execute(moduleList, ui, storage);
        Command addThird = Parser.parseCommand(thirdModule);
        addThird.execute(moduleList, ui, storage);
        String setFirstToDone = "done cs1010 -g A+";
        String setSecondToDone = "done cs2040c -g A-";
        String setThirdToDone = "done cs1231s -g A";
        Command doneFirst = Parser.parseCommand(setFirstToDone);
        Command doneSecond = Parser.parseCommand(setSecondToDone);
        Command doneThird = Parser.parseCommand(setThirdToDone);
        doneFirst.execute(moduleList, ui, storage);
        doneSecond.execute(moduleList, ui, storage);
        doneThird.execute(moduleList, ui, storage);
    }

    /*----------------- Unit test -----------------------*/
    @Test
    void executeProgressCommand_validParameters_success() throws InvalidModuleTypeException, InputNotNumberException,
            ExistingModuleException, InvalidModularCreditException, ModuleNotCompleteException, SaveModuleFailException,
            InvalidModuleGradeException, UnableToDeletePrereqModuleException, PrerequisiteNotFoundException,
            ModuleNotFoundException, InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, InvalidCommandException {

        Command progressCommand = new ProgressCommand();
        System.setOut(new PrintStream(outContent));
        progressCommand.execute(moduleList, ui, storage);
        assertEquals("Progress:" + System.lineSeparator() + "█░░░░░░░░░░ 7.50%" + System.lineSeparator()
                + "12MCs/160MCs Completed" + System.lineSeparator(), outContent.toString());
        System.out.println();
        System.setOut(originalOut);
    }

    @AfterEach
    void tearDownList() throws InvalidCommandException, InvalidModuleTypeException, InputNotNumberException,
            IncorrectParameterCountException, ExistingModuleException, InvalidModularCreditException,
            ModuleNotCompleteException, SaveModuleFailException, InvalidModuleGradeException,
            UnableToDeletePrereqModuleException, PrerequisiteNotFoundException, ModuleNotFoundException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException, InvalidModuleCodeException {
        String firstModule = "Delete cs1010";
        String secondModule = "Delete cs2040c";
        String thirdModule = "Delete cs1231s";
        Command deleteFirst = Parser.parseCommand(firstModule);
        Command deleteSecond = Parser.parseCommand(secondModule);
        Command deleteThird = Parser.parseCommand(thirdModule);
        deleteFirst.execute(moduleList, ui, storage);
        deleteSecond.execute(moduleList, ui, storage);
        deleteThird.execute(moduleList, ui, storage);
    }

}
