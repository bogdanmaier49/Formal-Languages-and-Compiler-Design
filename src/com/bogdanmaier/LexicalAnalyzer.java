package com.bogdanmaier;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LexicalAnalyzer {

    private SymbolTable symbolTable;
    private List<Pair<String, Integer>> PIF;

    private String[] reservedWords = {"const", "function", "let", "int", "console_out", "console_int", "if", "for", "while", "math_sqrt", "void", "else"};
    private String[] operators = {"+", ">", "<", "&&", ">=", "<=", "-", "/", "%", "!", "!=", "=", "=>"};
    private String[] separators = {":", ";", "{", "}", "(", ")", "[", "]", ","};

    public LexicalAnalyzer (SymbolTable sb) {
        this.symbolTable = sb;
        this.PIF = new ArrayList<>();
    }

    public void scan (String filePath) throws IOException, LexicException {
        File file = new File(filePath);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        int lineNumber = 0;
        while ((line = br.readLine()) != null) {
            String[] lineTokens = detect(line);

            for (String token: lineTokens) {
                System.out.println("Token: " + token);
                if (isReservedWord(token) || isSeparator(token) || isOperator(token)) {
                    genPif(token, 0);
                } else if (isIdentifier(token) || isConstant(token)) {
                    int index = symbolTable.add(token);
                    genPif(token, index);
                } else {
                    throw new LexicException(lineNumber, "Lexical error at:");
                }
            }

            lineNumber++;
        }

    }

    private void genPif (String token, int constant) {
        PIF.add(new Pair<String, Integer> (token, constant));
    }

    private boolean isReservedWord (String token) {
        for (String reservedWord: reservedWords) {
            if (reservedWord.equals(token)) {
                return true;
            }
        }

        return false;
    }

    private boolean isOperator (String token) {
        for (String operator: operators) {
            if (operator.equals(token)) {
                return true;
            }
        }

        return false;
    }

    private boolean isSeparator (String token) {
        for (String separator: separators) {
            if (separator.equals(token)) {
                return true;
            }
        }

        return false;
    }

    private boolean isIdentifier (String token) {
        return token.matches("[a-zA-Z]+[a-zA-Z0-9]*");
    }

    private boolean isStringConstant (String token) {
        if (token.charAt(0) != '"' || token.charAt(token.length() - 1) != '"') {
            return false;
        }

        return true;
    }

    private boolean isIntConstant (String token) {
        for (int i = 0; i < token.length(); i++) {
            if (token.charAt(i) < '0' || token.charAt(i) > '9') {
                return false;
            }
        }

        return true;
    }

    private boolean isBoolConstant (String token) {
        return (token.equals("true") || token.equals("false"));
    }

    private boolean isConstant (String token) {
        return isStringConstant(token) || isIntConstant(token) || isBoolConstant(token);
    }

    private String[] detect (String line) {
        List<String> tokens = new ArrayList<>();

        String newToken = "";
        boolean stringConstantStarted = false;
        for (int i = 0; i < line.length(); i++) {

            if (line.charAt(i) == '"' && stringConstantStarted == false) {
                stringConstantStarted = true;
            } else if (line.charAt(i) == '"' && stringConstantStarted == false) {
                stringConstantStarted = false;
            }

            if (isSeparator(line.charAt(i) + "") || isOperator(line.charAt(i) + "") ||  line.charAt(i) == ' ') {
                if (stringConstantStarted == false) {
                    tokens.add(newToken);
                    newToken = "";
                    if (line.charAt(i) != ' ') {
                        tokens.add(line.charAt(i) + "");
                    }
                }
            } else {
                newToken += line.charAt(i);
            }
        }
        tokens.add(newToken);

        String[] resultTokens  = tokens.stream().filter(token -> !token.equals("")).toArray(String[]::new);

        for (String token: resultTokens) {
            System.out.println("[" + token + "]");
        }

        return resultTokens;
    }

    public SymbolTable getSymbolTable () {
        return symbolTable;
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();

        sb.append("Program Internal Form: \n");
        for (Pair<String, Integer> pair : PIF) {
            sb.append(pair.getIdentifier() + " | " + pair.getConstant() + "\n");
        }

        return sb.toString();
    }

}
