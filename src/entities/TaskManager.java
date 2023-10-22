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

        boolean success = tasks.add(task);
        LocalDate currentDate = LocalDate.now();
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
            return task.getId();
        }
        return 0;
    }

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

    public PriorityQueue<Task> getTasksWithDueDate(LocalDate date){
        return dateTaskMap.get(date);
    }
    public void printTasksWithDate(){
        System.out.println(dateTaskMap);
    }

    public void printTasksInPriority(){
        PriorityQueue<Task> sortedTasks= Sorting.sortTaskQueue(tasks);
        System.out.println(sortedTasks);

    }

    public PriorityQueue<Task> getTasksWithTodayDeadline(){
        return getTasksWithDueDate(LocalDate.now());
    }

    public Task getCurrentTask(){
        return tasks.peek();
    }

    public int getTotalTasksForToday(){
        return dateTaskMap.get(LocalDate.now()).size();
    }
}
