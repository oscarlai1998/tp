package seedu.igraduate.logic.command;

import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.igraduate.exception.SaveModuleFailException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.PrerequisiteNotMetException;
import seedu.igraduate.exception.InvalidModuleGradeException;
import seedu.igraduate.exception.ModuleNotCompleteException;
import seedu.igraduate.exception.AddSelfToPrereqException;
import seedu.igraduate.exception.InvalidModularCreditException;

import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.logic.parser.Parser;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;
import seedu.igraduate.model.module.Module;

/**
 * Handles update command.
 */
public class UpdateCommand extends Command {
    private String moduleCode;
    private ArrayList<String> commandFlags;

    private Module targetModule;
    private double moduleCredit;
    private String moduleGrade;
    private String moduleName;
    private ArrayList<String> preRequisites;

    private static final Logger LOGGER = Logger.getLogger(AddCommand.class.getName());

    /**
     * Child class of the command class that contains the module code and command
     * flags.
     * 
     * @param moduleCode   module code.
     * @param commandFlags flags containing all the information to update.
     */
    public UpdateCommand(String moduleCode, ArrayList<String> commandFlags) {
        this.moduleCode = moduleCode;
        this.commandFlags = commandFlags;
    }

    /**
     * Executes the updateCommand function.
     * 
     * @param modules Module list consisting of all modules.
     * @param ui      User interface for printing result.
     * @param storage Storage for storing module list data.
     * @throws ModuleNotFoundException    If the module code is not found in module
     *                                    list.
     * @throws NumberFormatException      If module credit is not an integer (or
     *                                    double).
     * @throws InputNotNumberException    If module credit is not an integer (or
     *                                    double).
     * @throws ModuleNotCompleteException If the module has not been marked as
     *                                    completed.
     * @throws SaveModuleFailException    If the program fails to save changes.
     * @throws PrerequisiteNotMetException If prerequisite of the module has not been completed.
     */
    @Override
    public void execute(ModuleList modules, Ui ui, Storage storage)
            throws ModuleNotFoundException, NumberFormatException, InputNotNumberException, ModuleNotCompleteException,
            InvalidModuleGradeException, SaveModuleFailException, AddSelfToPrereqException,
            InvalidModularCreditException, PrerequisiteNotMetException {
        this.targetModule = modules.getModule(moduleCode);

        updateModuleGrade(this.commandFlags);
        updateModuleName(this.commandFlags);
        updateModuleCredits(this.commandFlags);
        updatePrerequisites(this.commandFlags, modules);
        storage.saveModulesToFile(modules);

        ui.printUpdateSuccess(targetModule);
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
     * @throws NumberFormatException   If module credit is not an integer (or
     *                                 double).
     * @throws InputNotNumberException If module credit is not an integer (or
     *                                 double).
     */
    private void updateModuleCredits(ArrayList<String> commandFlags)
            throws NumberFormatException, InputNotNumberException, InvalidModularCreditException {
        try {
            moduleCredit = Parser.extractModuleCredits(commandFlags);
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
     * @throws ModuleNotCompleteException If the module has not been marked as
     *                                    completed.
     */
    private void updateModuleGrade(ArrayList<String> commandFlags)
            throws ModuleNotCompleteException, InvalidModuleGradeException {
        try {
            moduleGrade = Parser.extractModuleGrade(commandFlags);
            if (!targetModule.isDone()) {
                LOGGER.warning("Module has not been completed, no grade update is permitted. ");
                throw new ModuleNotCompleteException();
            }
            if (!Module.isGradeValid(moduleGrade)) {
                throw new InvalidModuleGradeException();
            }
            targetModule.setGrade(moduleGrade);
        } catch (InvalidCommandException invalidCommandException) {
            LOGGER.info("No grade field found, no updates to grade done. ");
        }
    }

    /**
     * Updates prerequisites of a module from commandFlags. The method will
     * completely overwrite the existing list of prerequisites for a particular
     * module.
     *
     * @param commandFlags List containing all the flags and values.
     * @param modules      List of all modules.
     * @throws ModuleNotFoundException   If the module is not found.
     * @throws PrerequisiteNotMetException If the prerequisite of the module has not been completed.
     */
    private void updatePrerequisites(ArrayList<String> commandFlags, ModuleList modules)
            throws ModuleNotFoundException, AddSelfToPrereqException, PrerequisiteNotMetException {
        // Extract all new prerequisites
        preRequisites = Parser.extractPreRequisites(commandFlags);
        checkSelfPrerequisite(targetModule, preRequisites);

        // Extract new prerequisites that are not taken
        ArrayList<String> notTakenPrerequisites = extractPrerequisitesNotTaken(modules, preRequisites);

        if (targetModule.isDone() && notTakenPrerequisites.size() > 0) {
            throw new PrerequisiteNotMetException(moduleCode, notTakenPrerequisites);
        }

        // Remove targetModule from requiredBy of old prerequisites list and
        // add targetModule to requiredBy of new prerequisites
        removeModuleFromRequiredBy(targetModule, modules);
        addModuleToRequiredBy(targetModule, modules, preRequisites);
        targetModule.setUntakenPreRequisites(notTakenPrerequisites);
        targetModule.setPreRequisites(preRequisites);
    }

    /**
     * Extracts a list of prerequisites that are not taken from a list of all
     * prerequisites.
     *
     * @param modules       List of all modules.
     * @param prerequisites List of all prerequisites.
     * @return ArrayList of prerequisites not taken.
     * @throws ModuleNotFoundException If module is not found.
     */
    private ArrayList<String> extractPrerequisitesNotTaken(ModuleList modules, ArrayList<String> prerequisites)
            throws ModuleNotFoundException {
        ArrayList<String> notTakenPrerequisites = new ArrayList<>();
        for (String moduleCode : prerequisites) {
            if (!modules.getModule(moduleCode).isDone()) {
                notTakenPrerequisites.add(moduleCode);
            }
        }

        return notTakenPrerequisites;
    }

    /**
     * Checks if user is adding the target module code to its own list of
     * prerequisites.
     *
     * @param module        The target module the user wants to update.
     * @param preRequisites List of new prerequisites user wants to add to module.
     */
    private void checkSelfPrerequisite(Module module, ArrayList<String> preRequisites) throws AddSelfToPrereqException {
        String moduleCode = module.getCode();
        if (preRequisites.contains(moduleCode)) {
            throw new AddSelfToPrereqException();
        }
    }

    /**
     * Removes a module code from the requiredBy tables of a list of modules.
     *
     * @param targetModule Module to be removed from requiredBy.
     * @param moduleList   The list of modules to remove targetModule from
     *                     requiredBy.
     */
    private void removeModuleFromRequiredBy(Module targetModule, ModuleList moduleList) {
        for (Module module : moduleList.getModules()) {
            if (module.getRequiredByModules().contains(targetModule.getCode())) {
                module.getRequiredByModules().remove(targetModule.getCode());
            }
        }
    }

    /**
     * Adds a module code to the requiredBy tables of a list of modules.
     *
     * @param targetModule     Module to be added to requiredBy.
     * @param moduleList       List of all modules.
     * @param newPrerequisites List of modules that require the adding of
     *                         targetModule to its requiredBy table.
     * @throws ModuleNotFoundException If module is not found.
     */
    private void addModuleToRequiredBy(Module targetModule, ModuleList moduleList, ArrayList<String> newPrerequisites)
            throws ModuleNotFoundException {
        for (String moduleCode : newPrerequisites) {
            Module module = moduleList.getByCode(moduleCode);
            module.getRequiredByModules().add(targetModule.getCode());
        }
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
