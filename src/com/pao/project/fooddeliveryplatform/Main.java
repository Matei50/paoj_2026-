package com.pao.project.fooddeliveryplatform;

import com.pao.project.fooddeliveryplatform.exception.*;
import com.pao.project.fooddeliveryplatform.model.*;
import com.pao.project.fooddeliveryplatform.service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RestaurantService restaurantService = RestaurantService.getInstance();
        ClientService clientService = ClientService.getInstance();

        Restaurant r1 = new Restaurant("KFC", "10-22", CategorieRestaurant.FAST_FOOD, 4.5);
        Produs p1 = new Produs("Burger", "Mancare", 25);
        Produs p2 = new Produs("Cola", "Bautura", 10);

        r1.adaugaProdus(p1);
        r1.adaugaProdus(p2);
        restaurantService.adaugaRestaurant(r1);

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
            System.out.println("8. Plasare comanda");
            System.out.println("9. Verificare status comanda");
            System.out.println("10. Anulare comanda");
            System.out.println("11. Adaugare restaurant la favorite");
            System.out.println("12. Afisare restaurante favorite");
            System.out.println("13. Calculare total comanda");
            System.out.println("0. Iesire");
            System.out.print("Alege optiunea: ");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1 -> {
                    System.out.print("Nume: ");
                    String nume = scanner.nextLine();

                    System.out.print("Parola: ");
                    String parola = scanner.nextLine();

                    Adresa adresa = new Adresa("Bucuresti", "Unirii", 10);
                    client = new Client(nume, parola, adresa);
                    clientService.adaugaClient(client);

                    System.out.println("Cont creat cu succes.");
                }

                case 2 -> {
                    System.out.print("Nume client: ");
                    String nume = scanner.nextLine();

                    try {
                        client = clientService.cautaClient(nume);
                        System.out.println("Autentificare reusita: " + client.getNume());
                    } catch (ClientNegasitException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 3 -> {
                    restaurantService.afiseazaRestaurante();
                }

                case 4 -> {
                    System.out.print("Nume restaurant: ");
                    String nume = scanner.nextLine();

                    try {
                        Restaurant gasit = restaurantService.cautaRestaurant(nume);
                        System.out.println(gasit);
                    } catch (RestaurantNegasitException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 5 -> {
                    System.out.print("Nume restaurant: ");
                    String nume = scanner.nextLine();

                    try {
                        Restaurant gasit = restaurantService.cautaRestaurant(nume);
                        gasit.afiseazaMeniu();
                    } catch (RestaurantNegasitException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 6 -> {
                    if (client == null) {
                        System.out.println("Trebuie sa creezi/autentifici un client.");
                        break;
                    }

                    System.out.println("Produse disponibile:");
                    r1.afiseazaMeniu();

                    System.out.print("Alege produs: ");
                    String produsAles = scanner.nextLine();

                    Produs produs = null;
                    for (Produs p : r1.getListaProduse()) {
                        if (p.getNume().equalsIgnoreCase(produsAles)) {
                            produs = p;
                        }
                    }

                    try {
                        if (produs == null) {
                            throw new ProdusIndisponibilException("Produsul nu exista.");
                        }

                        client.getCos().adaugaProdus(produs);
                        System.out.println("Produs adaugat in cos.");
                    } catch (ProdusIndisponibilException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 7 -> {
                    if (client == null) {
                        System.out.println("Nu exista client autentificat.");
                    } else {
                        System.out.println(client.getCos());
                    }
                }

                case 8 -> {
                    if (client == null) {
                        System.out.println("Nu exista client autentificat.");
                        break;
                    }

                    comandaCurenta = new Comanda(1, client);

                    for (Produs p : client.getCos().getProduse()) {
                        comandaCurenta.adaugaProdus(p);
                    }

                    client.getComenziFavorite().add(comandaCurenta);
                    System.out.println("Comanda plasata:");
                    System.out.println(comandaCurenta);
                }

                case 9 -> {
                    if (comandaCurenta == null) {
                        System.out.println("Nu exista comanda.");
                    } else {
                        System.out.println("Status comanda: " + comandaCurenta.getStatus());
                    }
                }

                case 10 -> {
                    if (comandaCurenta == null) {
                        System.out.println("Nu exista comanda.");
                    } else {
                        comandaCurenta.setStatus("Anulata");
                        System.out.println("Comanda a fost anulata.");
                    }
                }

                case 11 -> {
                    if (client == null) {
                        System.out.println("Nu exista client autentificat.");
                        break;
                    }

                    client.adaugaRestaurantFavorit(r1);
                    System.out.println("Restaurant adaugat la favorite.");
                }

                case 12 -> {
                    if (client == null) {
                        System.out.println("Nu exista client autentificat.");
                        break;
                    }

                    for (Restaurant r : client.getRestauranteFavorite()) {
                        System.out.println(r);
                    }
                }

                case 13 -> {
                    if (comandaCurenta == null) {
                        System.out.println("Nu exista comanda.");
                    } else {
                        System.out.println("Total comanda: " + comandaCurenta.calculeazaTotal() + " lei");
                    }
                }

                case 0 -> {
                    ruleaza = false;
                    System.out.println("La revedere!");
                }

                default -> System.out.println("Optiune invalida.");
            }
        }

        scanner.close();
    }
}