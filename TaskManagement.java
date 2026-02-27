package business.logic;

import data.model.Employee;
import data.model.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManagement implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<Employee, List<Task>> taskMap = new HashMap<>();

    public void assignTaskToEmployee(Employee employee, Task task) {
        if (!taskMap.containsKey(employee)) {
            taskMap.put(employee, new ArrayList<>());
            //caut ce e put if absent pe map
        }
        taskMap.get(employee).add(task);
    }

    public int calculateEmployeeWorkDuration(Employee employee) {
        int totalDuration = 0;
        List<Task> tasks = taskMap.get(employee);

        if (tasks != null) {
            for (Task task : tasks) {
                if ("Completed".equals(task.getStatusTask())) {
                    totalDuration += task.estimateDuration();
                }
            }
        }

        return totalDuration;
    }

    public void modifyTaskStatus(Employee employee, int taskId, String newStatus) {
        List<Task> tasks = taskMap.get(employee);

        if (tasks != null) {
            for (Task task : tasks) {
                if (task.getIdTask() == taskId) {
                    task.setStatusTask(newStatus);
                    break;
                }
            }
        }
    }

    public Map<Employee, List<Task>> getTaskMap() {
        return taskMap;
    }

    @Override
    public String toString() {
        return "TaskManagement\n" + getTaskMap();
    }
}

//pachetele fara _, sa le pun cu .~~~~~

//caut ce e put if absent pe map=este o metodă utilă care verifică dacă
//o cheie NU există în Map. Dacă lipsește, adaugă cheia cu o
//valoare generată. Dacă există deja, nu face nimic.
//~~~~

//sa fac utility si angajati
// peste 40 de ore
//sa schimb legatura pentru gui si serializatioon