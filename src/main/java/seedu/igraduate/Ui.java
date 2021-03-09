package seedu.igraduate;

import seedu.igraduate.module.Module;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final int ERR_CODE = 0;

    private static final String GREETING_MESSAGE = "iGraduate starting up...\n Welcome to iGraduate, "
            + "your one stop study planning service!\n What would you like to do today?";
    private static final String GOODBYE_MESSAGE = "See you soon! Happy studying!";
    private static final String BORDER_LINE = "___________________________________________________________";
    private static final String TEMPLATE_ERROR_MESSAGE = "Add more errors as we encounter them.";

    public String getCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void printWelcomeMessage() {
        System.out.println(GREETING_MESSAGE);
        printBorderLine();
    }

    public void printExitMessage() {
        System.out.println(GOODBYE_MESSAGE);
        printBorderLine();
    }

    public void printBorderLine() {
        System.out.println(BORDER_LINE);
    }

    public void printEntireList(ArrayList<Module> modules) {
        for (int i = 0; i < modules.size(); i++) {
            printModuleDetails(modules.get(i));
        }
    }

    public void printModuleDetails(Module module) {

    }

    public void printError(int error) {
        switch (error) {
        // No fallthrough required
        case ERR_CODE:
            System.out.println(TEMPLATE_ERROR_MESSAGE);
            break;
        default:
            System.out.println("Some error");
            break;
        }
    }
}
