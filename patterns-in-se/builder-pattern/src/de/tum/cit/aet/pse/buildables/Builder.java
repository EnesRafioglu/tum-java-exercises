package de.tum.cit.aet.pse.buildables;

import de.tum.cit.aet.pse.buildables.burgeringredients.*;

public interface Builder<T> {

    Builder<T> addPatty(Patty patty);

    Builder<T> addBun(Bun bun);

    Builder<T> addLettuce(Lettuce lettuce);

    Builder<T> addTomato(Tomato tomato);

    Builder<T> addOnion(Onion onion);

    Builder<T> addPickle(Pickle pickle);

    Builder<T> addCheese(Cheese cheese);

    Builder<T> addSauce(Sauce sauce);

    Builder<T> reset();

    T getResult();



    // TODO 4: Implement the reset() method, which resets the Builder to the initialization state.

}
