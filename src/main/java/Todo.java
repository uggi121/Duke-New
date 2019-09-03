public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[Todo] " + super.toString();
    }
}