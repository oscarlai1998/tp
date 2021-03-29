package seedu.igraduate.logic.command;

import java.util.ArrayList;
import java.util.logging.Logger;
import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.logic.Parser;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;
import seedu.igraduate.exception.InputNotNumberException;
import seedu.igraduate.exception.InvalidCommandException;
import seedu.igraduate.exception.ModuleNotCompleteException;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.SaveModuleFailException;
import seedu.igraduate.exception.InvalidModuleGradeException;
import seedu.igraduate.model.module.Module;

/**
 * Handles update command. 
 */
public class UpdateCommand extends Command {
    private String moduleCode;
    private String[] commandFlags;

    private Module targetModule;
    private double moduleCredit;
    private String moduleGrade;
    private String moduleName;
    private ArrayList<String> preRequisites;

    private static final Logger LOGGER = Logger.getLogger(AddCommand.class.getName());

    /**
     * Child class of the command class that contains the module code and command flags. 
     * 
     * @param moduleCode module code. 
     * @param commandFlags flags containing all the information to update. 
     */
    public UpdateCommand(String moduleCode, String[] commandFlags) {
        this.moduleCode = moduleCode;
        this.commandFlags = commandFlags;
    }

    /**
     * Executes the updateCommand function. 
     * 
     * @param modules Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     * @throws ModuleNotFoundException If the module code is not found in module list. 
     * @throws NumberFormatException If module credit is not an integer (or double). 
     * @throws InputNotNumberException If module credit is not an integer (or double). 
     * @throws ModuleNotCompleteException If the module has not been marked as completed. 
     * @throws SaveModuleFailException If the program fails to save changes. 
     */
    @Override
    public void execute(ModuleList modules, Ui ui, Storage storage) 
            throws ModuleNotFoundException,
            NumberFormatException, InputNotNumberException, ModuleNotCompleteException,
            InvalidModuleGradeException, SaveModuleFailException {
        this.targetModule = modules.getModule(moduleCode);
        
        try {
            updateModuleGrade(this.commandFlags);
        } catch (ModuleNotCompleteException exception) {
            throw exception;
        } finally {
            updateModuleName(this.commandFlags);
            updateModuleCredits(this.commandFlags);
            updatePrerequisites(this.commandFlags, modules);
            storage.saveModulesToFile(modules);
        }

        ui.printUpdateSuccess(targetModule);
    }

    /**
     * Updates the module name from commandFlags. 
     * 
     * @param commandFlags List containing all flags and values. 
     */
    private void updateModuleName(String[] commandFlags) {
        try {
            moduleName = Parser.extractModuleName(commandFlags);
            targetModule.setName(moduleName);
        } catch (InvalidCommandException exception) {
            LOGGER.info("No name field found, not updates to name done. ");
        }
    }

    /**
     * Updates the module credit from commandFlags. 
     * 
     * @param commandFlags List containing all flags and values. 
     * @throws NumberFormatException If module credit is not an integer (or double). 
     * @throws InputNotNumberException If module credit is not an integer (or double). 
     */
    private void updateModuleCredits(String[] commandFlags) throws NumberFormatException, InputNotNumberException {
        try {
            moduleCredit = Parser.extractModuleCredits(commandFlags);
            targetModule.setCredit(moduleCredit);
        } catch (InvalidCommandException exception) {
            LOGGER.info("No credit field found, no updates to credit done. ");
        }
    }

    /**
     * Updates module grade from commandFlags. 
     * If the module is incomplete, no updates is done on the module grade.  
     * 
     * @param commandFlags List containing all flags and values. 
     * @throws ModuleNotCompleteException If the module has not been marked as completed. 
     */
    private void updateModuleGrade(String[] commandFlags) throws ModuleNotCompleteException,
        InvalidModuleGradeException {
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
     * Updates prerequisites of a module from commandFlags.
     * The method will completely overwrite the existing list of prerequisites for a particular module.
     *
     * @param commandFlags List containing all the flags and values.
     * @param modules List of all modules.
     * @throws ModuleNotFoundException If the module is not found.
     */
    private void updatePrerequisites(String[] commandFlags, ModuleList modules)
            throws ModuleNotFoundException {
        // Extract all new prerequisites
        preRequisites = Parser.extractPreRequisites(commandFlags);
        // Extract new prerequisites that are not taken
        ArrayList<String> notTakenPrerequisites = extractPrerequisitesNotTaken(modules, preRequisites);
        // Remove targetModule from requiredBy of old prerequisites list and
        // add targetModule to requiredBy of new prerequisites
        removeModuleFromRequiredBy(targetModule, modules);
        addModuleToRequiredBy(targetModule, modules, preRequisites);
        targetModule.setUntakenPreRequisites(notTakenPrerequisites);
        targetModule.setPreRequisites(preRequisites);
    }

    /**
     * Extracts a list of prerequisites that are not taken from a list of all prerequisites.
     *
     * @param modules List of all modules.
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
     * Removes a module code from the requiredBy tables of a list of modules.
     *
     * @param targetModule Module to be removed from requiredBy.
     * @param moduleList The list of modules to remove targetModule from requiredBy.
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
     * @param targetModule Module to be added to requiredBy.
     * @param moduleList List of all modules.
     * @param newPrerequisites List of modules that require the adding of targetModule to its requiredBy table.
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
