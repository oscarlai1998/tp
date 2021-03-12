package seedu.igraduate.command;

import seedu.igraduate.ModuleList;
import seedu.igraduate.Storage;
import seedu.igraduate.Ui;
import seedu.igraduate.exception.IncorrectModuleTypeException;
import seedu.igraduate.exception.SaveModuleFailException;
import seedu.igraduate.module.CoreModule;
import seedu.igraduate.module.ElectiveModule;
import seedu.igraduate.module.GeModule;
import seedu.igraduate.module.MathModule;
import seedu.igraduate.module.Module;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles add command.
 */
public class AddCommand extends Command {
    protected String moduleCode;
    protected String moduleType;
    protected Double moduleCredits;

    private static final String CORE = "core";
    private static final String UE = "ue";
    private static final String MATH = "math";
    private static final String GE = "ge";
    private static final String MOD_NAME = "module name";
    private static final String DEFAULT_STATUS = "not taken";
    private static final String DEFAULT_GRADE = "nil";

    private ArrayList<String> preRequisites;

    public AddCommand(String moduleCode, String moduleType, double moduleCredits) {
        this.moduleCode = moduleCode;
        this.moduleType = moduleType;
        this.moduleCredits = moduleCredits;
    }

    /**
     * Todo: Add comments here.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage)
            throws SaveModuleFailException, IncorrectModuleTypeException {
        try {
            Module module = createModuleByType();
            moduleList.add(module);
            storage.saveModulesToFile(moduleList);
            ui.printAddedModuleSuccess(module);
        } catch (IOException e) {
            throw new SaveModuleFailException();
        } catch (IncorrectModuleTypeException e) {
            throw new IncorrectModuleTypeException();
        }
    }

    public Module createModuleByType() throws IncorrectModuleTypeException {
        Module module;
        switch (moduleType) {
        case CORE:
            module = new CoreModule(moduleCode, MOD_NAME, moduleCredits,
                    DEFAULT_STATUS, DEFAULT_GRADE, preRequisites);
            break;
        case UE:
            module = new ElectiveModule(moduleCode, MOD_NAME, moduleCredits,
                    DEFAULT_STATUS, DEFAULT_GRADE, preRequisites);
            break;
        case MATH:
            module = new MathModule(moduleCode, MOD_NAME, moduleCredits,
                    DEFAULT_STATUS, DEFAULT_GRADE, preRequisites);
            break;
        case GE:
            module = new GeModule(moduleCode, MOD_NAME, moduleCredits,
                    DEFAULT_STATUS, DEFAULT_GRADE, preRequisites);
            break;
        default:
            throw new IncorrectModuleTypeException();
        }
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
