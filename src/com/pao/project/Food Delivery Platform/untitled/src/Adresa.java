/*
Structura Proiect

 --- Actiuni / Interogari ---
Creare cont
Autentificare
Accesarea hartii restaurantelor
Cautarea Restaurantelor dupa nume / categorie
Afisare meniu restaurant
Adaugarea Produselor in cos
Plasare comanda
Anulare Comanda
Verificare status comanda
Adaugare restaurant la favorite
Adaugare comanda la favorite
Afisarea restaurantelor favorite
Afisarea comenzilor favorite
Adaugarea unui Review pt restaurant
Calculare total comanda


 --- Obiecte ---
Utilizator
    Nume, parola, adresa
Client (extends Utilizator)
    Nume, parola, adresa, cos, listacomenzi, restaurantefavorite si comenzi favorite
Curier (extends Utilizator)
    Nume, parola, adresa, tip_vehicul, experienta, comenzi_livrate
Restaurant
    Nume, program, categorie (categorieRestaurant), listaProduse, rating
Produs
    nume, pret, categorie
CategorieRestaurant --- ENUM
MANCARE_GATITA
FAST_FOOD
CAFENEA
Comanda
    id, client, listaProduse, pretTotal, status, curier, durata
Cos
    Nrproduse, pret
Review
    Mesaj, rating, client, restaurant
Adresa
    oras strada numar
Plata
    metoda, status
 */

public class Adresa{
    private String oras, strada;
    private int numar;

    public Adresa(String oras, String strada, int numar) {
        this.oras = oras;
        this.strada = strada;
        this.numar = numar;
    }

    public String getOras() {
        return oras;
    }

    public String getStrada() {
        return strada;
    }

    public int getNumar(){
        return numar;
    }

    public void setOras(String oras){
        this.oras = oras;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    @Override
    public String toString() {
        return oras + ", " + strada + " nr. " + numar;
    }
}
