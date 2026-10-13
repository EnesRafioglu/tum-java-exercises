package de.tum.cit.aet.pse.buildables;

import de.tum.cit.aet.pse.buildables.burgeringredients.*;

public class IngredientsListBuilder implements Builder<IngredientsList> {

    private IngredientsList ingredientsList = new IngredientsList();

    @Override
    public Builder<IngredientsList> addPatty(Patty patty) {
        ingredientsList.getBurgerPatties().add(patty);
        return this;
    }

    @Override
    public Builder<IngredientsList> addBun(Bun bun) {
        ingredientsList.getBuns().add(bun);
        return this;
    }

    @Override
    public Builder<IngredientsList> addLettuce(Lettuce lettuce) {
        ingredientsList.getLettuceList().add(lettuce);
        return this;
    }

    @Override
    public Builder<IngredientsList> addTomato(Tomato tomato) {
        ingredientsList.getTomatoes().add(tomato);
        return this;
    }

    @Override
    public Builder<IngredientsList> addOnion(Onion onion) {
        ingredientsList.getOnions().add(onion);
        return this;
    }

    @Override
    public Builder<IngredientsList> addPickle(Pickle pickle) {
        ingredientsList.getPickles().add(pickle);
        return this;
    }

    @Override
    public Builder<IngredientsList> addCheese(Cheese cheese) {
        ingredientsList.getCheeseList().add(cheese);
        return this;
    }

    @Override
    public Builder<IngredientsList> addSauce(Sauce sauce) {
        ingredientsList.getSauces().add(sauce);
        return this;
    }

    @Override
    public Builder<IngredientsList> reset() {
        ingredientsList = new IngredientsList();
        return this;
    }

    @Override
    public IngredientsList getResult() {
        return ingredientsList;
    }
}
