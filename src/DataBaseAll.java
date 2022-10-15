import java.util.ArrayList;

public class DataBaseAll {

    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static ArrayList<Rider> riders = new ArrayList<Rider>();
    public static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();



    public static void initialCustomer() {

        Address customerA_Address = new Address("120 A'Beckett St", 30, 40);
        Address customerB_Address = new Address("90 Colin St", 60, 70);
        Address customerC_Address = new Address("144 Elizabeth St", 50, 50);
        Address customerD_Address = new Address("123 Queen St", 20, 60);
        Customer A = new Customer("A", customerA_Address);
        Customer B = new Customer("B", customerB_Address);
        Customer C = new Customer("C", customerC_Address);
        Customer D = new Customer("D", customerD_Address);
        customers.add(A);
        customers.add(B);
        customers.add(C);
        customers.add(D);

    }

    public static void initialRider() {

        Address riderX_Address = new Address("", (int)(Math.random()*1000), (int)(Math.random()*1000));
        Address riderY_Address = new Address("", (int)(Math.random()*1000), (int)(Math.random()*1000));
        Address riderZ_Address = new Address("", (int)(Math.random()*1000), (int)(Math.random()*1000));
        Rider X = new Rider("X", riderX_Address);
        Rider Y = new Rider("Y", riderY_Address);
        Rider Z = new Rider("Z", riderZ_Address);
        riders.add(X);
        riders.add(Y);
        riders.add(Z);

    }
    public static void initialRestaurant() {
        Address cupanda_Address = new Address("116 A'Beckett St", 350, 405);
        Address gongcha_Address = new Address("90 Colin St", 427, 397);
        Address kfc_Address = new Address("144 Elizabeth St", 255, 113);
        Address mcdonald_Address = new Address("123 Queen St", 650, 444);

        Restaurant cupanda = new Restaurant("CuPanda", cupanda_Address);
        Restaurant gongcha = new Restaurant("GongCha", gongcha_Address);
        Restaurant kfc = new Restaurant("KFC", kfc_Address);
        Restaurant mcdonald = new Restaurant("McDonald", mcdonald_Address);

        Cuisine cupanda_Cuisine1 = new Cuisine("Fried Rice",15);
        Cuisine cupanda_Cuisine2 = new Cuisine("Roast Duck",20);
        Cuisine cupanda_Cuisine3 = new Cuisine("Noodle",16);
        Cuisine cupanda_Cuisine4 = new Cuisine("Pork Dumpling",10);
        cupanda.cuisines.add(cupanda_Cuisine1);
        cupanda.cuisines.add(cupanda_Cuisine2);
        cupanda.cuisines.add(cupanda_Cuisine3);
        cupanda.cuisines.add(cupanda_Cuisine4);

        Cuisine gongcha_Cuisine1 = new Cuisine("Milk Tea",6);
        Cuisine gongcha_Cuisine2 = new Cuisine("Matcha",6);
        Cuisine gongcha_Cuisine3 = new Cuisine("Coconut Milk",6);
        Cuisine gongcha_Cuisine4 = new Cuisine("Strawberry Yogurt",8);
        gongcha.cuisines.add(gongcha_Cuisine1);
        gongcha.cuisines.add(gongcha_Cuisine2);
        gongcha.cuisines.add(gongcha_Cuisine3);
        gongcha.cuisines.add(gongcha_Cuisine4);

        Cuisine kfc_Cuisine1 = new Cuisine("Fried Chicken",15);
        Cuisine kfc_Cuisine2 = new Cuisine("Nugget",20);
        Cuisine kfc_Cuisine3 = new Cuisine("Burger",16);
        Cuisine kfc_Cuisine4 = new Cuisine("French Fries",10);
        kfc.cuisines.add(kfc_Cuisine1);
        kfc.cuisines.add(kfc_Cuisine2);
        kfc.cuisines.add(kfc_Cuisine3);
        kfc.cuisines.add(kfc_Cuisine4);

        Cuisine mcdonald_Cuisine1 = new Cuisine("McFish",6);
        Cuisine mcdonald_Cuisine2 = new Cuisine("CheeseBurger",4);
        Cuisine mcdonald_Cuisine3 = new Cuisine("BigMac",8);
        Cuisine mcdonald_Cuisine4 = new Cuisine("Angus",12);
        mcdonald.cuisines.add(mcdonald_Cuisine1);
        mcdonald.cuisines.add(mcdonald_Cuisine2);
        mcdonald.cuisines.add(mcdonald_Cuisine3);
        mcdonald.cuisines.add(mcdonald_Cuisine4);

        restaurants.add(cupanda);
        restaurants.add(gongcha);
        restaurants.add(kfc);
        restaurants.add(mcdonald);
    }




}
