import java.util.List;
import java.util.ArrayList;

public class Comanda {
    private int id;
    private Client client;
    private List<Produs> produse;
    private Curier curier;
    private String status;
    private int durata;

    public Comanda(int id, Client client){
        this.id = id;
        this.client = client;
        this.produse = new ArrayList<>();
        this.status = "Plasata";
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Curier getCurier() {
        return curier;
    }

    public String getStatus() {
        return status;
    }

    public int getDurata() {
        return durata;
    }

    public void setCurier(Curier curier){
        this.curier = curier;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void adaugaProdus(Produs produs) {
        produse.add(produs);
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public double calculeazaTotal() {
        double total = 0;
        for (Produs p : produse) {
            total += p.getPret();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Comanda #" + id + ", client: " + client.getNume() + ", produse: " + produse.size() + ", total: " + calculeazaTotal() + " lei" + ", status: " + status + (curier != null ? ", curier: " + curier.getNume() : "");
    }
}

