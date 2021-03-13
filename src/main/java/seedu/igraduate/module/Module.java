package seedu.igraduate.module;

import java.util.ArrayList;

public abstract class Module {
    private String code;
    private String name;
    private double credit;
    private String status;
    private String grade;
    private ArrayList<String> preRequisites;

    private static double totalCompletedMCs = 0;

    public Module(String code, String name, double credit, String status, String grade,
                  ArrayList<String> preRequisites) {
        setCode(code);
        setName(name);
        setCredit(credit);
        setStatus(status);
        setGrade(grade);
        setPreRequisites(preRequisites);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setStatus(String status) {
        if (status.equalsIgnoreCase("taken")) {
            totalCompletedMCs += getCredit();
        }
        this.status = status;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setPreRequisites(ArrayList<String> preRequisites) {
        this.preRequisites = preRequisites;
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

    public String getStatusIcon() {
        String status = getStatus();
        if (status.equals("taken")) {
            return "✓";
        } else if (status.equals("not taken")) {
            return "✘";
        } else {
            return "-";
        }
    }

    public static double getTotalCompletedMCs() {
        return totalCompletedMCs;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-8s %-50s %8s", getStatusIcon(), getCode(), getName(), getCredit());
    }
}
