package entities;

import utils.IdGenerator;

import java.time.LocalDate;
import java.time.LocalTime;

/*
            Task is an entity of the project, it has its own fields and methods.
            Each task will contain it's unique id, and other fields such as:
            name, description, deadline, priority and date of creation.
            All these fields will be private.
            getter and setter will be available for all the fields except id.

            @author: Shashwat Kumar Singh
            @date: 22/10/2023

 */
public class Task {
    private long id; // id of each task, will be generated using a util method
    private String name; // task name
    private String description; // task description
    private LocalDate deadline; // deadline of the task
    private LocalTime deadlineTiming; // deadline time
    private int priority; // priority of task, lower the number higher the priority
    private LocalDate dateCreated; // date on which the task was created

    // default constructor
    public Task(){
        this.id = IdGenerator.generateId();
        this.name = null;
        this.description = null;
        this.deadline = null;
        this.priority = -1;
        this.dateCreated = LocalDate.now();
        this.deadlineTiming = LocalTime.parse("00:00:00");
    }

    // constructor which takes name, description, deadline and priority : automatically assigns id and dateCreated
    public Task(String name, String description, LocalDate deadline, int priority, LocalTime deadlineTiming) {
        this.id = IdGenerator.generateId(); // generate id using a util method
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.dateCreated = LocalDate.now(); // set dateCreated as current date
        this.deadlineTiming = deadlineTiming;
    }


    // getter for name
    public String getName() {
        return name;
    }

    // setter for name
    public void setName(String name) {
        this.name = name;
    }

    // getter for description
    public String getDescription() {
        return description;
    }

    // setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // getter for deadline
    public LocalDate getDeadline() {
        return deadline;
    }

    // setter for deadline
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    // getter for deadline timing
    public LocalTime getDeadlineTiming() {
        return deadlineTiming;
    }

    // setter for timing
    public void setDeadlineTiming(LocalTime deadlineTiming) {
        this.deadlineTiming = deadlineTiming;
    }

    // getter for priority
    public int getPriority() {
        return priority;
    }

    // setter for priority
    public void setPriority(int priority) {
        this.priority = priority;
    }

    // getter for creation date
    public LocalDate getDateCreated() {
        return dateCreated;
    }

    // setter for creation date
    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    // getter for task id
    public long getId() {
        return id;
    }

    // overriding toString method to style the printing of task object in informative way
    @Override
    public String toString() {
        return "Task{\n" +
                "\tname = '" + name + '\'' +
                "\n\tdescription = '" + description + '\'' +
                "\n\tdeadline = " + deadline +
                "\n\tpriority = " + priority +
                "\n\tdate created = " + dateCreated +
                "\n}";
    }
}
