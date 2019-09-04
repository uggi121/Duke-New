import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoneCommand extends Command {
    private String input;

    public DoneCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui) throws DukeException {
        String cleanedInput = input.strip().toLowerCase();
        String output = markAsDone(cleanedInput, tasks);
        ui.displayOutput(output);
    }

    private String markAsDone(String input, TaskList tasks) throws DukeException {
        try {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(input);
            ArrayList<Integer> listOfTaskIds = new ArrayList<>();
            while (m.find()) {
                int taskId = Integer.parseInt(m.group());
                listOfTaskIds.add(taskId);
            }
            if (listOfTaskIds.isEmpty()) {
                throw new DukeException("Invalid \"done\" command!"
                        + " Please enter one or more integer IDs after \"done\".");
            }
            return tasks.getDoneUpdates(listOfTaskIds);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskDukeException("Task not found! Please enter a valid task ID.");
        }
    }

    public boolean checkExit() {
        return false;
    }
}
