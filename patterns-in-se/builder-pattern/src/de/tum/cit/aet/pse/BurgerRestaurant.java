package de.tum.cit.aet.pse;

import de.tum.cit.aet.pse.buildables.Burger;
import de.tum.cit.aet.pse.buildables.Builder;
import de.tum.cit.aet.pse.buildables.burgeringredients.*;

public class BurgerRestaurant {

    public Burger buildStandardBeefBurger(Builder<Burger> builder) {
        builder.reset();

        builder.addPatty(Patty.BEEF_PATTY)
                .addSauce(Sauce.KETCHUP)
                .addCheese(Cheese.AMERICAN_CHEESE)
                .addLettuce(Lettuce.ICEBERG_LETTUCE)
                .addPickle(Pickle.CORNICHON)
                .addBun(Bun.BRIOCHE_BUN)
                .addOnion(Onion.CARAMELIZED_ONION)
                .addTomato(Tomato.BEEFSTEAK_TOMATO);

        return builder.getResult();
    }

    public Burger buildSpecialBeefBurger(Builder<Burger> builder) {
        builder.reset();

        builder.addPatty(Patty.BEEF_PATTY)
                .addPatty(Patty.BEEF_PATTY)
                .addSauce(Sauce.MAYO)
                .addSauce(Sauce.KETCHUP)
                .addSauce(Sauce.BBQ_SAUCE)
                .addCheese(Cheese.BRIE_CHEESE)
                .addCheese(Cheese.CHEDDAR_CHEESE)
                .addBun(Bun.SESAME_BUN)
                .addPickle(Pickle.SPICY_SOUR_PICKLE)
                .addLettuce(Lettuce.ROMAINE_LETTUCE)
                .addOnion(Onion.CARAMELIZED_ONION)
                .addTomato(Tomato.BEEFSTEAK_TOMATO);

        return builder.getResult();
    }

    public Burger buildStandardChickenBurger(Builder<Burger> builder) {
        builder.reset();

        builder.addPatty(Patty.CHICKEN_PATTY)
                .addOnion(Onion.DEEP_FRIED_ONION)
                .addTomato(Tomato.CHERRY_TOMATO)
                .addBun(Bun.CIABATTA_BUN)
                .addLettuce(Lettuce.GREEN_LEAF_LETTUCE)
                .addPickle(Pickle.FULL_SOUR_PICKLE)
                .addCheese(Cheese.GOUDA_CHEESE)
                .addSauce(Sauce.YELLOW_MUSTARD)
                .addSauce(Sauce.CHIMICHURRI_SAUCE);

        return builder.getResult();
    }
}
