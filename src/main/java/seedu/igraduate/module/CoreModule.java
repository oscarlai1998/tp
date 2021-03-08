package seedu.igraduate.module;

import java.util.ArrayList;

public class CoreModule extends Module {
    public CoreModule(String code, String name, String credit, String status, String grade,
                      ArrayList<String> preRequisites) {
        super(code, name, credit, status, grade, preRequisites);
    }
}
