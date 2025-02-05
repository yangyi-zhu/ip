class Task {
    String desc;
    boolean isDone;

    Task(String description) {
        this.desc = description;
        this.isDone = false;
    }

    void mark() {
        this.isDone = true;
    }

    void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[✓] " : "[ ] ") + desc;
    }
}