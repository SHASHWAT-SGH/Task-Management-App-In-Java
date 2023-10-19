package entities;

import utils.IdGenerator;

import java.time.LocalDate;
public class Task {
    private long id;
    private String name;
    private String description;
    private LocalDate deadline;
    private int priority;
    private LocalDate dateCreated;

    public Task(){
        this.id = IdGenerator.generateId();
        this.name = null;
        this.description = null;
        this.deadline = null;
        this.priority = -1;
        this.dateCreated = LocalDate.now();
    }
    public Task(String name, String description, LocalDate deadline, int priority) {
        this.id = IdGenerator.generateId();
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.dateCreated = LocalDate.now();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

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
