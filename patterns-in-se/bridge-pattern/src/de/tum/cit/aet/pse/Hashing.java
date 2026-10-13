package de.tum.cit.aet.pse;

public abstract class Hashing {

    public abstract String hashDocument(String s);

    private HashFunction implementation;

    public Hashing(HashFunction implementation) {
        this.implementation = implementation;
    }

    public HashFunction getImplementation() {
        return implementation;
    }
}
