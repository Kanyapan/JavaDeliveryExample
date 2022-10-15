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
    private String name;
    private Address address;

    private String status;
    private String statusNewOrder;
    private int state;
    private int distance;
    private DataBaseAll dataBaseAll;

    public Rider() {

    }

    public Rider(String name, Address address) {
        this.name = name;
        this.address = address;
        this.status = "Available";
        this.statusNewOrder = "";
        this.state = 0;
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
        System.out.print("Fill your name : ");
        String riderName = scan.next();
        mainMenu(riderName);
    }

    public void showRider() {
        int i = 1;
        for (Rider rider : dataBaseAll.riders) {
            System.out.println(i + ". " + rider.getName() + " Status :" + rider.getStatus());
            i++;
        }
    }

    public void mainMenu(String riderName) {
        int menuSelect = 0;
        String pickUpStatus = "";
        String deliveringStatus = "";
        if (getRiderByName(riderName).restaurantFoodReady.size() > 0) {

            pickUpStatus = getRiderByName(riderName).restaurantFoodReady.get(0).getName() + " location at  " + getRiderByName(riderName).restaurantFoodReady.get(0).getAddress().getName();
        } else {
            pickUpStatus="";
        }
        if (getRiderByName(riderName).cuisinesPickedUp.size() > 0) {

            deliveringStatus = "Customer " + getRiderByName(riderName).customersForThisRider.get(0).getName() + " " + getRiderByName(riderName).customersForThisRider.get(0).getAddress().getName() + " Distance = " + distanceFromCustomer(riderName) + " m";
        } else {
            deliveringStatus = "No delivering";
        }
        System.out.println("================================");
        System.out.println("Hello " + getRiderByName(riderName).getName() + " What would you like to do?");
        System.out.println("1. New Order :" + getRiderByName(riderName).statusNewOrder);
        System.out.println("2. Pick up :" + pickUpStatus);
        System.out.println("3. Delivering :" + deliveringStatus);
        System.out.println("4. History");
        System.out.print("Select :");
        menuSelect = scan.nextInt();

        switch (menuSelect) {
            case 1:
                menuNewOrder(riderName);
                break;
            case 2:
                menuPickUp(riderName);
                break;
            case 3:
                menuDelivering(riderName);
                break;
            case 4:
                menuHistory(riderName);
                break;

        }
    }

    public void menuNewOrder(String riderName) {
        System.out.println("Confirm this Order?y/n");
        String confirm = scan.next();

        if (confirm.equals("y")) {
            getRiderByName(riderName).restaurantsNotConfirmedYet.add(getRiderByName(riderName).restaurantNewOrder.get(0));
            getRiderByName(riderName).restaurantsNotConfirmedYet.get(0).cuisinesOrdered.add(cuisinesConfirmed.get(0));
            getRiderByName(riderName).restaurantsNotConfirmedYet.get(0).ridersWhoPick.add(getRiderByName(riderName));
            getRiderByName(riderName).restaurantsNotConfirmedYet.get(0).setStatus("You have new order!");
            getRiderByName(riderName).customersForThisRider.add(customersWhoOrdered.get(0));
            getRiderByName(riderName).customersWhoOrdered.remove(0);
            getRiderByName(riderName).restaurantNewOrder.remove(0);
            getRiderByName(riderName).setStatusNewOrder("");
            getRiderByName(riderName).setStatus("Waiting Food ready");
            for (Rider rider : dataBaseAll.riders) {
                if (rider.getState() == 2)
                    rider.setState(0);
            }
            getRiderByName(riderName).setState(1);
        } else {
            getRiderByName(riderName).setState(2);
            getRiderByName(riderName).setStatusNewOrder("");
            getRiderByName(riderName).setStatus("Available");
            getRiderByPosition().restaurantNewOrder.add(getRiderByName(riderName).restaurantNewOrder.get(0));
            getRiderByName(riderName).restaurantNewOrder.remove(0);
            getRiderByPosition().setStatus("You have new order");
            getRiderByPosition().setStatusNewOrder("Pick up at " + getRiderByPosition().restaurantNewOrder.get(0).getName() + " Address :" + getRiderByPosition().restaurantNewOrder.get(0).getAddress().getName() + " Distance = " + getRiderByPosition().getDistance() + " m");
        }
    }

    public void menuPickUp(String riderName) {
        System.out.println(getRiderByName(riderName).restaurantFoodReady.get(0).getName() + "Food :" + getRiderByName(riderName).cuisinesReadyToPickup.get(0).getFood());
        System.out.println("confirm to pick up?y/n");
        String confirm = scan.next();
        if (confirm.equals("y")) {
            getRiderByName(riderName).cuisinesPickedUp.add(getRiderByName(riderName).cuisinesReadyToPickup.get(0));
            getRiderByName(riderName).customersForThisRider.get(0).ridersIsComing.add(getRiderByName(riderName));
            getRiderByName(riderName).customersForThisRider.get(0).restaurantsOfCuisinesIsComing.add(getRiderByName(riderName).restaurantFoodReady.get(0));
            getRiderByName(riderName).customersForThisRider.get(0).cuisinesIsComing.add(getRiderByName(riderName).cuisinesPickedUp.get(0));
            getRiderByName(riderName).restaurantFoodReady.remove(0);
            getRiderByName(riderName).cuisinesReadyToPickup.remove(0);
            getRiderByName(riderName).setStatus("Delivering : Customer " + getRiderByName(riderName).customersForThisRider.get(0).getName() + " " + getRiderByName(riderName).customersForThisRider.get(0).getAddress().getName() + " Distance = " + distanceFromCustomer(riderName) + " m");
        }
    }

    public void menuDelivering(String riderName) {
        System.out.print("Set Distance :");
        int distanceSet = scan.nextInt();
        getRiderByName(riderName).setDistance(distanceSet);
        if (getRiderByName(riderName).getDistance() == 0) {
            System.out.println("You've arrived");
            System.out.print("Deliver?y/n :");
            String confirm = scan.next();
            if (confirm.equals("y")) {
                getRiderByName(riderName).setState(0);
                getRiderByName(riderName).setStatus("Available");
                getRiderByName(riderName).customersHistory.add(getRiderByName(riderName).customersForThisRider.get(0));
                getRiderByName(riderName).cuisinesHistory.add(getRiderByName(riderName).cuisinesPickedUp.get(0));
                getRiderByName(riderName).restaurantsHistory.add(getRiderByName(riderName).getRiderByName(riderName).customersForThisRider.get(0).restaurantsOfCuisinesIsComing.get(0));
                getRiderByName(riderName).customersForThisRider.get(0).restaurantsHistory.add(getRiderByName(riderName).customersForThisRider.get(0).restaurantsOfCuisinesIsComing.get(0));
                getRiderByName(riderName).customersForThisRider.get(0).cuisinesHistory.add(getRiderByName(riderName).cuisinesPickedUp.get(0));
                getRiderByName(riderName).customersForThisRider.get(0).ridersHistory.add(getRiderByName(riderName));
                getRiderByName(riderName).customersForThisRider.get(0).restaurantsOfCuisinesIsComing.remove(0);
                getRiderByName(riderName).customersForThisRider.get(0).cuisinesIsComing.remove(0);
                getRiderByName(riderName).customersForThisRider.get(0).ridersIsComing.remove(0);
                getRiderByName(riderName).customersForThisRider.remove(0);
                getRiderByName(riderName).cuisinesPickedUp.remove(0);
                System.out.println("Delivered");
            }
        } else {
            System.out.println("You haven't arrived!!");
        }

    }

    public void menuHistory(String riderName) {
        if (getRiderByName(riderName).customersHistory.size() > 0)
            for (int i = 0; i < getRiderByName(riderName).customersHistory.size(); i++) {
                System.out.println(i + 1 + ". " + getRiderByName(riderName).customersHistory.get(i).getName() + " : " + getRiderByName(riderName).restaurantsHistory.get(0).getName() + " " + getRiderByName(riderName).cuisinesHistory.get(i).getFood());
            }
        else {
            System.out.println("No History");
        }
    }

    public Rider getRiderByName(String riderName) {

        for (Rider rider : dataBaseAll.riders) {
            if (rider.getName().equals(riderName)) {
                return rider;
            }
        }
        return null;
    }

    public Rider getRiderByPosition() {
        for (Restaurant restaurant : restaurantNewOrder) {
            Rider chosenRider = new Rider();
            int initialTotalPositionX = dataBaseAll.riders.get(0).getAddress().getLocationX() - restaurant.getAddress().getLocationX();
            int initialTotalPositionY = dataBaseAll.riders.get(0).getAddress().getLocationY() - restaurant.getAddress().getLocationY();
            int shortestPosition = Math.abs(initialTotalPositionX) + Math.abs(initialTotalPositionY);
            for (Rider rider : dataBaseAll.riders) {
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

    public int distanceFromCustomer(String riderName) {
        int totalPositionX = getRiderByName(riderName).getAddress().getLocationX() - getRiderByName(riderName).customersForThisRider.get(0).getAddress().getLocationX();
        int totalPositionY = getRiderByName(riderName).getAddress().getLocationY() - getRiderByName(riderName).customersForThisRider.get(0).getAddress().getLocationY();
        int totalPosition = Math.abs(totalPositionX) + Math.abs(totalPositionY);
        return totalPosition;
    }
}