package seedu.igraduate;

import java.util.Scanner;

public class Parser {
    public String getCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
