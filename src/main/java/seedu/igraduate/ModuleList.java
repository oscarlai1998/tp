package seedu.igraduate;

import seedu.igraduate.exception.ExistingModuleException;
import seedu.igraduate.exception.ModuleNotFoundException;

import seedu.igraduate.exception.PrerequisiteNotFoundException;
import seedu.igraduate.module.Module;
import seedu.igraduate.module.MathModule;
import seedu.igraduate.module.CoreModule;
import seedu.igraduate.module.ElectiveModule;
import seedu.igraduate.module.GeModule;

import java.util.ArrayList;

/**
 * Handles underlying operations on modules ArrayList.
 */
public class ModuleList {

    /**
     * ArrayList that stores all the modules data.
     */
    private ArrayList<Module> modules;

    private static final int DEFAULT_INDEX = -1;

    /**
     * Constructs new ArrayList if no data is provided.
     */
    public ModuleList() {
        this(new ArrayList<>());
    }

    /**
     * Assigns the existing ArrayList with modules data as the module storage.
     *
     * @param modules ArrayList consists of existing modules data.
     */
    public ModuleList(ArrayList<Module> modules) {
        this.modules = modules;
    }

    /**
     * Adds new module to the module storage if not already exists.
     *
     * @param module Module to be added into the module list.
     * @throws ExistingModuleException If the new module already exists.
     */
    public void add(Module module) throws ExistingModuleException, ModuleNotFoundException,
            PrerequisiteNotFoundException {
        String moduleCode = module.getCode();
        if (getModuleIndex(moduleCode) != DEFAULT_INDEX) {
            assert getModuleIndex(moduleCode) != DEFAULT_INDEX : "No repeating modules allowed to be added";
            throw new ExistingModuleException();
        }
        addModuleRequiredBy(module);
        modules.add(module);
    }

    public boolean checkPreRequisitesAvailability(ArrayList<String> preRequisites) {
        for (String preRequisite : preRequisites) {
            if (getModuleIndex(preRequisite) == DEFAULT_INDEX) {
                return false;
            }
        }
        return true;
    }

    public void addModuleRequiredBy(Module module) throws ModuleNotFoundException,
            PrerequisiteNotFoundException {
        ArrayList<String> preRequisites = module.getPreRequisites();

        if (checkPreRequisitesAvailability(preRequisites)) {
            for (String preRequisite : preRequisites) {
                Module requiredModule = getByCode(preRequisite);
                ArrayList<String> requiredBy = requiredModule.getRequiredByModules();
                requiredBy.add(module.getCode());
                requiredModule.setRequiredByModules(requiredBy);
            }
        } else {
            throw new PrerequisiteNotFoundException();
        }
    }

    /**
     * Removes specified module from the module storage.
     *
     * @param module Module to be removed from the module list.
     */
    public void delete(Module module) {
        modules.remove(module);
    }

    /**
     * Marks the specified module as taken.
     *
     * @param module Module to be marked as taken.
     */
    public void markAsTaken(Module module) throws ModuleNotFoundException {
        module.setStatus("taken");
        String moduleCode = module.getCode();
        ArrayList<String> requiredBy = module.getRequiredByModules();
        removeRequiredByModulePrerequisites(moduleCode, requiredBy);
    }

    public void removeRequiredByModulePrerequisites(String moduleCode, ArrayList<String> requiredByModules)
            throws ModuleNotFoundException {
        for (String requiredByModule : requiredByModules) {
            Module module = getByCode(requiredByModule);
            module.removePreRequisites(moduleCode);
        }
    }

    /**
     * Sets the specified module grade.
     *
     * @param module Module to be marked as taken.
     * @param grade  Grade obtained for the specified module.
     */
    public void setGrade(Module module, String grade) {
        module.setGrade(grade);
    }

    /**
     * Retrieves and returns the underlying ArrayList for storing modules.
     *
     * @return The task list used for storing all modules.
     */
    public ArrayList<Module> getModules() {
        return modules;
    }

    /**
     * Obtains and returns the current module list size.
     *
     * @return The number of modules in the ArrayList.
     */
    public int size() {
        return modules.size();
    }

    /**
     * Checks if the current module list is empty.
     *
     * @return Boolean value indicating whether the module list is empty.
     */
    public boolean isEmpty() {
        return modules.isEmpty();
    }

    /**
     * Retrieves specified module from module list.
     *
     * @param moduleCode Module code of module.
     * @return The retrieved module based on specified module code.
     * @throws ModuleNotFoundException If the module specified is not in the list.
     */
    public Module getByCode(String moduleCode) 
            throws ModuleNotFoundException {
        int moduleIndex = getModuleIndex(moduleCode);
        if (moduleIndex == DEFAULT_INDEX) {
            throw new ModuleNotFoundException();
        }
        assert moduleIndex != DEFAULT_INDEX : "Module code does not exists.";

        return modules.get(moduleIndex);
    }

    /**
     * Retrieves the index of module in module list.
     *
     * @param moduleCode Module code of module.
     * @return The retrieved module index on specified module code.
     */
    public int getModuleIndex(String moduleCode) {
        int index = DEFAULT_INDEX;

        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getCode().equalsIgnoreCase(moduleCode)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Retrieves the module type of specified module.
     *
     * @param module Module object for finding type.
     * @return The type of module specified.
     */
    public String getModuleType(Module module) {
        String moduleType = "Undefined";
        if (module instanceof CoreModule) {
            moduleType = "Core";
        } else if (module instanceof MathModule) {
            moduleType = "Math";
        } else if (module instanceof GeModule) {
            moduleType = "GE";
        } else if (module instanceof ElectiveModule) {
            moduleType = "Elective";
        }
        assert !moduleType.equals("Undefined") : "Module type is not valid.";

        return moduleType;
    }

    /**
     * Calculates the total completed MC of all modules.
     *
     * @return The total completed MCs.
     */
    public double getTotalCompletedMCs() {
        double totalCompletedMCs = 0;
        for (Module module : modules) {
            if (module.getStatus().equalsIgnoreCase("taken")) {
                assert totalCompletedMCs >= module.getCredit() : "Completed MCs should be more or equal to credits"
                       + "of done modules";
                totalCompletedMCs += module.getCredit();
            }
        }
        return totalCompletedMCs;
    }
}
