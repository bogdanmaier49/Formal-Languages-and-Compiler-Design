package com.bogdanmaier;

import java.util.ArrayList;
import java.util.List;

public class Production {

    private String start;

    private List<List<String>> terminalsAndNonTerminals;

    public Production (String start) {
        this.start = start;
        terminalsAndNonTerminals = new ArrayList<>();
    }

    public String getStart() {
        return start;
    }

    public List<List<String>> getTerminalsAndNonTerminals() {
        return terminalsAndNonTerminals;
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();
        sb.append(terminalsAndNonTerminals.toString() + '\n');
        sb.append(start);
        sb.append(" -> ");
        for (int i = 0; i < terminalsAndNonTerminals.size(); i++) {
            for (String production : terminalsAndNonTerminals.get(i)) {
                sb.append(production);
            }
            if (i < terminalsAndNonTerminals.size() - 1)
                sb.append(" | ");
        }
        return sb.toString();
    }
}
