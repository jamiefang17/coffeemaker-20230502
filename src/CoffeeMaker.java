import java.util.Scanner;

import Coffee.Drink;
import Coffee.Resource;
import Data.ResourceRepository;
import Data.DrinkRepository;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMaker {

    public void run(ResourceRepository resourceItems, DrinkRepository drinkItems) throws Exception {
        Map<String, Resource> resources = new HashMap<String, Resource>();
        for (Resource resource : resourceItems) {
            resources.put(resource.name.toUpperCase(), resource);
        }
        
        Map<String, Drink> drinks = new HashMap<String, Drink>();
    
        for (Drink drink : drinkItems) {
            drinks.put(drink.name.toUpperCase(), drink);
        }

        boolean machineOn = true;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("\033[H\033[2J"); // clear console
            System.out.flush();
            System.out.println("What would you like? (espresso/latte/cappuccino)");
            String input = scanner.next();

            switch (input.toUpperCase()) {
                case "OFF":
                    machineOn = false;
                    System.out.println("Shutting down... Press [Enter] to continue.");
                    System.in.read();
                    break;
                case "REPORT":
                    System.out.println("Current Status:");
                    for (Resource resource : resources.values()) {
                        System.out.println(String.format("%s=%s%s", resource.name, resource.amount, resource.measure));
                    }
                    System.out.println("Press [Enter] to continue.");
                    System.in.read();
                    break;
                case "MENU":
                    System.out.println("Current Menu:");
                    for (Drink drink : drinks.values()) {
                        System.out.println(
                                String.format("%s %s%s", drink.name, drink.price, drink.currency));
                    }

                    System.out.println("Press [Enter] to continue.");
                    System.in.read();
                    break;
                default:
                    Drink item = drinks.getOrDefault(input.toUpperCase(), null);
                    
                    // handle invalid selection
                    if (item == null) {
                        System.out.println("Invalid selection, please try again. Press [Enter] to continue.");
                        System.in.read();
                    } else if (item.hasRequiredResources(resources)) {

                        // ask for money
                        double amountPaid = 0;
                        int coins;

                        System.out.println(String.format("Please pay %s%s:", item.price, item.currency));
                        System.out.println("How many quarters?");

                        coins = scanner.nextInt();
                        amountPaid += coins * .25;

                        System.out.println("How many dimes?");
                        coins = scanner.nextInt();
                        amountPaid += coins * .10;

                        System.out.println("How many nickles?");
                        coins = scanner.nextInt();
                        amountPaid += coins * .05;

                        System.out.println("How many pennies?");
                        coins = scanner.nextInt();
                        amountPaid += coins * .01;

                        if (amountPaid < item.price) {
                            System.out.println("Sorry not enough money...");
                        } else {
                            item.brew(resources);
                            System.out.println(String.format("Here is your change %.2f%s",
                                    amountPaid - item.price, item.currency));
                            System.out.println(String.format("Here is your %s. Enjoy!", item.name));
                        }
                    } else {
                        // cannot make right now
                        System.out.println("Sorry not enough resource to make your order...");
                    }

                    System.out.println("Press [Enter] to continue.");
                    System.in.read();
                    break;
            }

        } while (machineOn);
        scanner.close();

        System.out.println("Goodbye.");

    }
}
