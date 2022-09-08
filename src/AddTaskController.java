import basicClasses.Task;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddTaskController implements Initializable {
    @FXML
    private CheckComboBox<Task> predecessorComboBox;

    @FXML
    private TextField taskNameTextField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private TextField taskDurationTextField;

            public static ObservableList<Task> selectedPredecessorTasks ;

    @FXML
    void cancelAction(ActionEvent event) {
TaskTableController.addTaskDialogStage.hide();
    }


    boolean checkIfNameDuplicated(String name){
        for (Task task : TaskTableController.tasksList)
            if(task.getTaskName().equals(name))
                return true;
        return false;

    }
    @FXML
    void confirmAction(ActionEvent event) {

        System.out.println("branch checked [1] "+taskNameTextField.getText().equals(""));
        System.out.println("branch checked [2] "+(taskNameTextField.getText().equals("") && taskDurationTextField.getText().equals("") && checkIfNameDuplicated(taskNameTextField.getText())));
        System.out.println("branch checked [3] "+(taskDurationTextField.getText().equals("")));
        System.out.println("branch checked [4] "+( checkIfNameDuplicated(taskNameTextField.getText())));

        if(taskNameTextField.getText().equals("") &&taskDurationTextField.getText().equals("") && !checkIfNameDuplicated(taskNameTextField.getText())) {
            errorMessageLabel.setText("Check your information !");
            return;
        }
        errorMessageLabel.setText("");

            selectedPredecessorTasks.addAll( predecessorComboBox.getCheckModel().getCheckedItems());
            ArrayList<Task> predecessorTasks = new ArrayList<>();

            predecessorTasks.addAll(selectedPredecessorTasks);
            Task newTask = new Task(taskNameTextField.getText(),predecessorTasks ,Integer.parseInt(taskDurationTextField.getText()),TaskTableController.tasksList.size());//,bigDuration);
TaskTableController.tasksList.add(newTask);
TaskTableController.graph.addTask(newTask);
            //System.out.println("twehis after adding task ");
           // TaskTableController.graph.checkEndingDate(newTask);
            taskNameTextField.setText("");
            taskDurationTextField.setText("");
            predecessorComboBox.getCheckModel().clearChecks();
            predecessorComboBox.getItems().add(newTask);
        TaskTableController.buttonToogle.set(false);
            selectedPredecessorTasks.clear();
           // TaskTableController.addTaskDialogStage.close();



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        selectedPredecessorTasks = FXCollections.observableArrayList();

  /*      if(selectedPredecessorTasks==null) {
        }
        */
//System.out.println("another init ");

predecessorComboBox.getItems().addAll(TaskTableController.tasksList);
//System.out.println("combo box list "+predecessorComboBox.getItems());
        taskDurationTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    taskDurationTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
