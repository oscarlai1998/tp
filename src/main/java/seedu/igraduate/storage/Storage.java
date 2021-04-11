package seedu.igraduate.storage;

import seedu.igraduate.exception.DataFileNotFoundException;
import seedu.igraduate.exception.LoadModuleFailException;
import seedu.igraduate.exception.CorruptedStorageFileException;
import seedu.igraduate.exception.SaveModuleFailException;

import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.model.module.CoreModule;
import seedu.igraduate.model.module.ElectiveModule;
import seedu.igraduate.model.module.GeModule;
import seedu.igraduate.model.module.MathModule;
import seedu.igraduate.model.module.Module;

import seedu.igraduate.logic.parser.Parser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.google.gson.JsonParseException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents an instance of storage. A storage object corresponds to the saving
 * and loading of file.
 */
public class Storage {
    // Storage information
    private static Storage storage = null;
    private File filePath;

    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());

    //@@author kewenlok
    // Define the runtimeAdapterFactory for Gson to treat each module type as different object
    private RuntimeTypeAdapterFactory<Module> moduleAdaptorFactory = RuntimeTypeAdapterFactory.of(Module.class, "type")
            .registerSubtype(CoreModule.class, "core").registerSubtype(ElectiveModule.class, "elective")
            .registerSubtype(GeModule.class, "ge").registerSubtype(MathModule.class, "math");

    //@@author xseh
    /**
     * Creates a Singleton of Storage, which should only have one instance. If
     * storage has not been instantiated, create.
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
    private Storage(File filePath) {
        this.filePath = filePath;
    }

    /**
     * Prepares to load modules from file.
     *
     * @return The parsed array list containing all saved modules.
     * @throws LoadModuleFailException      If the module fails to load from file.
     * @throws DataFileNotFoundException    If the module data file does not exists.
     * @throws CorruptedStorageFileException If the json file has been modified in unintended manner.
     */
    public ArrayList<Module> loadModulesFromFile() throws LoadModuleFailException, DataFileNotFoundException,
        CorruptedStorageFileException {
        if (!filePath.exists()) {
            throw new DataFileNotFoundException();
        }

        Type objectType = new TypeToken<ArrayList<Module>>() {}.getType();

        try {
            ArrayList<Module> rawModules = loadFromJson(objectType, filePath);
            LOGGER.log(Level.INFO, "Module data loaded from disk successfully.");
            ArrayList<Module> distinctModules = removeDuplicateModules(rawModules);
            if (!isModuleDataValid(distinctModules)) {
                throw new CorruptedStorageFileException();
            }
            return distinctModules;
        } catch (JsonParseException e) {
            throw new CorruptedStorageFileException();
        } catch (IOException e) {
            LOGGER.warning("Failed to load module.");
            throw new LoadModuleFailException();
        }
    }

    /**
     * Checks if the module information imported is valid.
     *
     * @param modules Distinct module list imported from data file.
     * @return True if all modules are valid, false otherwise.
     */
    private boolean isModuleDataValid(ArrayList<Module> modules) {
        for (Module module : modules) {
            String moduleCode = module.getCode();
            String moduleGrade = module.getGrade();
            double credit = module.getCredit();
            String status = module.getStatus();

            boolean isInvalidModuleCode = !Parser.isModuleCodeValid(moduleCode);
            boolean isInvalidModuleGrade = !Parser.isModuleGradeValid(moduleGrade);
            boolean isInvalidModularCredit = !Parser.isModularCreditValid(credit);
            boolean isInvalidStatus = !(status.equalsIgnoreCase("taken")
                    || status.equalsIgnoreCase("not taken"));
            boolean isInvalidModuleData = isInvalidModuleCode || isInvalidModuleGrade || isInvalidModularCredit
                    || isInvalidStatus;

            if (isInvalidModuleData) {
                return false;
            }

            initialiseEmptyArrayLists(module);
        }
        return true;
    }

    //@@author kewenlok
    /**
     * Initialises empty prerequisites, untakenPrerequisites and requiredByModule list.
     *
     * @param module Module object for checking and initialising empty array list.
     */
    private void initialiseEmptyArrayLists(Module module) {
        ArrayList<String> prerequisites = module.getPrerequisites();
        ArrayList<String> untakenPrerequisites = module.getUntakenPrerequisites();
        ArrayList<String> requiredByModules = module.getRequiredByModules();

        if (prerequisites == null) {
            module.setPrerequisites(new ArrayList<>());
        }

        if (untakenPrerequisites == null) {
            module.setUntakenPrerequisites(new ArrayList<>());
        }

        if (requiredByModules == null) {
            module.setRequiredByModules(new ArrayList<>());
        }
    }

    //@@author xseh
    /**
     * Removes all duplicate modules (identified by module code) from list.
     *
     * @param modules List of modules loaded into the application.
     * @return List of modules containing all distinct modules (if all distinct,
     *         return original module list)
     */
    private ArrayList<Module> removeDuplicateModules(ArrayList<Module> modules) {
        return modules.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Loads the stored modules from json file.
     *
     * @param type     Module type.
     * @param jsonFile File opened for reading.
     * @return Parsed array list containing saved modules.
     * @throws IOException If the file failed to be read.
     */
    private ArrayList<Module> loadFromJson(Type type, File jsonFile) throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(moduleAdaptorFactory).create();

        FileReader fileReader = new FileReader(jsonFile);

        return gson.fromJson(fileReader, type);
    }

    /**
     * Prepares to save the array list into a json format.
     *
     * @param modules Array list of all modules.
     * @throws SaveModuleFailException If the module fails to save to file.
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
     * @param jsonFile File opened for writing.
     * @param modules  Array list of all the modules.
     * @throws IOException If the file failed to be written.
     */
    private void saveToJson(File jsonFile, ArrayList<Module> modules) throws IOException {
        // Register module adaptor factory to gson builder for module type labelling
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(moduleAdaptorFactory).create();

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