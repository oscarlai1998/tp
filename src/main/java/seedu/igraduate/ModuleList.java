package seedu.igraduate;

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
     * @param arrayList ArrayList consists of existing modules data.
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
     * @param index Index of module in the module list.
     * @return The retrieved module based on specified index.
     */
    public Module get(int index) {
        return modules.get(index);
    }
}
