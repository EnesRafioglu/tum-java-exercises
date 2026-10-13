package de.tum.cit.aet.pse;

public final class Client {

	private Client() {
	}

	public static void main(String[] args) {

        System.out.println("Test");

		
        ConcreteMediator mediator = new ConcreteMediator();
        InOfficeColleague allan = new InOfficeColleague(mediator, "Allan");
        InOfficeColleague barbara = new InOfficeColleague(mediator, "Barbara");
        MobileColleague cleo = new MobileColleague(mediator, "Cleo");
        mediator.addColleague(allan);
        mediator.addColleague(barbara);
        mediator.addColleague(cleo);
        allan.send("Hello World from Allan");
        cleo.send("Hello from Cleo on her mobile");

	}
}
