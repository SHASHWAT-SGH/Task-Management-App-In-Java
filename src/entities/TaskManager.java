package entities;

import utils.TaskPriorityComparator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TaskManager {
    private PriorityQueue<Task> allTasks = new PriorityQueue<>(new TaskPriorityComparator());
    private PriorityQueue<Task> oneDayTasks = new PriorityQueue<>(new TaskPriorityComparator());
    private HashMap<LocalDate, PriorityQueue<Task>> dateTaskMap = new HashMap<>();

    public int addTask(){
        return 0;
    }

}
