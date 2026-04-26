public class Review {
    private String mesaj;
    private int rating;
    private Client client;
    private Restaurant restaurant;

    public Review(String mesaj, int rating, Client client, Restaurant restaurant){
        this.mesaj = mesaj;
        this.rating = rating;
        this.client = client;
        this.restaurant = restaurant;
    }

    public String getMesaj() {
        return mesaj;
    }

    public int getRating() {
        return rating;
    }

    public Client getClient() {
        return client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    @Override
    public String toString(){
        return "Review pentru " + restaurant.getNume() + " de la " + client.getNume() + " de la " + client.getNume() + ": " + rating + "/5 - " + mesaj;
    }
}
