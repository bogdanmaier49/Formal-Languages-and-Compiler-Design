package com.bogdanmaier;

public class Pair<A, B> {
    private A identifier;
    private B constant;


    public Pair (A identifier, B constant) {
        this.identifier = identifier;
        this.constant = constant;
    }


    public A getIdentifier() {
        return identifier;
    }

    public void setIdentifier(A identifier) {
        this.identifier = identifier;
    }

    public B getConstant() {
        return constant;
    }

    public void setConstant(B constant) {
        this.constant = constant;
    }

}
