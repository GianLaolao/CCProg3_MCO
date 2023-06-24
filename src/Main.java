import java.util.*;


public class Main {
    public static void main(String[] args) {
        
        VendingMachine vendo = new VendingMachine();
        Main main = new Main();

        main.vendingFeatures(vendo, main);
    }

    public void vendingFeatures (VendingMachine vendo, Main main) {

        int choice, slot;
        ArrayList<Item> item = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        vendo.createRegularVendo();

        for (int i = 0; i < 3; i++)
            main.addItem(sc, vendo);

        item =  vendo.getRegular().getItems();

        for (int i = 0; i < item.size(); i++) {

            System.out.println((i+1) + "\tItem: " + item.get(i).getName()); 
            System.out.println("\t\tPrice: " + item.get(i).getPrice());
            System.out.println("\t\tQuantity: " + item.get(i).getQuantity());
            
        }

        sc.close();
    }

    public void addItem(Scanner sc, VendingMachine vendo) {

        String name;
        int price;
        float calories;

        System.out.println("Name, Price, Calories");
        name = sc.nextLine();
        price = sc.nextInt();
        sc.nextLine();
        calories = sc.nextFloat();
        sc.nextLine();

        vendo.addItem(name, price, calories);
    }

    public void maintenanceFeatures (VendingMachine vendo) {

        //code for maintenance features
    }

}
