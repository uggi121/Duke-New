public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui) {
        ui.displayOutput("Bye! See you again :-)");
    }

    public boolean checkExit() {
        return true;
    }
}
