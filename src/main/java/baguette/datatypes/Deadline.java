package baguette.datatypes;

import baguette.Parser;
import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime ddl;

    public Deadline(String description, String deadline) {
        super(description);
        this.ddl = Parser.parseDateTime(deadline);
    }

    public String getType() {
        return "[D]";
    }

    public String getDdl() {
        return Parser.toStringDateTime(ddl);
    }

    @Override
    public String toString() {
        return super.toString() + " (due by: " + getDdl() + ")";
    }
}