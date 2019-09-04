public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        setAt(at);
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getTime() {
        return at;
    }

    @Override
    public String toString() {
        return "[Event] " + super.toString() + " (at: " + at + ")";
    }
}