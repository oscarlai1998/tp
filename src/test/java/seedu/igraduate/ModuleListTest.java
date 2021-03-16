package seedu.igraduate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;
import seedu.igraduate.exception.ExistingModuleException;
import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.module.GeModule;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModuleListTest {

    private ModuleList modules = new ModuleList();

    @Test
    void add_module_success() throws ModuleNotFoundException, ExistingModuleException {
        ArrayList<String> preRequisites = new ArrayList<>();
        GeModule geModule = new GeModule("GER1000", "Quantitative Reasoning",
                4.0, "taken", "A-", preRequisites);
        modules.add(geModule);
        assertEquals("GER1000", modules.getByCode("GER1000").getCode());
    }

    @Test
    void add_module_throwsExistingModuleException() throws ModuleNotFoundException, ExistingModuleException {
        ArrayList<String> preRequisites = new ArrayList<>();
        GeModule geModule = new GeModule("GER1000", "Quantitative Reasoning",
                4.0, "taken", "A-", preRequisites);
        modules.add(geModule);

        assertThrows(ExistingModuleException.class, "The module code"
                + " already exists.", () -> modules.add(geModule));
    }

    @Test
    void delete_module_success() throws ExistingModuleException {
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
    void get_existingModule_success() throws ModuleNotFoundException, ExistingModuleException {
        ArrayList<String> preRequisites = new ArrayList<>();
        GeModule geModule = new GeModule("GER1000", "Quantitative Reasoning",
                4.0, "taken", "A-", preRequisites);
        modules.add(geModule);
        assertEquals(geModule, modules.getByCode("GER1000"));
    }

    /**
     * Asserts that the {@code executable} throws the {@code expectedType} Exception with the {@code expectedMessage}.
     *
     * @param expectedType expected exception
     * @param expectedMessage expected message from exception
     * @param executable method that throws exception
     */
    public static void assertThrows(Class<? extends Throwable> expectedType, String expectedMessage,
                                    Executable executable) {
        Throwable thrownException = Assertions.assertThrows(expectedType, executable);
        Assertions.assertEquals(expectedMessage, thrownException.getMessage());
    }

}