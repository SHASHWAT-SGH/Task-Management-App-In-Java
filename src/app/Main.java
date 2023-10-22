package app;
import entities.Task;
import entities.TaskManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
        TaskManager app = new TaskManager();

        do{
            System.out.println("\n\n\t\t ***** MENU *****");
            System.out.println("1. Add new task");
            System.out.println("2. Mark task as completed");
            System.out.println("3. Show all tasks in priority order");
            System.out.println("4. Show tasks with deadlines grouping");
            System.out.println("5. Show tasks with today's deadline");
            System.out.println("6. Show tasks using deadline");
            System.out.println("7. Get current task");
            System.out.println("8. Get total tasks to do today");
            System.out.println("9. Exit");

            System.out.print(">>> ");
            int option = scanner.nextInt();

            switch (option){
                case 1:
                    // add new task
                    System.out.print("Enter task name: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter deadline date (format: YYYY/MM/DD): ");
                    LocalDate deadline = LocalDate.parse(scanner.nextLine(), formatter);
                    System.out.print("Enter task priority: ");
                    int priority = scanner.nextInt();

                    // create task
                    Task task = new Task(name, description, deadline, priority);
                    app.addTask(task);
                    break;

                case 2:
                    // Mark task as completed
                    Task completedTask = app.markAsCompleted();
                    if(completedTask == null){
                        System.out.println("ALL TASKS ARE ALREADY COMPLETED, NOTHING TO COMPLETE!");
                    }else{
                        System.out.println("This task is marked completed: "+completedTask);

                    }
                    break;

                case 3:
                    // Show all tasks in priority order
                    app.printTasksInPriority();
                    break;

                case 4:
                    // Show tasks with deadlines grouping
                    app.printTasksWithDate();
                    break;

                case 5:
                    // Show tasks with today's deadline
                    PriorityQueue<Task> tasks = app.getTasksWithTodayDeadline();
                    System.out.println(tasks);
                    break;

                case 6:
                    // Show task using deadline
                    scanner.nextLine();
                    System.out.print("Enter date (format: YYYY/MM/DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);
                    tasks = app.getTasksWithDueDate(date);
                    System.out.println(tasks);
                    break;

                case 7:
                    // Get current task
                    task = app.getCurrentTask();
                    if(task == null){
                        System.out.println("No Task to do!");
                    }else{
                        System.out.println(task);
                    }
                    break;

                case 8:
                    // Get total tasks to do today
                    int n = app.getTotalTasksForToday();
                    if(n!=0){
                        System.out.println("Task count for today: "+ n);
                    }else{
                        System.out.println("No task to do today : 0");
                    }

                    break;


                case 9:
                    System.exit(0);

                default:
                    System.out.println("Invalid Input!");
            }
        }while(true);

    }
}