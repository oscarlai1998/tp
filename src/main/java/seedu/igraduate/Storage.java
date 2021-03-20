package seedu.igraduate;

import seedu.igraduate.exception.LoadModuleFailException;
import seedu.igraduate.exception.SaveModuleFailException;

import seedu.igraduate.module.CoreModule;
import seedu.igraduate.module.ElectiveModule;
import seedu.igraduate.module.GeModule;
import seedu.igraduate.module.MathModule;
import seedu.igraduate.module.Module;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents an instance of storage. 
 * A storage object corresponds to the saving and loading of file.  
 */
public class Storage {
    private static Storage storage = null;
    private File filePath;
    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());

    // Define the runtimeAdapterFactory for Gson to treat each module type as different object
    private RuntimeTypeAdapterFactory<Module> moduleAdaptorFactory = RuntimeTypeAdapterFactory
            .of(Module.class, "type")
            .registerSubtype(CoreModule.class, "core")
            .registerSubtype(ElectiveModule.class, "elective")
            .registerSubtype(GeModule.class, "ge")
            .registerSubtype(MathModule.class, "math");

    /**
     * Creates a Singleton of Storage, which should only have one instance.
     * If storage has not been instantiated, create. 
     * 
     * @param filePath File opened for read. 
     * @return Storage object. 
     */
    public static Storage getStorage(File filePath) {
        if (storage == null) {
            storage = new Storage(filePath);
        }
        return storage;
    }

    /**
     * Instantiates the storage object. 
     */
    private Storage (File filePath) {
        this.filePath = filePath;
    }

    /**
     * Prepares to load modules from file. 
     * 
     * @return the parsed array list containing all saved modules. 
     * @throws IOException if file cannot be read or processed. 
     * @throws LoadModuleFailException if the module fails to save to file. 
     */
    public ArrayList<Module> loadModulesFromFile()
            throws LoadModuleFailException {
        if (!filePath.exists()) {
            throw new LoadModuleFailException();
        }

        Type objectType = new TypeToken<ArrayList<Module>>() {}.getType();

        try {
            ArrayList<Module> modules = loadFromJson(objectType, filePath);
            LOGGER.log(Level.INFO, "Module data loaded from disk successfully.");
            return modules;
        } catch (IOException exception) {
            LOGGER.warning("Failed to load module.");
            throw new LoadModuleFailException();
        }
    }

    /**
     * Loads the stored modules from json file.  
     * 
     * @param type module type. 
     * @param jsonFile file opened for reading. 
     * @return parsed array list containing saved modules. 
     * @throws IOException if the file failed to be read. 
     */
    public ArrayList<Module> loadFromJson(Type type, File jsonFile) 
            throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(moduleAdaptorFactory).create();

        FileReader fileReader = new FileReader(jsonFile);

        return gson.fromJson(fileReader, type);
    }

    /**
     * Prepares to save the array list into a json format. 
     * 
     * @param modules array list of all modules. 
     * @throws SaveModuleFailException if the module fails to save to file. 
     */
    public void saveModulesToFile(ModuleList modules) throws SaveModuleFailException {
        // Creates parent directories if file does not exist
        if (!filePath.exists()) {
            filePath.getParentFile().mkdirs();
        }
        try {
            saveToJson(filePath, modules.getModules());
            LOGGER.log(Level.INFO, "Module data saved to disk successfully.");
        } catch (Exception exception) {
            LOGGER.warning("Failed to save module.");
            throw new SaveModuleFailException();
        }
    }

    /**
     * Saves the array list to json file. 
     * 
     * @param jsonFile file opened for writing. 
     * @param modules array list of all the modules. 
     * @throws IOException if the file failed to be written. 
     */
    public void saveToJson(File jsonFile, ArrayList<Module> modules) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapterFactory(moduleAdaptorFactory).create();

        int arraySize = modules.size();
        int lastIndex = arraySize - 1;

        FileWriter fileWriter = new FileWriter(jsonFile);
        fileWriter.write("[\n");
        for (int i = 0; i < arraySize; i++) {
            String json = gson.toJson(modules.get(i), Module.class);
            fileWriter.write(json);
            if (i != lastIndex) {
                fileWriter.write(", \n");
            }
        }
        fileWriter.write("\n]");
        fileWriter.flush();
        fileWriter.close();
    }
}