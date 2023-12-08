import Coffee.Drink;
import Coffee.Resource;

public class App {
    public static void main(String[] args) throws Exception {
 
        Data.DrinkRepository drinks = new Data.InMemory.DrinkRepository();

        drinks.add(DrinkFactory.espresso(1.5));
        drinks.add(new Drink("mocha", 7, "USD",
                new Resource("water", 20, "oz"),
                new Resource("coffee", 20, "g"),
                new Resource("cocao", 20, "g"),
                new Resource("sugar", 20, "g"),
                new Resource("cream", 20, "oz")));

        Data.ResourceRepository resources = new Data.InMemory.ResourceRepository();

        resources.add(ResourceFactory.water(500));
        resources.add(ResourceFactory.coffee(100));
        resources.add(new Resource("cocao", 200, "g"));
        resources.add(new Resource("sugar", 200, "g"));
        resources.add(new Resource("cream", 200, "oz"));
        
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.run(resources, drinks);
    }
}
