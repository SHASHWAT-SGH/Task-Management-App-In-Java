package entities;

import utils.Sorting;
import utils.TaskPriorityComparator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.PriorityQueue;

/*

            Task manager is the soul of the project, it will manage all the necessary operations.
            It has a "tasks" named priority queue which will store the tasks in order of the priority.
            It also has "dateTaskMap" named Hashmap, with key as deadline and value as a priority queue of the tasks
            having deadline as the key.

            The module has all the necessary methods for task management such as:
            method to add task
            method to mark a task as complete
            method to get the list of tasks with custom due date
            method to print all the tasks in order of the priority
            method to get all the tasks having deadline of today
            method to get the task which needs to be done first
            method to get the total number of tasks to be done today

            @author: Shashwat Kumar Singh
            @Date: 22/10/2023

*/

public class TaskManager {

    // priority queue to store the tasks
     private PriorityQueue<Task> tasks = new PriorityQueue<>(new TaskPriorityComparator());

    // hash map to store tasks with deadlines
    private HashMap<LocalDate, PriorityQueue<Task>> dateTaskMap = new HashMap<>();


    // method to add task in the queue and hashmap
    // it will return the id of the task created if successful else it will return 0
    public long addTask(Task task){
        // variable which will be true if task is added to priority queue else false
        boolean success = tasks.add(task);
        // if the task is added in the priority queue then add in hash map also
        // hashmap will contain key as deadline and value as priority queue of the tasks
        if(success){
            PriorityQueue<Task> tasksFromMap;
            tasksFromMap = dateTaskMap.get(task.getDeadline());
            if (tasksFromMap == null){
                PriorityQueue<Task> tempTasks = new PriorityQueue<>(new TaskPriorityComparator());
                tempTasks.add(task);
                dateTaskMap.put(task.getDeadline(), tempTasks);
            }else{
                tasksFromMap.add(task);
                dateTaskMap.put(task.getDeadline(), tasksFromMap);
            }
            // return id of task if task is added successfully
            return task.getId();
        }

        // return 0 if task is not added successfully
        return 0;
    }

    // this method is used to dequeue from tasks
    // it will return the task dequeued if the tasks queue is not empty else
    // it will return null
    public Task markAsCompleted(){
        Task taskPolled = tasks.poll();
        if(taskPolled == null){
            return null;
        }
        PriorityQueue<Task> tasksFromMap = dateTaskMap.get(taskPolled.getDeadline());
        tasksFromMap.poll();
        dateTaskMap.put(taskPolled.getDeadline(),tasksFromMap);
        return taskPolled;
    }

    // this method returns a priority queue of task filtered with due date
    public PriorityQueue<Task> getTasksWithDueDate(LocalDate date){
        PriorityQueue<Task> unsortedTasks = dateTaskMap.get(date);
        if(unsortedTasks == null || unsortedTasks.isEmpty()){
            return null;
        }
        return Sorting.sortTaskQueue(unsortedTasks);
    }

    // this method prints the relation of due date with the tasks
    public void printTasksWithDate(){
        System.out.println(dateTaskMap);
    }

    // this method prints the sorted queue with priority
    public void printTasksInPriority(){
        if(tasks.isEmpty())
            System.out.println("There are no tasks left.");
        else{
            PriorityQueue<Task> sortedTasks= Sorting.sortTaskQueue(tasks);
            System.out.println(sortedTasks);
        }

    }

    // this method returns the queue of task having due date as current date
    public PriorityQueue<Task> getTasksWithTodayDeadline(){
        return getTasksWithDueDate(LocalDate.now());
    }

    // this method returns the current task to do as per priority
    public Task getCurrentTask(){
        return tasks.peek();
    }

    // this method returns the total count of tasks to do on the current day
    public int getTotalTasksForToday(){
        PriorityQueue<Task> temp  = dateTaskMap.get(LocalDate.now());
        if(temp == null || temp.isEmpty()){
            return 0;
        }
        return temp.size();
    }

    // Method to start the background thread for deadline notifications
    public void startDeadlineNotifier() {
        Thread notifierThread = new Thread(new DeadlineNotifier(this));
        notifierThread.start();
    }
}
