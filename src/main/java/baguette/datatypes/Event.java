package baguette.datatypes;

import baguette.Parser;
import java.time.LocalDateTime;

/**
 * Represents an event task in the Baguette application.
 * Inherits from the Task class and includes start and end times.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start date and time in string format, which is parsed into a LocalDateTime object.
     * @param to The end date and time in string format, which is parsed into a LocalDateTime object.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = Parser.parseDateTime(from);
        this.to = Parser.parseDateTime(to);
    }

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start date and time in LocalDateTime format, which is parsed into a LocalDateTime object.
     * @param to The end date and time in LocalDateTime format, which is parsed into a LocalDateTime object.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Retrieves the formatted start date and time of the event.
     *
     * @return A string representation of the event's start time.
     */
    public String getFrom() {
        return Parser.toStringDateTime(from);
    }

    /**
     * Retrieves the formatted end date and time of the event.
     *
     * @return A string representation of the event's end time.
     */
    public String getTo() {
        return Parser.toStringDateTime(to);
    }

    /**
     * Retrieves the type identifier for the event task.
     *
     * @return A string representing the task type, "[E]" for event.
     */
    public String getType() {
        return "[E]";
    }

    /**
     * Returns a string representation of the event task, including its description, start time, and end time.
     *
     * @return A formatted string representation of the task.
     */

    @Override
    public String toString() {
        return super.toString() + " (from: " + getFrom() + ", to: " + getTo() + ")";
    }
}
