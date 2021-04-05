package seedu.igraduate.logic.command.infocommand;

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
import seedu.igraduate.exception.InvalidListTypeException;
import seedu.igraduate.exception.PrerequisiteNotMetException;
import seedu.igraduate.exception.AddSelfToPrereqException;
import seedu.igraduate.exception.MarkCompletedModuleException;

import seedu.igraduate.logic.command.Command;
import seedu.igraduate.logic.parser.Parser;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.model.module.CoreModule;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InfoCommandTest {

    private static final File FILEPATH = Paths.get("./commandteststorage/infoCommandData.json").toFile();

    private Storage storage = Storage.getStorage(FILEPATH);
    private Ui ui = new Ui();
    private ModuleList moduleList = new ModuleList();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void executeInfoCommand_existsModule_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {
        // Add one core module to module list
        String moduleCode = "CS1010";
        String moduleName = "Programming Methodology";
        double moduleCredits = 4.0;
        String moduleStatus = "not taken";
        String moduleGrade = "NIL";
        ArrayList<String> preRequisites = new ArrayList<>();
        ArrayList<String> untakenPreRequisites = new ArrayList<>();
        ArrayList<String> requiredByModules = new ArrayList<>();
        CoreModule coreModule = new CoreModule(moduleCode, moduleName, moduleCredits, moduleStatus, moduleGrade,
                preRequisites, untakenPreRequisites);
        moduleList.add(coreModule);

        String line = "info Cs1010";
        Command infoCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        infoCommand.execute(moduleList, ui, storage);
        String expectedOutput = String.format(Ui.MODULES_INFO_MESSAGE, moduleCode, "Core", moduleCode, moduleName,
                moduleCredits, moduleStatus, moduleGrade, preRequisites, untakenPreRequisites, requiredByModules)
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeInfoCommand_nonExistenceModules_exceptionThrown() throws InvalidModuleTypeException,
            IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, InvalidListTypeException, IllegalParametersException {

        String line = "info cs1111";
        Command infoCommand = Parser.parseCommand(line);
        Exception exception = assertThrows(ModuleNotFoundException.class,
                () -> infoCommand.execute(moduleList, ui, storage));
        String exceptionMessage = ModuleNotFoundException.MODULE_NOT_FOUND_ERROR_MESSAGE;
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void isExit() throws InvalidModuleTypeException, IncorrectParameterCountException, InvalidCommandException,
            InputNotNumberException, InvalidListTypeException, InvalidModularCreditException,
            IllegalParametersException {
        String line = "info cs1010";
        Command infoCommand = Parser.parseCommand(line);
        assertEquals(false, infoCommand.isExit());
    }
}