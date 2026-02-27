package data.model;

import java.util.ArrayList;
import java.util.List;

public final class ComplexTask extends Task {
    private List<Task> subTasks;

    public ComplexTask(int idTask, String statusTask) {
        super(idTask, statusTask);
        this.subTasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        if (task != null) {
            subTasks.add(task);
        }
    }

    public void deleteTask(Task task) {
        if (task != null) {
            subTasks.remove(task);
        }
    }

    @Override
    public int estimateDuration() {
        int totalDuration = 0;
        for (Task subTask : subTasks) {
            totalDuration += subTask.estimateDuration();
        }
        return totalDuration;
    }

    @Override
    public String toString() {
        return "ComplexTask " + idTask + " [" + statusTask + "] " + "Subtasks: " + subTasks.size();
    }
}
