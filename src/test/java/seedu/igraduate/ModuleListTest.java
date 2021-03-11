package seedu.igraduate;

import org.junit.jupiter.api.Test;

import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.module.GeModule;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModuleListTest {

    private ModuleList modules = new ModuleList();

    @Test
    void add_module_success() throws ModuleNotFoundException {
        ArrayList<String> preRequisites = new ArrayList<>();
        GeModule geModule = new GeModule("GER1000", "Quantitative Reasoning",
                4.0, "taken", "A-", preRequisites);
        modules.add(geModule);
        assertEquals("GER1000", modules.get("GER1000").getCode());
    }

    @Test
    void delete_module_success() {
        ArrayList<String> preRequisites = new ArrayList<>();
        GeModule geModule = new GeModule("GER1000", "Quantitative Reasoning",
                4.0, "taken", "A-", preRequisites);
        modules.add(geModule);
        modules.delete(geModule);
        assertEquals(0, modules.size());
    }

    @Test
    void getModules_emptyModuleList_success() {
        assertEquals(0, modules.size());
    }

    @Test
    void size_moduleList_success() {
        assertEquals(0, modules.size());
    }

    @Test
    void get_existingModule_success() throws ModuleNotFoundException {
        ArrayList<String> preRequisites = new ArrayList<>();
        GeModule geModule = new GeModule("GER1000", "Quantitative Reasoning",
                4.0, "taken", "A-", preRequisites);
        modules.add(geModule);
        assertEquals(geModule, modules.get("GER1000"));
    }
}