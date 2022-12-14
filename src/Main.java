import basicClasses.Task;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent TaskRoot = FXMLLoader.load(getClass().getResource("TaskTable.fxml"));

        Scene TaskScene = new Scene(TaskRoot);
       // TaskScene.getStylesheets().add(getClass().getResource("stylesheet.css").toString());



        primaryStage.setScene(TaskScene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
