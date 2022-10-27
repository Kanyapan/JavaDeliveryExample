import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
    public ArrayList<Cuisine> cuisines = new ArrayList<Cuisine>();
    public ArrayList<Cuisine> cuisinesOrdered = new ArrayList<Cuisine>();
    public ArrayList<Rider> ridersWhoPick = new ArrayList<Rider>();
    Scanner scan = new Scanner(System.in);
    private int id;
    private String name;
    private Address address;
    private String status;
    private Repository repository = new Repository();

    public Restaurant() {

    }

    public Restaurant(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.status = "";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void mainMenuLogin() {
        System.out.println("================================");
        showRestaurant();
        System.out.print("Fill your ID : ");
        int restaurantID = scan.nextInt();
        mainMenu(restaurantID);
    }

    public void mainMenu(int restaurantID) {
        System.out.println("================================");
        System.out.println("Hello " + getRestaurantByIndex(restaurantID).getName() + " Please make your food ready");
        int i = 1;
        for (Cuisine cuisine : getRestaurantByIndex(restaurantID).cuisinesOrdered) {

            System.out.println(i + ". " + cuisine.getFood());
            i++;
        }
        if (getRestaurantByIndex(restaurantID).cuisinesOrdered.size() != 0) {
            System.out.print("Select you food :");
            int cuisineSelect = scan.nextInt();
            System.out.print("Confirm?y/n :");
            String confirm = scan.next();
            if (confirm.equals("y")) {
                getRestaurantByIndex(restaurantID).ridersWhoPick.get(0).cuisinesReadyToPickup.add(getRestaurantByIndex(restaurantID).cuisinesOrdered.get(cuisineSelect - 1));
                getRestaurantByIndex(restaurantID).cuisinesOrdered.remove(cuisineSelect - 1);
                getRestaurantByIndex(restaurantID).ridersWhoPick.get(0).setStatus("Food Ready to pick");
                getRestaurantByIndex(restaurantID).ridersWhoPick.get(0).restaurantFoodReady.add(getRestaurantByIndex(restaurantID));
                getRestaurantByIndex(restaurantID).ridersWhoPick.get(0).restaurantsNotConfirmedYet.remove(0);
            }
        } else {
            System.out.println("No order now");
        }

    }

    public void showRestaurant() {
        int i = 1;
        for (Restaurant restaurant : repository.restaurants) {

            System.out.println(i + ". " + restaurant.getName() + ":" + restaurant.getAddress().getName() + " " + restaurant.getStatus());
            i++;
        }
    }

    public Restaurant getRestaurantByIndex(int index) {
        return repository.restaurants.get(index - 1);

    }

    public Cuisine getCuisineByIndex(int index) {
        return cuisines.get(index - 1);

    }
}
