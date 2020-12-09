package com.bogdanmaier;

public class Pair {

    private String key;
    private String value;

    public Pair (String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey () {
        return this.key;
    }

    public String getValue () {
        return this.value;
    }

    @Override
    public String toString () {
        return "(" + key + "->" + value + ")";
    }

}
