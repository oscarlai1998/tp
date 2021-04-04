package seedu.igraduate.logic.command;

import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;

import seedu.igraduate.exception.SaveModuleFailException;
import seedu.igraduate.exception.InvalidModuleTypeException;
import seedu.igraduate.exception.ExistingModuleException;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.PrerequisiteNotFoundException;
import seedu.igraduate.exception.InvalidModularCreditException;

import seedu.igraduate.model.module.CoreModule;
import seedu.igraduate.model.module.ElectiveModule;
import seedu.igraduate.model.module.GeModule;
import seedu.igraduate.model.module.MathModule;
import seedu.igraduate.model.module.Module;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles add command.
 */
public class AddCommand extends Command {
    protected String moduleCode;
    protected String moduleName;
    protected String moduleType;
    protected Double moduleCredits;
    protected ArrayList<String> preRequisites;
    protected ArrayList<String> untakenPreRequisites;

    private static final String CORE = "core";
    private static final String UE = "ue";
    private static final String MATH = "math";
    private static final String GE = "ge";
    private static final String DEFAULT_STATUS = "not taken";
    private static final String DEFAULT_GRADE = "nil";

    private static final Logger LOGGER = Logger.getLogger(AddCommand.class.getName());

    /**
     * Child class of the command class that contains the module name, code, type, credits
     * and pre-requisite modules to be added.
     * 
     * @param moduleCode module code. 
     * @param moduleName module name, customised according to user input. 
     * @param moduleType module type (core, ue, ge or math). 
     * @param moduleCredits number of credits for the module.
     * @param preRequisites ArrayList containing all pre-requisite modules.
     */
    public AddCommand(String moduleCode, String moduleName, String moduleType, double moduleCredits,
                      ArrayList<String> preRequisites, ArrayList<String> untakenPreRequisites) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleType = moduleType;
        this.moduleCredits = moduleCredits;
        this.preRequisites = preRequisites;
        this.untakenPreRequisites = untakenPreRequisites;
    }

    /**
     * Retrieves and return module code for the current module.
     *
     * @return module code.
     */
    private String getModuleCode() {
        return moduleCode;
    }

    /**
     * Executes the addCommand function. 
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     * @throws SaveModuleFailException If storage fail to save module data to disk.
     * @throws InvalidModuleTypeException If the module type is invalid.
     * @throws ExistingModuleException If the module to be added already exists in module list.
     * @throws ModuleNotFoundException If the module code is not found.
     * @throws PrerequisiteNotFoundException If any of the pre-requisite module does not exists.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage)
        throws SaveModuleFailException, InvalidModuleTypeException, ExistingModuleException,
        ModuleNotFoundException, PrerequisiteNotFoundException, InvalidModularCreditException {
        LOGGER.log(Level.INFO, "Executing add command...");
        if (moduleCredits > 32) {
            LOGGER.log(Level.WARNING, "Number of MCs exceeds 32. Adding command terminated.");
            ui.printExceededMaxMCs();
            return;
        }
        try {
            Module module = createModuleByType();
            moduleList.add(module);
            storage.saveModulesToFile(moduleList);
            ui.printAddedModuleSuccess(module);
            LOGGER.log(Level.INFO, String.format("Successfully added %s module to module list.",
                    getModuleCode()));
        } catch (InvalidModuleTypeException e) {
            LOGGER.log(Level.WARNING, "Failed to add invalid module type.", e);
            throw new InvalidModuleTypeException();
        } catch (ExistingModuleException e) {
            LOGGER.log(Level.WARNING, "Failed to add duplicated module to module list.", e);
            throw new ExistingModuleException();
        } catch (PrerequisiteNotFoundException e) {
            LOGGER.log(Level.WARNING, "Failed to add non-existing pre-requisite modules", e);
            throw new PrerequisiteNotFoundException();
        } finally {
            LOGGER.log(Level.INFO, "End of add command execution.");
        }
    }

    /**
     * Create a module based on its category.
     * Types: Core, UE, Math, GE.
     *
     * @return the created module.
     * @throws InvalidModuleTypeException if module type does not fit any categories.
     */
    public Module createModuleByType() throws InvalidModuleTypeException {
        LOGGER.log(Level.INFO, "Creating module...");
        Module module;
        switch (moduleType) {
        case CORE:
            module = new CoreModule(moduleCode, moduleName, moduleCredits,
                    DEFAULT_STATUS, DEFAULT_GRADE, preRequisites, untakenPreRequisites);
            break;
        case UE:
            module = new ElectiveModule(moduleCode, moduleName, moduleCredits,
                    DEFAULT_STATUS, DEFAULT_GRADE, preRequisites, untakenPreRequisites);
            break;
        case MATH:
            module = new MathModule(moduleCode, moduleName, moduleCredits,
                    DEFAULT_STATUS, DEFAULT_GRADE, preRequisites, untakenPreRequisites);
            break;
        case GE:
            module = new GeModule(moduleCode, moduleName, moduleCredits,
                    DEFAULT_STATUS, DEFAULT_GRADE, preRequisites, untakenPreRequisites);
            break;
        default:
            LOGGER.log(Level.INFO, "Failed to create invalid module type.");
            LOGGER.log(Level.INFO, "End of module creation.");
            throw new InvalidModuleTypeException();
        }
        LOGGER.log(Level.INFO, "End of module creation.");

        return module;
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
