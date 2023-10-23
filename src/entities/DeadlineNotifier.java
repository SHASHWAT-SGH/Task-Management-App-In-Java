package entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/*

            This class is defined to keep track of deadlines
            of the tasks.
            This is used to print the task details when the deadline is near

            @author: Shashwat Kumar Singh
            @date: 22/10/2023

 */

// this class implements runnable interface to make it a thread
public class DeadlineNotifier implements Runnable {

    private final TaskManager taskManager; // Store the reference to TaskManager

    public DeadlineNotifier(TaskManager taskManager) {
        this.taskManager = taskManager; // Store the reference
    }
    @Override
    public void run() {
        while (true) {
                Task task = taskManager.getCurrentTask();
                if (task != null) {
                    LocalDate todaysDate = LocalDate.now();
                    LocalDate deadlineDate = task.getDeadline();
                    if(!todaysDate.equals(deadlineDate))
                        continue;
                    LocalTime currentTime = LocalTime.now();
                    LocalTime timeUntilDeadline = task.getDeadlineTiming();
                    Duration timeRemaining = Duration.between(currentTime, timeUntilDeadline);

                    // Check if the deadline is within 30 minutes
                    if (timeRemaining.toMinutes() % 60 < 30 ) {
                        System.out.println("Upcoming deadline in 30 minutes: " + task);
                    }
                }

            try {
                // Sleep for a while before checking again
                Thread.sleep(60000); // Adjust the interval as needed current is : 1 minute
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
