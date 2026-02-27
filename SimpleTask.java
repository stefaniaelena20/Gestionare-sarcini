package data.model;

public non-sealed class SimpleTask extends Task {
    private int startHour;
    private int endHour;

    public SimpleTask(int idTask, String statusTask, int startHour, int endHour) {
        super(idTask, statusTask);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    @Override
    public int estimateDuration() {
        if (endHour > startHour) {
            return endHour - startHour;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "SimpleTask " + idTask + " [" + statusTask + "] - " + estimateDuration() + "h";
    }

    public void setName(String subTaskName) {
    }
}
