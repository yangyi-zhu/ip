package baguette.datatypes;

/**
 * Represents a Todo task in the Baguette application.
 * Inherits from the Task class and does not have any additional constraints.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Retrieves the type identifier for the Todo task.
     *
     * @return A string representing the task type, "[T]" for Todo.
     */
    @Override
    public String getType() {
        return "[T]";
    }
}
