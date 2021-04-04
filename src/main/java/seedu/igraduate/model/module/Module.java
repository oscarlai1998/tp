package seedu.igraduate.model.module;

import java.util.ArrayList;

/**
 * Parent class for more specific command child class.
 * Contains basic information applicable to all module related classes.
 */
public abstract class Module {
    private String code;
    private String name;
    private double credit;
    private String status;
    private String grade;
    private ArrayList<String> preRequisites;
    private ArrayList<String> untakenPreRequisites;
    private ArrayList<String> requiredByModules;

    /**
     * Creates an instance of a module based on the corresponding module type. 
     * 
     * @param code module code. 
     * @param name module name as specified in the user input. 
     * @param credit number of modular credits. 
     * @param status status of completion (tick for completed, cross for uncompleted). 
     * @param grade grade attained for the module, only applicable is status is done. 
     * @param preRequisites prerequisites required for the module.
     * @param untakenPreRequisites pre-requisite modules not taken yet.
     */
    public Module(String code, String name, double credit, String status, String grade,
                  ArrayList<String> preRequisites, ArrayList<String> untakenPreRequisites) {
        setCode(code);
        setName(name);
        setCredit(credit);
        setStatus(status);
        setGrade(grade);
        setPreRequisites(preRequisites);
        setUntakenPreRequisites(untakenPreRequisites);
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

    public void setPreRequisites(ArrayList<String> preRequisites) {
        this.preRequisites = preRequisites;
    }

    public void setUntakenPreRequisites(ArrayList<String> untakenPreRequisites) {
        this.untakenPreRequisites = untakenPreRequisites;
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

    public boolean isDone() {
        String status = getStatus();
        if (status.equals("taken")) {
            return true;
        } else if (status.equals("not taken")) {
            return false;
        } else {
            return false;
        }
    }

    /**
     * Check if grade is valid.
     * [A+, A, A-, B+, B, B-, C+, C, D+, D, F, S, U, CS, CU].
     *
     * @return boolean value true if grade is value and false if invalid.
     */
    public static boolean isGradeValid(String moduleGrade) {
        ArrayList<String> validGrades = new ArrayList<>() {
            {
                add("A+");
                add("A");
                add("A-");
                add("B+");
                add("B");
                add("B-");
                add("C+");
                add("C");
                add("D+");
                add("F");
                add("S");
                add("U");
                add("CS");
                add("CU");

            }
        };
        if (validGrades.contains(moduleGrade.toUpperCase())) {
            return true;
        }
        return false;
    }

    public ArrayList<String> getPreRequisites() {
        return preRequisites;
    }

    public ArrayList<String> getUntakenPreRequisites() {
        return untakenPreRequisites;
    }

    public ArrayList<String> getRequiredByModules() {
        return requiredByModules;
    }

    public void removeUntakenPreRequisite(String moduleCode) {
        untakenPreRequisites.remove(moduleCode);
    }

    public void removeRequredByModule(String moduleCode) {
        requiredByModules.remove(moduleCode);
    }

    /**
     * Gets the completion status of the module.
     *  
     * @return tick if taken, cross if not taken and dash if not applicable.
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

    @Override
    public String toString() {
        return String.format("[%s] %-8s %-55s %3s %3s MC", getStatusIcon(), getCode(), getName(), getGrade(),
                Math.round(getCredit()));
    }
}
