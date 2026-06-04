package com.pao.project.fooddeliveryplatform;

import com.pao.project.fooddeliveryplatform.exception.*;
import com.pao.project.fooddeliveryplatform.model.*;
import com.pao.project.fooddeliveryplatform.repository.*;
import com.pao.project.fooddeliveryplatform.service.*;
import com.pao.project.fooddeliveryplatform.util.DatabaseConnection;

import java.sql.Connection;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuditService auditService = AuditService.getInstance();
        ReportService reportService = ReportService.getInstance();

        // Initializam Repositories
        ClientRepository clientRepository = new ClientRepository();
        RestaurantRepository restaurantRepository = new RestaurantRepository();
        ProdusRepository produsRepository = new ProdusRepository();
        ComandaRepository comandaRepository = new ComandaRepository();

        // Inseram date demo in baza de date
        Restaurant r1 = new Restaurant("KFC", "10-22", CategorieRestaurant.FAST_FOOD, 4.5);
        if (restaurantRepository.findByNume("KFC").isEmpty()) {
            restaurantRepository.save(r1);
            
            Produs p1 = new Produs("Burger", "Mancare", 25);
            p1.setIdRestaurant(r1.getId());
            Produs p2 = new Produs("Cola", "Bautura", 10);
            p2.setIdRestaurant(r1.getId());
            
            produsRepository.save(p1);
            produsRepository.save(p2);
        } else {
            r1 = restaurantRepository.findByNume("KFC").get();
        }

        Client client = null;
        Comanda comandaCurenta = null;

        System.out.println("=== BUN VENIT IN FOOD DELIVERY PLATFORM ===");

        boolean ruleaza = true;

        while (ruleaza) {
            System.out.println("\n--- MENIU ---");
            System.out.println("1. Creare cont client");
            System.out.println("2. Autentificare client");
            System.out.println("3. Afisare restaurante");
            System.out.println("4. Cautare restaurant");
            System.out.println("5. Afisare meniu restaurant");
            System.out.println("6. Adaugare produs in cos");
            System.out.println("7. Afisare cos");
            System.out.println("8. Plasare comanda (TRANZACTIE)");
            System.out.println("9. Verificare status comanda");
            System.out.println("10. Raport: Detalii Comenzi (JOIN)");
            System.out.println("11. Raport: Total comenzi per client (JOIN)");
            System.out.println("12. Raport: Produse per restaurant (JOIN)");
            System.out.println("13. Calculare total comanda curenta");
            System.out.println("0. Iesire");
            System.out.print("Alege optiunea: ");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1 -> {
                    auditService.logAction("creare_cont_client");
                    System.out.print("Nume: ");
                    String nume = scanner.nextLine();

                    System.out.print("Parola: ");
                    String parola = scanner.nextLine();

                    Adresa adresa = new Adresa("Bucuresti", "Unirii", 10);
                    client = new Client(nume, parola, adresa);
                    
                    if(clientRepository.findByNume(nume).isEmpty()) {
                        clientRepository.save(client);
                        System.out.println("Cont creat cu succes. ID: " + client.getId());
                    } else {
                        System.out.println("Numele exista deja in DB.");
                    }
                }

                case 2 -> {
                    auditService.logAction("autentificare_client");
                    System.out.print("Nume client: ");
                    String nume = scanner.nextLine();

                    Optional<Client> gasit = clientRepository.findByNume(nume);
                    if (gasit.isPresent()) {
                        client = gasit.get();
                        System.out.println("Autentificare reusita: " + client.getNume() + " (ID: " + client.getId() + ")");
                    } else {
                        System.out.println("Clientul nu a fost gasit in DB.");
                    }
                }

                case 3 -> {
                    auditService.logAction("afisare_restaurante");
                    System.out.println("Restaurante in DB:");
                    for (Restaurant r : restaurantRepository.findAll()) {
                        System.out.println(r);
                    }
                }

                case 4 -> {
                    auditService.logAction("cautare_restaurant");
                    System.out.print("Nume restaurant: ");
                    String nume = scanner.nextLine();

                    Optional<Restaurant> gasit = restaurantRepository.findByNume(nume);
                    if (gasit.isPresent()) {
                        System.out.println(gasit.get());
                    } else {
                        System.out.println("Restaurantul nu a fost gasit in DB.");
                    }
                }

                case 5 -> {
                    auditService.logAction("afisare_meniu_restaurant");
                    System.out.print("Nume restaurant: ");
                    String nume = scanner.nextLine();

                    Optional<Restaurant> gasit = restaurantRepository.findByNume(nume);
                    if (gasit.isPresent()) {
                        Restaurant r = gasit.get();
                        System.out.println("Meniu " + r.getNume() + ":");
                        for(Produs p : produsRepository.findByRestaurantId(r.getId())) {
                            System.out.println(" - " + p.getNume() + " : " + p.getPret() + " lei");
                        }
                    } else {
                        System.out.println("Restaurantul nu a fost gasit.");
                    }
                }

                case 6 -> {
                    auditService.logAction("adaugare_produs_cos");
                    if (client == null) {
                        System.out.println("Trebuie sa creezi/autentifici un client.");
                        break;
                    }

                    System.out.print("Introdu numele produsului dorit (ex: Burger): ");
                    String produsAles = scanner.nextLine();
                    
                    boolean found = false;
                    for (Produs p : produsRepository.findAll()) {
                        if (p.getNume().equalsIgnoreCase(produsAles)) {
                            client.getCos().adaugaProdus(p);
                            System.out.println("Produs adaugat in cos: " + p.getNume());
                            found = true;
                            break;
                        }
                    }
                    if(!found) {
                        System.out.println("Produsul nu a fost gasit in baza de date.");
                    }
                }

                case 7 -> {
                    auditService.logAction("afisare_cos");
                    if (client == null) {
                        System.out.println("Nu exista client autentificat.");
                    } else {
                        System.out.println(client.getCos());
                    }
                }

                case 8 -> {
                    auditService.logAction("plasare_comanda");
                    if (client == null) {
                        System.out.println("Nu exista client autentificat.");
                        break;
                    }
                    if (client.getCos().getProduse().isEmpty()) {
                        System.out.println("Cosul este gol.");
                        break;
                    }

                    comandaCurenta = new Comanda(0, client);
                    for (Produs p : client.getCos().getProduse()) {
                        comandaCurenta.adaugaProdus(p);
                    }

                    comandaRepository.save(comandaCurenta); // Aici ruleaza TRANZACTIA JDBC
                    System.out.println("Comanda plasata si salvata in DB.");
                    
                    // Golim cosul dupa plasare
                    client.getCos().getProduse().clear();
                }

                case 9 -> {
                    auditService.logAction("verificare_status_comanda");
                    if (comandaCurenta == null) {
                        System.out.println("Nu exista comanda curenta.");
                    } else {
                        System.out.println("Status comanda: " + comandaCurenta.getStatus());
                    }
                }

                case 10 -> {
                    auditService.logAction("raport_detalii_comenzi");
                    reportService.afiseazaDetaliiComenzi();
                }

                case 11 -> {
                    auditService.logAction("raport_comenzi_client");
                    reportService.afiseazaTotalComenziPerClient();
                }

                case 12 -> {
                    auditService.logAction("raport_produse_restaurant");
                    System.out.print("Pentru ce restaurant doresti raportul (ex: KFC): ");
                    String num = scanner.nextLine();
                    reportService.afiseazaProduseRestaurant(num);
                }

                case 13 -> {
                    auditService.logAction("calculare_total_comanda");
                    if (comandaCurenta == null) {
                        System.out.println("Nu exista comanda.");
                    } else {
                        System.out.println("Total comanda: " + comandaCurenta.calculeazaTotal() + " lei");
                    }
                }

                case 0 -> {
                    auditService.logAction("iesire");
                    ruleaza = false;
                    System.out.println("La revedere!");
                }

                default -> System.out.println("Optiune invalida.");
            }
        }

        scanner.close();
    }
}