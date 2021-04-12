package seedu.igraduate.model.module;

import java.util.ArrayList;

/**
 * Parent class for more specific command child class. Contains basic
 * information applicable to all module related classes.
 */
public abstract class Module {
    /**
     * Module code of the module object.
     */
    private String code;
    /**
     * Module name of the module object.
     */
    private String name;
    /**
     * Modular credit of the module object.
     */
    private double credit;
    /**
     * Status of the module, whether it is "taken" or "not taken".
     */
    private String status;
    /**
     * The grade of taken modules.
     */
    private String grade;
    /**
     * A list of prerequisite modules.
     */
    private ArrayList<String> prerequisites;
    /**
     * A list of unsatisfied prerequisite modules.
     */
    private ArrayList<String> untakenPrerequisites;
    /**
     * A list of modules requiring the current module as a prerequisite.
     */
    private ArrayList<String> requiredByModules;

    //@@author kewenlok
    /**
     * Constructs a new Module object.
     * 
     * @param code                 Module code of the module object.
     * @param name                 Module name of the module object.
     * @param credit               Modular credit of the module object.
     * @param status               Status of the module, whether it is “taken” or “not taken”.
     * @param grade                Default module grade when module is created.
     * @param prerequisites        Prerequisites for the module object.
     * @param untakenPrerequisites Unsatisfied prerequisites for module object.
     */
    public Module(String code, String name, double credit, String status, String grade, ArrayList<String> prerequisites,
            ArrayList<String> untakenPrerequisites) {
        setCode(code);
        setName(name);
        setCredit(credit);
        setStatus(status);
        setGrade(grade);
        setPrerequisites(prerequisites);
        setUntakenPrerequisites(untakenPrerequisites);
        setRequiredByModules(new ArrayList<>());
    }

    public void setCode(String code) {
        this.code = code.toUpperCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGrade(String grade) {
        this.grade = grade.toUpperCase();
    }

    public void setPrerequisites(ArrayList<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setUntakenPrerequisites(ArrayList<String> untakenPrerequisites) {
        this.untakenPrerequisites = untakenPrerequisites;
    }

    public void setRequiredByModules(ArrayList<String> requiredByModules) {
        this.requiredByModules = requiredByModules;
    }

    public String getGrade() {
        return grade;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getCredit() {
        return credit;
    }

    public String getStatus() {
        return status;
    }

    //@@author oscarlai1998
    /**
     * Checks if the current module is done/taken.
     *
     * @return True if module is done/taken, false otherwise.
     */
    public boolean isDone() {
        String status = getStatus();
        if (status.equalsIgnoreCase("taken")) {
            return true;
        } else if (status.equalsIgnoreCase("not taken")) {
            return false;
        } else {
            return false;
        }
    }

    /**
     * Check whether S/U option is exercised on the module.
     *
     * @return true if S/U option is exercised, false if S/U option is not exercised.
     */
    public boolean isGradeSu() {
        String grade = getGrade();
        if (grade.equals("S") || grade.equals("U")) {
            return true;
        } else {
            return false;
        }
    }

    //@@author kewenlok
    /**
     * Checks whether all the prerequisites are satisfied.
     *
     * @return True if all prerequisites are satisfied, false otherwise.
     */
    public boolean isPrerequisitesSatisfied() {
        return untakenPrerequisites.isEmpty();
    }

    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }

    public ArrayList<String> getUntakenPrerequisites() {
        return untakenPrerequisites;
    }

    public ArrayList<String> getRequiredByModules() {
        return requiredByModules;
    }

    /**
     * Remove specified prerequisite module from untakenPrerequisites list.
     *
     * @param moduleCode Module code of module to be removed from untakenPrerequisites list.
     */
    public void removeUntakenPrerequisite(String moduleCode) {
        untakenPrerequisites.remove(moduleCode);
    }

    /**
     * Remove the specified module requiring current module as prerequisite.
     *
     * @param moduleCode Module code of module to be removed from requiredByModule list.
     */
    public void removeRequiredByModule(String moduleCode) {
        requiredByModules.remove(moduleCode);
    }

    /**
     * Gets the completion status icon of the module.
     * 
     * @return "O" if taken, "X" if not taken and dash otherwise.
     */
    public String getStatusIcon() {
        String status = getStatus();

        if (status.equals("taken")) {
            return "O";
        } else if (status.equals("not taken")) {
            return "X";
        } else {
            return "-";
        }
    }

    //@@author xseh
    /**
     * Creates a custom hashcode for comparison in streams. 
     */
    @Override
    public int hashCode() {
        return code.hashCode();
    }

    /**
     * Creates a custom comparator for identifying the module.
     */
    @Override
    public boolean equals(Object module) {
        return this.getCode().equals(((Module) module).getCode());
    }

    //@@author kewenlok
    @Override
    public String toString() {
        return String.format("[%s] %-9s %-55s %3s %3s MC", getStatusIcon(), getCode(), getName(), getGrade(),
                Math.round(getCredit()));
    }
}
