package de.tum.cit.aet.pse;

public interface Observer<T> {

	void onUpdate(T newState);
}
