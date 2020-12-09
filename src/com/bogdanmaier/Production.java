package com.bogdanmaier;

import java.util.ArrayList;
import java.util.List;

public class Production {

    private String start;

    private List<List<String>> results;

    public Production (String start) {
        this.start = start;
        results = new ArrayList<>();
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public List<List<String>> getResults() {
        return results;
    }

    public void setResults(List<List<String>> results) {
        this.results = results;
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();
        sb.append(results.toString() + '\n');
        sb.append(start);
        sb.append(" -> ");
        for (int i = 0; i < results.size(); i++) {
            for (String production : results.get(i)) {
                sb.append(production);
            }
            if (i < results.size() - 1)
                sb.append(" | ");
        }
        return sb.toString();
    }
}
