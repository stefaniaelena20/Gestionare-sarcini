package business.logic;

import data.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import data.model.Task;

public class Utility {

    public static List<Employee> getEmployeesOver40HoursSorted(Map<Employee, List<Task>> taskMap) {
        List<Employee> result = new ArrayList<>();

        for (Employee emp : taskMap.keySet()) {
            int totalHours = 0;

            List<Task> tasks = taskMap.get(emp);
            if (tasks != null) {
                for (Task t : tasks) {
                    if ("Completed".equals(t.getStatusTask())) {
                        totalHours += t.estimateDuration();
                    }

                }
            }

            if (totalHours > 40) {
                result.add(emp);
            }
        }

        // Sortare crescătoare după nume
        Collections.sort(result, Comparator.comparing(Employee::getName));

        return result;
    }
}
