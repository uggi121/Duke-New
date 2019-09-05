package duke.task;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) throws InvalidTaskDukeException {
        this.description = description;
        if (description.isBlank()) {
            throw new InvalidTaskDukeException("Description cannot be empty! Please enter a valid description.");
        }
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public abstract String getTime();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}