import basicClasses.Graph;
import basicClasses.Task;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TaskTableController implements Initializable {

    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, String> taskCell;

    @FXML
    private TableColumn<Task, String> predecessorCell;

    @FXML
    private TableColumn<Task,Integer> durationCell;

    @FXML
    private TableColumn<Task, Boolean> deleteButtonCell;
    @FXML
    private Button showGraphDetailsButton;



    @FXML
    private Button clearTasksButton;

public static SimpleBooleanProperty buttonToogle;
    public static ObservableList<Task> tasksList = FXCollections.observableArrayList();
    public static Stage addTaskDialogStage;
    public static   Graph graph;
    public void addTaskAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addTask.fxml"));
        Parent parent = fxmlLoader.load();


        Scene scene = new Scene(parent, 430, 670);
    //    if(addTaskDialogStage==null) {
            addTaskDialogStage = new Stage();
            addTaskDialogStage.initModality(Modality.APPLICATION_MODAL);
            addTaskDialogStage.setScene(scene);
    //    }
        addTaskDialogStage.showAndWait();
    }

    void exportToLatexFile(String fileContent){

        try {
           // File myObj = new File("./graphTable.tex");
            FileWriter myWriter = new FileWriter("graphTable.tex");
            myWriter.write(fileContent);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
@FXML
    public void ShowGraphDetailsAction(ActionEvent actionEvent) {
String fileHeader="\\documentclass{article}\n\\begin{document}\n\\begin{center}\n\\begin{tabular}{|c| c| c| c| c| c|}\n";
String fileContent="";
String tableHeader=" \\hline\n Task & Predecessors & Duration & In Critical Path & Free Margin & Total Margin\\\\ [0.5ex]";
String fileFooter="\\end{tabular}\n\\end{center}\n\\end{document}";
StringBuilder fileBodyBuilder= new StringBuilder();
ArrayList <Task>freeMarginTasks = graph.getFreeMargin()   ;
    ArrayList <Task>criticalPathTasks =   graph.getCriticalPath();
    String [] freeMargin_criticalPath = new String[2];
for (Task task  :tasksList){
    freeMargin_criticalPath[0] = criticalPathTasks.contains(task) ? "Yes" : "No";

    freeMargin_criticalPath[1]= freeMarginTasks.contains(task) ? task.getFreeMargin().toString()  : "0";
System.out.println("this is the task : "+task.getTaskName());
    System.out.println("this is the task typing  : "+freeMargin_criticalPath[0] + " " +freeMargin_criticalPath[1]);
    System.out.println("======================");


    fileBodyBuilder.append("\\hline\n").append(task.getTaskName()).append(" ").append("&").append(" ").append(task.getPredecessorTasks()).append(" ").append("&").append(" ").append(task.getDuration()).append(" ").append("&").append(" ").append(freeMargin_criticalPath[0]).append(" ").append("&").append(" ").append(freeMargin_criticalPath[1]).append(" ").append("&").append(" ").append(freeMargin_criticalPath[1]).append(" \\\\").append("\n");

}
    fileBodyBuilder.append("\\hline\n");
fileContent=fileHeader+tableHeader+fileBodyBuilder+fileFooter;
System.out.println(fileContent);
exportToLatexFile(fileContent);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Saving file");
    alert.setHeaderText(null);
    alert.setContentText("Successfully wrote to the file.");

    alert.showAndWait();
    for (Task task : graph.getFreeMargin()){
        System.out.println("this is a free margin task "+task.getTaskName());
    }
   System.out.println("this is e : "+freeMarginTasks.contains(graph.getAllTasks().get(1)));
        return;
      /*  String formatCriticalPath="";
      for ( Task criticalTask : graph.getCriticalPath()) {
          formatCriticalPath +=criticalTask.getTaskName()+" -> ";
      }
   formatCriticalPath=     formatCriticalPath.substring(0,formatCriticalPath.length()-3);

      System.out.println("this is the critical Path ::");
        System.out.println(formatCriticalPath);*/

    }
@FXML
    public void clearTasks(ActionEvent actionEvent) {
       // tasksList.clear();
    //    graph.clearGraphsTask();
   // buttonToogle.set(true);
    for(Task task : graph.getAllTasks()){

        System.out.println("this is the task : "+task.getTaskName() +" and this is the isa predecessor "+task.isApredecessor());
    }
    }
@FXML
    public void showFreeMargin(ActionEvent actionEvent) {
        graph.getFreeMargin();
    }

    public class CustomButtonCell<T, S> extends TableCell<T, S> {
        private Button supButton = new Button("Supprimer");


        @Override
        protected void updateItem(S item, boolean empty) {

            super.updateItem(item, empty);
            if (empty || item == null) {
                this.setText("");
                this.setGraphic(null);
            } else {
                //    if( !SendEmailMessageController.ForDisableModifyButton.get())
                supButton.setId("EditButton");
                supButton.setOnAction(event -> {
               //     indexInList = getIndex();
                 //   machine = (Machine) this.getTableView().getItems().get(getIndex());


                });

                this.setGraphic(supButton);
            }
        }
    }
    public class CustomButtonCell2<T, S> extends TableCell<T, S> {
        private Button modifButton = new Button("Modifier");


        @Override
        protected void updateItem(S item, boolean empty) {

            super.updateItem(item, empty);
            if (empty || item == null) {
                this.setText("");
                this.setGraphic(null);
            } else {
                //    if( !SendEmailMessageController.ForDisableModifyButton.get())
                modifButton.setId("EditButton");
                modifButton.setOnAction(event -> {
                //    indexInList = getIndex();
                  //  machine = (Machine) this.getTableView().getItems().get(getIndex());


                });

                this.setGraphic(modifButton);
            }
        }
    }
    /*
    public void fillList(){
        Task A = new Task("A",(Task) null,10);

        Task B = new Task("B",A,5);

        Task C = new Task("C",new ArrayList<>(Arrays.asList(A,B)) ,9);
        //C.addTask(A);
        tasksList.add(A);
        A.setTaskId(tasksList.size()-1);


        tasksList.add(B);
        B.setTaskId(tasksList.size()-1);

        tasksList.add(C);
        C.setTaskId(tasksList.size()-1);
         graph = new Graph(new ArrayList<>( Arrays.asList(A,B,C)));


    }



     */


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonToogle = new SimpleBooleanProperty(true);

        clearTasksButton.setDisable(true);
        showGraphDetailsButton.setDisable(true);


        //fillList();
        graph = new Graph();
        //graph.setCalculatedEndingTask();

        taskCell.setCellValueFactory(new PropertyValueFactory<Task, String>("taskName"));
        durationCell.setCellValueFactory(new PropertyValueFactory<Task, Integer>("duration"));
        predecessorCell.setCellValueFactory(new PropertyValueFactory<Task, String>("predecessorTasks"));
       // deleteButtonCell.setCellValueFactory(new PropertyValueFactory<Task, String>("nomAtelier"));
      ///  FilteredList<Task> Results = new FilteredList<>(tasksList, b -> true);

        taskTable.setItems(tasksList);

buttonToogle.addListener((observable, oldValue, newValue) -> {

    clearTasksButton.setDisable(newValue);
    showGraphDetailsButton.setDisable(newValue);
});
    }


}
