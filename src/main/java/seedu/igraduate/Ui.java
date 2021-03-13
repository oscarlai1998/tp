package seedu.igraduate;

import seedu.igraduate.module.Module;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LOGO = " _  ____               _             _\n"
            + "(_)/ ___|_ __ __ _  __| |_   _  __ _| |_ ___ \n"
            + "| | |  _| '__/ _` |/ _` | | | |/ _` | __/ _ \\\n"
            + "| | |_| | | | (_| | (_| | |_| | (_| | ||  __/\n"
            + "|_|\\____|_|  \\__,_|\\__,_|\\__,_|\\__,_|\\__\\___|";
    private static final String GREETING_MESSAGE = "iGraduate starting up...\nWelcome to iGraduate, "
            + "your one stop study planning service!\nWhat would you like to do today?";
    private static final String GOODBYE_MESSAGE = "See you soon! Happy studying!";
    private static final String BORDER_LINE = "------------------------------------------------------------";

    private static final String MODULE_DELETED_MESSAGE = "\"%s\" module %s has been deleted.";
    private static final String MODULES_TAKEN_MESSAGE = "Modules you have taken:\n";
    private static final String MODULES_LEFT_MESSAGE = "Modules you can take:\n";
    private static final String EMPTY_LIST_MESSAGE = "List is empty. Add a module.";
    private static final String PROGRESS_MESSAGE = "%dMCs/160MCs Completed";

    private static final Scanner SCANNER = new Scanner(System.in);

    public String getCommand() {
        return SCANNER.nextLine();
    }

    public void printWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(GREETING_MESSAGE);
        printBorderLine();
    }

    public void printExitMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public void printBorderLine() {
        System.out.println(BORDER_LINE);
    }

    public void printEntireList(ArrayList<Module> modules) {
        System.out.println("Module List: ");
        for (int i = 0; i < modules.size(); i++) {
            System.out.print(i + 1 + ": ");
            printModuleDetails(modules.get(i));
        }
    }

    public void printModuleDetails(Module module) {
        String moduleDetails = module.toString();
        System.out.println(moduleDetails);
    }

    public void printAddedModuleSuccess(Module module) {
        System.out.println("Module added to the list.");
        System.out.println(module);
    }

    public void printDeletedModuleSuccess(String name, String type) {
        System.out.println(String.format(MODULE_DELETED_MESSAGE, type, name));
    }

    public void printAllModules() {
        printModulesTakenMessage();
        printModulesRemainingMessage();
    }

    public void printMarkAsTakenMessage(Module module) {
        System.out.println("Nice! I've marked this module as done:");
        System.out.println("  " + module);
    }

    public void printModulesTakenMessage() {
        System.out.println(MODULES_TAKEN_MESSAGE);
        System.out.println(); // Print module names
    }

    public void printModulesRemainingMessage() {
        System.out.println(MODULES_LEFT_MESSAGE);
        System.out.println(); // Print module names
    }

    public void printListEmptyMessage() {
        System.out.println(EMPTY_LIST_MESSAGE);
    }

    public void printProgressBar(float completedMCs, float percentageDone, String percentage) {
        System.out.println("Progress:");
        if (percentageDone == 100.0) {
            System.out.println("██████████ 100%");
        } else if (percentageDone >= 90.0 && percentageDone < 100.0) {
            System.out.println("█████████░ " + percentage + "%");
        } else if (percentageDone >= 80.0 && percentageDone < 90.0) {
            System.out.println("████████░░ " + percentage + "%");
        } else if (percentageDone >= 70.0 && percentageDone < 80.0) {
            System.out.println("███████░░░ " + percentage + "%");
        } else if (percentageDone >= 60.0 && percentageDone < 70.0) {
            System.out.println("██████░░░░ " + percentage + "%");
        } else if (percentageDone >= 50.0 && percentageDone < 80.0) {
            System.out.println("█████░░░░░ " + percentage + "%");
        } else if (percentageDone >= 40.0 && percentageDone < 50.0) {
            System.out.println("████░░░░░░ " + percentage + "%");
        } else if (percentageDone >= 30.0 && percentageDone < 40.0) {
            System.out.println("███░░░░░░░ " + percentage + "%");
        } else if (percentageDone >= 20.0 && percentageDone < 30.0) {
            System.out.println("██░░░░░░░░ " + percentage + "%");
        } else if (percentageDone > 0.0 && percentageDone < 20.0) {
            System.out.println("█░░░░░░░░░ " + percentage + "%");
        } else if (percentageDone == 0.0) {
            System.out.println("░░░░░░░░░░ 0%");
        }
        System.out.println(String.format(PROGRESS_MESSAGE, Math.round(completedMCs)));
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