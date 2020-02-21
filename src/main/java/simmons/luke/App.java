package simmons.luke;

import java.util.ArrayList;
import java.util.Random;
import java.util.Hashtable;
import java.util.Map;

/**
 * App
 * 
 */
public class App 
{
    public static void main( final String[] args)
    {
        Map<Fruitstand, Integer> basketPrices = basketPrices();
        ArrayList<Fruitstand> cheapestFruitstand = new ArrayList<Fruitstand>();
        // Kludge. We should be able to sort the dictionary and
        // grab the first entry. I thought this should be doable
        // with a TreeMap, but it's not working as expected.
        // Instead, we'll just make sure that we always have 
        // the lowest price.
        Integer previousLowestPrice = null;
        for (Map.Entry<Fruitstand, Integer> fruitstandPrices : basketPrices.entrySet()) {
            Fruitstand fruitstand = fruitstandPrices.getKey();
            if (previousLowestPrice == null) {
                previousLowestPrice = fruitstandPrices.getValue();
            }

            if (previousLowestPrice > fruitstandPrices.getValue()) {
                previousLowestPrice = fruitstandPrices.getValue();
                cheapestFruitstand.clear();
                cheapestFruitstand.add(fruitstand);
            }
        }

        Fruitstand bestFruitstand = cheapestFruitstand.get(0);

        System.out.println("Pelle and Kajsa found the cheapest fruitstand: \n");
        System.out.println("Cherries: " + bestFruitstand.cherries.price);
        System.out.println("Peaches: " + bestFruitstand.peaches.price);
    }

    public static Map<Fruitstand, Integer> basketPrices() {
        ArrayList<Fruitstand> fruitstands = seedFruitstands(10);
        Map<Fruitstand, Integer> fruitstandPrices = new Hashtable<Fruitstand, Integer>();

        // Calculate the total price of all the baskets
        // and add them to a dictionary
        for (Fruitstand fruitstand : fruitstands) {
            fruitstandPrices.put(fruitstand, fruitstand.cherries.price + fruitstand.peaches.price);
        }
        return fruitstandPrices;
    }

    public static ArrayList<Fruitstand> seedFruitstands(int number) {
        ArrayList<Fruitstand> fruitstands = new ArrayList<Fruitstand>();
        Random rand = new Random();
        for (int i = 0; i < number; i++) {
            Basket cherries = new Basket();
            cherries.price = rand.nextInt(100);
            Basket peaches = new Basket();
            peaches.price = rand.nextInt(100);
            Fruitstand fruitstand = new Fruitstand();
            fruitstand.cherries = cherries;
            fruitstand.peaches = peaches;
            fruitstands.add(fruitstand);
        }
        return fruitstands;
    }
}
