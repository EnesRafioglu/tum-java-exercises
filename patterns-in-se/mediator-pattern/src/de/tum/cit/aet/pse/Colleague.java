package de.tum.cit.aet.pse;

public abstract class Colleague {

    private final Mediator mediator;

    private String name;

    public Colleague(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public abstract void receive(String message);

    public void send(String message) {
        mediator.send(message, this);
    }


}
