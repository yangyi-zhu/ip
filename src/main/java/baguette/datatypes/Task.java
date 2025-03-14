package baguette.datatypes;

/**
 * Serves as a base class for specific task types such as Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String desc;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * By default, the task is not completed.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.desc = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, setting it back to incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Retrieves the type identifier for the specific task type.
     *
     * @return A string representing the task type.
     */
    public abstract String getType();

    /**
     * Returns a string representation of the task, including its type, status, and description.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        return getType() + (isDone ? "[✓] " : "[ ] ") + desc;
    }
}