package com.pao.laboratory05.audit;

import java.time.LocalDateTime;
import java.util.Arrays;

public class AngajatService {
    private Angajat[] angajati = new Angajat[0];
    private AuditEntry[] auditLog = new AuditEntry[0];

    private AngajatService() {
    }

    private static class Holder {
        private static final AngajatService INSTANCE = new AngajatService();
    }

    public static AngajatService getInstance() {
        return Holder.INSTANCE;
    }

    private void logAction(String action, String target) {
        AuditEntry entry = new AuditEntry(action, target, LocalDateTime.now().toString());

        AuditEntry[] temp = new AuditEntry[auditLog.length + 1];
        System.arraycopy(auditLog, 0, temp, 0, auditLog.length);
        temp[temp.length - 1] = entry;
        auditLog = temp;
    }

    public void addAngajat(Angajat a) {
        Angajat[] temp = new Angajat[angajati.length + 1];
        System.arraycopy(angajati, 0, temp, 0, angajati.length);
        temp[temp.length - 1] = a;
        angajati = temp;

        System.out.println("Angajat adăugat: " + a.getNume());
        logAction("ADD", a.getNume());
    }

    public void printAll() {
        if (angajati.length == 0) {
            System.out.println("Nu există angajați.");
            return;
        }

        for (Angajat angajat : angajati) {
            System.out.println(angajat);
        }
    }

    public void listBySalary() {
        if (angajati.length == 0) {
            System.out.println("Nu există angajați.");
            return;
        }

        Angajat[] copy = angajati.clone();
        Arrays.sort(copy);

        System.out.println("--- Angajați după salariu (descrescător) ---");
        for (int i = 0; i < copy.length; i++) {
            System.out.println((i + 1) + ". " + copy[i]);
        }
    }

    public void findByDepartament(String numeDept) {
        logAction("FIND_BY_DEPT", numeDept);

        boolean found = false;
        System.out.println("--- Angajați din " + numeDept + " ---");

        for (Angajat angajat : angajati) {
            if (angajat.getDepartament().nume().equalsIgnoreCase(numeDept)) {
                System.out.println(angajat);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Niciun angajat în departamentul: " + numeDept);
        }
    }

    public void printAuditLog() {
        if (auditLog.length == 0) {
            System.out.println("Audit log gol.");
            return;
        }

        System.out.println("--- Audit Log ---");
        for (AuditEntry entry : auditLog) {
            System.out.println(entry);
        }
    }
}