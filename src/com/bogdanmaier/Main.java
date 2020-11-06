package com.bogdanmaier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static final String BASE_PATH = "/Users/bogdanmaier/Documents/faculta/Limbaje formale/lab2/src/";

    public static void writeToFile (String filePath, String content) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to write the output file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(10);
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(symbolTable);
        System.out.println(new File(".").getAbsolutePath());

        try {
            lexicalAnalyzer.scan(BASE_PATH + "p3.in");
            writeToFile(BASE_PATH + "PIF.out", lexicalAnalyzer.toString());
            writeToFile(BASE_PATH + "ST.out", lexicalAnalyzer.getSymbolTable().toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to open the input file");
        } catch (LexicException e) {
            writeToFile(BASE_PATH + "PIF.out", "Lexical error at line " + e.getLine());
            writeToFile(BASE_PATH + "ST.out", "Lexical error at line " + e.getLine());
        }
    }
}
