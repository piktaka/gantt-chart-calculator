package basicClasses;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Graph {
    ArrayList <Task> allTasks;

    ArrayList <Task> criticalPath;
    ArrayList<Task>freeMargin;
    Task startedTask;
    Task endingTask ;

    public Graph(Task startedTask, Task endingTask) {
        this.startedTask = startedTask;
        this.endingTask = endingTask;
    }

    public Graph(ArrayList<Task> allTasks) {
        this.allTasks = new ArrayList<>(allTasks);
        this.criticalPath = new ArrayList<>();
        this.freeMargin = new ArrayList<>();
    }

    public Graph() {
        this.allTasks = new ArrayList<>();
        this. criticalPath = new ArrayList<>();
        this.freeMargin = new ArrayList<>();
    }

    public void setCalculatedEndingTask(){
        Integer endingDate = 0;
        Integer taskIndex=0;
        for (Task task : allTasks){
            if (task.getEndDate() > endingDate) {
                endingDate = task.getEndDate();
            taskIndex = task.getTaskId();
            }

        }
        this.endingTask= new Task();
endingTask.setTask(allTasks.get(taskIndex));

//System.out.println("the ending task of the graph is "+endingTask.getTaskName()+" and this is the endDate "+endingTask.getEndDate());

    }


    public void clearGraphsTask(){
        this.criticalPath.clear();
        this.allTasks.clear();
        this.endingTask=null;
        this.startedTask= null;
        this.freeMargin.clear();
    }
  public  void addTask(Task task){
      //  if (allTasks == null)
         //   allTasks = new ArrayList<>();
      //  System.out.println("----- bed add task");

      if(allTasks.isEmpty()) {
    //      System.out.println("we are here ");
          this.endingTask = new Task(task);
      //    System.out.println("init ending task "+this.endingTask.getTaskName());
          allTasks.add(task);
          return;
      }
    if (task.getEndDate() > this.endingTask.getEndDate() ) {
          endingTask.setTask(task);
          System.out.println("the ending task of the graph is "+endingTask.getTaskName()+" and this is the endDate "+endingTask.getEndDate());

      }
      allTasks.add(task);

    }

   public void checkEndingDate(Task task) {
        if (task.getEndDate() > this.endingTask.getEndDate() )
            endingTask.setTask(task);
    }

    public ArrayList<Task> getCriticalPath(){

this.criticalPath.clear();

     //System.out.println("all tasks test "+this.allTasks);
          //   System.out.println("this is the id "+ this.endingTask.getTaskId());

        Task endingTaskPath = this.allTasks.get(this.endingTask.getTaskId());
        this.criticalPath.add(endingTaskPath);

        while(true){
           // System.out.println("this is critical tasks "+this.criticalPath);
            for(Task previousTask:endingTaskPath.getPredecessorArray()){

                if(previousTask.getEndDate().equals(endingTaskPath.getStartDate())) {

                    this.criticalPath.add(previousTask);
                    endingTaskPath =previousTask;

break;
                }

            }

            if(endingTaskPath.getStartDate().equals(0)){
                break;
            }
        }
        System.out.println("critical path array "+this.criticalPath);

        Collections.reverse(this.criticalPath);

return this.criticalPath;
    }


   public ArrayList <Task > getFreeMargin(){

Task copyTask ;
        for (Task task : this.allTasks){
            System.out.println("for task : "+task.getTaskName());
            System.out.println("-------------");
            System.out.println("");
            System.out.println("task start date : "+task.getStartDate());

            System.out.println("");

            for (Task predecessorTask : task.getPredecessorArray()){
    copyTask=predecessorTask;
    copyTask.setFreeMargin( task.getStartDate()-predecessorTask.getEndDate());
System.out.println("this is copy task : "+copyTask.getTaskName()+" and the end date "+copyTask.getEndDate());
                System.out.println("-------------");
                System.out.println("");

    if(copyTask.getEndDate() < task.getStartDate()) {

    this.freeMargin.add(copyTask);
        System.out.println("new free margin task "+copyTask.getTaskName());
        System.out.println("-------------");
        System.out.println("");
        System.out.println("");

}
else {
    ;
        System.out.println("removed  free margin task "+copyTask.getTaskName()+" is removed : "+this.freeMargin.remove(copyTask));
        System.out.println("-------------");
        System.out.println("");
        System.out.println("");


    }

}
//System.out.println("and this is the free margin : "+this.freeMargin.get(0));


        }

       System.out.println("this is freeMarginTasks "+this.freeMargin);
       System.out.println("this is the free margin duration  "+this.freeMargin.get(0).getFreeMargin());

        return  this.freeMargin;
    }
}
