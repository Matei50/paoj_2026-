import java.util.List;
import java.util.ArrayList;

public class Restaurant {
    private String nume, program;
    private CategorieRestaurant categorie;
    private List<Produs> listaProduse;
    private double rating;

    public Restaurant(String nume, String program, CategorieRestaurant categorie, double rating) {
        this.nume = nume;
        this.program = program;
        this.rating = rating;
        this.categorie = categorie;
        this.listaProduse = new ArrayList<>();
    }

    public String getNume(){
        return nume;
    }

    public String getProgram() {
        return program;
    }

    public CategorieRestaurant getCategorie() {
        return categorie;
    }

    public double getRating() {
        return rating;
    }

    public List<Produs> getListaProduse() {
        return listaProduse;
    }

    public void adaugaProdus(Produs produs) {
        listaProduse.add(produs);
    }

    public void afiseazaMeniu() {
        for (Produs produs : listaProduse) {
            System.out.println(produs);
        }
    }

    @Override
    public String toString() {
        return nume + " (" + categorie + "), program: " + program + ", rating: " + rating;
    }
}
