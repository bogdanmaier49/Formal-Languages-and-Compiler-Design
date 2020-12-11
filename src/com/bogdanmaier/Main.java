package com.bogdanmaier;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void printMenu () {
        System.out.println("1. Show terminals");
        System.out.println("2. Show non terminals");
        System.out.println("3. Show set of productions");
        System.out.println("4. Show production of a given non terminal");
        System.out.println("0. Quit");
    }

    public static void main(String[] args) {

        try {
            Grammar grammar = new Grammar("/Users/bogdanmaier/Documents/faculta/Limbaje formale/lab5/src/g1.txt");
            Parser parser = new Parser(grammar);
            System.out.println(grammar);

            try {
                System.out.println("Parse result for [aacbc]: " + parser.parse(Arrays.asList(new String[]{"a", "a", "c", "b", "c"})));
                System.out.println("Parse tree: " + parser.getParseTreeOutput());
                ParserOutput parserOutput = new ParserOutput(grammar, parser);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            Scanner scanner = new Scanner(System.in);

            String command = "";
            do {
                printMenu();

                command = scanner.nextLine();

                switch (command) {
                    case "1":
                        System.out.println(grammar.getTerminals());
                        break;
                    case "2":
                        System.out.println(grammar.getNonTerminals());
                        break;
                    case "3":
                        for (Production p : grammar.getProductions())
                            System.out.println(p);
                        break;
                    case "4":
                        String terminal = scanner.nextLine();
                        System.out.println(grammar.findProduction(terminal));
                        break;
                }

                System.out.println();
            } while (!command.equals("0"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
