public class DoneCommand extends Command {
    private String input;

    public DoneCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui) throws DukeException {
        String cleanedInput = input.strip().toLowerCase();
        String output = tasks.markAsDone(cleanedInput);
        ui.displayOutput(output);
    }

    public boolean checkExit() {
        return false;
    }
}
