import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    public ArrayList<Rider> ridersIsComing = new ArrayList<Rider>();
    public ArrayList<Rider> ridersHistory = new ArrayList<Rider>();
    public ArrayList<Cuisine> cuisinesIsComing = new ArrayList<Cuisine>();
    public ArrayList<Cuisine> cuisinesHistory = new ArrayList<Cuisine>();
    public ArrayList<Restaurant> restaurantsOfCuisinesIsComing = new ArrayList<Restaurant>();
    public ArrayList<Restaurant> restaurantsHistory = new ArrayList<Restaurant>();

    Restaurant restaurant = new Restaurant();
    Scanner scan = new Scanner(System.in);
    private String name;
    private Address address;
    private int status;
    private DataBaseAll dataBaseAll;

    public Customer() {
    }

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
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

    public void mainMenu(String customerName) {
        int menuSelect = 0;
        String orderIsComingStatus = "No Order";
        if (getCustomerByName(customerName).ridersIsComing.size() > 0) {
            orderIsComingStatus = getCustomerByName(customerName).ridersIsComing.get(0).getName() + " is coming";
        } else {
            System.out.println("NoOrder");
        }
        System.out.println("================================");
        System.out.println("Hello " + getCustomerByName(customerName).getName() + " What would you like to do?");
        System.out.println("1. Order");
        System.out.println("2. Order is coming :" + orderIsComingStatus);
        System.out.println("3. History");
        System.out.print("Select :");
        menuSelect = scan.nextInt();

        switch (menuSelect) {
            case 1:
                menuOrder(customerName);
                break;
            case 2:
                menuOrderIsComing(customerName);
                break;
            case 3:
                menuHistory(customerName);
                break;

        }

    }

    public void mainMenuLogin() {
        System.out.println("================================");
        showCustomer();
        System.out.print("Fill your name : ");
        String customerName = scan.next();
        mainMenu(customerName);


    }

    public void menuOrder(String customerName) {
        System.out.println("================================");
        int i = 1;
        restaurant.showRestaurant();
        System.out.print("Select :");
        Rider rider = new Rider();
        int menuSelect = scan.nextInt();
        System.out.println(restaurant.getRestaurantByIndex(menuSelect).getName());
        for (Cuisine cuisine : restaurant.getRestaurantByIndex(menuSelect).cuisines) {
            System.out.println(i + ". " + cuisine.getFood() + ":" + cuisine.getPrice() + " $");
            i++;
        }
        int cuisineSelect = scan.nextInt();
        System.out.println("You selected " + restaurant.getRestaurantByIndex(menuSelect).getCuisineByIndex(cuisineSelect).getFood());
        System.out.println("Do you confirm?y/n :");
        String confirm = scan.next();
        if (confirm.equals("y")) {
            rider.restaurantNewOrder.add(restaurant.getRestaurantByIndex(menuSelect));
            rider.getRiderByPosition().restaurantNewOrder.add(restaurant.getRestaurantByIndex(menuSelect));
            rider.getRiderByPosition().setStatus("You have new order");
            rider.getRiderByPosition().setStatusNewOrder("Pick up at " + restaurant.getRestaurantByIndex(menuSelect).getName() + " Address :" + restaurant.getRestaurantByIndex(menuSelect).getAddress().getName() + " Distance = " + rider.getRiderByPosition().getDistance() + " m");
            rider.getRiderByPosition().cuisinesConfirmed.add(restaurant.getRestaurantByIndex(menuSelect).getCuisineByIndex(cuisineSelect));
            rider.getRiderByPosition().customersWhoOrdered.add(getCustomerByName(customerName));
        }

    }


    public void menuOrderIsComing(String customerName) {
        String whoRiderIsComing ="" ;
        if (getCustomerByName(customerName).ridersIsComing.size()!=0){
            whoRiderIsComing = getCustomerByName(customerName).ridersIsComing.get(0).getName();
        }
        else {
            System.out.println("");
        }
        System.out.println("================================");
        System.out.println("Order is coming by :"+whoRiderIsComing);


    }

    public void menuHistory(String customerName) {
        if (getCustomerByName(customerName).restaurantsHistory.size()>0) {
            for (int i = 0; i < getCustomerByName(customerName).restaurantsHistory.size(); i++) {
                System.out.println(i + 1 + ". " + getCustomerByName(customerName).restaurantsHistory.get(i).getName() + " : " + getCustomerByName(customerName).cuisinesHistory.get(i).getFood() + " by " + getCustomerByName(customerName).ridersHistory.get(i).getName());
            }
        }
        else {
            System.out.println("No History");
        }

    }


    public Customer getCustomerByName(String customerName) {

        for (Customer customer : dataBaseAll.customers) {
            if (customer.getName().equals(customerName)) {
                return customer;
            }
        }
        return null;
    }

    public void showCustomer() {

        int i = 1;
        for (Customer customer : dataBaseAll.customers) {

            System.out.println(i + ". " + customer.getName());
            i++;
        }

    }

}
