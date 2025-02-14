public abstract class Task {
    protected String desc;
    protected boolean isDone;

    // Type 0: To-do (default)
    // Type 1: Deadline
    // Type 2: Event
    private int type;

    public Task(String description) {
        this.desc = description;
        this.isDone = false;
        //this.type = type;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + (isDone ? "[✓] " : "[ ] ") + desc;
    }
}