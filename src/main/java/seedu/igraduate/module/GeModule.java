package seedu.igraduate.module;

import java.util.ArrayList;

public class GeModule extends Module {
    public GeModule(String code, String name, double credit, String status, String grade,
                    ArrayList<String> preRequisites) {
        super(code, name, credit, status, grade, preRequisites);
    }

    @Override
    public String toString() {
        return "[G]" + super.toString();
    }
}
