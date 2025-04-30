

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public TaskManager() {
        this.tasks = new ArrayList<>();

    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    public boolean deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            return true;
        }
        return false;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}
