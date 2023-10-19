package app;
import entities.Task;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Task task = new Task("Fill Water bottel", "I need to fill water bottel before the deadline",LocalDate.now() , 1);
        System.out.println(task);

    }
}