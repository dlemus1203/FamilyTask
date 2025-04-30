
import java.util.List;
import java.util.Scanner;

public class FamilyTaskManagerApp {
    private static TaskManager taskManager = new TaskManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Family Task Manager");

        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();

        System.out.print("Enter due date: ");
        String dueDate = scanner.nextLine();

        Task task = new Task(name, dueDate);
        taskManager.addTask(task);
        System.out.println("Task added successfully!");
    }

    private static void viewTasks() {
        List<Task> tasks = taskManager.getTasks();

        if (tasks.isEmpty()) {
            System.out.println("No tasks available!");
            return;
        }
        System.out.println("\nTask List:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}