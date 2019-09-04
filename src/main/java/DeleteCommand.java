public class DeleteCommand extends Command {

    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui) throws InvalidDeleteDukeException {
        String cleanedInput = input.strip().toLowerCase();
        String output = tasks.deleteTask(cleanedInput);
        ui.displayOutput(output);
    }

    public boolean checkExit() {
        return false;
    }

}
