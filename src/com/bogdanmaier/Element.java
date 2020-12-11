package com.bogdanmaier;

public class Element {
    private String value;
    private int productionNumber;

    public Element (String value) {
        this.productionNumber = 0;
        this.value = value;
    }

    public String getValue () {
        return value;
    }

    public int getProductionNumber () {
        return productionNumber;
    }

    public void setValue (String val) {
        this.value = val;
    }

    public void setProductionNumber (int val) {
        this.productionNumber = val;
    }

    @Override
    public String toString() {
        return value + productionNumber;
    }

}
