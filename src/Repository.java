import javax.management.Query;
import java.sql.*;
import java.util.ArrayList;

public class Repository {

    static ArrayList<Customer> customers = new ArrayList<Customer>();
    static ArrayList<Rider> riders = new ArrayList<Rider>();
    static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
    final String DB_URL = "jdbc:sqlite:deliveryData.db";
    final String USER = "";
    final String PASS = "";


    public Connection connectDB() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void initialCustomer() {
        String QUERYAddress = "SELECT name, locationX, locationY FROM Address";
        String QUERYCustomer = "SELECT id , name FROM Customer";

        // Open a connection
        try {
            Connection conn = connectDB();
            Statement stmtAddress = conn.createStatement();
            Statement stmtCustomer = conn.createStatement();
            ResultSet rsAddress = stmtAddress.executeQuery(QUERYAddress);
            ResultSet rsCustomer = stmtCustomer.executeQuery(QUERYCustomer);
            while (rsCustomer.next()) {
                Address customerAddress = new Address(rsAddress.getString ("name"),rsAddress.getInt("locationX"), rsAddress.getInt("locationY"));
                // Retrieve by column name
                Customer customer = new Customer(rsCustomer.getInt("id"), rsCustomer.getString("name"), customerAddress);
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void initialRider() {
        String QUERY = "SELECT id, name FROM Rider";
        // Open a connection
        try {
            Connection conn = connectDB();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                Address riderAddress = new Address("", (int) (Math.random() * 1000), (int) (Math.random() * 1000));
                // Retrieve by column name
                Rider rider = new Rider(rs.getInt("id"), rs.getString("name"), riderAddress);
                riders.add(rider);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void initialRestaurant() {



        // Open a connection
        try {
            String QUERYRestaurantAddress = "SELECT Address.name,locationX,locationY FROM Address INNER JOIN Restaurant ON Address.name=Restaurant.address";
            String QUERYRestaurant="SELECT id,name FROM Restaurant";
            Connection conn = connectDB();
            Statement stmtRestaurant = conn.createStatement();
            Statement stmtRestaurantAddress = conn.createStatement();
            ResultSet rsRestaurantAddress = stmtRestaurantAddress.executeQuery(QUERYRestaurantAddress);
            ResultSet rsRestaurant = stmtRestaurant.executeQuery(QUERYRestaurant);

            while (rsRestaurant.next()) {
                String QUERTCuisine= "SELECT food,price FROM Cuisine WHERE restaurant="+"\""+rsRestaurant.getString("name")+"\"";
                Statement stmtCuisine = conn.createStatement();
                ResultSet rsCuisine = stmtCuisine.executeQuery(QUERTCuisine);
                rsRestaurantAddress.next();
                Address restaurantAddress = new Address(rsRestaurantAddress.getString("name"),rsRestaurantAddress.getInt("locationX"), rsRestaurantAddress.getInt("locationY"));
                // Retrieve by column name
                Restaurant restaurant = new Restaurant(rsRestaurant.getInt("id"), rsRestaurant.getString("name"), restaurantAddress);
                    while (rsCuisine.next()){
                        Cuisine cuisine=new Cuisine(rsCuisine.getString("food"),rsCuisine.getInt("price"));
                        restaurant.cuisines.add(cuisine);
                    }


                restaurants.add(restaurant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
//        Address cupandaAddress = new Address("116 A'Beckett St", 350, 405);
//        Address gongchaAddress = new Address("GongCha", 427, 397);
//        Address kfcAddress = new Address("144 Elizabeth St", 255, 113);
//        Address mcdonaldAddress = new Address("123 Queen St", 650, 444);
//
//        Restaurant cupanda = new Restaurant();
//        Restaurant gongcha = new Restaurant();
//        Restaurant kfc = new Restaurant();
//        Restaurant mcdonald = new Restaurant();
//
//        Cuisine cupandaCuisine1 = new Cuisine("Fried Rice", 15);
//        Cuisine cupandaCuisine2 = new Cuisine("Roast Duck", 20);
//        Cuisine cupandaCuisine3 = new Cuisine("Noodle", 16);
//        Cuisine cupandaCuisine4 = new Cuisine("Pork Dumpling", 10);
//        cupanda.cuisines.add(cupandaCuisine1);
//        cupanda.cuisines.add(cupandaCuisine2);
//        cupanda.cuisines.add(cupandaCuisine3);
//        cupanda.cuisines.add(cupandaCuisine4);
//
//        Cuisine gongchaCuisine1 = new Cuisine("Milk Tea", 6);
//        Cuisine gongchaCuisine2 = new Cuisine("Matcha", 6);
//        Cuisine gongchaCuisine3 = new Cuisine("Coconut Milk", 6);
//        Cuisine gongchaCuisine4 = new Cuisine("Strawberry Yogurt", 8);
//        gongcha.cuisines.add(gongchaCuisine1);
//        gongcha.cuisines.add(gongchaCuisine2);
//        gongcha.cuisines.add(gongchaCuisine3);
//        gongcha.cuisines.add(gongchaCuisine4);
//
//        Cuisine kfcCuisine1 = new Cuisine("Fried Chicken", 15);
//        Cuisine kfcCuisine2 = new Cuisine("Nugget", 20);
//        Cuisine kfcCuisine3 = new Cuisine("Burger", 16);
//        Cuisine kfcCuisine4 = new Cuisine("French Fries", 10);
//        kfc.cuisines.add(kfcCuisine1);
//        kfc.cuisines.add(kfcCuisine2);
//        kfc.cuisines.add(kfcCuisine3);
//        kfc.cuisines.add(kfcCuisine4);
//
//        Cuisine mcdonaldCuisine1 = new Cuisine("McFish", 6);
//        Cuisine mcdonaldCuisine2 = new Cuisine("CheeseBurger", 4);
//        Cuisine mcdonaldCuisine3 = new Cuisine("BigMac", 8);
//        Cuisine mcdonaldCuisine4 = new Cuisine("Angus", 12);
//        mcdonald.cuisines.add(mcdonaldCuisine1);
//        mcdonald.cuisines.add(mcdonaldCuisine2);
//        mcdonald.cuisines.add(mcdonaldCuisine3);
//        mcdonald.cuisines.add(mcdonaldCuisine4);
//
//        restaurants.add(cupanda);
//        restaurants.add(gongcha);
//        restaurants.add(kfc);
//        restaurants.add(mcdonald);
    }


}
