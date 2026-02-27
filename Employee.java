package data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idEmployee;
    private String name;
    private List<Task> tasks;

    public Employee(int idEmployee, String name) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public String getName() {
        return name;
    }

    public void assignTask(Task task)
    {
        if (task != null) {
            tasks.add(task);
        }
    }

    @Override
    public String toString()
    {
        return name;
    }
}
