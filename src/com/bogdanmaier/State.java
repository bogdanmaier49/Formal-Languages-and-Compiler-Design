package com.bogdanmaier;

public class State {

    private String value;

    public State (String value) {
        this.value = value;
    }

    public String getValue () {
        return value;
    }

    @Override
    public String toString () {
        return value;
    }

}
