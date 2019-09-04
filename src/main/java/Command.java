public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;

    public abstract boolean checkExit();
}
