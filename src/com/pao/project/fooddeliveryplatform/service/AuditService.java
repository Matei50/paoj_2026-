package com.pao.project.fooddeliveryplatform.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.ReentrantLock;

public class AuditService {
    private static AuditService instance;
    private final String fileName = "audit.csv";
    private final ReentrantLock lock = new ReentrantLock();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private AuditService() {
        try (FileWriter fw = new FileWriter(fileName, true)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    public void logAction(String actionName) {
        lock.lock();
        try (FileWriter fw = new FileWriter(fileName, true)) {
            String timestamp = LocalDateTime.now().format(formatter);
            fw.write(actionName + "," + timestamp + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
