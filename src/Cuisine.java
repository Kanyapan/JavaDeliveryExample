public class Cuisine {
    private String food;
    private int price;

    public Cuisine(String food, int price) {
        this.food = food;
        this.price = price;
    }
    Restaurant restaurant = new Restaurant();
    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Cuisine getCuisineByIndex(int index) {
        return restaurant.cuisines.get(index-1);

    }

}
