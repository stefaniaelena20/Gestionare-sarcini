package data.access;

import business.logic.TaskManagement;

import java.io.*;

public class Serialization {

    private static final String FILE_NAME = "task_data.ser";


    public static void saveData(TaskManagement taskManagement) {
        try (FileOutputStream fos = new FileOutputStream("task_data.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(taskManagement);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static TaskManagement loadData() {
        try (FileInputStream fis = new FileInputStream("task_data.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (TaskManagement) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new TaskManagement();
        }
    }
}

