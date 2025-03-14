package baguette.datatypes;

public abstract class Task {
    protected String desc;
    protected boolean isDone;

    // Type 0: To-do (default)
    // Type 1: Baguette.datatypes.Deadline
    // Type 2: Baguette.datatypes.Event
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

    public boolean getIsDone() {
        return isDone;
    }

    public String getDesc() {
        return desc;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + (isDone ? "[✓] " : "[ ] ") + desc;
    }
}