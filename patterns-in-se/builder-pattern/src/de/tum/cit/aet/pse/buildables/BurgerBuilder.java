package de.tum.cit.aet.pse.buildables;

import de.tum.cit.aet.pse.buildables.burgeringredients.*;

public class BurgerBuilder implements Builder<Burger> {

    private static final int PATTY_SLOTS = 2;
    private static final int CHEESE_SLOTS = 2;
    private static final int SAUCE_SLOTS = 3;

    private Burger burger = new Burger();

    private int saucesAdded = 0;
    private int pattiesAdded = 0;
    private int cheeseAdded = 0;

    @Override
    public Builder<Burger> addPatty(Patty patty) {
        if (pattiesAdded >= PATTY_SLOTS) {
            throw new IllegalStateException("Cannot add more patties. Limit: " + PATTY_SLOTS);
        }

        if (pattiesAdded == 0) {
            burger.setBurgerPatty1(patty);
        } else {
            burger.setBurgerPatty2(patty);
        }

        pattiesAdded++;
        return this;
    }

    @Override
    public Builder<Burger> addBun(Bun bun) {
        burger.setBun(bun);
        return this;
    }

    @Override
    public Builder<Burger> addLettuce(Lettuce lettuce) {
        burger.setLettuce(lettuce);
        return this;
    }

    @Override
    public Builder<Burger> addTomato(Tomato tomato) {
        burger.setTomato(tomato);
        return this;
    }

    @Override
    public Builder<Burger> addOnion(Onion onion) {
        burger.setOnion(onion);
        return this;
    }

    @Override
    public Builder<Burger> addPickle(Pickle pickle) {
        burger.setPickle(pickle);
        return this;
    }

    @Override
    public Builder<Burger> addCheese(Cheese cheese) {
        if (cheeseAdded >= CHEESE_SLOTS) {
            throw new IllegalStateException("Cannot add more cheese slices. Limit: " + CHEESE_SLOTS);
        }

        if (cheeseAdded == 0) {
            burger.setCheese1(cheese);
        } else {
            burger.setCheese2(cheese);
        }

        cheeseAdded++;
        return this;
    }

    @Override
    public Builder<Burger> addSauce(Sauce sauce) {
        if (saucesAdded >= SAUCE_SLOTS) {
            throw new IllegalStateException("Cannot add more sauces. Limit: " + SAUCE_SLOTS);
        }

        if (saucesAdded == 0) {
            burger.setSauce1(sauce);
        } else if (saucesAdded == 1) {
            burger.setSauce2(sauce);
        } else {
            burger.setSauce3(sauce);
        }

        saucesAdded++;
        return this;
    }

    @Override
    public Builder<Burger> reset() {
        burger = new Burger();
        saucesAdded = 0;
        pattiesAdded = 0;
        cheeseAdded = 0;
        return this;
    }

    @Override
    public Burger getResult() {
        return burger;
    }
}
