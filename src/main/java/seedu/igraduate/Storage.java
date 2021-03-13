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

public class Storage {
    private File filePath;
    private RuntimeTypeAdapterFactory<Module> moduleAdaptorFactory = RuntimeTypeAdapterFactory
            .of(Module.class, "type")
            .registerSubtype(CoreModule.class, "core")
            .registerSubtype(ElectiveModule.class, "elective")
            .registerSubtype(GeModule.class, "ge")
            .registerSubtype(MathModule.class, "math");

    public Storage(File filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Module> loadModulesFromFile()
            throws LoadModuleFailException {
        if (!filePath.exists()) {
            throw new LoadModuleFailException();
        }

        Type objectType = new TypeToken<ArrayList<Module>>() {}.getType();

        try {
            return loadFromJson(objectType, filePath);
        } catch (Exception e) {
            throw new LoadModuleFailException();
        }
    }

    public ArrayList<Module> loadFromJson(Type type, File jsonFile) 
            throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(moduleAdaptorFactory).create();

        FileReader fileReader = new FileReader(jsonFile);
        return gson.fromJson(fileReader, type);
    }

    public void saveModulesToFile(ModuleList modules) throws SaveModuleFailException {
        // Creates parent directories if file does not exist
        if (!filePath.exists()) {
            filePath.getParentFile().mkdirs();
        }
        try {
            saveToJson(filePath, modules.getModules());
        } catch (Exception e) {
            throw new SaveModuleFailException();
        }
    }

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