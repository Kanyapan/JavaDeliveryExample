import java.util.Scanner;

public class Main {
    static DataBaseAll dataBaseAll = new DataBaseAll();
    static Customer customer = new Customer();
    static Rider rider = new Rider();

    static Restaurant restaurant=new Restaurant();
    static Scanner scan = new Scanner(System.in);
    static int menuSelect;

    public static void main(String[] args) {

        dataBaseAll.initialCustomer();
        dataBaseAll.initialRider();
        dataBaseAll.initialRestaurant();

        do {
            mainMenu();
            switch (menuSelect) {
                case 1 -> customer.mainMenuLogin();
                case 2 -> rider.mainMenuLogin();
                case 3 -> restaurant.mainMenuLogin();
            }
        } while (menuSelect > 0);
    }



    public static void mainMenu() {
        System.out.println("================================");
        System.out.println("Who is using?");
        System.out.println("1. Customer");
        System.out.println("2. Rider");
        System.out.println("3. Restaurant");
        System.out.print("Select :");
        menuSelect = scan.nextInt();


    }


}
