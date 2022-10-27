import java.util.ArrayList;
import java.util.Scanner;

public class Rider {
    //new Order Before Check Position
    public static ArrayList<Cuisine> cuisinesConfirmed = new ArrayList<Cuisine>();
    public static ArrayList<Customer> customersWhoOrdered = new ArrayList<>();
    //new Order After Check Position
    public ArrayList<Restaurant> restaurantNewOrder = new ArrayList<Restaurant>();
    public ArrayList<Customer> customersForThisRider = new ArrayList<>();
    //Ready To pick
    public ArrayList<Restaurant> restaurantsNotConfirmedYet = new ArrayList<>();
    public ArrayList<Restaurant> restaurantFoodReady = new ArrayList<Restaurant>();
    public ArrayList<Cuisine> cuisinesReadyToPickup = new ArrayList<Cuisine>();
    //After Picked
    public ArrayList<Cuisine> cuisinesPickedUp = new ArrayList<Cuisine>();
    public ArrayList<Restaurant> restaurantsPicked = new ArrayList<Restaurant>();
    //history
    public ArrayList<Customer> customersHistory = new ArrayList<Customer>();
    public ArrayList<Restaurant> restaurantsHistory = new ArrayList<Restaurant>();
    public ArrayList<Cuisine> cuisinesHistory = new ArrayList<Cuisine>();


    Scanner scan = new Scanner(System.in);
    private int id;
    private String name;
    private Address address;
    private String status;
    private String statusNewOrder;
    private int state;
    private int distance;
    private Repository repository;

    public Rider() {

    }

    public Rider(int id, String name, Address address) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.status = "Available";
        this.statusNewOrder = "";
        this.state = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusNewOrder() {
        return statusNewOrder;
    }

    public void setStatusNewOrder(String statusNewOrder) {
        this.statusNewOrder = statusNewOrder;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void mainMenuLogin() {
        System.out.println("================================");
        showRider();
        System.out.print("Fill your ID : ");
        int riderID = scan.nextInt();
        mainMenu(riderID);
    }

    public void showRider() {
        int i = 1;
        for (Rider rider : Repository.riders) {
            System.out.println(i + ". " + rider.getName() + " Status :" + rider.getStatus());
            i++;
        }
    }

    public void mainMenu(int riderID) {
        int menuSelect = 0;
        String pickUpStatus = "";
        String deliveringStatus = "";
        if (getRiderByID(riderID).restaurantFoodReady.size() > 0) {

            pickUpStatus = getRiderByID(riderID).restaurantFoodReady.get(0).getName() + " location at  " + getRiderByID(riderID).restaurantFoodReady.get(0).getAddress().getName();
        } else {
            pickUpStatus = "";
        }
        if (getRiderByID(riderID).cuisinesPickedUp.size() > 0) {

            deliveringStatus = "Customer " + getRiderByID(riderID).customersForThisRider.get(0).getName() + " " + getRiderByID(riderID).customersForThisRider.get(0).getAddress().getName() + " Distance = " + distanceFromCustomer(riderID) + " m";
        } else {
            deliveringStatus = "No delivering";
        }
        System.out.println("================================");
        System.out.println("Hello " + getRiderByID(riderID).getName() + " What would you like to do?");
        System.out.println("1. New Order :" + getRiderByID(riderID).statusNewOrder);
        System.out.println("2. Pick up :" + pickUpStatus);
        System.out.println("3. Delivering :" + deliveringStatus);
        System.out.println("4. History");
        System.out.print("Select :");
        menuSelect = scan.nextInt();

        switch (menuSelect) {
            case 1 -> menuNewOrder(riderID);
            case 2 -> menuPickUp(riderID);
            case 3 -> menuDelivering(riderID);
            case 4 -> menuHistory(riderID);
        }
    }

    public void menuNewOrder(int riderID) {
        System.out.println("Confirm this Order?y/n");
        String confirm = scan.next();

        if (confirm.equals("y")) {
            getRiderByID(riderID).restaurantsNotConfirmedYet.add(getRiderByID(riderID).restaurantNewOrder.get(0));
            getRiderByID(riderID).restaurantsNotConfirmedYet.get(0).cuisinesOrdered.add(cuisinesConfirmed.get(0));
            getRiderByID(riderID).restaurantsNotConfirmedYet.get(0).ridersWhoPick.add(getRiderByID(riderID));
            getRiderByID(riderID).restaurantsNotConfirmedYet.get(0).setStatus("You have new order!");
            getRiderByID(riderID).customersForThisRider.add(customersWhoOrdered.get(0));
            getRiderByID(riderID).customersWhoOrdered.remove(0);
            getRiderByID(riderID).restaurantNewOrder.remove(0);
            getRiderByID(riderID).setStatusNewOrder("");
            getRiderByID(riderID).setStatus("Waiting Food ready");
            for (Rider rider : Repository.riders) {
                if (rider.getState() == 2)
                    rider.setState(0);
            }
            getRiderByID(riderID).setState(1);
        } else {
            getRiderByID(riderID).setState(2);
            getRiderByID(riderID).setStatusNewOrder("");
            getRiderByID(riderID).setStatus("Available");
            getRiderByPosition().restaurantNewOrder.add(getRiderByID(riderID).restaurantNewOrder.get(0));
            getRiderByID(riderID).restaurantNewOrder.remove(0);
            getRiderByPosition().setStatus("You have new order");
            getRiderByPosition().setStatusNewOrder("Pick up at " + getRiderByPosition().restaurantNewOrder.get(0).getName() + " Address :" + getRiderByPosition().restaurantNewOrder.get(0).getAddress().getName() + " Distance = " + getRiderByPosition().getDistance() + " m");
        }
    }

    public void menuPickUp(int riderID) {
        System.out.println(getRiderByID(riderID).restaurantFoodReady.get(0).getName() + "Food :" + getRiderByID(riderID).cuisinesReadyToPickup.get(0).getFood());
        System.out.println("confirm to pick up?y/n");
        String confirm = scan.next();
        if (confirm.equals("y")) {
            getRiderByID(riderID).cuisinesPickedUp.add(getRiderByID(riderID).cuisinesReadyToPickup.get(0));
            getRiderByID(riderID).customersForThisRider.get(0).ridersIsComing.add(getRiderByID(riderID));
            getRiderByID(riderID).customersForThisRider.get(0).restaurantsOfCuisinesIsComing.add(getRiderByID(riderID).restaurantFoodReady.get(0));
            getRiderByID(riderID).customersForThisRider.get(0).cuisinesIsComing.add(getRiderByID(riderID).cuisinesPickedUp.get(0));
            getRiderByID(riderID).restaurantFoodReady.remove(0);
            getRiderByID(riderID).cuisinesReadyToPickup.remove(0);
            getRiderByID(riderID).setStatus("Delivering : Customer " + getRiderByID(riderID).customersForThisRider.get(0).getName() + " " + getRiderByID(riderID).customersForThisRider.get(0).getAddress().getName() + " Distance = " + distanceFromCustomer(riderID) + " m");
        }
    }

    public void menuDelivering(int riderID) {
        System.out.print("Set Distance (Must be 0 m) :");
        int distanceSet = scan.nextInt();
        getRiderByID(riderID).setDistance(distanceSet);
        if (getRiderByID(riderID).getDistance() == 0) {
            System.out.println("You've arrived");
            System.out.print("Deliver?y/n :");
            String confirm = scan.next();
            if (confirm.equals("y")) {
                getRiderByID(riderID).setState(0);
                getRiderByID(riderID).setStatus("Available");
                getRiderByID(riderID).customersHistory.add(getRiderByID(riderID).customersForThisRider.get(0));
                getRiderByID(riderID).cuisinesHistory.add(getRiderByID(riderID).cuisinesPickedUp.get(0));
                getRiderByID(riderID).restaurantsHistory.add(getRiderByID(riderID).getRiderByID(riderID).customersForThisRider.get(0).restaurantsOfCuisinesIsComing.get(0));
                getRiderByID(riderID).customersForThisRider.get(0).restaurantsHistory.add(getRiderByID(riderID).customersForThisRider.get(0).restaurantsOfCuisinesIsComing.get(0));
                getRiderByID(riderID).customersForThisRider.get(0).cuisinesHistory.add(getRiderByID(riderID).cuisinesPickedUp.get(0));
                getRiderByID(riderID).customersForThisRider.get(0).ridersHistory.add(getRiderByID(riderID));
                getRiderByID(riderID).customersForThisRider.get(0).restaurantsOfCuisinesIsComing.remove(0);
                getRiderByID(riderID).customersForThisRider.get(0).cuisinesIsComing.remove(0);
                getRiderByID(riderID).customersForThisRider.get(0).ridersIsComing.remove(0);
                getRiderByID(riderID).customersForThisRider.remove(0);
                getRiderByID(riderID).cuisinesPickedUp.remove(0);
                System.out.println("Delivered");
            }
        } else {
            System.out.println("You haven't arrived!!");
        }

    }

    public void menuHistory(int riderID) {
        if (getRiderByID(riderID).customersHistory.size() > 0)
            for (int i = 0; i < getRiderByID(riderID).customersHistory.size(); i++) {
                System.out.println(i + 1 + ". " + getRiderByID(riderID).customersHistory.get(i).getName() + " : " + getRiderByID(riderID).restaurantsHistory.get(0).getName() + " " + getRiderByID(riderID).cuisinesHistory.get(i).getFood());
            }
        else {
            System.out.println("No History");
        }
    }

    public Rider getRiderByID(int riderID) {

        for (Rider rider : Repository.riders) {
            if (rider.getId() == (riderID)) {
                return rider;
            }
        }
        return null;
    }

    public Rider getRiderByPosition() {
        for (Restaurant restaurant : restaurantNewOrder) {
            Rider chosenRider = new Rider();
            int initialTotalPositionX = Repository.riders.get(0).getAddress().getLocationX() - restaurant.getAddress().getLocationX();
            int initialTotalPositionY = Repository.riders.get(0).getAddress().getLocationY() - restaurant.getAddress().getLocationY();
            int shortestPosition = Math.abs(initialTotalPositionX) + Math.abs(initialTotalPositionY);
            for (Rider rider : Repository.riders) {
                int totalPositionX = rider.getAddress().getLocationX() - restaurant.getAddress().getLocationX();
                int totalPositionY = rider.getAddress().getLocationY() - restaurant.getAddress().getLocationY();
                int totalPosition = Math.abs(totalPositionX) + Math.abs(totalPositionY);
                rider.distance = totalPosition;
                if (totalPosition <= shortestPosition && rider.getState() == 0) {
                    shortestPosition = totalPosition;
                    chosenRider = rider;
                }
            }
            return chosenRider;
        }
        return null;
    }

    public int distanceFromCustomer(int riderID) {
        int totalPositionX = getRiderByID(riderID).getAddress().getLocationX() - getRiderByID(riderID).customersForThisRider.get(0).getAddress().getLocationX();
        int totalPositionY = getRiderByID(riderID).getAddress().getLocationY() - getRiderByID(riderID).customersForThisRider.get(0).getAddress().getLocationY();
        int totalPosition = Math.abs(totalPositionX) + Math.abs(totalPositionY);
        return totalPosition;
    }
}