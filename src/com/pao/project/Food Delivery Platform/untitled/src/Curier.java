import java.util.List;
import java.util.ArrayList;

public class Curier extends Utilizator {

    private String tipVehicul;
    private int experienta;
    private List<Comanda> comenziLivrate;

    public Curier(String n, String p, Adresa a, String tipVehicul, int experienta) {
        super(n, p, a);

        this.tipVehicul = tipVehicul;
        this.experienta = experienta;
        this.comenziLivrate = new ArrayList<>();
    }

    public String getTipVehicul() {
        return tipVehicul;
    }

    public int getExperienta() {
        return experienta;
    }

    public List<Comanda> getComenziLivrate() {
        return comenziLivrate;
    }

    public void adaugaComandaLivrata(Comanda comanda) {
        getComenziLivrate.add(comanda);
    }

    @Override
    public String toString() {
        return super.toString() + ", vehicul: " + tipVehicul + " experienta: " + experienta + ", comenzi livrate: " + comenziLivrate.size();
    }
}
