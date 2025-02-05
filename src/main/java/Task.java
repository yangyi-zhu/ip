public class Task {
    private String desc;
    private boolean isDone;

    // Type 0: To-do (default)
    // Type 1: Deadline
    // Type 2: Event
    private int type;

    public Task(String description, int type) {
        this.desc = description;
        this.isDone = false;
        this.type = type;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String typeBox = new String("");
        switch (type) {
        case 0:
            typeBox = "[T]";
            break;
        case 1:
            typeBox = "[D]";
            break;
        case 2:
            typeBox = "[E]";
        }
        return typeBox + (isDone ? "[✓] " : "[ ] ") + desc;
    }
}