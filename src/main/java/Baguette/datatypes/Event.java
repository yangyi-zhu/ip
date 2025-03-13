package Baguette.datatypes;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + ", to: " + to + ")";
    }
}
