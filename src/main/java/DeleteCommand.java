import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteCommand extends Command {

    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDeleteDukeException {
        String cleanedInput = input.strip().toLowerCase();
        String output = deleteTask(cleanedInput, tasks);
        ui.displayOutput(output);
    }

    private String deleteTask(String input, TaskList tasks) throws InvalidDeleteDukeException {
        String cleanedInput = input.strip().toLowerCase();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(cleanedInput);
        ArrayList<Integer> taskIds = new ArrayList<>();
        while (m.find()) {
            taskIds.add(Integer.parseInt(m.group()));
        }
        if (taskIds.size() != 1) {
            throw new InvalidDeleteDukeException("Invalid \"delete\" command. Please enter only ONE task ID.");
        }
        return tasks.removeTaskFromList(taskIds.get(0));
    }

    public boolean checkExit() {
        return false;
    }

}
