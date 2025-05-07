import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

public class FamilyTaskManagerApp extends Application {
    private TaskManager taskManager = new TaskManager();
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Family Task Manager");
        
        
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(20));
        
        
        Label header = new Label("Family Task Manager");
        header.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        mainLayout.setTop(header);
        
        
        StackPane centerContent = new StackPane();
        mainLayout.setCenter(centerContent);
        
        
        HBox buttonBar = new HBox(10);
        Button addButton = new Button("Add Task");
        Button viewButton = new Button("View Tasks");
        buttonBar.getChildren().addAll(addButton, viewButton);
        mainLayout.setBottom(buttonBar);
        
        
        GridPane addTaskView = createAddTaskView(primaryStage, centerContent);
        
        
        VBox tasksView = createTasksView(primaryStage, centerContent);
        
        
        centerContent.getChildren().add(new Label("Select an option to begin"));
        
        
        addButton.setOnAction(e -> {
            centerContent.getChildren().clear();
            centerContent.getChildren().add(addTaskView);
        });
        
        viewButton.setOnAction(e -> {
            centerContent.getChildren().clear();
            centerContent.getChildren().add(tasksView);
            refreshTasksView(tasksView);
        });
        
        Scene scene = new Scene(mainLayout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private GridPane createAddTaskView(Stage stage, StackPane centerContent) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        
        Label nameLabel = new Label("Task Name:");
        TextField nameField = new TextField();
        
        Label dateLabel = new Label("Due Date:");
        TextField dateField = new TextField();
        
        Button saveButton = new Button("Save Task");
        
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(dateLabel, 0, 1);
        grid.add(dateField, 1, 1);
        grid.add(saveButton, 1, 2);
        
        saveButton.setOnAction(e -> {
            Task task = new Task(nameField.getText(), dateField.getText());
            taskManager.addTask(task);
            nameField.clear();
            dateField.clear();
            new Alert(Alert.AlertType.INFORMATION, "Task added successfully!").show();
        });
        
        return grid;
    }
    
    private VBox createTasksView(Stage stage, StackPane centerContent) {
        VBox tasksBox = new VBox(10);
        tasksBox.setPadding(new Insets(20));
        refreshTasksView(tasksBox);
        return tasksBox;
    }
    
    private void refreshTasksView(VBox tasksBox) {
        tasksBox.getChildren().clear();
        
        List<Task> tasks = taskManager.getTasks();
        if (tasks.isEmpty()) {
            tasksBox.getChildren().add(new Label("No tasks available!"));
            return;
        }
        
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            HBox taskBox = new HBox(10);
            
            CheckBox completeCheck = new CheckBox();
            completeCheck.setSelected(task.isComplete());
            completeCheck.setOnAction(e -> {
                task.markComplete();
                refreshTasksView(tasksBox);
            });
            
            Label taskLabel = new Label(task.toString());
            
            Button deleteButton = new Button("Delete");
            deleteButton.setStyle("-fx-background-color: #ff4444; -fx-text-fill: white;");
            deleteButton.setOnAction(e -> {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, 
                    "Are you sure you want to delete this task?", 
                    ButtonType.YES, ButtonType.NO);
                confirm.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.YES) {
                        taskManager.deleteTask(tasks.indexOf(task));
                        refreshTasksView(tasksBox);
                    }
                });
            });
            
            taskBox.getChildren().addAll(completeCheck, taskLabel, deleteButton);
            tasksBox.getChildren().add(taskBox);
        }
    }
}