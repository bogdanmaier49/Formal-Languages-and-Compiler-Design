package com.bogdanmaier;
import java.util.Scanner;

public class Main {


    public static void printMenu () {
        System.out.println("1. Show set of states");
        System.out.println("2. Show alphabet");
        System.out.println("3. Show transitions");
        System.out.println("4. Show initial state");
        System.out.println("5. Show final states");
        System.out.println("6. Check sequence");
        System.out.println("0. Quit");
    }

    public static void main(String[] args) {

        Automata automata = new Automata();

        Scanner scanner = new Scanner(System.in);

        String command = "";
        do {
            printMenu();

            command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.println(automata.getStates());
                    break;
                case "2":
                    System.out.println(automata.getAlphabet());
                    break;
                case "3":
                    System.out.println(automata.getTransitions());
                    break;
                case "4":
                    System.out.println(automata.getInitialState());
                    break;
                case "5":
                    System.out.println(automata.getFinalStates());
                case "6":
                    String sequence = scanner.nextLine();
                    if (automata.checkSequence(sequence)) {
                        System.out.println("The sequence [" + sequence + "] is accepted");
                    } else {
                        System.out.println("The sequence [" + sequence + "] is NOT accepted");
                    }
            }

            System.out.println();
        } while (!command.equals("0"));

    }
}
