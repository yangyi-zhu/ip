public class Deadline extends Task {
    protected String ddl;

    public Deadline(String description, String ddl) {
        super(description);
        this.ddl = ddl;
    }

    public String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + "(due by: " + ddl + ")";
    }
}