package seedu.igraduate;

import seedu.igraduate.module.Module;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    // Welcome message
    private static final String LOGO = " _  ____               _             _\n"
            + "(_)/ ___|_ __ __ _  __| |_   _  __ _| |_ ___ \n"
            + "| | |  _| '__/ _` |/ _` | | | |/ _` | __/ _ \\\n"
            + "| | |_| | | | (_| | (_| | |_| | (_| | ||  __/\n"
            + "|_|\\____|_|  \\__,_|\\__,_|\\__,_|\\__,_|\\__\\___|";
    private static final String GREETING_MESSAGE = "iGraduate starting up...\nWelcome to iGraduate, "
            + "your one stop study planning service!\nWhat would you like to do today?";
    private static final String GOODBYE_MESSAGE = "See you soon! Happy studying!";
    private static final String BORDER_LINE = "------------------------------------------------------------"
            + "--------------------------";

    // Messages for successful execution
    public static final String MODULE_ADDED_MESSAGE = "Added %s %s to the list. (%sMCs)";
    public static final String MODULE_DELETED_MESSAGE = "\"%s\" module %s has been deleted.";
    public static final String MODULES_TAKEN_MESSAGE = "Modules you have taken:\n";
    public static final String MODULES_LEFT_MESSAGE = "Modules you have yet to take:\n";
    public static final String EMPTY_LIST_MESSAGE = "List is empty. Add a module.";
    public static final String PROGRESS_MESSAGE = "%dMCs/160MCs Completed";
    public static final String PREREQUISITES_MESSAGE = "List of pre-requisites needed to take %s: ";
    public static final String PREREQUISITES_UNFULFILLED_MESSAGE = "You have not completed the prerequisites for"
            + "%s!\n";
    public static final String CAP_MESSAGE = "Current CAP: %.2f";

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Gets command from user input. 
     * 
     * @return user input. 
     */
    public String getCommand() {
        return SCANNER.nextLine();
    }

    /**
     * Prints the welcome message. 
     */
    public void printWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(GREETING_MESSAGE);
        printBorderLine();
    }

    /**
     * Prints the exit message. 
     */
    public void printExitMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Prints the message border. 
     */
    public void printBorderLine() {
        System.out.println(BORDER_LINE);
    }

    /**
     * Prints all the modules in array list. 
     * 
     * @param modules array list containing the modules. 
     */
    public void printEntireList(ArrayList<Module> modules) {
        System.out.println("Module List: ");
        for (int i = 0; i < modules.size(); i++) {
            System.out.print(String.format("%-4d: ", i + 1));
            printModuleDetails(modules.get(i));
        }
    }

    /**
     * Prints the modules in the array list that user has taken.
     *
     * @param modules array list containing the modules.
     */
    public void printCompletedList(ArrayList<Module> modules) {
        System.out.println(MODULES_TAKEN_MESSAGE);
        for (Module module : modules) {
            if (module.isDone()) {
                printModuleDetails(module);
            }
        }
    }

    /**
     * Prints the modules in the array list that user has not taken.
     *
     * @param modules array list containing the modules.
     */
    public void printIncompletedList(ArrayList<Module> modules) {
        System.out.println(MODULES_LEFT_MESSAGE);
        for (Module module : modules) {
            if (!module.isDone()) {
                printModuleDetails(module);
            }
        }
    }

    /**
     * Prints prerequisites required when user tries to complete a module they with prerequisites remaining.
     *
     * @param moduleCode module code from user input.
     * @param prerequisites list of prerequisites remaining for module corresponding to moduleCode.
     */
    public void printPrerequisitesUnfulfilled(String moduleCode, ArrayList<String> prerequisites) {
        System.out.println(String.format(PREREQUISITES_UNFULFILLED_MESSAGE, moduleCode));
        printPrerequisites(moduleCode, prerequisites);
    }

    /**
     * Prints the module information. 
     * 
     * @param module array list containing the modules. 
     */
    public void printModuleDetails(Module module) {
        String moduleDetails = module.toString();
        System.out.println(moduleDetails);
    }

    /**
     * Displays success message after adding new module. 
     * 
     * @param module array list containing the modules. 
     */
    public void printAddedModuleSuccess(Module module) {
        System.out.println(String.format(MODULE_ADDED_MESSAGE, module.getCode(), module.getName(), module.getCredit()));
        if (module.getPreRequisites().size() > 0) {
            printPrerequisites(module.getCode(), module.getPreRequisites());
        }
        System.out.println(module);
    }

    /**
     * Prints all prerequisites of a module.
     *
     * @param prerequisites array list containing all prerequisites.
     */
    public void printPrerequisites(String moduleCode, ArrayList<String> prerequisites) {
        System.out.print(String.format(PREREQUISITES_MESSAGE, moduleCode));
        for (int i = 0; i < prerequisites.size() - 1; i++) {
            System.out.print(prerequisites.get(i) + ", ");
        }
        System.out.println(prerequisites.get(prerequisites.size() - 1));
    }

    /**
     * Displays success message after deleting the module. 
     * 
     * @param name module name. 
     * @param type module type.
     */
    public void printDeletedModuleSuccess(String name, String type) {
        System.out.println(String.format(MODULE_DELETED_MESSAGE, type, name));
    }

    /**
     * Displays success message after marking a module as completed. 
     * 
     * @param module module marked as completed. 
     */
    public void printMarkAsTakenMessage(Module module) {
        System.out.println("Nice! I've marked this module as done:");
        System.out.println("  " + module);
    }

    public void printUpdateSuccess(Module module) {
        System.out.println("Nice! I've updated this module:");
        System.out.println("  " + module);
    }

    /**
     * Displays message if module list is empty. 
     */
    public void printListEmptyMessage() {
        System.out.println(EMPTY_LIST_MESSAGE);
    }

    /**
     * Displays the progress bar for university graduation completion. 
     * 
     * @param completedMCs total number of credits completed. 
     * @param percentage percentage of academic career completed in string. 
     */
    public void printProgressBar(double completedMCs, String percentage) {
        System.out.println("Progress:");
        int completedMCsRatio = (int)completedMCs / 10;

        for (int i = 0; i < 11; i++) {
            if (i < completedMCsRatio) {
                System.out.print("█");
            } else if (i >= completedMCsRatio) {
                System.out.print("░");
            }
        }
        System.out.println(" " + percentage + "%");
        System.out.println(String.format(PROGRESS_MESSAGE, Math.round(completedMCs)));
    }

    /**
     * Prints CAP of user based on their grades.
     *
     * @param cap CAP of user.
     */
    public void printCap(double cap) {
        System.out.println(String.format(CAP_MESSAGE, cap));
    }

    /**
     * Prints out underlying error message for exception.
     *
     * @param exception Exception object caught.
     */
    public void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }
}