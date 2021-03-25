package seedu.igraduate.model.list;

import seedu.igraduate.exception.ExistingModuleException;
import seedu.igraduate.exception.ModuleNotFoundException;

import seedu.igraduate.exception.PrerequisiteNotFoundException;
import seedu.igraduate.exception.UnableToDeletePrereqModuleException;
import seedu.igraduate.model.module.Module;
import seedu.igraduate.model.module.MathModule;
import seedu.igraduate.model.module.CoreModule;
import seedu.igraduate.model.module.ElectiveModule;
import seedu.igraduate.model.module.GeModule;

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
     * Adds new module to the module storage if not already exists and pre-requisite
     * modules exists.
     *
     * @param module Module to be added into the module list.
     * @throws ExistingModuleException If the new module already exists.
     * @throws PrerequisiteNotFoundException If any of the pre-requisite module does not exists.
     */
    public void add(Module module)
            throws ExistingModuleException, ModuleNotFoundException, PrerequisiteNotFoundException {
        String moduleCode = module.getCode();
        if (getModuleIndex(moduleCode) != DEFAULT_INDEX) {
            assert getModuleIndex(moduleCode) != DEFAULT_INDEX : "No repeating modules allowed to be added";
            throw new ExistingModuleException();
        }
        addModuleRequiredBy(module);
        removeTakenPreRequisiteModule(module);
        modules.add(module);
    }

    /**
     * Removes taken pre-requisite module from current untaken pre-requisite ArrayList.
     *
     * @param module Module object to be added to moduleList.
     * @throws PrerequisiteNotFoundException If any of the pre-requisite module does not exists.
     */
    public void removeTakenPreRequisiteModule(Module module) throws PrerequisiteNotFoundException {
        ArrayList<String> preRequisites = module.getPreRequisites();

        for (String preRequisite : preRequisites) {
            try {
                Module preRequisiteModule = getByCode(preRequisite);
                String status = preRequisiteModule.getStatus();
                if (status.equals("taken")) {
                    module.removeUntakenPreRequisite(preRequisite);
                }
            } catch (ModuleNotFoundException e) {
                throw new PrerequisiteNotFoundException();
            }
        }
    }

    /**
     * Checks if module list contains module.
     *
     * @param module Module object to be added to moduleList.
     * @return boolean value indicating if module list contains module.
     */
    public boolean isContains(Module module) {
        if (modules.contains(module)) {
            return true;
        }
        return false;
    }

    /**
     * Check if the pre-requisite modules exists in the current module list.
     *
     * @param preRequisites ArrayList containing the pre-requisite module codes for module to be added.
     * @return Boolean value indicating whether all the pre-requisite modules exist.
     */
    public boolean isPreRequisiteExist(ArrayList<String> preRequisites) {
        for (String preRequisite : preRequisites) {
            if (getModuleIndex(preRequisite) == DEFAULT_INDEX) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add the module code of the new module to the requiredBy list of its pre-requisite modules.
     *
     * @param module Module object to be added to moduleList.
     * @throws PrerequisiteNotFoundException If any of the pre-requisite module does not exists.
     */
    public void addModuleRequiredBy(Module module) throws PrerequisiteNotFoundException {
        ArrayList<String> preRequisites = module.getPreRequisites();

        if (!isPreRequisiteExist(preRequisites)) {
            throw new PrerequisiteNotFoundException();
        }

        for (String preRequisite : preRequisites) {
            try {
                Module requiredModule = getByCode(preRequisite);
                ArrayList<String> requiredByModules = requiredModule.getRequiredByModules();
                String moduleCode = module.getCode();
                if (!requiredByModules.contains(moduleCode)) {
                    requiredByModules.add(moduleCode);
                    requiredModule.setRequiredByModules(requiredByModules);
                }
            } catch (ModuleNotFoundException e) {
                throw new PrerequisiteNotFoundException();
            }
        }
    }

    /**
     * Removes specified module from the module storage if it is not required by any module.
     *
     * @param module Module to be removed from the module list.
     * @throws PrerequisiteNotFoundException If the pre-requisite module is deleted beforehand.
     * @throws UnableToDeletePrereqModuleException If the module is a pre-requisite of other modules.
     */
    public void delete(Module module) throws PrerequisiteNotFoundException,
            UnableToDeletePrereqModuleException {
        ArrayList<String> requiredByModules = module.getRequiredByModules();

        if (requiredByModules.isEmpty()) {
            modules.remove(module);
            removeFromPreRequisiteModuleRequiredBy(module);
        } else {
            throw new UnableToDeletePrereqModuleException(requiredByModules);
        }
    }

    /**
     * Remove deleted module from its pre-requisite modules' required by list.
     *
     * @param module Module object deleted from module list.
     * @throws PrerequisiteNotFoundException If the pre-requisite module is deleted beforehand.
     */
    public void removeFromPreRequisiteModuleRequiredBy(Module module) throws PrerequisiteNotFoundException {
        ArrayList<String> preRequisites = module.getPreRequisites();
        String moduleCode = module.getCode();

        for (String preRequisite : preRequisites) {
            try {
                Module preRequisiteModule = getByCode(preRequisite);
                preRequisiteModule.removeRequredByModule(moduleCode);
            } catch (ModuleNotFoundException e) {
                throw new PrerequisiteNotFoundException();
            }
        }
    }

    /**
     * Checks if Prerequisite of module is done.
     *
     * @param module Module object marked done.
     * @return boolean value indicating whether module is valid.
     * @throws PrerequisiteNotFoundException If the pre-requisite module is not found.
     */
    public boolean isModuleValid(Module module) throws PrerequisiteNotFoundException {
        ArrayList<String> preRequisites = module.getPreRequisites();

        for (String preRequisite : preRequisites) {
            try {
                Module preRequisiteModule = getByCode(preRequisite);
                if (!preRequisiteModule.isDone()) {
                    return false;
                }
            } catch (ModuleNotFoundException e) {
                throw new PrerequisiteNotFoundException();
            }
        }
        return true;
    }

    /**
     * Marks the specified module as taken.
     *
     * @param module Module to be marked as taken.
     * @throws ModuleNotFoundException If module is not found in module list.
     */
    public void markAsTaken(Module module) throws ModuleNotFoundException {
        module.setStatus("taken");
        String moduleCode = module.getCode();
        ArrayList<String> requiredByModules = module.getRequiredByModules();
        removeFromModuleUntakenPrerequisites(moduleCode, requiredByModules);
    }

    /**
     * Remove taken module from modules requiring it as pre-requisite.
     *
     * @param moduleCode Module code of taken module.
     * @param requiredByModules ArrayList of modules requiring taken module as pre-requisite.
     * @throws ModuleNotFoundException If requiredBy module does not exists in module list.
     */
    public void removeFromModuleUntakenPrerequisites(String moduleCode, ArrayList<String> requiredByModules)
            throws ModuleNotFoundException {
        for (String requiredByModule : requiredByModules) {
            Module module = getByCode(requiredByModule);
            module.removeUntakenPreRequisite(moduleCode);
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
     * Checks if the entire module list is empty.
     *
     * @return Boolean value indicating whether the module list is empty.
     */
    public boolean isEmpty() {
        return modules.isEmpty();
    }

    /**
     * Checks if the list for completed module is empty.
     *
     * @return Boolean value indicating whether there are any completed modules.
     */
    public boolean isCompletedModulesEmpty() {
        for (Module module : modules) {
            if (module.isDone()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the list for incomplete module is empty.
     *
     * @return Boolean value indicating whether there are any incomplete modules.
     */
    public boolean isIncompletedModulesEmpty() {
        for (Module module: modules) {
            if (!module.isDone()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves specified module from module list.
     *
     * @param moduleCode Module code of module.
     * @return The retrieved module based on specified module code.
     * @throws ModuleNotFoundException If the module specified is not in the list.
     */
    public Module getByCode(String moduleCode) throws ModuleNotFoundException {
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
     * Retrieves the module in module list.
     *
     * @param moduleCode Module code of module.
     * @return The retrieved module index on specified module code.
     */
    public Module getModule(String moduleCode) throws ModuleNotFoundException {
        for (Module module : modules) {
            if (module.getCode().equalsIgnoreCase(moduleCode)) {
                return module;
            }
        }
        throw new ModuleNotFoundException();
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
                totalCompletedMCs += module.getCredit();
                assert totalCompletedMCs >= module.getCredit()
                    : "Completed MCs should be more or equal to credits" + "of done modules";
            }
        }
        return totalCompletedMCs;
    }
}
