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
            sc.nextLine();

            switch(choice) {
                case 1:
                    main.createVendingMachine(vendo, sc);
                    break;
                case 2: 
                    if (vendo.getRegular() != null)
                        main.testVendo(vendo, main);
                    else
                        System.out.println("\tNo Vending Machine to Test");
                    break;
                case 3:
                    System.out.println("\tExiting...");
                    break;
                default:
                    System.out.println("\tInvalid Input");
                    break;
            }
        } while (choice != 3);
    
    }

    public void createVendingMachine(VendingMachine vendo, Scanner sc) {
        
        int choice;

        do {
            System.out.println("Choose Vending Machine: [1] Regular Vending Machine");
            System.out.println("                        [2] Coming Soon...");
            System.out.println("                        [3] Back to Options");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    vendo.createRegularVendo();
                    break;
                case 2:
                    System.out.println("\tCOMING SOON");
                    break;
                case 3: 
                    System.out.println("\tReturning to Options...");
                    break;
                default:
                    System.out.println("\tInvalid Option...");
                    break;
            }
        } while (choice < 1 || choice > 3);
    }

    public void testVendo(VendingMachine vendo, Main main) {

        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("\tVending Machine Testing");
        System.out.println("------------------------------------");
        System.out.println("\t[1] Test Vending Features");
        System.out.println("\t[2] Maintenance Features");
        System.out.println("\t[3] Exit");
        choice = sc.nextInt();
        sc.nextLine();

        switch(choice) {
            case 1:
                main.vendingFeatures(vendo, main, sc);
                break;
            case 2: 
                main.maintenanceFeatures(vendo, main);
                break;
            case 3:
                System.out.println("\tExiting...");
                break;
            default:
                System.out.println("\tInvalid Option...");
                break;
            
        }

        sc.close();
    }

    public int vendingFeatures (VendingMachine vendo, Main main, Scanner sc) {

        Item[] item;
        item = vendo.getRegular().getItems();
        int slot;

        main.displayItems(item);

        System.out.println("Enter Slot Number");
        

        return 0;
    }

    public void displayItems(Item[] item) {

        if (item == null) {
            System.out.println("\tVending Machine Empty");
        }
        else {
            for (int i = 0; i < item.length; i++) {
                System.out.println((i+1) + "Item: " + item[i].getName());
                System.out.println("\tPrice: " + item[i].getPrice());
                System.out.println("\tCalories: " + item[i].getCalories());
                System.out.println("\tAvaliable: " + item[i].getQuantity());
                System.out.print("\n");
            }
        }
    }

    public void addItem(Scanner sc, VendingMachine vendo) {

        String name;
        int price, quantity;
        float calories;

        System.out.print("\n\tItem Name: ");
        name = sc.nextLine();
        System.out.print("\tItem Price: ");
        price = sc.nextInt();
        System.out.print("\tItem Calories: ");
        calories = sc.nextFloat();
        System.out.print("\tItem Quantity: ");
        quantity = sc.nextInt();
        sc.nextLine();

        if (quantity < 1 || quantity > 10) {
            System.out.println("\tQuantity should be 1 to 10");
        }
        else if(vendo.addItem(name, price, calories, quantity)) {
            System.out.println("\tItem Added!");
        }
        else {
            System.out.println("\tItem Slots FULL!");
        }
    }

    public void removeItem (Scanner sc, VendingMachine vendo, Main main) {
        
        int slot;
        Item[] item;

        item = vendo.getRegular().getItems();

        do {    
            main.displayItems(item);
            slot = sc.nextInt();

            if (slot < 1 || slot >= item.length) {
                System.out.println("\tInvalid Choice");
            }
            else{
                vendo.removeItem(slot);
            }

        } while (slot < 1 || slot >= item.length);
           
    }

    public void restockItem (Scanner sc, VendingMachine vendo, Main main) {

        int slot, quantity;
        Item[] item;

        item = vendo.getRegular().getItems();

        do {    
            main.displayItems(item);
            slot = sc.nextInt();

            if (slot < 1 || slot >= item.length) {
                System.out.println("\tInvalid Choice");
            }
            else{
                System.out.println("Free Space: " + (10 - item[slot].getQuantity()));
                System.out.println("Input Quantity: ");
                quantity = sc.nextInt();
                vendo.restockItem(quantity, slot);
            }

        } while (slot < 1 || slot >= item.length);
           
    }

    public void setPrice (Scanner sc, VendingMachine vendo, Main main) {

        int slot, quantity;
        Item[] item;

        item = vendo.getRegular().getItems();

        do {    
            main.displayItems(item);
            slot = sc.nextInt();




        } while(slot < 1 || slot >= item.length);

    }

    public void collectProfit(Scanner sc, VendingMachine vendo, Main main) {

        int profit = vendo.retrieveProfit();
        
        System.out.println("Profit Collected: " + profit);
    }

    public void addChange(Scanner sc, VendingMachine vendo, Main main) {

        int choice;

        System.out.println("Choose Denomination: ");
        System.out.println("[1] One Peso");
        System.out.println("[2] Five Peso");
        System.out.println("[3] Ten Peso");
        System.out.println("[4] Twenty Peso");
        System.out.println("[5] Fifty Peso");
        System.out.println("[6] Hundred Peso");
        System.out.println("[0] Exit");
        choice = sc.nextInt();

        switch(choice){
            case 1:

            case 2:

            case 3: 
        }
    }

    public void maintenanceFeatures (VendingMachine vendo, Main main) {

        int choice;
        Scanner sc = new Scanner (System.in);


            System.out.println("\tMAINTENANCE:");
            System.out.println("-----------------------------");
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
                    break;
                case 2:
                    main.removeItem(sc, vendo, main);
                    break;
                case 3:
                    main.restockItem(sc, vendo, main);
                    break;
                case 4:
                    main.setPrice(sc, vendo, main);
                    break;
                case 5:
                    main.collectProfit(sc, vendo, main);
                    break;
                case 6:
                    main.addChange(sc, vendo, main);
                    break;
                case 7:
            }
    }

}
