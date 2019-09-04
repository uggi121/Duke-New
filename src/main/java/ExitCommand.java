public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayOutput("Bye! See you again :-)");
    }

    public boolean checkExit() {
        return true;
    }
}