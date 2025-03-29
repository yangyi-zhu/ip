package baguette.datatypes;

import baguette.Parser;
import java.time.LocalDateTime;

/**
 * Represents a task with a deadline in the Baguette application.
 * Inherits from the Task class and includes a due date.
 */
public class Deadline extends Task {
    protected LocalDateTime ddl;

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param deadline The due date and time in string format, which is parsed into a LocalDateTime object.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.ddl = Parser.parseDateTime(deadline);
    }

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param deadline The due date and time in LocalDateTime format, which is parsed into a LocalDateTime object.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.ddl = deadline;
    }

    /**
     * Retrieves the type identifier for the deadline task.
     *
     * @return A string representing the task type, "[D]" for deadline.
     */
    public String getType() {
        return "[D]";
    }

    /**
     * Retrieves the formatted deadline date and time as a string.
     *
     * @return A string representation of the deadline.
     */
    public String getDdl() {
        return Parser.toStringDateTime(ddl);
    }

    /**
     * Returns a string representation of the deadline task, including its description and due date.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        return super.toString() + " (due by: " + getDdl() + ")";
    }
}