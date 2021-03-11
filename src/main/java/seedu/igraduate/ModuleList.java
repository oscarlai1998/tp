package seedu.igraduate;

import seedu.igraduate.exception.ModuleNotFoundException;
import seedu.igraduate.module.Module;

import java.util.ArrayList;

/**
 * Handles underlying operations on modules ArrayList.
 */
public class ModuleList {
    /**
     * ArrayList that stores all the modules data.
     */
    private ArrayList<Module> modules;

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
     * Adds new module to the module storage.
     *
     * @param module Module to be added into the module list.
     */
    public void add(Module module) {
        modules.add(module);
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
     * Retrieves specified module from module list.
     *
     * @param moduleCode Module code of module.
     * @return The retrieved module based on specified module code.
     * @throws ModuleNotFoundException If the module specified is not in the list.
     */
    public Module get(String moduleCode) throws ModuleNotFoundException {
        int moduleIndex = getModuleIndex(moduleCode);

        if (moduleIndex == -1) {
            throw new ModuleNotFoundException();
        }

        return modules.get(moduleIndex);
    }

    /**
     * Retrieves the index of module in module list.
     *
     * @param moduleCode Module code of module.
     * @return The retrieved module index on specified module code.
     */
    public int getModuleIndex(String moduleCode) {
        int index = -1;

        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getCode().equalsIgnoreCase(moduleCode)) {
                index = i;
                break;
            }
        }

        return index;
    }
}
