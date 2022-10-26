import java.sql.*;
import java.util.ArrayList;

public class Repository {

    final String DB_URL = "jdbc:sqlite:deliveryData.db";
    final String USER = "";
    final String PASS = "";
    static ArrayList<Customer> customers = new ArrayList<Customer>();
    static ArrayList<Rider> riders = new ArrayList<Rider>();
    static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

    public void initialCustomer() {

        Address customerAddressA = new Address();
        Address customerAddressB = new Address();
        Address customerAddressC = new Address();
        Address customerAddressD = new Address();
        Customer a = new Customer(1,"A", customerAddressA);
        Customer b = new Customer(2,"B", customerAddressB);
        Customer c = new Customer(3,"C", customerAddressC);
        Customer d = new Customer(4,"D", customerAddressD);
        customers.add(a);
        customers.add(b);
        customers.add(c);
        customers.add(d);

    }

    public void initialRider() {
        String QUERY = "SELECT id, name FROM Rider";
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY)) {
            // Extract data from result set
            while (rs.next()) {
                Address riderAddress = new Address("", (int) (Math.random() * 1000), (int) (Math.random() * 1000));
                // Retrieve by column name
                Rider rider = new Rider(rs.getInt("id"),rs.getString("name"), riderAddress);
                riders.add(rider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void initialRestaurant() {
        Address cupandaAddress = new Address("116 A'Beckett St", 350, 405);
        Address gongchaAddress = new Address("GongCha", 427, 397);
        Address kfcAddress = new Address("144 Elizabeth St", 255, 113);
        Address mcdonaldAddress = new Address("123 Queen St", 650, 444);

        Restaurant cupanda = new Restaurant("CuPanda", cupandaAddress);
        Restaurant gongcha = new Restaurant("GongCha", gongchaAddress);
        Restaurant kfc = new Restaurant("KFC", kfcAddress);
        Restaurant mcdonald = new Restaurant("McDonald", mcdonaldAddress);

        Cuisine cupandaCuisine1 = new Cuisine("Fried Rice", 15);
        Cuisine cupandaCuisine2 = new Cuisine("Roast Duck", 20);
        Cuisine cupandaCuisine3 = new Cuisine("Noodle", 16);
        Cuisine cupandaCuisine4 = new Cuisine("Pork Dumpling", 10);
        cupanda.cuisines.add(cupandaCuisine1);
        cupanda.cuisines.add(cupandaCuisine2);
        cupanda.cuisines.add(cupandaCuisine3);
        cupanda.cuisines.add(cupandaCuisine4);

        Cuisine gongchaCuisine1 = new Cuisine("Milk Tea", 6);
        Cuisine gongchaCuisine2 = new Cuisine("Matcha", 6);
        Cuisine gongchaCuisine3 = new Cuisine("Coconut Milk", 6);
        Cuisine gongchaCuisine4 = new Cuisine("Strawberry Yogurt", 8);
        gongcha.cuisines.add(gongchaCuisine1);
        gongcha.cuisines.add(gongchaCuisine2);
        gongcha.cuisines.add(gongchaCuisine3);
        gongcha.cuisines.add(gongchaCuisine4);

        Cuisine kfcCuisine1 = new Cuisine("Fried Chicken", 15);
        Cuisine kfcCuisine2 = new Cuisine("Nugget", 20);
        Cuisine kfcCuisine3 = new Cuisine("Burger", 16);
        Cuisine kfcCuisine4 = new Cuisine("French Fries", 10);
        kfc.cuisines.add(kfcCuisine1);
        kfc.cuisines.add(kfcCuisine2);
        kfc.cuisines.add(kfcCuisine3);
        kfc.cuisines.add(kfcCuisine4);

        Cuisine mcdonaldCuisine1 = new Cuisine("McFish", 6);
        Cuisine mcdonaldCuisine2 = new Cuisine("CheeseBurger", 4);
        Cuisine mcdonaldCuisine3 = new Cuisine("BigMac", 8);
        Cuisine mcdonaldCuisine4 = new Cuisine("Angus", 12);
        mcdonald.cuisines.add(mcdonaldCuisine1);
        mcdonald.cuisines.add(mcdonaldCuisine2);
        mcdonald.cuisines.add(mcdonaldCuisine3);
        mcdonald.cuisines.add(mcdonaldCuisine4);

        restaurants.add(cupanda);
        restaurants.add(gongcha);
        restaurants.add(kfc);
        restaurants.add(mcdonald);
    }


}
