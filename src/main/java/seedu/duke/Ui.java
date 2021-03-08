package seedu.duke;

public class Ui {
    private static final int ERR_CODE = 0;

    private static final String GREETING_MESSAGE = "iGraduate starting up...\n Welcome to iGraduate, " +
            "your one stop study planning service!";
    private static final String GOODBYE_MESSAGE = "See you soon! Happy studying!";
    private static final String BORDER_LINE = "___________________________________________________________";
    private static final String TEMPLATE_ERROR_MESSAGE = "Add more errors as we encounter them.";

    public void printWelcomeMessage() {
        System.out.println(GREETING_MESSAGE);

    }

    public void printExitMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public void printBorderLine() {
        System.out.println(BORDER_LINE);
    }

    public void printEntireList() {

    }
    public void printError(int error) {
        switch(error) {
        // No fallthrough required
        case ERR_CODE:
            System.out.println(TEMPLATE_ERROR_MESSAGE);
            break;
        }
    }
}
