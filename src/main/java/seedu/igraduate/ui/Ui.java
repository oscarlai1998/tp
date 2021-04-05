package seedu.igraduate.ui;

import seedu.igraduate.model.list.ModuleList;
import seedu.igraduate.model.module.CoreModule;
import seedu.igraduate.model.module.ElectiveModule;
import seedu.igraduate.model.module.GeModule;
import seedu.igraduate.model.module.MathModule;
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
    public static final String MODULES_CORE_MESSAGE = "Core modules in the list:";
    public static final String MODULES_ELECTIVE_MESSAGE = "Elective modules in the list:";
    public static final String MODULES_GE_MESSAGE = "GE modules in the list:";
    public static final String MODULES_MATH_MESSAGE = "Math modules in the list:";
    public static final String MODULES_INFO_MESSAGE = "Printing %s module information...\n"
            + "Module Type                           : %s\n"
            + "Module Code                           : %s\n"
            + "Module Name                           : %s\n"
            + "Modular Credits                       : %s MC\n"
            + "Status                                : %s\n"
            + "Grade                                 : %s\n"
            + "Prerequisites                         : %s\n"
            + "Incomplete Prerequisites              : %s\n"
            + "Prerequisite for                      : %s";
    public static final String EMPTY_LIST_MESSAGE = "List is empty. Add a module.";
    public static final String EMPTY_COMPLETE_LIST_MESSAGE = "There are no completed modules.";
    public static final String EMPTY_INCOMPLETE_LIST_MESSAGE = "There are no incomplete modules.";
    public static final String EMPTY_AVAILABLE_LIST_MESSAGE = "There are no modules available for take.";
    public static final String EMPTY_CORE_MODULE_LIST_MESSAGE = "There are no core modules.";
    public static final String EMPTY_ELECTIVE_MODULE_LIST_MESSAGE = "There are no elective modules.";
    public static final String EMPTY_GE_MODULE_LIST_MESSAGE = "There are no ge modules.";
    public static final String EMPTY_MATH_MODULE_LIST_MESSAGE = "There are no math modules.";
    public static final String PROGRESS_MESSAGE = "%dMCs/160MCs Completed";
    public static final String PROGRESS_COMPLETED_MESSAGE = "Congratulations! You are ready to graduate.";
    public static final String PROGRESS_EXCEEDED_MESSAGE = "Great job studying beyond your graduation requirements!";
    public static final String ADD_EXCEEDS_MAX_CREDITS_MESSAGE = "Cannot add modules worth more than 32 MCs!";
    public static final String PREREQUISITES_MESSAGE = "List of pre-requisites needed to take %s: ";
    public static final String CAP_MESSAGE = "Current CAP: %.2f\nCurrent Degree Classification: %s";

    public static final String HELP_INTRO = "iGraduate is a command line application that acts as a centralised hub "
            + "for\nNUS students majoring in Information Security to plan their academic journey.\nThe application "
            + "comes with 9 features:\n-add\n-delete\n-update\n-done\n-info\n-list\n-progress\n-cap\n-exit\n\nType "
            + "help <command> to view further details on each command.";
    public static final String HELP_ADD = "The Add command adds a new module to the list of modules you wish to track."
            + " The list serves to keep track of the modules that you have taken, are currently taking or intend "
            + "to take in the future.\n\nSyntax: add <name> -c <module code> -t <core|math|ue|ge> -mc "
            + "<number of credits> [-p <prerequisite1,prerequisite2,...>]";
    public static final String HELP_DELETE = "The Delete command deletes an existing module from the list of modules "
            + "added via the module code.\n\nSyntax: delete <module code>";
    public static final String HELP_UPDATE = "The Update command updates relevant information (name, credit and grade)"
            + " for the selected module.\n\nSyntax: update <module code> [-n <name>] [-mc <credit>] [-g <grade>] "
            + "[-p <prerequisite1,prerequisite2,...>]";
    public static final String HELP_DONE = "The Done command marks a module as completed via its module code. "
            + "You must include the grade obtained to facilitate the calculation of CAP.\n\n"
            + "Syntax: done <module code> -g <grade>";
    public static final String HELP_INFO = "The Info command prints out module information of the module specified "
            + "using the module code.\n\n"
            + "Syntax: info <module code>";
    public static final String HELP_LIST = "The list command lists modules added to your list according "
            + "to the filter. The filters are:\n"
            + "-all: Lists all modules on the list\n"
            + "-complete: Lists modules marked as done\n"
            + "-incomplete: Lists modules yet to be marked as done\n"
            + "-available: Lists modules with prerequisites fulfilled and can be completed\n"
            + "-core: Lists core modules on the list\n"
            + "-elec: Lists elective modules on the list\n"
            + "-ge: Lists general education modules on the list\n"
            + "-math: Lists math modules on the list\n\n"
            + "Syntax: list all|incomplete|complete|available|core|elec|ge|math";
    public static final String HELP_PROGRESS = "The Progress command displays a bar that represents the current "
            + "progress of your academic career. The progress bar shows the percentage of your total completed module "
            + "credits against the total number of credits needed for graduation requirements.\n\nSyntax: progress";
    public static final String HELP_CAP = "The Cap command calculates your current Cumulative Average Point (CAP) and "
            + "displays current degree classification based on CAP.\n\nSyntax: cap";
    public static final String HELP_EXIT = "The Exit command exits the program.\n\nSyntax: exit";

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
     * Prints the core modules in the array list.
     *
     * @param modules array list containing the modules.
     */
    public void printCoreModuleList(ArrayList<Module> modules) {
        System.out.println(MODULES_CORE_MESSAGE);
        int count = 0;
        for (Module module : modules) {
            if (module instanceof CoreModule) {
                System.out.println((count + 1) + ": " + module);
                count++;
            }
        }
    }

    /**
     * Prints the elective modules in the array list.
     *
     * @param modules array list containing the modules.
     */
    public void printElectiveModuleList(ArrayList<Module> modules) {
        System.out.println(MODULES_ELECTIVE_MESSAGE);
        int count = 0;
        for (Module module : modules) {
            if (module instanceof ElectiveModule) {
                System.out.println((count + 1) + ": " + module);
                count++;
            }
        }
    }

    /**
     * Prints the ge modules in the array list.
     *
     * @param modules array list containing the modules.
     */
    public void printGeModuleList(ArrayList<Module> modules) {
        System.out.println(MODULES_GE_MESSAGE);
        int count = 0;
        for (Module module : modules) {
            if (module instanceof GeModule) {
                System.out.println((count + 1) + ": " + module);
                count++;
            }
        }
    }

    /**
     * Prints the math modules in the array list.
     *
     * @param modules array list containing the modules.
     */
    public void printMathModuleList(ArrayList<Module> modules) {
        System.out.println(MODULES_MATH_MESSAGE);
        int count = 0;
        for (Module module : modules) {
            if (module instanceof MathModule) {
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

    /**
     * Displays success message after the specified information is updated.
     *
     * @param module module with updated information.
     */
    public void printUpdateSuccess(Module module) {
        System.out.println("Nice! I've updated this module:");
        System.out.println(INDENTATION + module);
    }

    /**
     * Prints all information of the specified module.
     *
     * @param module module for printing details.
     * @param moduleList moduleList consisting all modules.
     */
    public void printModuleInfo(Module module, ModuleList moduleList) {
        String moduleType = moduleList.getModuleType(module);
        String moduleCode = module.getCode();
        String moduleName = module.getName();
        double moduleCredits = module.getCredit();
        String moduleStatus = module.getStatus();
        String moduleGrade = module.getGrade();
        ArrayList<String> preRequisites = module.getPreRequisites();
        ArrayList<String> untakenPreRequisites = module.getUntakenPreRequisites();
        ArrayList<String> requiredByModules = module.getRequiredByModules();

        String message = String.format(MODULES_INFO_MESSAGE, moduleCode, moduleType, moduleCode, moduleName,
                moduleCredits, moduleStatus, moduleGrade, preRequisites, untakenPreRequisites, requiredByModules);
        System.out.println(message);
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
     * Displays message if there are no core modules.
     */
    public void printCoreModuleListEmptyMessage() {
        System.out.println(EMPTY_CORE_MODULE_LIST_MESSAGE);
    }

    /**
     * Displays message if there are no elective modules.
     */
    public void printElectiveModuleListEmptyMessage() {
        System.out.println(EMPTY_ELECTIVE_MODULE_LIST_MESSAGE);
    }

    /**
     * Displays message if there are no ge modules.
     */
    public void printGeModuleListEmptyMessage() {
        System.out.println(EMPTY_GE_MODULE_LIST_MESSAGE);
    }

    /**
     * Displays message if there are no math modules.
     */
    public void printMathModuleListEmptyMessage() {
        System.out.println(EMPTY_MATH_MODULE_LIST_MESSAGE);
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

    public void printIntroHelp() {
        System.out.println(HELP_INTRO);
    }

    public void printAddHelp() {
        System.out.println(HELP_ADD);
    }

    public void printDeleteHelp() {
        System.out.println(HELP_DELETE);
    }

    public void printUpdateHelp() {
        System.out.println(HELP_UPDATE);
    }

    public void printDoneHelp() {
        System.out.println(HELP_DONE);
    }

    public void printInfoHelp() {
        System.out.println(HELP_INFO);
    }

    public void printListHelp() {
        System.out.println(HELP_LIST);
    }

    public void printProgressHelp() {
        System.out.println(HELP_PROGRESS);
    }

    public void printCapHelp() {
        System.out.println(HELP_CAP);
    }

    public void printExitHelp() {
        System.out.println(HELP_EXIT);
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
     *  Prints warning message when user adds a module that exceeds 32 MCs.
     */
    public void printExceededMaxMCs() {
        System.out.println(ADD_EXCEEDS_MAX_CREDITS_MESSAGE);
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