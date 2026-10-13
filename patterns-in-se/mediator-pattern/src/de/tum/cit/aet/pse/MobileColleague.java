package de.tum.cit.aet.pse;

public class MobileColleague extends Colleague {

    public MobileColleague(Mediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void receive(String message) {

        System.out.println("Received " + message + " in mobile!");

    }
}
