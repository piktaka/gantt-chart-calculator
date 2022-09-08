package basicClasses;

import java.util.ArrayList;

public class Task {

    Integer taskId;
    String taskName;
   ArrayList <Task> predecessorTasks;
    Integer duration, startDate,endDate,freeMargin;

    Task predecessorTask;

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }
    public void setCalculatedEndDate() {
        Integer bigDuration=0;
        for (Task task :this.predecessorTasks ){

            if (task.getEndDate() > bigDuration) {
                bigDuration = task.getEndDate();

            }

        }
        this.endDate = bigDuration+this.duration;

    }

    public Integer getFreeMargin() {
        return freeMargin;
    }

    public void setFreeMargin(Integer freeMargin) {
        this.freeMargin = freeMargin;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getPredecessorTasks() {
        String taskNames="";
       // System.out.println("where in task "+this.taskName+ " and this is the tasks "+this.predecessorTasks);
        if(this.predecessorTasks.size()==0)
            return "None";
  for(Task task : this.predecessorTasks){
     // System.out.println("this is a teesst");
      taskNames+=task.getTaskName()+", ";

  }

        return taskNames.substring(0,taskNames.length()-2);
    }

    public void setPredecessorTasks(ArrayList<Task> predecessorTasks) {
        this.predecessorTasks = new ArrayList<>(predecessorTasks);

    }
    public void addTask(Task predecessorTask) {
      /*  Boolean broken=false;
        for (Task task :this.predecessorTasks ){

            if (predecessorTask.getEndDate() < task.getEndDate()) {
             broken=true;
                break;
            }*/


     //   }
// here we are comparing the task that we've added to the predecessor
        // inorder to check if it has the big endDate


        //    System.out.println("we are at "+taskName+" and itis the case");

        if(predecessorTasks.isEmpty()){
            System.out.println("we are at "+taskName+" and itis the case");
            this.endDate=duration + predecessorTask.getEndDate();
            this.startDate= predecessorTask.getEndDate();
            return;
        }

if (this.startDate < predecessorTask.getEndDate())
    this.endDate=duration + predecessorTask.getEndDate();

        this.predecessorTasks.add(predecessorTask);

    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Task getPredecessorTask() {
        return predecessorTask;
    }

    public void setPredecessorTask(Task predecessorTask) {
        this.predecessorTask = predecessorTask;
    }

    public Task(String taskName, ArrayList<Task> predecessorTasks, Integer duration) {
        this.taskName = taskName;
        this.predecessorTasks = new ArrayList<>();
        if (predecessorTasks != null) {

            this.predecessorTasks.addAll(predecessorTasks);

        }
        else System.out.println("we are here "+taskName);
        this.duration = duration;
        // here we are searching for the task that has the big EndDate fro the calculating
        // the end date of this task
        Integer bigDuration=0;
        for (Task task :this.predecessorTasks ){

            if (task.getEndDate() > bigDuration)
                bigDuration=task.getEndDate();

        }
        // it will start from the ending of the predecessor task immediately
        this.startDate=bigDuration;
        this.endDate = bigDuration+this.duration;
//System.out.println("final date of "+this.taskName +" is :  "+this.endDate );
    }
    public Task(String taskName, ArrayList<Task> predecessorTasks, Integer duration , Integer id) {
        this.taskName = taskName;
        this.taskId=id;
        this.predecessorTasks = new ArrayList<>();
        this.predecessorTask=new Task();
        if (predecessorTasks != null) {

            this.predecessorTasks.addAll(predecessorTasks);

        }
        else System.out.println("we are here "+taskName);
        this.duration = duration;
        // here we are searching for the task that has the big EndDate fro the calculating
        // the end date of this task
        Integer bigDuration=0;
        for (Task task :this.predecessorTasks ){

            if (task.getEndDate() > bigDuration) {
                bigDuration = task.getEndDate();
                this.predecessorTask.setTask(task);
            }

        }
        // it will start from the ending of the predecessor task immediately
        this.startDate=bigDuration;
        this.endDate = bigDuration+this.duration;
//System.out.println("final date of "+this.taskName +" is :  "+this.endDate );
    }
    public Task(String taskName, Task predecessorTask, Integer duration) {
        this.taskName = taskName;
        this.predecessorTasks = new ArrayList<>();

        this.duration = duration;
        // here we make a sum of the predecessor task endDate directly
        // because this is constrictor
        if(predecessorTask==null){
            this.endDate=this.duration;
            this.startDate=0;
            return;

        }
        this.predecessorTasks.add(predecessorTask);

        this.endDate=this.duration+predecessorTask.getEndDate();
this.startDate=predecessorTask.getEndDate();
    }
    public Task(){
        // this constrictor for not make the task null
        //this.predecessorTasks = new ArrayList<>();

    }
    public Task(Task task){
        // this constrictor for not make the task null
        //this.predecessorTasks = new ArrayList<>();
        this.taskName = task.getTaskName();
        if(this.predecessorTasks==null)
            this.predecessorTasks = new ArrayList<>();
        this.predecessorTasks.clear();
        this.predecessorTasks.addAll(task.getPredecessorArray());
        this.duration = task.getDuration();
        this.endDate=task.getEndDate();
        this.taskId=task.getTaskId();
        this.startDate = task.getStartDate();
        if(task.getPredecessorTask()!=null)
        this.predecessorTask=task.getPredecessorTask();
        else
            this.predecessorTask=null;

    }
ArrayList<Task> getPredecessorArray(){
        return this.predecessorTasks;
}
void setTask(Task task){
    this.taskName = task.getTaskName();
    if(this.predecessorTasks==null)
    this.predecessorTasks = new ArrayList<>();
    this.predecessorTasks.clear();
    this.predecessorTasks.addAll(task.getPredecessorArray());
    this.duration = task.getDuration();
    this.endDate=task.getEndDate();
    this.taskId=task.getTaskId();
    this.startDate = task.getStartDate();




}
    @Override
    public String toString() {
        return "Task{" +
                "'" + taskName + '\'' +
                '}';
    }
}
