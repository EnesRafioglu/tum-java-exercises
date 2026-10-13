package de.tum.cit.aet.pse;

public class EnterpriseHashing extends Hashing {

    public EnterpriseHashing() {
        super(new CryptoSecureHashAlgorithm());
    }

    @Override
    public String hashDocument(String s) {
        return getImplementation().calculateHashCode(s);
    }
}
