import java.util.*;


public class Main {
    public static void main(String[] args) {
        
        VendingMachine vendo = new VendingMachine();
        Main main = new Main();
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("Options: [1] Create a Vending Machine");
            System.out.println("         [2] Test a Vending Machine");
            System.out.println("         [3] Exit");
            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    main.createVendingMachine(vendo);
                case 2: 
                    if (vendo.getRegular() != null)
                        main.vendingFeatures(vendo, main);
                case 3:
                    System.out.println("\tExiting...");
            }
        } while (choice != 3);
    
    }

    public void createVendingMachine(VendingMachine vendo) {
        
        int choice;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Choose Vending Machine: [1] Regular Vending Machine");
            System.out.println("                        [2] Coming Soon...");
            System.out.println("                        [3] Back to Options");
            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    vendo.createRegularVendo();
                case 2:
                    System.out.println("\tCOMING SOON");
                case 3: 
                    System.out.println("\tReturning to Options...");
                default:
                    System.out.println("\tInvalid Option...");
            }
        } while (choice < 1 || choice > 3);

        sc.close();
    }

    public void testVendo(VendingMachine vendo, Main main) {

        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("[1] Test Vending Features");
        System.out.println("[2] Maintenance Features");
        System.out.println("[3] Exit");
        choice = sc.nextInt();

        switch(choice) {
            case 1:
                main.vendingFeatures(vendo, main);
            case 2: 
                main.maintenanceFeatures(vendo, main);
            case 3:
                System.out.println("\tExiting...");
            default:
                System.out.println("\tInvalid Option...");
            
        }

        sc.close();
    }

    public int vendingFeatures (VendingMachine vendo, Main main) {

        ArrayList<Item> item = new ArrayList<>();
        item = vendo.getRegular().getItems();
        int slot;

        main.displayItems(item);

        System.out.println("Enter Slot Number");
        

        return 0;
    }

    public void displayItems(ArrayList<Item> item) {

        for (int i = 0; i < item.size(); i++) {
            System.out.println((i+1) + "Item: " + item.get(i).getName());
            System.out.println("\tPrice: " + item.get(i).getPrice());
            System.out.println("\tCalories: " + item.get(i).getCalories());
            System.out.println("\tAvaliable: " + item.get(i).getQuantity());
            System.out.print("\n");
        }
    }

    public void addItem(Scanner sc, VendingMachine vendo) {

        String name;
        int price, quantity;
        float calories;

        System.out.print("\n\tItem Name: ");
        name = sc.nextLine();
        System.out.print("\n\tItem Price: ");
        price = sc.nextInt();
        System.out.print("\n\tItem Calories: ");
        calories = sc.nextFloat();
        System.out.print("\n\tItem Quantity: ");
        quantity = sc.nextInt();

        if(vendo.addItem(name, price, calories, quantity)) {
            System.out.println("\tItem Added!");
        }
        else {
            System.out.println("\tItem Slots FULL!");
        }
            

        

    }

    public void maintenanceFeatures (VendingMachine vendo, Main main) {

        int choice;
        Scanner sc = new Scanner (System.in);


            System.out.println("\tMAINTENANCE:\n");
            System.out.println("------------------------");
            System.out.println("\t[1] Add Item");
            System.out.println("\t[2] Remove Item");
            System.out.println("\t[3] Restock Item");
            System.out.println("\t[4] Set Price");
            System.out.println("\t[5] Collect Profit");
            System.out.println("\t[6] Add Change");
            System.out.println("\t[7] Exit");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    main.addItem(sc, vendo);
                case 2:

                case 3:

                case 4:

                case 5:

                case 6:

                case 7:
            }
    }

}
