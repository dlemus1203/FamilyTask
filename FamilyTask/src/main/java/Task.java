

public class Task {
    private String name;
    private String dueDate;
    private boolean isComplete;

    public Task(String name, String dueDate) {
        this.name = name;
        this.dueDate = dueDate;
        this.isComplete = false;
    }

    public void markComplete() {
        this.isComplete = true;
    }

    public String getName() {
        return name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isComplete() {
        return isComplete;
    }
    @Override
    public String toString() {
        String status = isComplete ? "[X]" : "[ ]";
        return status + " " + name + " (Due: " + dueDate + ")";

    }
}