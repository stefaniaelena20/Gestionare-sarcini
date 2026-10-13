# ✅ Task Management System

A desktop application for managing employees and their tasks. Built with **Java** and **Java Swing**, it provides a graphical interface for creating tasks, assigning them to employees, tracking completion, viewing work statistics, and saving data between sessions.

<p align="center">
  <img src="https://img.shields.io/badge/Java-23-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 23">
  <img src="https://img.shields.io/badge/GUI-Java%20Swing-6A5ACD?style=for-the-badge" alt="Java Swing">
  <img src="https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
</p>

## ✨ Features

- **Employee management** — add employees to the application.
- **Simple tasks** — create tasks with a name and estimated start and end hours.
- **Complex tasks** — group simple tasks and nested tasks together.
- **Task assignment** — assign tasks to employees.
- **Status tracking** — mark assigned tasks as completed or uncompleted.
- **Work statistics** — view the completed work hours for each employee.
- **Overtime overview** — find employees with more than 40 completed work hours.
- **Save and load** — persist application data using Java serialization.

## 🧰 Built With

- **Java 23**
- **Java Swing** — graphical user interface
- **Maven** — project configuration and build management
- **Java Object Serialization** — saving and loading application data

## 🖥️ Application Actions

| Action | Description |
|---|---|
| **Add Employee** | Add an employee to the system |
| **Add Task** | Create a simple task or a complex task with subtasks |
| **Assign Task** | Assign a task to an employee |
| **View Stats** | See each employee’s completed work hours |
| **Set Task Status** | Mark an assigned task as completed or uncompleted |
| **>40 Hours** | Display employees with more than 40 completed work hours |
| **Save** | Save the current data to a file |
| **Load** | Restore previously saved data |

## 🧩 Task Types

### Simple Task

A simple task stores a name, a status, and a start and end hour. Its estimated duration is calculated from those hours.

### Complex Task

A complex task can contain multiple subtasks, including other complex tasks. Its estimated duration is calculated by adding the durations of its subtasks.

## 🚀 Getting Started

### Requirements

- **JDK 23**
- **IntelliJ IDEA** or another Java IDE with Maven support

### Run the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/stefaniaelena20/Gestionare-sarcini.git
   cd Gestionare-sarcini
