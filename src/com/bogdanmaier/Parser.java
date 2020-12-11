package com.bogdanmaier;

import java.util.*;

public class Parser {

    private Grammar grammar;
    private Stack<Element> workingStack;
    private Stack<Element> inputStack;
    private int positionIndex;
    private String currentState;
    private Element start;
    private List<Element> parseTreeOutput;

    public Parser (Grammar grammar) {
        this.grammar = grammar;
    }

    public boolean parse (List<String> sequence) throws ParseException {
        currentState = State.NORMAL_STATE;
        positionIndex = 0;
        this.inputStack = new Stack<>();
        this.workingStack = new Stack<>();
        start = new Element(grammar.getStartSymbol());
        inputStack.push(start);
        this.parseTreeOutput = new ArrayList<>();

        while (currentState != State.FINAL_STATE && currentState != State.ERROR_STATE) {

            if (currentState == State.NORMAL_STATE) {
                if (inputStack.empty() && positionIndex == sequence.size()) {
                    success();
                    printStep("success");
                } else if (inputStack.empty()) {
                    momentaryInsuccess();
                    printStep("momentary insuccess");
                } else {
                    Element top = inputStack.peek();
                    if (grammar.isNonTerminal(top.getValue())) {
                        expand();
                        printStep("expand");
                    } else if (positionIndex < sequence.size() && top.getValue().equals(sequence.get(positionIndex))) {
                        advance();
                        printStep("advance");
                    } else {
                        momentaryInsuccess();
                        printStep("momentary insuccess");
                    }
                }
            } else if (currentState == State.BACK_STATE) {
                Element top = workingStack.peek();
                if (grammar.isNonTerminal(top.getValue())) {
                    anotherTry();
                    printStep("another try");
                } else {
                    back();
                    printStep("back");
                }
            }

        }

        if (currentState == State.ERROR_STATE) {
            return false;
        }

        // Set  the parse tree output
        while(! workingStack.empty()) {
            Element top = workingStack.pop();
            if (grammar.isNonTerminal(top.getValue())) {
                this.parseTreeOutput.add(top);
            }
        }
        Collections.reverse(this.parseTreeOutput);

        return true;
    }

    private void printStep (String step) {
        System.out.println("|- " + step + " \n(" + currentState + ", " + positionIndex + ", " + workingStack + ", " + inputStack + ")");
    }

    public List<Element> getParseTreeOutput () {
        return this.parseTreeOutput;
    }

    private void expand () {
        Element element = inputStack.peek();
        element.setProductionNumber(1);
        workingStack.push(element);
        inputStack.pop();

        List<List<String>> terminalsAndNonTerminals = grammar.findProduction(element.getValue()).getTerminalsAndNonTerminals();

        for (int i = terminalsAndNonTerminals.get(0).size() - 1; i >= 0; --i) {
            String s = terminalsAndNonTerminals.get(0).get(i);
            inputStack.push(new Element(s));
        }
    }

    private void advance () {
        positionIndex++;
        workingStack.push(inputStack.peek());
        inputStack.pop();
    }

    private void momentaryInsuccess () {
        currentState = State.BACK_STATE;
    }

    private void back () {
        positionIndex--;
        inputStack.push(workingStack.peek());
        workingStack.pop();
    }

    private void anotherTry () throws ParseException {
        Element element = workingStack.peek();
        if (positionIndex == 0 && element.getValue().equals(start.getValue())) {
            currentState = State.ERROR_STATE;
            throw new ParseException("at position index: " + positionIndex + ", at element: " + element.getValue() + ", with productionNumber: " + element.getProductionNumber());
        }

        workingStack.pop();
        List<List<String>> terminalsAndNonTerminals = grammar.findProduction(element.getValue()).getTerminalsAndNonTerminals();

        for (int i = 0; i < terminalsAndNonTerminals.get(element.getProductionNumber() - 1).size(); ++i) {
            inputStack.pop();
        }

        if (terminalsAndNonTerminals.size() == element.getProductionNumber()) {
            element.setProductionNumber(0);
            inputStack.push(element);
        } else {
            currentState = State.NORMAL_STATE;
            element.setProductionNumber(element.getProductionNumber() + 1);
            workingStack.push(element);
            for (int i = terminalsAndNonTerminals.get(element.getProductionNumber() - 1).size() - 1; i >= 0; --i) {
                String s = terminalsAndNonTerminals.get(element.getProductionNumber() - 1).get(i);
                inputStack.push(new Element(s));
            }
        }
    }

    private void success () {
        currentState = State.FINAL_STATE;
    }

}
