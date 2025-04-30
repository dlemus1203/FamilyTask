package test.java;

import org.junit.jupiter.api.Test;

import main.java.TaskManager;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {
    @Test
    public void testAddAndGetTasks() {
        TaskManager manager = new TaskManager();
        Task task1 = new Task("Grocery shopping", "2023-12-01");
        Task task2 = new Task("Doctor appointment", "2023-12-15");

        manager.addTask(task1);
        manager.addTask(task2);

        asssertEquals(2, manager.getTask().size());
        asssertEquals("Grocery shopping", manager.getTasks().get(0).getName());


    }
    @Test
    public void testDeleteTask() {
        TaskManager manager = new TaskManager();
        Task task = new Task("Test task", "2023-12-01");
        manager.addTask(task);

        assertTrue(manager.deleteTask(0));
        assertEquals(0, manager.getTasks().size());

        assertFalse(manager.deleteTask(0));
    }
}
