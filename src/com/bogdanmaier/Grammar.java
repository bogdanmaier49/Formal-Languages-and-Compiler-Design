package com.bogdanmaier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Grammar {

    private List<String> terminals;
    private List<String> nonTerminals;
    private List<Production> productions;
    private String startSymbol;

    public Grammar (String filePath) throws FileNotFoundException {
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);
        String data = myReader.nextLine();
        nonTerminals = Arrays.asList(data.split(","));
        data = myReader.nextLine();
        terminals = Arrays.asList(data.split(","));
        data = myReader.nextLine();
        startSymbol = data;

        productions = new ArrayList<>();
        while (myReader.hasNextLine()) {
            data = myReader.nextLine();
            String[] sData = data.strip().split("->");
            Production production = new Production((sData[0].trim()));

            String[] rightSide = sData[1].split("\\|");
            for (String rsi: rightSide) {
                String[] productionElems = rsi.trim().split(" ");
                production.getTerminalsAndNonTerminals().add(Arrays.asList(productionElems));
            }
            productions.add(production);
        }
        myReader.close();
    }

    public Production findProduction (String nonTerminal) {
        for (Production production : productions) {
            if (production.getStart().equals(nonTerminal)) {
                return production;
            }
        }

        return null;
    }

    public boolean isNonTerminal (String value) {
        for (String nonTerm : this.nonTerminals) {
            if (nonTerm.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getTerminals() {
        return terminals;
    }

    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public String getStartSymbol () {
        return this.startSymbol;
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();

        sb.append("Terminals: \n");
        sb.append(terminals.toString() + "\n");
        sb.append("Non Terminals: \n");
        sb.append(nonTerminals.toString() + "\n");
        sb.append("Productions: \n");
        for (Production p : productions)
            sb.append(p.toString() + "\n");
        sb.append("Start Symbol: \n").append(startSymbol + "\n");

        return sb.toString();
    }

}
