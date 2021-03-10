package seedu.igraduate;

public class Ui {
    private static final int ERR_CODE = 0;

    private static final String LOGO = " _  ____               _             _       \n" +
            "(_)/ ___|_ __ __ _  __| |_   _  __ _| |_ ___ \n" +
            "| | |  _| '__/ _` |/ _` | | | |/ _` | __/ _ \\\n" +
            "| | |_| | | | (_| | (_| | |_| | (_| | ||  __/\n" +
            "|_|\\____|_|  \\__,_|\\__,_|\\__,_|\\__,_|\\__\\___|";
    private static final String GREETING_MESSAGE = "iGraduate starting up...\n Welcome to iGraduate, "
            + "your one stop study planning service!";
    private static final String GOODBYE_MESSAGE = "See you soon! Happy studying!";
    private static final String BORDER_LINE = "___________________________________________________________";
    private static final String TEMPLATE_ERROR_MESSAGE = "Add more errors as we encounter them.";

    public void printWelcomeMessage() {
        System.out.println(LOGO);
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

    public void printErrorMessage(int error) {
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
