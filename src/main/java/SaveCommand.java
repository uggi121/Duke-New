import java.io.IOException;
import java.util.ArrayList;

public class SaveCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskDukeException {
        try {
            ArrayList<Task> taskList = tasks.getTasks();
            ArrayList<String> stringTasks = new ArrayList<>();
            Serializer serializer = new Serializer();
            for (Task t : taskList) {
                String serializedTask = serializer.serializeTask(t);
                stringTasks.add(serializedTask);
            }
            storage.saveTasks(stringTasks);
        } catch (IOException e) {
            ui.displayOutput("Unable to save tasks. Please check your file path.");
        }
    }

    public boolean checkExit() {
        return false;
    }

}
