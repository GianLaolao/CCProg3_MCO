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
                        main.testVendo(vendo, main, sc);
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

    public void testVendo(VendingMachine vendo, Main main, Scanner sc) {

        int choice;

        do {
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
        } while (choice != 3);    
    }

    public int vendingFeatures (VendingMachine vendo, Main main, Scanner sc) {

        Item[] item;
        item = vendo.getRegular().getItems();
        int slot, quantity, payment;
        boolean check;

        do {
            main.displayItems(item);
            System.out.println("\nInput [0] to Exit and cancel Transaction");

            System.out.print("Enter Slot Number");
            slot = sc.nextInt();

            check = checkSlot(slot, item);

            if (check)
                System.out.print("\n\tEnter Itam Quantity: ");
                quantity = sc.nextInt();
                sc.nextLine();

                if (quantity <= item[slot].getQuantity()) {

                    System.out.println("\tDispensing Item...");
                    Item dispensed = vendo.dispenseItem(slot, quantity);
                    main.displayItemInfo(dispensed, payment);
                }
                else {
                    System.out.println("\tInvalid Item Quantity");
                    check = !check;
                }
            
        } while (!check); 
        return 0;
    }

    public void displayItemInfo(Item dispensed, int payment) {

        System.out.println("Item Bought: ");
    }

    public void displayItems(Item[] item) {

        if (item == null) {
            System.out.println("\tVending Machine Empty");
        }
        else {
            for (int i = 0; i < item.length; i++) {
                
                if (item[i] != null)
                    System.out.println("Slot Number " + (i+1)); 
                    System.out.println("Item: " + item[i].getName());
                    System.out.println("\tPrice: " + item[i].getPrice());
                    System.out.println("\tCalories: " + item[i].getCalories());
                    System.out.println("\tAvaliable: " + item[i].getQuantity());
                    System.out.print("\n");
            }
        }
    }

    public boolean checkSlot(int slot, Item[] item) {

        if (slot == 0) {
            System.out.println("\tExiting");
            return false;
        }
        else if (slot < 0 || slot > item.length || item[slot] == null) {
            System.out.println("\tInvalid Choice");
            return false;
        }

        return true;
    }

    public void addItem(Scanner sc, VendingMachine vendo, Main main) {

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
            System.out.println("\nInput [0] to Exit");
            System.out.print("Input Item Slot Number: ");
            slot = sc.nextInt();
            sc.nextLine();
            
            if (main.checkSlot(slot, item)){
                vendo.removeItem(slot-1);
            }

        } while (slot < 0 || slot > item.length);       
    }

    public void restockItem (Scanner sc, VendingMachine vendo, Main main) {

        int slot, quantity;
        Item[] item;

        item = vendo.getRegular().getItems();

        do {    
            main.displayItems(item);
            System.out.println("\nInput [0] to Exit");
            System.out.print("Input Item Slot Number: ");

            slot = sc.nextInt();

            if (main.checkSlot(slot, item)){
                System.out.println("Free Space: " + (10 - item[slot].getQuantity()));
                System.out.println("Input Quantity: ");
                quantity = sc.nextInt();

                if (vendo.restockItem(quantity, slot-1))
                    System.out.println("\tItem restocked!");
                else 
                    System.out.println("\tQuantity Overflow!");

                
            }

        } while (slot < 0 || slot > item.length || item[slot] == null);
           
    }

    public void setPrice (Scanner sc, VendingMachine vendo, Main main) {

        int slot, price;
        Item[] item;

        item = vendo.getRegular().getItems();

        do {    
            main.displayItems(item);

            slot = sc.nextInt();

            System.out.println("Enter new Price: ");
            price = sc.nextInt();
            sc.nextLine();
        
            if (vendo.setItemPrice(price, slot-1))
                System.out.println("\tItem Price Chaanged!");
            else
                System.out.println("\tInvalid Slot");


        } while(slot < 0 || slot > item.length);

    }

    public void collectProfit(Scanner sc, VendingMachine vendo, Main main) {

        int profit = vendo.retrieveProfit();
        
        System.out.println("Profit Collected: " + profit);
    }

    public void addChange(Scanner sc, VendingMachine vendo, Main main) {

        int choice, quantity = 0;

        do {
            System.out.println("Choose Denomination: ");
            System.out.println("[1] One Peso");
            System.out.println("[2] Five Peso");
            System.out.println("[3] Ten Peso");
            System.out.println("[4] Twenty Peso");
            System.out.println("[5] Fifty Peso");
            System.out.println("[6] Hundred Peso");
            System.out.println("[0] Exit");
            choice = sc.nextInt();

            if (choice > 0 && choice <= 6) {
                System.out.print("Add Quantity: ");
                quantity = sc.nextInt();
                sc.nextLine();
            }

            switch(choice){
                case 1:
                    vendo.getMoney().setOnePeso(quantity);
                case 2:
                    vendo.getMoney().setFivePeso(quantity);
                case 3: 
                    vendo.getMoney().setTenPeso(quantity);
                case 4:
                    vendo.getMoney().setTwentyPeso(quantity);
                case 5:
                    vendo.getMoney().setFiftyPeso(quantity);
                case 6:
                    vendo.getMoney().setHundredPeso(quantity);
                case 0:

                default:
            }
        } while (choice != 0);
    }

    public void maintenanceFeatures (VendingMachine vendo, Main main) {

        int choice;
        Scanner sc = new Scanner (System.in);

        do {
            System.out.println("\n\tMAINTENANCE:");
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
                    main.addItem(sc, vendo, main);
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
                    System.out.println("\tExiting....");
                    break;
                default:
                    System.out.println("\tInvalid Option...");
            }
        } while (choice != 7);
    }

}
