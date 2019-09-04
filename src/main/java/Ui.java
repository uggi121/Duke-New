import java.util.Scanner;

public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| |_| | | | | |/ / _ \\\n"
            + "|  _ _/ |_| |   <  __/\n"
            + "|_|   \\__,_|_|\\_\\___|\n";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greetUser() {
        displayOutput("Hello from\n" + LOGO);
    }

    public void displayOutput(String output) {
        String bound = "     ____________________________________________________________\n     ";
        String intermediate = output.replace("\n", "\n     ");
        String finalOutput = bound + intermediate + "\n" + bound;
        System.out.println(finalOutput);
    }

    public String readInput() {
        return sc.nextLine();
    }

    public boolean checkNextInput() {
        return sc.hasNext();
    }
}
