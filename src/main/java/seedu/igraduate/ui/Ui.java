package seedu.igraduate.ui;

import seedu.igraduate.model.module.Module;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    // Welcome message
    public static final String LOGO = " _  ____               _             _\n"
            + "(_)/ ___|_ __ __ _  __| |_   _  __ _| |_ ___ \n"
            + "| | |  _| '__/ _` |/ _` | | | |/ _` | __/ _ \\\n"
            + "| | |_| | | | (_| | (_| | |_| | (_| | ||  __/\n"
            + "|_|\\____|_|  \\__,_|\\__,_|\\__,_|\\__,_|\\__\\___|";
    public static final String GREETING_MESSAGE = "iGraduate starting up...\nWelcome to iGraduate, "
            + "your one stop study planning service!\nWhat would you like to do today?";
    public static final String GOODBYE_MESSAGE = "See you soon! Happy studying!";
    public static final String BORDER_LINE = "------------------------------------------------------------"
            + "--------------------------";
    public static final String INDENTATION = "  ";

    // Messages for successful execution
    public static final String MODULE_ADDED_MESSAGE = "Added %s %s to the list. (%sMCs)";
    public static final String MODULE_DELETED_MESSAGE = "\"%s\" module %s has been deleted.";
    public static final String MODULES_TAKEN_MESSAGE = "Modules you have have completed:";
    public static final String MODULES_LEFT_MESSAGE = "Modules you have yet to complete:";
    public static final String MODULES_AVAILABLE_MESSAGE = "Modules can be taken:";
    public static final String EMPTY_LIST_MESSAGE = "List is empty. Add a module.";
    public static final String EMPTY_COMPLETE_LIST_MESSAGE = "There are no completed modules.";
    public static final String EMPTY_INCOMPLETE_LIST_MESSAGE = "There are no incomplete modules.";
    public static final String EMPTY_AVAILABLE_LIST_MESSAGE = "There are no modules available for take.";
    public static final String PROGRESS_MESSAGE = "%dMCs/160MCs Completed";
    public static final String PROGRESS_COMPLETED_MESSAGE = "Congratulations! You are ready to graduate.";
    public static final String PROGRESS_EXCEEDED_MESSAGE = "Great job studying beyond your graduation requirements!";

    public static final String PREREQUISITES_MESSAGE = "List of pre-requisites needed to take %s: ";
    public static final String CAP_MESSAGE = "Current CAP: %.2f\nCurrent Degree Classification: %s";


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
            System.out.println((i + 1) + ": " + modules.get(i));
        }
    }

    /**
     * Prints the modules in the array list that user has taken.
     *
     * @param modules array list containing the modules.
     */
    public void printCompletedList(ArrayList<Module> modules) {
        System.out.println(MODULES_TAKEN_MESSAGE);
        int count = 0;
        for (Module module : modules) {
            if (module.isDone()) {
                System.out.println((count + 1) + ": " + module);
                count++;
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
        int count = 0;
        for (Module module : modules) {
            if (!module.isDone()) {
                System.out.println((count + 1) + ": " + module);
                count++;
            }
        }
    }

    /**
     * Prints the modules in the array list that user can take.
     *
     * @param modules array list containing the modules.
     */
    public void printAvailableList(ArrayList<Module> modules) {
        System.out.println(MODULES_AVAILABLE_MESSAGE);
        int count = 0;
        for (Module module : modules) {
            ArrayList<String> untakenPreRequisites = module.getUntakenPreRequisites();
            boolean isPreRequisiteCleared = untakenPreRequisites.isEmpty();
            boolean isIncomplete = module.getStatus().equalsIgnoreCase("not taken");
            if (isPreRequisiteCleared && isIncomplete) {
                System.out.println((count + 1) + ": " + module);
                count++;
            }
        }
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
        System.out.println(INDENTATION + module);
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
        System.out.println(INDENTATION + module);
    }

    public void printUpdateSuccess(Module module) {
        System.out.println("Nice! I've updated this module:");
        System.out.println(INDENTATION + module);
    }

    /**
     * Displays message if module list is empty. 
     */
    public void printListEmptyMessage() {
        System.out.println(EMPTY_LIST_MESSAGE);
    }

    /**
     * Displays message if there are no completed modules.
     */
    public void printCompleteListEmptyMessage() {
        System.out.println(EMPTY_COMPLETE_LIST_MESSAGE);
    }

    /**
     * Displays message if there are no incomplete modules.
     */
    public void printIncompleteListEmptyMessage() {
        System.out.println(EMPTY_INCOMPLETE_LIST_MESSAGE);
    }

    /**
     * Displays message if there are no available modules.
     */
    public void printAvailableListEmptyMessage() {
        System.out.println(EMPTY_AVAILABLE_LIST_MESSAGE);
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
        printProgressCompletionMessage(Double.parseDouble(percentage));
    }

    /**
     * Displays progress completion message.
     */
    public void printProgressCompletionMessage(double percentage) {
        if (percentage == 100) {
            System.out.println(PROGRESS_COMPLETED_MESSAGE);
        } else if (percentage > 100) {
            System.out.println(PROGRESS_EXCEEDED_MESSAGE);
        }
    }

    /**
     * Prints CAP and degree classification of user based on their grades.
     *
     * @param cap user's cap
     * @param degreeClassification user's degree classification
     */
    public void printCap(double cap, String degreeClassification) {
        System.out.println(String.format(CAP_MESSAGE, cap, degreeClassification));
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