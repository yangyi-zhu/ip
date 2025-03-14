package baguette.datatypes;

import baguette.Parser;
import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = Parser.parseDateTime(from);
        this.to = Parser.parseDateTime(to);
    }

    public String getFrom() {
        return Parser.toStringDateTime(from);
    }

    public String getTo() {
        return Parser.toStringDateTime(to);
    }

    public String getType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + getFrom() + ", to: " + getTo() + ")";
    }
}
