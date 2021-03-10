package seedu.igraduate.module;

import java.util.ArrayList;

public class Module {
    private String code;
    private String name;
    private double credit;
    private String status;
    private String grade;
    private ArrayList<String> preRequisites;

    public Module(String code, String name, double credit, String status, String grade,
                  ArrayList<String> preRequisites) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.status = status;
        this.grade = grade;
        this.preRequisites = preRequisites;
    }
}
