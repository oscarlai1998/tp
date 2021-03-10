package seedu.igraduate;

import seedu.igraduate.module.Module;

import java.util.ArrayList;

import java.io.File;

public class Storage {
    /**
     * File object used for loading and storing IGraduate program module data.
     */
    private File dataFile;

    /**
     * Constructs the initial value of dataFile.
     *
     * @param dataFile File object for loading and storing of data.
     */
    public Storage(File dataFile) {
        this.dataFile = dataFile;
    }

    /**
     * Loads the module data from data file on disk.
     * Returns empty ArrayList if file does not exist or fail to load data.
     *
     * @return An ArrayList with tasks data loaded from dataFile.
     */
    public ArrayList<Module> loadModulesFromFile() {

        ArrayList<Module> modules = new ArrayList<>();

        // Todo: Load data from files

        return modules;
    }
}
