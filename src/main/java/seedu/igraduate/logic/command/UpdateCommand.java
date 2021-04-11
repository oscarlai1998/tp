package seedu.igraduate.logic.command;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.igraduate.exception.SaveModuleFailException;
import seedu.igraduate.exception.AddSelfToPrereqException;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.ModuleNotCompleteException;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.PrerequisiteNotFoundException;
import seedu.igraduate.exception.PrerequisiteNotMetException;
import seedu.igraduate.exception.InvalidModularCreditException;
import seedu.igraduate.exception.InvalidModuleGradeException;
import seedu.igraduate.exception.InvalidCommandException;

import seedu.igraduate.logic.parser.Parser;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;
import seedu.igraduate.model.module.Module;
import seedu.igraduate.model.list.ModuleList;

/**
 * Handles update command.
 */
public class UpdateCommand extends Command {
    // Update information provided by the user
    private String moduleCode;
    private ArrayList<String> commandFlags;

    // Information used to aid update operations
    private Module targetModule;
    private double moduleCredit;
    private String moduleGrade;
    private String moduleName;
    private ArrayList<String> prerequisites;

    private static final Logger LOGGER = Logger.getLogger(UpdateCommand.class.getName());

    /**
     * Constructs a new UpdateCommand object.
     * 
     * @param moduleCode   Module code of target module.
     * @param commandFlags Flags containing all the information to update.
     */
    public UpdateCommand(String moduleCode, ArrayList<String> commandFlags) {
        this.moduleCode = moduleCode;
        this.commandFlags = commandFlags;
    }

    /**
     * Updates the information of a module specified by the user.
     * Prints the update message after successfully updated the value.
     * 
     * @param modules Module list consisting of all modules.
     * @param ui      User interface for printing result.
     * @param storage Storage for storing module list data.
     * @throws ModuleNotFoundException       If the module code is not found in module list.
     * @throws InputNotNumberException       If module credit is not an integer (or double).
     * @throws ModuleNotCompleteException    If the module has not been marked as completed.
     * @throws InvalidModuleGradeException   If the module grade is not valid.
     * @throws SaveModuleFailException       If the program fails to save changes.
     * @throws AddSelfToPrereqException      If attempts to add the module as its own prerequisite.
     * @throws InvalidModularCreditException If modular credit is not valid.
     * @throws PrerequisiteNotMetException   If prerequisite of the module has not been completed.
     * @throws PrerequisiteNotFoundException If any prerequisite is not found in module list.
     */
    @Override
    public void execute(ModuleList modules, Ui ui, Storage storage) throws ModuleNotFoundException,
            InputNotNumberException, ModuleNotCompleteException, InvalidModuleGradeException,
            SaveModuleFailException, AddSelfToPrereqException, InvalidModularCreditException,
            PrerequisiteNotMetException, PrerequisiteNotFoundException {

        LOGGER.log(Level.INFO, "Executing update command...");

        this.targetModule = modules.getModuleByCode(moduleCode);

        try {
            updateModuleGrade(this.commandFlags);
        } catch (ModuleNotCompleteException exception) {
            LOGGER.log(Level.WARNING, "Could not update grade for incomplete module...");
            throw new ModuleNotCompleteException();
        } finally {
            updateModuleName(this.commandFlags);
            updateModuleCredits(this.commandFlags);
            updatePrerequisites(this.commandFlags, modules);
            storage.saveModulesToFile(modules);
        }
        
        ui.printUpdateModuleMessage(targetModule);

        LOGGER.log(Level.INFO, "End of update command execution.");
    }

    /**
     * Updates the module name from commandFlags.
     * 
     * @param commandFlags List containing all flags and values.
     */
    private void updateModuleName(ArrayList<String> commandFlags) {
        try {
            moduleName = Parser.extractModuleName(commandFlags);
            targetModule.setName(moduleName);
        } catch (InvalidCommandException exception) {
            LOGGER.info("No name field found, not updates to name done.");
        }
    }

    /**
     * Updates the module credit from commandFlags.
     * 
     * @param commandFlags List containing all flags and values.
     * @throws InputNotNumberException If module credit is not an integer (or double).
     * @throws InvalidModularCreditException If module credit is not valid.
     */
    private void updateModuleCredits(ArrayList<String> commandFlags) throws InputNotNumberException,
            InvalidModularCreditException {
        try {
            moduleCredit = Parser.extractModularCredit(commandFlags);
            targetModule.setCredit(moduleCredit);
        } catch (InvalidCommandException exception) {
            LOGGER.info("No credit field found, no updates to credit done. ");
        }
    }

    /**
     * Updates module grade from commandFlags. If the module is incomplete, no
     * updates is done on the module grade.
     * 
     * @param commandFlags List containing all flags and values.
     * @throws ModuleNotCompleteException If the module has not been marked as completed.
     * @throws InvalidModuleGradeException If the module grade provided is not valid.
     */
    private void updateModuleGrade(ArrayList<String> commandFlags) throws ModuleNotCompleteException,
            InvalidModuleGradeException {
        try {
            moduleGrade = Parser.extractModuleGrade(commandFlags);
            if (!targetModule.isDone()) {
                LOGGER.warning("Module has not been completed, no grade update is permitted.");
                throw new ModuleNotCompleteException();
            }
            targetModule.setGrade(moduleGrade);
        } catch (InvalidModuleGradeException e) {
            LOGGER.info("Module grade provided is invalid.");
            throw new InvalidModuleGradeException();
        } catch (InvalidCommandException e) {
            LOGGER.info("No grade field found, no updates to grade done.");
        }
    }

    /**
     * Updates prerequisites of a module from commandFlags. The method will
     * completely overwrite the existing list of prerequisites for a particular
     * module.
     *
     * @param commandFlags List containing all the flags and values.
     * @param moduleList      List of all modules.
     * @throws ModuleNotFoundException     If the module is not found.
     * @throws AddSelfToPrereqException    If attempts to add a module to its own prerequisite.
     * @throws PrerequisiteNotMetException If the prerequisite of the module has not been completed.
     * @throws PrerequisiteNotFoundException If any prerequisite is not found in module list.
     */
    private void updatePrerequisites(ArrayList<String> commandFlags, ModuleList moduleList)
            throws ModuleNotFoundException, AddSelfToPrereqException, PrerequisiteNotMetException,
            PrerequisiteNotFoundException {
        // Extract all new prerequisites
        prerequisites = Parser.extractPrerequisites(commandFlags);

        if (isOwnPrerequisite(targetModule, prerequisites)) {
            throw new AddSelfToPrereqException();
        }

        // Extract new prerequisites that are not taken
        ArrayList<String> notTakenPrerequisites = extractNotTakenPrerequisites(moduleList, prerequisites);

        if (targetModule.isDone() && notTakenPrerequisites.size() > 0) {
            throw new PrerequisiteNotMetException(moduleCode, notTakenPrerequisites);
        }

        // Remove targetModule from requiredBy of old prerequisites list and
        // add targetModule to requiredBy of new prerequisites
        moduleList.removeFromPrerequisiteModuleRequiredBy(targetModule);
        targetModule.setUntakenPrerequisites(notTakenPrerequisites);
        targetModule.setPrerequisites(prerequisites);
        moduleList.addModuleToRequiredBy(targetModule);
    }

    /**
     * Extracts a list of prerequisites that are not taken from a list of all
     * prerequisites.
     *
     * @param moduleList       List of all modules.
     * @param prerequisites List of all prerequisites.
     * @return ArrayList of prerequisites not taken.
     * @throws ModuleNotFoundException If module is not found.
     */
    private ArrayList<String> extractNotTakenPrerequisites(ModuleList moduleList, ArrayList<String> prerequisites)
            throws ModuleNotFoundException {
        ArrayList<String> notTakenPrerequisites = new ArrayList<>();
        for (String moduleCode : prerequisites) {
            if (!moduleList.getModuleByCode(moduleCode).isDone()) {
                notTakenPrerequisites.add(moduleCode);
            }
        }

        return notTakenPrerequisites;
    }

    /**
     * Checks if user is adding the target module code to its own list of prerequisites.
     *
     * @param module        The target module the user wants to update.
     * @param prerequisites List of new prerequisites user wants to add to module.
     * @return True if the module is adding to its own prerequisite, false otherwise.
     */
    private boolean isOwnPrerequisite(Module module, ArrayList<String> prerequisites) {
        String moduleCode = module.getCode();
        if (prerequisites.contains(moduleCode)) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
