package com.bogdanmaier;

public class SymbolTable {

    private String[][] table;
    private int size;

    public SymbolTable (int size) {
        this.size = size;
        table = new String[size][];
    }

    /**
     * Adds a symbol in the table, if not exists.
     *
     * @param value the symbol to be added.
     * @return the position from the hash table where the symbol was added.
     */
    public int add (String value) {
        int id = find(value);

        if (id == -1) {
            id = hash(value);

            if (table[id] == null) {
                table[id] = new String[1];
                table[id][0] = value;
            } else {
                String[] oldRow = table[id];

                table[id] = new String[oldRow.length + 1];

                for (int i = 0; i < oldRow.length; i++) {
                    table[id][i] = oldRow[i];
                }

                table[id][table[id].length - 1] = value;
            }
        }

        return id;
    }

    /**
     * Searches for a symbol in the table.
     *
     * @param value symbol to search for.
     * @return The position of the symbol in the hash table, -1 if the symbol is not found.
     */
    public int find (String value) {
        int id = hash(value);

        if (table[id] != null) {
            for (String st : table[id]) {
                if (st.equals(value)) {
                    return id;
                }
            }
        }

        return -1;
    }

    /**
     * Get all symbols from a given id.
     *
     * @param id, positive integer
     * @return array with symbols if exists, null otherwise
     */
    public String[] get (int id) {
        if (id >= size || id < 0) {
            return null;
        }

        return table[id];
    }

    /**
     * Computes the hash for a given value.
     *
     * @param val, symbol
     * @return Sum of all ascii codes of a string % size.
     */
    private int hash (String val) {
        int asciiSum = 0;
        for (int i = 0; i < val.length(); i++) {
            asciiSum += (int) val.charAt(i);
        }
        return asciiSum % size;
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();

        sb.append("Symbol Table: \n");
        for (int id = 0; id < table.length; id++) {
            sb.append(id + ": ");
            if (table[id] != null)
                for (int i = 0; i < table[id].length; i++) {
                    sb.append(table[id][i] + " ");
                }

            sb.append('\n');
        }

        return sb.toString();
    }

}
