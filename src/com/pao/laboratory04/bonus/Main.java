package com.pao.laboratory04.bonus;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        TaskService service = TaskService.getInstance();

        System.out.println("=== Adaugare task-uri ===");

        Task t1 = service.addTask("Fix login bug", Priority.CRITICAL);
        Task t2 = service.addTask("Add dark mode", Priority.LOW);
        Task t3 = service.addTask("Update docs", Priority.MEDIUM);
        Task t4 = service.addTask("Fix memory leak", Priority.HIGH);
        Task t5 = service.addTask("Refactor DB layer", Priority.HIGH);

        System.out.println("Adaugat: " + t1);
        System.out.println("Adaugat: " + t2);
        System.out.println("Adaugat: " + t3);
        System.out.println("Adaugat: " + t4);
        System.out.println("Adaugat: " + t5);

        System.out.println("\n=== Asignare ===");

        service.assignTask("T001", "Ana");
        service.assignTask("T003", "Mihai");
        service.assignTask("T004", "Elena");

        System.out.println("T001 -> Ana");
        System.out.println("T003 -> Mihai");
        System.out.println("T004 -> Elena");

        System.out.println("\n=== Schimbari status ===");

        service.changeStatus("T001", Status.IN_PROGRESS);
        System.out.println("T001: TODO -> IN_PROGRESS OK");

        service.changeStatus("T001", Status.DONE);
        System.out.println("T001: IN_PROGRESS -> DONE OK");

        service.changeStatus("T003", Status.IN_PROGRESS);
        System.out.println("T003: TODO -> IN_PROGRESS OK");

        try {
            service.changeStatus("T001", Status.TODO);
        } catch (InvalidTransitionException e) {
            System.out.println("T001: DONE -> TODO -> InvalidTransitionException: " + e.getMessage());
        }

        System.out.println("\n=== Task-uri HIGH ===");

        List<Task> highTasks = service.getTasksByPriority(Priority.HIGH);

        for (Task task : highTasks) {
            System.out.println(task);
        }

        System.out.println("\n=== Sumar status ===");

        Map<Status, Long> summary = service.getStatusSummary();

        for (Map.Entry<Status, Long> entry : summary.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\n=== Task-uri neasignate ===");

        List<Task> unassignedTasks = service.getUnassignedTasks();

        for (Task task : unassignedTasks) {
            System.out.println(task.getId() + ": " + task.getTitle());
        }

        System.out.println("\n=== Scor urgenta (baseDays=5) ===");

        double totalScore = service.getTotalUrgencyScore(5);
        System.out.println("Total: " + totalScore);

        System.out.println("\n=== Audit Log ===");

        service.printAuditLog();

        System.out.println("\n=== Exceptii ===");

        try {
            service.findById("T999");
        } catch (TaskNotFoundException e) {
            System.out.println("TaskNotFoundException: " + e.getMessage());
        }
    }
}