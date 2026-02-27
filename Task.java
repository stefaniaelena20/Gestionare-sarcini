package data.model;

import java.io.Serializable;

public sealed abstract class Task implements Serializable permits SimpleTask, ComplexTask {
    private static final long serialVersionUID = 1L;
    protected int idTask;
    protected String statusTask;

    public Task(int idTask, String statusTask) {
        this.idTask = idTask;
        this.statusTask = statusTask;
    }

    public abstract int estimateDuration();

    public int getIdTask() {

        return idTask;
    }

    public String getStatusTask() {

        return statusTask;
    }

    public void setStatusTask(String statusTask) {
        if (statusTask != null && !statusTask.isEmpty()) {
            this.statusTask = statusTask;
        }
    }

    @Override
    public String toString() {

        return "Task" + idTask + " [" + statusTask + "] ";
    }
}
