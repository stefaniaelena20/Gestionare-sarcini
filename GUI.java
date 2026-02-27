package gui;

import business.logic.TaskManagement;
import business.logic.Utility;
import data.access.Serialization;
import data.model.ComplexTask;
import data.model.Employee;
import data.model.SimpleTask;
import data.model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static business.logic.Utility.getEmployeesOver40HoursSorted;

public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<Employee> employees;
    private List<Task> tasks;
    private TaskManagement taskManagement;

    public GUI() {
        taskManagement = Serialization.loadData();
        employees = new ArrayList<>(taskManagement.getTaskMap().keySet());
        tasks = new ArrayList<>();
        for (List<Task> tList : taskManagement.getTaskMap().values()) {
            tasks.addAll(tList);
        }

        setTitle("Task Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBackground(new Color(240, 200, 220));

        JButton addEmployeeBtn = createButton("Add Employee");
        JButton addTaskBtn = createButton("Add Task");
        JButton assignTaskBtn = createButton("Assign Task");
        JButton viewStatsBtn = createButton("View Stats");
        JButton serializeBtn = createButton("Save");
        JButton deserializeBtn = createButton("Load");
        JButton completeTaskBtn = createButton("Set Task Status");
        JButton utilityBtn = createButton(">40 Hours");

        Font font = new Font("Comic Sans MS", Font.BOLD, 24);
        addEmployeeBtn.setFont(font);
        addTaskBtn.setFont(font);
        assignTaskBtn.setFont(font);
        viewStatsBtn.setFont(font);
        serializeBtn.setFont(font);
        deserializeBtn.setFont(font);
        completeTaskBtn.setFont(font);
        utilityBtn.setFont(font);

        panel.add(addEmployeeBtn);
        panel.add(addTaskBtn);
        panel.add(assignTaskBtn);
        panel.add(viewStatsBtn);
        panel.add(serializeBtn);
        panel.add(deserializeBtn);
        panel.add(completeTaskBtn);
        panel.add(utilityBtn);

        add(panel);

        addEmployeeBtn.addActionListener(e -> addEmployee());
        addTaskBtn.addActionListener(e -> addTask());
        assignTaskBtn.addActionListener(e -> assignTask());
        viewStatsBtn.addActionListener(e -> viewStats());
        serializeBtn.addActionListener(e -> makeSerialization());
        deserializeBtn.addActionListener(e -> makeDeserialization());
        completeTaskBtn.addActionListener(e -> markTaskStatus());
        utilityBtn.addActionListener(e -> utilityMethod());

    }


    private void makeSerialization() {
        Serialization.saveData(taskManagement);
        JOptionPane.showMessageDialog(this, "Data were saved!", "Serialization", JOptionPane.INFORMATION_MESSAGE);
    }

    private void utilityMethod() {
        List<Employee> over40Employees = Utility.getEmployeesOver40HoursSorted(taskManagement.getTaskMap());

        if (over40Employees.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No employees with more than 40 hours worked.", "Result", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder result = new StringBuilder("Employees with more than 40 hours worked:\n\n");
        for (Employee emp : over40Employees) {
            int totalHours = taskManagement.calculateEmployeeWorkDuration(emp);
            result.append("- ").append(emp.getName()).append(" (").append(totalHours).append(" hours)\n");
        }

        JOptionPane.showMessageDialog(this, result.toString(), "Result", JOptionPane.INFORMATION_MESSAGE);
    }


    private void makeDeserialization() {
        taskManagement = Serialization.loadData();
        employees.clear();
        tasks.clear();
        for (Map.Entry<Employee, List<Task>> entry : taskManagement.getTaskMap().entrySet()) {

            employees.add(entry.getKey());
            tasks.addAll(entry.getValue());
        }


        StringBuilder info = new StringBuilder("Data loaded:\n\n");
        for (Employee emp : employees) {
            info.append("- ").append(emp.getName()).append(": ");
            List<Task> empTasks = taskManagement.getTaskMap().get(emp);
            if (empTasks != null && !empTasks.isEmpty()) {
                info.append(empTasks.size()).append(" task(s)\n");
                for (Task t : empTasks) {
                    info.append("    • ").append(t.toString()).append("\n");
                }
            } else {
                info.append("0 tasks\n");
            }
        }

        JOptionPane.showMessageDialog(this, info.toString(), "Load completed", JOptionPane.INFORMATION_MESSAGE);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(255, 105, 180));
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setPreferredSize(new Dimension(250, 100));
        return button;
    }


    private void assignTask() {
        if (employees.isEmpty() || tasks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No employees or tasks available!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Employee selectedEmployee = (Employee) JOptionPane.showInputDialog(this, "Select Employee:",
                "Assign Task", JOptionPane.QUESTION_MESSAGE, null, employees.toArray(), employees.get(0));

        Task selectedTask = (Task) JOptionPane.showInputDialog(this, "Select Task:",
                "Assign Task", JOptionPane.QUESTION_MESSAGE, null, tasks.toArray(), tasks.get(0));

        if (selectedEmployee != null && selectedTask != null) {
            taskManagement.assignTaskToEmployee(selectedEmployee, selectedTask);
            JOptionPane.showMessageDialog(this, "Task assigned to " + selectedEmployee.getName() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void markTaskStatus() {
        if (employees.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No employees available!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Employee selectedEmployee = (Employee) JOptionPane.showInputDialog(this, "Select Employee:",
                "Set Task Status", JOptionPane.QUESTION_MESSAGE, null, employees.toArray(), employees.get(0));

        if (selectedEmployee != null) {
            List<Task> assignedTasks = taskManagement.getTaskMap().get(selectedEmployee);
            if (assignedTasks == null || assignedTasks.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selected employee has no tasks!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Task selectedTask = (Task) JOptionPane.showInputDialog(this, "Select Task:",
                    "Set Task Status", JOptionPane.QUESTION_MESSAGE, null, assignedTasks.toArray(), assignedTasks.get(0));

            if (selectedTask != null) {
                String[] statusOptions = {"Completed", "Uncompleted"};
                String newStatus = (String) JOptionPane.showInputDialog(this, "Select New Status:",
                        "Task Status", JOptionPane.QUESTION_MESSAGE, null, statusOptions, selectedTask.getStatusTask());

                if (newStatus != null) {
                    selectedTask.setStatusTask(newStatus);
                    JOptionPane.showMessageDialog(this, "Task status updated to " + newStatus + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void viewStats() {
        if (employees.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No employees available!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder stats = new StringBuilder("<html><h2>Task Stats</h2>");
        for (Employee emp : employees) {
            stats.append("<p><b>").append(emp.getName()).append(":</b> ")
                    .append(taskManagement.calculateEmployeeWorkDuration(emp))
                    .append(" hours worked</p>");
        }
        stats.append("</html>");

        JOptionPane.showMessageDialog(this, stats.toString(), "Statistics", JOptionPane.INFORMATION_MESSAGE);
    }


    private void addEmployee() {
        String name = JOptionPane.showInputDialog(this, "Enter Employee Name:");
        if (name != null && !name.isEmpty()) {
            Employee emp = new Employee(employees.size() + 1, name);
            employees.add(emp);
            JOptionPane.showMessageDialog(this, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void addTask() {
        String[] options = {"Simple Task", "Complex Task"};
        int choice = JOptionPane.showOptionDialog(this, "Select task type:",
                "Add Task", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        if (choice == 0) {
            addSimpleTaskToMainList();
        } else if (choice == 1) {
            ComplexTask complexTask = createComplexTaskRecursive();
            tasks.add(complexTask);
            JOptionPane.showMessageDialog(this, "Complex Task added!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void addSimpleTaskToMainList() {
        String taskName = JOptionPane.showInputDialog(this, "Enter Task Name:");
        if (taskName == null || taskName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String startHourStr = JOptionPane.showInputDialog(this, "Enter Start Hour:");
        String endHourStr = JOptionPane.showInputDialog(this, "Enter End Hour:");

        if (startHourStr != null && endHourStr != null) {
            try {
                int startHour = Integer.parseInt(startHourStr);
                int endHour = Integer.parseInt(endHourStr);
                if (endHour > startHour) {
                    SimpleTask task = new SimpleTask(tasks.size() + 1, "Uncompleted", startHour, endHour);
                    task.setName(taskName);
                    tasks.add(task);
                    JOptionPane.showMessageDialog(this, "Simple Task added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "End hour must be greater than start hour.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private ComplexTask createComplexTaskRecursive() {
        ComplexTask complexTask = new ComplexTask(tasks.size() + 1, "Uncompleted");

        while (true) {
            int subtaskChoice = JOptionPane.showConfirmDialog(this,
                    "Do you want to add a subtask?",
                    "Add Subtask", JOptionPane.YES_NO_OPTION);

            if (subtaskChoice != JOptionPane.YES_OPTION) {
                break;
            }

            String[] subTaskOptions = {"Simple Task", "Complex Task"};
            int taskType = JOptionPane.showOptionDialog(this, "Select subtask type:",
                    "Subtask Type", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, subTaskOptions, subTaskOptions[0]);

            if (taskType == 0) {
                SimpleTask subTask = createSimpleSubTask();
                if (subTask != null) {
                    complexTask.addTask(subTask);
                }
            } else if (taskType == 1) {
                ComplexTask nestedComplex = createComplexTaskRecursive();
                complexTask.addTask(nestedComplex);
            }
        }

        return complexTask;
    }

    private SimpleTask createSimpleSubTask() {
        String subTaskName = JOptionPane.showInputDialog(this, "Enter Subtask Name:");
        if (subTaskName == null || subTaskName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Subtask name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String subTaskStartStr = JOptionPane.showInputDialog(this, "Enter Start Hour for Subtask:");
        String subTaskEndStr = JOptionPane.showInputDialog(this, "Enter End Hour for Subtask:");

        if (subTaskStartStr != null && subTaskEndStr != null) {
            try {
                int subStart = Integer.parseInt(subTaskStartStr);
                int subEnd = Integer.parseInt(subTaskEndStr);
                if (subEnd > subStart) {
                    SimpleTask subTask = new SimpleTask(tasks.size() + 1, "Uncompleted", subStart, subEnd);
                    subTask.setName(subTaskName);
                    return subTask;
                } else {
                    JOptionPane.showMessageDialog(this, "End hour must be greater than start hour.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI gui = new GUI();
                gui.setVisible(true);
            }
        });
    }

}
