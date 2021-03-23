package seedu.igraduate.logic.command;

import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.storage.Storage;
import seedu.igraduate.ui.Ui;
import seedu.igraduate.exception.InvalidModuleGradeException;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.exception.SaveModuleFailException;
import seedu.igraduate.model.module.Module;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CapCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(DoneCommand.class.getName());

    private static float cap;
    private static String degreeClassification;

    /**
     * Execute the capCommand function.
     *
     * @param moduleList Module list consisting of all modules.
     * @param ui User interface for printing result.
     * @param storage Storage for storing module list data.
     * @throws SaveModuleFailException If storage fail to save module data to disk.
     * @throws ModuleNotFoundException If the module type is invalid.
     * @throws InvalidModuleGradeException If the module grade is invalid.
     */
    public  void execute(ModuleList moduleList, Ui ui, Storage storage)
            throws SaveModuleFailException, ModuleNotFoundException, InvalidModuleGradeException {

        LOGGER.log(Level.INFO, "Executing CAP command...");

        double totalCredit = 0;
        double moduleCredit = 0;
        float moduleCap = 0.0F;
        float totalModuleCap = 0.0F;
        cap = 0.0F;

        for (int i = 0; i < moduleList.getModules().size(); i++) {
            Module module = moduleList.getModules().get(i);
            boolean completed = module.isDone();
            boolean su = checkSu(module);
            if (completed && !su) {
                moduleCap = convertGradeToCap(module.getGrade());
                moduleCredit = module.getCredit();
                totalCredit += moduleCredit;
                totalModuleCap += moduleCap * moduleCredit;
            }
        }
        cap = calculateCap(totalCredit, totalModuleCap);
        degreeClassification = getDegreeClassification(cap);
        ui.printCap(cap, degreeClassification);
        LOGGER.log(Level.INFO, "Successfully calculated CAP.");
    }



    /**
     * Check whether S/U option is exercised on the module.
     *
     * @param module module to be checked.
     * @return true if S/U option is exercised, false if S/U option is not exercised.
     */
    private boolean checkSu(Module module) {
        String grade = module.getGrade();
        if (grade.equals("S") || grade.equals("U")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Convert grade into numeric cap score.
     *
     * @param grade grade that is being converted to cap.
     * @return numeric cap score.
     * @throws InvalidModuleGradeException if module grade does not fit any categories.
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
     * Calculate cap.
     *
     * @param totalCredit total number of credits taken.
     * @param totalModuleCap sum of of the numeric cap scores.
     * @return final cap.
     */
    private float calculateCap(double totalCredit, float totalModuleCap) {
        float cap;
        if (totalCredit == 0) {
            cap = 0.0F;
        } else {
            cap = (float) (totalModuleCap / totalCredit);
        }

        return cap;
    }

    /**
     * Get the degree classification based on the cap.
     *
     * @param cap current cap.
     * @return current degree classification.
     */
    private String getDegreeClassification(float cap) {
        String capClass;

        if (cap >= 2.00 && cap <= 2.99) {
            capClass = "Pass";
        } else if (cap >= 3.00 && cap <= 3.49) {
            capClass = "Honours";
        } else if (cap >= 3.50 && cap <= 3.99) {
            capClass = "Honours (Merit)";
        } else if (cap >= 4.00 && cap <= 4.49) {
            capClass = "Honours (Distinction)";
        } else if (cap >= 4.50) {
            capClass = "Honours (Highest Distinction)";
        } else {
            capClass = "Fail";
        }

        return capClass;
    }

    /**
     * Retrieves and return cap.
     *
     * @return cap.
     */
    public static float getCap() {
        return cap;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
