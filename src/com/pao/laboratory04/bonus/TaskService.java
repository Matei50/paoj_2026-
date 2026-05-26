package com.pao.laboratory04.bonus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskService {
    private static TaskService instance;

    private final Map<String, Task> tasksById;
    private final Map<Priority, List<Task>> tasksByPriority;
    private final List <String> auditLog;

    private int nextId;

    private TaskService() {
        this.tasksById = new HashMap<>();
        this.tasksByPriority = new HashMap<>();
        this.auditLog = new ArrayList<>();
        this.nextId = 1;

        for (Priority priority : Priority.values()) {
            tasksByPriority.put(priority, new ArrayList<>());
        }
    }

    public static TaskService getInstance() {
        if (instance == null ){
            instance = new TaskService();
        }
        return instance;
    }

    public Task addTask(String title, Priority priority) {
        String id = String.format("T%03d", nextId++);

        if (tasksById.containsKey(id)) {
            throw new DuplicateTaskException("Task-ul " + id + " exista deja");
        }

        Task task = new Task(id, title, priority);

        tasksById.put(id, task);
        tasksByPriority.get(priority).add(task);

        auditLog.add("[ADD] " + task.getId() + ": '" + task.getTitle() + "' (" + task.getPriority() + ")");

        return task;
    }

    public Task findById(String taskId) {
        Task task = tasksById.get(taskId);

        if(task == null) {
            throw new TaskNotFoundException("Task-ul " + taskId + " nu a fost gasit");
        }
        return task;
    }

    public void assignTask(String taskId, String assignee) {
        Task task = findById(taskId);

        task.setAssignee(assignee);

        auditLog.add("[ASSIGN] " + taskId + " -> " + assignee);
    }

    public void changeStatus(String taskId, Status newStatus) {
        Task task = findById(taskId);

        Status oldStatus = task.getStatus();

        if(!oldStatus.canTransitionTo(newStatus)) {
            throw new InvalidTransitionException(oldStatus, newStatus);
        }

        task.setStatus(newStatus);

        auditLog.add("[STATUS] " + taskId + ": " + oldStatus + " -> " + newStatus);
    }

    public List<Task> getTasksByPriority(Priority priority) {
        return tasksByPriority.getOrDefault(priority, new ArrayList<>());
    }

    public Map<Status, Long> getStatusSummary() {
        Map<Status, Long> summary = new HashMap<>();

        for (Status status : Status.values()) {
            long count = 0;

            for (Task task : tasksById.values()) {
                if (task.getStatus() == status)
                    count ++;
            }
            summary.put(status, count);
        }
        return summary;
    }

    public List<Task> getUnassignedTasks() {
        List <Task> result = new ArrayList<>();

        for (Task task : tasksById.values()) {
            if (task.getAssignee() == null)
                result.add(task);
        }
        return result;
    }

    public double getTotalUrgencyScore(int baseDays) {
        double total = 0;

        for (Task task : tasksById.values()) {
            if (task.getStatus() != Status.DONE && task.getStatus() != Status.CANCELLED) {
                total += task.getPriority().calculateScore(baseDays);
            }
        }
        return total;
    }

    public void printAuditLog() {
        for (String log : auditLog) {
            System.out.println(log);
        }
    }
}
