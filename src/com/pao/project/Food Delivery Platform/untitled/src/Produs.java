public class Produs {
    private String nume, categorie;
    private double pret;


    public Produs(String n, String c, double p){
        this.nume= n;
        this.pret = p;
        this.categorie = c;
    }

    public String getNume() {
        return nume;
    }

    public String getCategorie() {
        return categorie;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double p) {
        this.pret = p;
    }

    public void setCategorie(String c) {
        this.categorie = c;
    }

    public void setNume(String n) {
        this.nume = n;
    }

    @Override
    public String toString() {
        return "Produs: " + nume + " pret: " + pret + " lei (" + categorie + ")";
    }
}
