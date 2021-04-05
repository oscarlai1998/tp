package seedu.igraduate.logic.command.listcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import seedu.igraduate.logic.command.AddCommand;
import seedu.igraduate.logic.parser.Parser;
import seedu.igraduate.model.module.Module;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;
import seedu.igraduate.model.list.ModuleList;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;

class ListCommandTest {

    private static final File FILEPATH = Paths.get("./commandteststorage/listCommandData.json").toFile();

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
        Module module = moduleList.getModule("cs1010");
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
    void executeListCommand_allModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {
        String line = "list all";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        Module firstModule = moduleList.getByCode("cs1010");
        Module secondModule = moduleList.getByCode("cs2100");
        String successMessage = "Module List: " + System.lineSeparator() + "1: " + firstModule + System.lineSeparator()
                + "2: " + secondModule + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_noModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {
        // Assign empty module list
        moduleList = new ModuleList();

        // Execute list command
        String line = "list all";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        String successMessage = Ui.EMPTY_LIST_MESSAGE + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_completedModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {
        // Mark CS1010 as taken
        Module module = moduleList.getByCode("cs1010");
        moduleList.markAsTaken(module);
        module.setGrade("A");

        String line = "list complete";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        String successMessage = ui.MODULES_TAKEN_MESSAGE + System.lineSeparator() + "1: " + module
                + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_noCompletedModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {
        String line = "list complete";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        String successMessage = Ui.EMPTY_COMPLETE_LIST_MESSAGE + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_incompletedModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {
        // Mark CS1010 as taken
        Module module = moduleList.getByCode("cs1010");
        moduleList.markAsTaken(module);
        module.setGrade("A");

        String line = "list incomplete";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        Module incompletedModule = moduleList.getByCode("cs2100");
        String successMessage = ui.MODULES_LEFT_MESSAGE + System.lineSeparator() + "1: " + incompletedModule
                + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_noIncompletedModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {
        // Mark all modules as taken
        Module firstModule = moduleList.getByCode("cs1010");
        moduleList.markAsTaken(firstModule);
        firstModule.setGrade("A");
        Module secondModule = moduleList.getByCode("cs2100");
        moduleList.markAsTaken(secondModule);
        secondModule.setGrade("A");

        String line = "list incomplete";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        String successMessage = Ui.EMPTY_INCOMPLETE_LIST_MESSAGE + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_availableModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {
        // Mark CS1010 as taken
        Module module = moduleList.getByCode("cs1010");
        moduleList.markAsTaken(module);
        moduleList.setGrade(module, "A");
        storage.saveModulesToFile(moduleList);

        String line = "list available";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        Module availableModule = moduleList.getByCode("cs2100");
        String successMessage = ui.MODULES_AVAILABLE_MESSAGE + System.lineSeparator() + "1: " + availableModule
                + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_noAvailableModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {
        // Mark all modules as taken
        Module firstModule = moduleList.getByCode("cs1010");
        moduleList.markAsTaken(firstModule);
        firstModule.setGrade("A");
        Module secondModule = moduleList.getByCode("cs2100");
        moduleList.markAsTaken(secondModule);
        secondModule.setGrade("A");

        String line = "list available";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        String successMessage = Ui.EMPTY_AVAILABLE_LIST_MESSAGE + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_coreModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {

        String line = "list core";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        Module firstCoreModule = moduleList.getByCode("cs1010");
        Module secondCoreModule = moduleList.getByCode("cs2100");
        String successMessage = ui.MODULES_CORE_MESSAGE + System.lineSeparator() + "1: " + firstCoreModule
                + System.lineSeparator() + "2: " + secondCoreModule + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_noCoreModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {
        // Assign empty module list
        moduleList = new ModuleList();

        String line = "list core";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        String successMessage = Ui.EMPTY_CORE_MODULE_LIST_MESSAGE + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_electiveModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {
        // Add an elective module
        ArrayList<String> preRequisites = new ArrayList<>();
        ArrayList<String> untakenPreRequisites = new ArrayList<>();
        AddCommand addCommand = new AddCommand("laj1201", "Japanese 1", "ue", 4.0,
                preRequisites, untakenPreRequisites);
        addCommand.execute(moduleList, ui, storage);

        String line = "list elec";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        Module electiveModule = moduleList.getByCode("LAJ1201");
        String successMessage = ui.MODULES_ELECTIVE_MESSAGE + System.lineSeparator() + "1: " + electiveModule
                + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_noElectiveModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {

        String line = "list elec";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        String successMessage = Ui.EMPTY_ELECTIVE_MODULE_LIST_MESSAGE + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_geModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {
        // Add a ge module
        ArrayList<String> preRequisites = new ArrayList<>();
        ArrayList<String> untakenPreRequisites = new ArrayList<>();
        AddCommand addCommand = new AddCommand("GER1000", "Quantitative Reasoning", "ge", 4.0,
                preRequisites, untakenPreRequisites);
        addCommand.execute(moduleList, ui, storage);

        String line = "list ge";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        Module geModule = moduleList.getByCode("gEr1000");
        String successMessage = ui.MODULES_GE_MESSAGE + System.lineSeparator() + "1: " + geModule
                + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_noGeModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {

        String line = "list ge";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        String successMessage = Ui.EMPTY_GE_MODULE_LIST_MESSAGE + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_mathModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {
        // Add a math module
        ArrayList<String> preRequisites = new ArrayList<>();
        ArrayList<String> untakenPreRequisites = new ArrayList<>();
        AddCommand addCommand = new AddCommand("ma1521", "Calculus for Computing", "math", 4.0,
                preRequisites, untakenPreRequisites);
        addCommand.execute(moduleList, ui, storage);

        String line = "list math";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        Module mathModule = moduleList.getByCode("mA1521");
        String successMessage = ui.MODULES_MATH_MESSAGE + System.lineSeparator() + "1: " + mathModule
                + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void executeListCommand_noMathModules_success() throws ExistingModuleException, InvalidModuleTypeException,
            SaveModuleFailException, IncorrectParameterCountException, InvalidCommandException, InputNotNumberException,
            InvalidModularCreditException, ModuleNotFoundException, PrerequisiteNotFoundException,
            ModuleNotCompleteException, UnableToDeletePrereqModuleException, InvalidModuleGradeException,
            InvalidListTypeException, PrerequisiteNotMetException, AddSelfToPrereqException,
            MarkCompletedModuleException, IllegalParametersException {

        String line = "list math";
        Command listCommand = Parser.parseCommand(line);
        System.setOut(new PrintStream(outContent));
        listCommand.execute(moduleList, ui, storage);
        String successMessage = Ui.EMPTY_MATH_MODULE_LIST_MESSAGE + System.lineSeparator();
        assertEquals(successMessage, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void isExit() throws InvalidModuleTypeException, IncorrectParameterCountException, InvalidCommandException,
            InputNotNumberException, InvalidListTypeException, InvalidModularCreditException, 
            IllegalParametersException {
        String line = "list all";
        Command listCommand = Parser.parseCommand(line);
        assertEquals(false, listCommand.isExit());
    }

    @AfterEach
    void tearDown() {
        moduleList = new ModuleList();
    }
}