class Task {
    String desc;
    boolean isDone;
    int type; // 0: T, 1: D, 2: E

    Task(String description, int type) {
        this.desc = description;
        this.isDone = false;
        this.type = type;
    }

    void mark() {
        this.isDone = true;
    }

    void unmark() {
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