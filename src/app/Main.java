package app;
import entities.Task;
import entities.TaskManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
                This is the main executable class.
                It contains menu for all the features.
                Steps to use:
                1. run the main class (press ctrl + shift + f10 if you are using IJ)
                2. enter option number
                3. If asked for, enter the required fields
                4. Check for the result

                Note: You will abruptly be notified with a message
                      about a deadline before 6 hours.

                Note: The priority of task is higher if the number is lower
                      (Lower the number, higher the priority)

                That's it, it's simple and easy to use: Go on and try the application.

                @author: Shashwat Kumar Singh
                @date: 22/10/2023

 */

public class Main {
    public static void main(String[] args) {

        // initializing a scanner object to take input from keyboard
        Scanner scanner = new Scanner(System.in);
        // defining a custom date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
        // creating Task manager app
        TaskManager app = new TaskManager();
        // start the deadline notifier: it is a method which keeps running in background
        // and checks for deadline and notifies
        app.startDeadlineNotifier();

        // created menu
        do{
            System.out.println("\n\n\t\t ***** MENU *****");
            System.out.println("1. Add new task");
            System.out.println("2. Mark current task as completed");
            System.out.println("3. Show all tasks in priority order");
            System.out.println("4. Show tasks with deadlines grouping");
            System.out.println("5. Show tasks with today's deadline");
            System.out.println("6. Show tasks using deadline");
            System.out.println("7. Get current task");
            System.out.println("8. Get total tasks to do today");
            System.out.println("9. Exit");

            int option;
            while(true){
                try{
                    System.out.print(">>> ");
                    option = scanner.nextInt();
                    break;
                }catch (Exception e){
                    System.out.println("INVALID INPUT!! Enter only Integer.");
                    scanner.nextLine();
                }
            }

            switch (option){
                case 1:
                    // add new task
                    System.out.print("Enter task name: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();

                    LocalDate deadline;
                    while(true){
                        System.out.print("Enter deadline date (format: YYYY/MM/DD): ");
                        try{
                            deadline = LocalDate.parse(scanner.nextLine(), formatter);
                            break;
                        }catch (Exception e){
                            System.out.println("INVALID INPUT!! Enter date in the format (YYYY/MM/DD) only.");
                            scanner.nextLine();
                        }
                    }

                    LocalTime deadlineTiming;
                    while (true){
                        System.out.print("Enter Deadline timing (hr:min:sec): ");
                        try{
                            deadlineTiming = LocalTime.parse(scanner.nextLine());
                            break;
                        }catch (Exception e){
                            System.out.println("INVALID INPUT!! Enter time in the format (hr:min:sec) only.");
                            scanner.nextLine();
                        }
                    }

                    int priority;
                    while(true){
                        try{
                            System.out.print("Enter task priority: ");
                            priority = scanner.nextInt();
                            break;
                        }catch (Exception e){
                            System.out.println("INVALID INPUT!! Enter only Integer.");
                            scanner.nextLine();
                        }
                    }

                    // create task
                    Task task = new Task(name, description, deadline, priority, deadlineTiming);
                    long id = app.addTask(task);
                    if(id!=0){
                        System.out.println("Task added successfully.");
                    }else{
                        System.out.println("Unable to add task, try again!");
                    }
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
                    LocalDate date;
                    while(true){
                        System.out.print("Enter date (format: YYYY/MM/DD): ");
                        try{
                            date = LocalDate.parse(scanner.nextLine(), formatter);
                            break;
                        }catch (Exception e){
                            System.out.println("INVALID INPUT!! Enter date in the format (YYYY/MM/DD) only.");
                            scanner.nextLine();
                        }
                    }


                    tasks = app.getTasksWithDueDate(date);
                    if(tasks.size() == 0){
                        System.out.println("No task with due date "+ date);
                    }else
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
                        System.out.println("No task to do today.");
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