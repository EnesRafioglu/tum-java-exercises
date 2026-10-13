package de.tum.cit.aet.pse;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the RemoteControl used to control the drone with.
 * It keeps a history with the previously sent commands and functions to control the drone with
 */
public class RemoteControl {

	private final List<Command> history;

    public List<Command> getHistory() {
        return history;
    }

    public RemoteControl() {
        history = new ArrayList<>();
    }

    public void controlDrone(Command command) {
        history.add(command);
        command.execute();
    }
}
