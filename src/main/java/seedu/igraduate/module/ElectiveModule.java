package seedu.igraduate.module;

import java.util.ArrayList;

public class ElectiveModule extends Module {
    public ElectiveModule(String code, String name, String credit, String status, String grade,
                          ArrayList<String> preRequisites) {
        super(code, name, credit, status, grade, preRequisites);
    }
}
