package utils;
import entities.Task;

import java.util.PriorityQueue;

/*

            Sorting is a utility class for the project, it contains a static method which will
            create a deep copy of the priority queue and then sort the queue and return the sorted queue.
            Note: it will not change the original queue

            @author: Shashwat Kumar Singh
            @Date: 22/10/2023

 */



public class Sorting {
    // method used to sort the priority queue in order to print in priority order
    public static PriorityQueue<Task> sortTaskQueue(PriorityQueue<Task> unsortedTasks) {
        // create a new priority queue to store sorted queue
        PriorityQueue<Task> sortedTasks = new PriorityQueue<>(new TaskPriorityComparator());
        // create a deep copy of the main queue i.e. unsortedQueue
        PriorityQueue<Task> temp = new PriorityQueue<>(unsortedTasks);
        // remove and add all the elements of the temporary queue in the sortedQueue
        while (!temp.isEmpty()){
            // removing will dequeue the queue in the priority order and enqueue will be hence done in the
            // order of priority
            // there for the queue will be sorted
            sortedTasks.add(temp.poll());
        }
        // return the sorted queue
        return sortedTasks;
    }
}