package seedu.igraduate.logic.command;

import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.model.module.Module;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;

import seedu.igraduate.exception.InvalidModuleGradeException;
import seedu.igraduate.exception.SaveModuleFailException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CapCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(CapCommand.class.getName());

    /**
     * Iterates through the modules in module list and calculates the current cap.
     * Prints cap message after cap is calculated.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     * @throws SaveModuleFailException If storage fail to save module data to disk.
     * @throws InvalidModuleGradeException If the module grade is invalid.
     */
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws SaveModuleFailException,
            InvalidModuleGradeException {

        LOGGER.log(Level.INFO, "Executing CAP command...");
        float cap = calculateCap(moduleList);
        String degreeClassification = getDegreeClassification(cap);
        ui.printCapMessage(cap, degreeClassification);
        LOGGER.log(Level.INFO, "Successfully calculated CAP.");
    }

    /**
     * Converts grade into numeric cap score.
     *
     * @param grade Grade that is being converted to cap.
     * @return Numeric cap score.
     * @throws InvalidModuleGradeException If module grade is invalid.
     */
    private float convertGradeToCap(String grade) throws InvalidModuleGradeException {
        LOGGER.log(Level.INFO, "Converting grade to cap score...");
        float cap;
        switch (grade) {
        case "A+":     // Fallthrough
        case "A":
            cap = 5.0F;
            break;
        case "A-":
            cap = 4.5F;
            break;
        case "B+":
            cap = 4.0F;
            break;
        case "B":
            cap = 3.5F;
            break;
        case "B-":
            cap = 3.0F;
            break;
        case "C+":
            cap = 2.5F;
            break;
        case "C":
            cap = 2.0F;
            break;
        case "D+":
            cap = 1.5F;
            break;
        case "D":
            cap = 1.0F;
            break;
        case "F":
            cap = 0.0F;
            break;
        default:
            LOGGER.log(Level.WARNING, "Failure to convert invalid module grade.");
            throw new InvalidModuleGradeException();
        }

        LOGGER.log(Level.INFO, "End of conversion.");
        return cap;
    }

    /**
     * Calculates cap.
     *
     * @param moduleList Module list containing all modules.
     * @return Resulting cap.
     * @throws InvalidModuleGradeException If module grade is invalid.
     */
    private float calculateCap(ModuleList moduleList) throws InvalidModuleGradeException {
        double totalCredit = 0;
        float totalGradePoint = 0.0F;
        float cap = 0.0F;

        ArrayList<Module> modules = moduleList.getModules();

        for (Module module : modules) {
            boolean isCompleted = module.isDone();
            boolean isSu = module.isGradeSu();
            if (isCompleted && !isSu) {
                float moduleCap = convertGradeToCap(module.getGrade());
                double modularCredit = module.getCredit();
                totalCredit += modularCredit;
                totalGradePoint += moduleCap * modularCredit;
            }
        }

        if (totalCredit != 0) {
            cap = (float) (totalGradePoint / totalCredit);
        }
        return cap;
    }

    /**
     * Gets the degree classification based on the cap.
     *
     * @param cap Current cap.
     * @return Current degree classification.
     */
    private String getDegreeClassification(float cap) {
        String degreeClassification;

        if (cap >= 2.00 && cap <= 2.99) {
            degreeClassification = "Pass";
        } else if (cap >= 3.00 && cap <= 3.49) {
            degreeClassification = "Honours";
        } else if (cap >= 3.50 && cap <= 3.99) {
            degreeClassification = "Honours (Merit)";
        } else if (cap >= 4.00 && cap <= 4.49) {
            degreeClassification = "Honours (Distinction)";
        } else if (cap >= 4.50) {
            degreeClassification = "Honours (Highest Distinction)";
        } else {
            degreeClassification = "Fail";
        }

        return degreeClassification;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
