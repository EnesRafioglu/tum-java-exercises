package de.tum.cit.aet.pse;

public class InOfficeColleague extends Colleague {

    public InOfficeColleague(Mediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void receive(String message) {
        System.out.println("Received " + message + " from the office!");
    }


}
