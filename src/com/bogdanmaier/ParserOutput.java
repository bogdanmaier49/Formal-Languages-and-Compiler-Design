package com.bogdanmaier;

public class ParserOutput {

    private Node<Element> root;
    private Grammar grammar;
    private Parser parser;

    public ParserOutput (Grammar grammar, Parser parser) {
        this.grammar = grammar;
        this.parser = parser;

        buildTree();
    }

    private void buildTree () {
        // root = new Node<String>(new Element());
    }

    public void writeTreeToConsole () {

    }

    public void writeToFile (String filePath) {

    }

}
