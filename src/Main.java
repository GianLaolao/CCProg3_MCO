import java.util.*;


public class Main {
    public static void main(String[] args) {
        
        VendingMachine vendo = new VendingMachine();
        Main main = new Main();
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\nVending Machine Options: ");
            System.out.println("    [1] Create a Vending Machine");
            System.out.println("    [2] Test a Vending Machine");
            System.out.println("    [3] Exit");
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
                    if(vendo.createRegularVendo())
                        System.out.println("\n\tVending Machine created...");
                    else
                        System.out.println("\t\nCreation failed...");
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
        boolean check;

        System.out.println("\n\tTEST VENDING FEATURES");
        System.out.println("===================================");

        do {
            main.displayItems(item);
            System.out.println("\nInput [0] to Exit and cancel Transaction");

            System.out.print("Enter Slot Number: ");
            slot = sc.nextInt();

            if(slot == 0)
                return 0;

            check = checkSlot(slot - 1, item);

            if (check) {

                do {
                    System.out.print("\n\tEnter Item Quantity: ");
                    quantity = sc.nextInt();
                    sc.nextLine();

                    if (quantity == 0) {
                        System.out.println("\n\tTransaction Cancelled...");
                        return 0;
                    }

                    if (quantity > item[slot-1].getQuantity() || quantity < 0) {
                            System.out.println("\tInvalid Item Quantity");
                        
                    }
                } while (quantity > item[slot-1].getQuantity() || quantity < 0);
                               
                
                if (main.getPayment(sc, vendo, slot - 1, quantity) == 8) {
                    MoneyBox change = vendo.getUserMoney();
                    System.out.println("\tMoney Returned...");
                    System.out.println("----------------------");
                    System.out.println("    One Peso          : " + change.getOnePeso().getQuantity());
                    System.out.println("    Five Peso         : " + change.getFivePeso().getQuantity());
                    System.out.println("    Ten Peso          : " + change.getTenPeso().getQuantity());
                    System.out.println("    Twenty Peso       : " + change.getTwentyPeso().getQuantity());
                    System.out.println("    Fifty Peso        : " + change.getFiftyPeso().getQuantity());
                    System.out.println("    One Hundred Peso  : " + change.getHundredPeso().getQuantity());
                    System.out.println("\nTotal: " + change.getTotal());
                    vendo.resetUserMoney();
                    return 0;
                }
                    
                int moneyPaid = vendo.getUserMoney().getTotal(); 
                MoneyBox change = vendo.produceChange(slot - 1, quantity);

                if(change == null) {
                    System.out.println("\n\tNot Enough Change. Transaction Canceled...");
                    return 0;
                }

                System.out.println("\tDispensing Item...");
                Item dispensed = vendo.dispenseItem(slot - 1, quantity);
                System.out.println("\tItem Dispensed...");
                main.displayTransacInfo(dispensed, change, moneyPaid, quantity);
                    
            }
        } while (!check); 

        

        return 1;
    }

    public int getPayment(Scanner sc, VendingMachine vendo, int slot, int quantity){

        int choice;
        boolean check = false;
        do {
            System.out.println("\nPrice: " + vendo.getRegular().getItem(slot).getPrice() * quantity);
            System.out.println("Current Money: " + vendo.getUserMoney().getTotal());
            System.out.println("\nInput Payment: [1] One Peso (1)");
            System.out.println("               [2] Five Peso (5)");
            System.out.println("               [3] Ten Peso (10)");
            System.out.println("               [4] Twenty Peso (20)");
            System.out.println("               [5] Fifty Peso (50)");
            System.out.println("               [6] One Hundred Peso (100)");
            System.out.println("\n               [7] Confirm Payment");
            System.out.println("               [8] Cancel Payment");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1: case 2: case 3: case 4: case 5: case 6:
                    vendo.takePayment(choice);
                    break;
                case 7:
                    if(vendo.checkUserMoney(slot, quantity)) {
                        System.out.println("\tPayment confirmed...");
                        check = true;
                    }
                    else
                        System.out.println("\tNot enough Money");
                    break;
                case 8:
                    System.out.println("\tTransaction Cancelled...");
                    check = true;
                    break;
                default:
                    System.out.println("\tInvalid Input...");
            }

        } while (!check);  

        return choice;
    }

    public void displayTransacInfo(Item dispensed, MoneyBox change, int payment, int quantity) {

        System.out.println("---------------------------------------");
        System.out.println("\nItem Bought: " + dispensed.getName());
        System.out.println("\tItem Price: " + dispensed.getPrice());
        System.out.println("\tCalories: " + dispensed.getCalories());
        System.out.println("---------------------------------------");
        System.out.println("Quantity: " + quantity);
        System.out.println("Gross Price: " + dispensed.getPrice() * quantity);
        System.out.println("\nAmount Paid: " + payment);
        System.out.println("---------------------------------------");
        System.out.println("\nChange Summary: ");
        System.out.println("    One Peso          : " + change.getOnePeso().getQuantity());
        System.out.println("    Five Peso         : " + change.getFivePeso().getQuantity());
        System.out.println("    Ten Peso          : " + change.getTenPeso().getQuantity());
        System.out.println("    Twenty Peso       : " + change.getTwentyPeso().getQuantity());
        System.out.println("    Fifty Peso        : " + change.getFiftyPeso().getQuantity());
        System.out.println("    One Hundred Peso  : " + change.getHundredPeso().getQuantity());
        System.out.println("\nTotal Change: " + change.getTotal());
        System.out.println("---------------------------------------");
    }

    public void displayItems(Item[] item) {

        for (int i = 0; i < item.length; i++) {        
            if (item[i] != null) {
                System.out.println("\nSlot Number " + (i+1)); 
                System.out.println("Item: \t" + item[i].getName());
                System.out.println("\n   Price: \t" + item[i].getPrice());
                System.out.println("   Calories: \t" + item[i].getCalories());
                System.out.println("   Avaliable: \t" + item[i].getQuantity());
                System.out.println("--------------------------");
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
        Item[] item = vendo.getRegular().getItems();
        int freeSlot = 0;

        for (int i = 0; i < item.length; i++) {
            if (item[i] == null) 
                freeSlot++;        
        }


        System.out.println("\n\tADD NEW ITEM");
        System.out.println("==============================");
        System.out.println("Free Slots: " + freeSlot);
        System.out.print("\n\tItem Name: ");
        name = sc.nextLine();
        System.out.print("\tItem Price: ");
        price = sc.nextInt();
        System.out.print("\tItem Calories: ");
        calories = sc.nextFloat();
        System.out.print("\tItem Quantity: ");
        quantity = sc.nextInt();
        sc.nextLine();

        if (quantity < 0 || quantity > 10) {
            System.out.println("\n\tQuantity should be 0 to 10");
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

        } while (slot < 0 || slot > item.length || item[slot -1] == null || slot != 0);

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

            System.out.println("\nVendo Money: ");
            System.out.println("One Peso Quantity:\t\t: " + vendo.getVendoMoney().getOnePeso().getQuantity());
            System.out.println("Five Peso Quantity:\t\t: " + vendo.getVendoMoney().getFivePeso().getQuantity());
            System.out.println("Ten Peso Quantity:\t\t: " + vendo.getVendoMoney().getTenPeso().getQuantity());
            System.out.println("Twenty Peso Quantity:\t\t: " + vendo.getVendoMoney().getTwentyPeso().getQuantity());
            System.out.println("Fifty Peso Quantity:\t\t: " + vendo.getVendoMoney().getFiftyPeso().getQuantity());
            System.out.println("One Hundred Peso Quantity:\t: " + vendo.getVendoMoney().getHundredPeso().getQuantity());

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
        System.out.println("-------------------------------------------------------------------------------");
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
            System.out.println("\t[0] Exit");
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
                case 0:
                    System.out.println("\tExiting....");
                    break;
                default:
                    System.out.println("\tInvalid Option...");
            }
        } while (choice != 0);
    }

}
