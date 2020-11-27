package com.bogdanmaier;

public class Transition {

    private State from;
    private State to;
    private String value;

    public Transition (State from, State to, String value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public State getFrom() {
        return from;
    }

    public State getTo() {
        return to;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString () {
        return from + " -" + value + "-> " + to;
    }

}
