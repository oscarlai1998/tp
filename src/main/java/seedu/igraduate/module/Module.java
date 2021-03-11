package seedu.igraduate.module;

import java.util.ArrayList;

public class Module {
    private String code;
    private String name;
    private String credit;
    private String status;
    private String grade;
    private ArrayList<String> preRequisites;

    public Module(String code, String name, String credit, String status, String grade,
                  ArrayList<String> preRequisites) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.status = status;
        this.grade = grade;
        this.preRequisites = preRequisites;
    }

    public String toString() {
        return new StringBuilder().append("Module{")
                .append("code: ").append(code)
                .append(", name: ").append(name)
                .append(", credit: ").append(credit)
                .append(", status: ").append(status)
                .append(", grade: ").append(grade)
                .append(", preRequisites: ").append(preRequisites)
                .append("}").toString();
    }
}
