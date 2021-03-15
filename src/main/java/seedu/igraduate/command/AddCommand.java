package seedu.igraduate.command;

import seedu.igraduate.ModuleList;
import seedu.igraduate.Storage;
import seedu.igraduate.Ui;

import seedu.igraduate.exception.ExistingModuleException;
import seedu.igraduate.exception.InvalidModuleTypeException;
import seedu.igraduate.exception.SaveModuleFailException;

import seedu.igraduate.module.CoreModule;
import seedu.igraduate.module.ElectiveModule;
import seedu.igraduate.module.GeModule;
import seedu.igraduate.module.MathModule;
import seedu.igraduate.module.Module;

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

    private static final String CORE = "core";
    private static final String UE = "ue";
    private static final String MATH = "math";
    private static final String GE = "ge";
    private static final String DEFAULT_STATUS = "not taken";
    private static final String DEFAULT_GRADE = "nil";

    private ArrayList<String> preRequisites;

    private static final Logger LOGGER = Logger.getLogger(AddCommand.class.getName());

    /**
     * Child class of the command class that contains the module name, code, type and credits to be added. 
     * 
     * @param moduleCode module code. 
     * @param moduleName module name, customised according to user input. 
     * @param moduleType module type (core, ue, ge or math). 
     * @param moduleCredits number of credits for the module. 
     */
    public AddCommand(String moduleCode, String moduleName, String moduleType, double moduleCredits) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleType = moduleType;
        this.moduleCredits = moduleCredits;
    }

    /**
     * Executes the addCommand function. 
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage)
            throws SaveModuleFailException, InvalidModuleTypeException, ExistingModuleException {
        LOGGER.log(Level.INFO, "going to start adding");
        try {
            Module module = createModuleByType();
            moduleList.add(module);
            storage.saveModulesToFile(moduleList);
            ui.printAddedModuleSuccess(module);
        } catch (InvalidModuleTypeException e) {
            LOGGER.log(Level.WARNING, "invalid module type", e);
            LOGGER.log(Level.INFO, "end of adding");
            throw new InvalidModuleTypeException();
        } catch (ExistingModuleException e) {
            LOGGER.log(Level.WARNING, "existing module", e);
            LOGGER.log(Level.INFO, "end of adding");
            throw new ExistingModuleException();
        }
        LOGGER.log(Level.INFO, "end of adding");
    }

    /**
     * Create a module based on its category.
     * Types: Core, UE, Math, GE.
     *
     * @return the created module.
     * @throws InvalidModuleTypeException if module type does not fit any categories.
     */
    public Module createModuleByType() throws InvalidModuleTypeException {
        LOGGER.log(Level.INFO, "going to start creating");
        Module module;
        switch (moduleType) {
        case CORE:
            module = new CoreModule(moduleCode, moduleName, moduleCredits,
                    DEFAULT_STATUS, DEFAULT_GRADE, preRequisites);
            break;
        case UE:
            module = new ElectiveModule(moduleCode, moduleName, moduleCredits,
                    DEFAULT_STATUS, DEFAULT_GRADE, preRequisites);
            break;
        case MATH:
            module = new MathModule(moduleCode, moduleName, moduleCredits,
                    DEFAULT_STATUS, DEFAULT_GRADE, preRequisites);
            break;
        case GE:
            module = new GeModule(moduleCode, moduleName, moduleCredits,
                    DEFAULT_STATUS, DEFAULT_GRADE, preRequisites);
            break;
        default:
            LOGGER.log(Level.INFO, "invalid module type");
            LOGGER.log(Level.INFO, "end of creating");
            throw new InvalidModuleTypeException();
        }
        LOGGER.log(Level.INFO, "end of creating");
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
