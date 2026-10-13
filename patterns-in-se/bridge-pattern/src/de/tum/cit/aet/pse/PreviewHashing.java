package de.tum.cit.aet.pse;

public class PreviewHashing extends Hashing {

    private static final int MAX_LENGTH = 1000;

    public PreviewHashing() {
        super(new SimpleHashAlgorithm());
    }

    @Override
    public String hashDocument(String s) {
        if (s.length() > MAX_LENGTH) {
            throw new IllegalArgumentException();
        }
        return getImplementation().calculateHashCode(s);
    }
}
