import java.util.List;
import java.util.ArrayList;

public class Client extends Utilizator {

    private Cos cos;
    private List<Restaurant> restauranteFavorite;
    private List<Comanda> comenziFavorite;

    public Client(String n, String p, Adresa a) {
        super(n, p, a); // apel catre constructorul din Utilizator

        this.cos = new Cos();
        this.restauranteFavorite = new ArrayList<>();
        this.comenziFavorite = new ArrayList<>();
    }

    public Cos getCos() {
        return cos;
    }

    public List<Restaurant> getRestauranteFavorite() {
        return restauranteFavorite;
    }

    public List<Comanda> getComenziFavorite() {
        return comenziFavorite;
    }

    public void adaugaRestaurantFavorit(Restaurant restaurant) {
        restauranteFavorite.add(restaurant);
    }

    public void adaugaComandaFavorita(Comanda comanda) {
        comenziFavorite.add(comanda);
    }

    @Override
    public String toString() {
        return super.toString() + ", restaurante favorite: " + restauranteFavorite.size() + ", nr. comenzi favorite: " + comenziFavorite.size();
    }
}
