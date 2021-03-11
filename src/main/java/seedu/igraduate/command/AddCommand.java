package seedu.igraduate.command;

import seedu.igraduate.ModuleList;
import seedu.igraduate.Storage;
import seedu.igraduate.Ui;
import seedu.igraduate.module.CoreModule;
import seedu.igraduate.module.ElectiveModule;
import seedu.igraduate.module.GeModule;
import seedu.igraduate.module.MathModule;

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
    public void execute(ModuleList moduleList, Ui ui, Storage storage) {
        // Todo: Command action
        switch (this.moduleType) {
        case CORE:
            CoreModule coreModule = new CoreModule(this.moduleCode, MOD_NAME, this.moduleCredits, DEFAULT_STATUS, DEFAULT_GRADE, preRequisites);
            moduleList.add(coreModule);
        case UE:
            ElectiveModule electiveModule = new ElectiveModule(this.moduleCode, MOD_NAME, this.moduleCredits, DEFAULT_STATUS, DEFAULT_GRADE, preRequisites);
            moduleList.add(electiveModule);
        case MATH:
            MathModule mathModule = new MathModule(this.moduleCode, MOD_NAME, this.moduleCredits, DEFAULT_STATUS, DEFAULT_GRADE, preRequisites);
            moduleList.add(mathModule);
        case GE:
            GeModule geModule = new GeModule(this.moduleCode, MOD_NAME, this.moduleCredits, DEFAULT_STATUS, DEFAULT_GRADE, preRequisites);
            moduleList.add(geModule);
        default:
            break;
        }

        ui.printAddedModuleSuccess(this.moduleCode, this.moduleType, this.moduleCredits);

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
