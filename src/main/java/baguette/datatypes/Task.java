package baguette.datatypes;

public abstract class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String description) {
        this.desc = description;
        this.isDone = false;
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