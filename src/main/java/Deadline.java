public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[Deadline] " + super.toString() + " (by: " + by + ")";
    }
}