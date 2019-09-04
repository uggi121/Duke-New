public class AddCommand extends Command {

    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui) throws InvalidTaskDukeException {
        String cleanedInput = input.strip().toLowerCase();
        String output = tasks.addTask(cleanedInput);
        ui.displayOutput(output);
    }

    public boolean checkExit() {
        return false;
    }

}
