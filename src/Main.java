import java.util.*;


public class Main {
    public static void main(String[] args) {
        
        VendingMachine vendo = new VendingMachine();
        Main main = new Main();
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\nOptions: [1] Create a Vending Machine");
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
            System.out.println("\nChoose Vending Machine: [1] Regular Vending Machine");
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
            System.out.println("\n\tVending Machine Testing");
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
        int slot, quantity;
        MoneyBox payment;
        boolean check;

        System.out.println("\n\tTEST VENDING FEATURES");
        System.out.println("================================");

        do {
            main.displayItems(item);
            System.out.println("\nInput [0] to Exit and cancel Transaction");

            System.out.print("Enter Slot Number: ");
            slot = sc.nextInt();

            check = checkSlot(slot - 1, item);

            if (check)
                System.out.print("\n\tEnter Itam Quantity: ");
                quantity = sc.nextInt();
                sc.nextLine();

                if (quantity <= item[slot-1].getQuantity()) {

                    payment = main.getPayment(sc, vendo);
                    System.out.println("\tDispensing Item...");
                    Item dispensed = vendo.dispenseItem(slot, quantity);
                    
                    //TODO
                }
                else {
                    System.out.println("\tInvalid Item Quantity");
                    check = !check;
                }

                //main.displayTransacInfo(dispensed, payment);
            
        } while (!check); 
        return 0;
    }

    public MoneyBox getPayment(Scanner sc, VendingMachine vendo){

        int choice;

        do {
            System.out.println("\nCurrent Money: " + vendo.getUserMoney().getTotal());
            System.out.println("\nInput Payment: [1] One Peso (1)");
            System.out.println("               [2] Five Peso (5)");
            System.out.println("               [3] Ten Peso (10)");
            System.out.println("               [4] Twenty Peso (20)");
            System.out.println("               [5] Fifty Peso (50)");
            System.out.println("               [6] One Hundred Peso (100)");
            System.out.println("               [0] Confirm Payment");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1: case 2: case 3: case 4: case 5: case 6:
                    vendo.takePayment(choice);
                case 0:
                    System.out.println("\tPayment confirmed...");
                default:
                    System.out.println("\tInvalid Input...");
            }

        } while (choice != 0);  

        return vendo.getUserMoney();
    }

    public void displayTransacInfo(Item dispensed, int payment) {

        System.out.println("\nItem Bought: " + dispensed.getName());
        System.out.println("\tCalories: " + dispensed.getCalories());

        //not done
        //TODO
    }

    public void displayItems(Item[] item) {

        for (int i = 0; i < item.length; i++) {        
            if (item[i] != null) {
                System.out.println("\nSlot Number " + (i+1)); 
                System.out.println("Item: " + item[i].getName());
                System.out.println("\tPrice: " + item[i].getPrice());
                System.out.println("\tCalories: " + item[i].getCalories());
                System.out.println("\tAvaliable: " + item[i].getQuantity());
            }
        }
    }

    public boolean checkSlot(int slot, Item[] item) {

        if (slot == -1) {
            System.out.println("\n\tExiting....");
            return false;
        }
        else if (slot < 0 || slot > item.length || item[slot] == null) {
            System.out.println("\n\tInvalid Choice");
            return false;
        }

        return true;
    }

    public void addItem(Scanner sc, VendingMachine vendo, Main main) {

        String name;
        int price, quantity;
        float calories;

        System.out.println("\n\tADD NEW ITEM");
        System.out.println("==============================");
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
            System.out.println("\n\tQuantity should be 1 to 10");
        }
        else if(vendo.addItem(name, price, calories, quantity)) {
            System.out.println("\n\tItem Added!");
        }
        else {
            System.out.println("\n\tItem Slots FULL!");
        }
    }

    public void removeItem (Scanner sc, VendingMachine vendo, Main main) {
        
        int slot;
        Item[] item;

        item = vendo.getRegular().getItems();

        System.out.println("\n\tREMOVE ITEM");
        System.out.println("==============================");

        do {    
            main.displayItems(item);
            System.out.println("\nInput [0] to Exit");
            System.out.print("Input Item Slot Number: ");
            slot = sc.nextInt();
            sc.nextLine();
            
            if (main.checkSlot(slot - 1, item)){
                vendo.removeItem(slot-1);
            }

        } while (slot < 0 || slot > item.length);       
    }

    public int restockItem (Scanner sc, VendingMachine vendo, Main main) {

        int slot, quantity;
        Item[] item;

        item = vendo.getRegular().getItems();

        System.out.println("\n\tRESTOCK ITEM");
        System.out.println("==============================");

        do {    
            main.displayItems(item);
            System.out.println("\nInput [0] to Exit");
            System.out.print("Input Item Slot Number: ");
            slot = sc.nextInt();

            if(slot == 0){
                System.out.println("\tExiting...");
                return 0;
            }

            if (main.checkSlot(slot - 1, item)){
                System.out.println("\n\tFree Space: " + (10 - item[slot -1].getQuantity()));
                System.out.println("Input Quantity: ");
                quantity = sc.nextInt();

                if (vendo.restockItem(quantity, slot-1))
                    System.out.println("\tItem restocked!");
                else 
                    System.out.println("\tQuantity Overflow!");
            }

        } while (slot < 0 || slot > item.length || item[slot -1] == null);

        return 1;
    }

    public int setPrice (Scanner sc, VendingMachine vendo, Main main) {

        int slot, price;
        Item[] item;

        item = vendo.getRegular().getItems();

        System.out.println("\n\tSET ITEM PRICE");
        System.out.println("==============================");

        do {    
            main.displayItems(item);
            System.out.println("\nInput [0] to Exit");
            System.out.print("Input Item Slot Number: ");
            slot = sc.nextInt();

            
            if (slot == 0) {
                System.out.println("\tExiting...");
                return 0;
            }

            System.out.println("Enter new Price: ");
            price = sc.nextInt();
            sc.nextLine();
            
            if (vendo.setItemPrice(price, slot-1))
                System.out.println("\tItem Price Chaanged!");
            else
                System.out.println("\tInvalid Slot");


        } while(slot < 0 || slot > item.length);

        return 1;
    }

    public void collectProfit(Scanner sc, VendingMachine vendo, Main main) {

        int profit = vendo.retrieveProfit();
        
        System.out.println("\n\tProfit Collected: " + profit);
    }

    public void addChange(Scanner sc, VendingMachine vendo, Main main) {

        int choice, quantity = 0;

        System.out.println("\n\tADD CHANGE");
        System.out.println("==============================");

        do {

            System.out.println("Vendo Money: ");
            System.out.println("One Peso Quantity:\t" + vendo.getVendoMoney().getOnePeso().getQuantity());
            System.out.println("Five Peso Quantity:\t" + vendo.getVendoMoney().getFivePeso().getQuantity());
            System.out.println("Ten Peso Quantity:\t" + vendo.getVendoMoney().getTenPeso().getQuantity());
            System.out.println("Twenty Peso Quantity:\t" + vendo.getVendoMoney().getTwentyPeso().getQuantity());
            System.out.println("Fifty Peso Quantity:\t" + vendo.getVendoMoney().getFiftyPeso().getQuantity());
            System.out.println("One Hundred Peso Quantity:\t" + vendo.getVendoMoney().getHundredPeso().getQuantity());

            System.out.println("\nChoose Denomination: ");
            System.out.println("[1] One Peso");
            System.out.println("[2] Five Peso");
            System.out.println("[3] Ten Peso");
            System.out.println("[4] Twenty Peso");
            System.out.println("[5] Fifty Peso");
            System.out.println("[6] Hundred Peso");
            System.out.println("[0] Exit");
            choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("\tExiting...");
            }
            else if (choice > 0 && choice <= 6) {
                System.out.print("Add Quantity: ");
                quantity = sc.nextInt();
                sc.nextLine();
                
                vendo.addMoney(quantity, choice);
            }
            else
                System.out.println("\tInvalid Input...");

        } while (choice != 0);
    }

    public void showSummary (VendingMachine vendo, Main main) {

        ArrayList<Record> record = new ArrayList<>();
        
        record = vendo.getRecords();
        String indent = "                ";
    
        System.out.println("\t\t\tStarting Inventory \tEnding Inventory \tQuantity Sold");

        for (int i = 0; i < record.size(); i++) {
        //    System.out.println(record.get(i).getItem().getName() + "\t\t" + record.get(i).getStartingInventory() + "\t\t" + record.get(i).getItem().getQuantity() + "\t\t" + record.get(i).getSold());
            String output = record.get(i).getItem().getName();
            output += indent.substring(0, indent.length() - output.length());
            System.out.printf("%s \t%-15d \t%-15d \t%-17d\n",
            output,
            record.get(i).getStartingInventory(),
            record.get(i).getItem().getQuantity(),
            record.get(i).getSold());
        }

        System.out.println("\nTotal Sales: " + vendo.getTotalSales());
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
            System.out.println("\t[7] Show Transaction Summary");
            System.out.println("\t[8] Exit");
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
                    main.showSummary(vendo, main);
                    break;
                case 8:
                    System.out.println("\tExiting....");
                    break;
                default:
                    System.out.println("\tInvalid Option...");
            }
        } while (choice != 8);
    }

}
