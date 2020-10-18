package com.bogdanmaier;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        SymbolTable st = new SymbolTable(5);

        st.add("abc");

        st.add("a12");
        st.add("a12");

        st.add("ilk");
        st.add("bho");
        st.add("a100");
        st.add("a90");
        st.add("wvb");
        st.add("pil");



        System.out.println(st.toString());

        System.out.println("Symbols from position 0 are: " + Arrays.toString(st.get(0)));
    }
}
