package de.tum.cit.aet.pse;

import java.util.ArrayList;
import java.util.List;

public class ConcreteMediator implements Mediator {

    private final List<Colleague> colleagues = new ArrayList<>();
    @Override
    public void send(String message, Colleague sender) {
        for (Colleague colleague : colleagues) {
            if (colleague != sender) {
                colleague.receive(message);
            }
        }
    }

    public void addColleague(Colleague colleague) {
        colleagues.add(colleague);
    }
}
