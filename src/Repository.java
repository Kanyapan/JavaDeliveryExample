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


    public void initialCustomer(Connection conn) {
        String QUERYAddress = "SELECT name, locationX, locationY FROM Address";
        String QUERYCustomer = "SELECT id , name FROM Customer";

        // Open a connection
        try {
            Statement stmtAddress = conn.createStatement();
            Statement stmtCustomer = conn.createStatement();
            ResultSet rsAddress = stmtAddress.executeQuery(QUERYAddress);
            ResultSet rsCustomer = stmtCustomer.executeQuery(QUERYCustomer);
            while (rsCustomer.next()) {
                Address customerAddress = new Address(rsAddress.getString("name"), rsAddress.getInt("locationX"), rsAddress.getInt("locationY"));
                // Retrieve by column name
                Customer customer = new Customer(rsCustomer.getInt("id"), rsCustomer.getString("name"), customerAddress);
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void initialRider(Connection conn) {
        String QUERY = "SELECT id, name FROM Rider";
        // Open a connection
        try {
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

    public void initialRestaurant(Connection conn) {


        // Open a connection
        try {
            String QUERYRestaurantAddress = "SELECT Address.name,locationX,locationY FROM Address INNER JOIN Restaurant ON Address.name=Restaurant.address";
            String QUERYRestaurant = "SELECT id,name FROM Restaurant";
            Statement stmtRestaurant = conn.createStatement();
            Statement stmtRestaurantAddress = conn.createStatement();
            ResultSet rsRestaurantAddress = stmtRestaurantAddress.executeQuery(QUERYRestaurantAddress);
            ResultSet rsRestaurant = stmtRestaurant.executeQuery(QUERYRestaurant);

            while (rsRestaurant.next()) {
                String QUERTCuisine = "SELECT food,price FROM Cuisine WHERE restaurant=" + "\"" + rsRestaurant.getString("name") + "\"";
                Statement stmtCuisine = conn.createStatement();
                ResultSet rsCuisine = stmtCuisine.executeQuery(QUERTCuisine);
                rsRestaurantAddress.next();
                Address restaurantAddress = new Address(rsRestaurantAddress.getString("name"), rsRestaurantAddress.getInt("locationX"), rsRestaurantAddress.getInt("locationY"));
                // Retrieve by column name
                Restaurant restaurant = new Restaurant(rsRestaurant.getInt("id"), rsRestaurant.getString("name"), restaurantAddress);
                while (rsCuisine.next()) {
                    Cuisine cuisine = new Cuisine(rsCuisine.getString("food"), rsCuisine.getInt("price"));
                    restaurant.cuisines.add(cuisine);
                }


                restaurants.add(restaurant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
