package com.kdb.binary;

import android.content.Context;

import com.kdb.binary.util.Converter;

import java.util.HashMap;

public class NumberSystemsRepository {

    private static NumberSystemsRepository INSTANCE;

    private HashMap<String, Integer> numberSystems;

    private NumberSystemsRepository(Context context) {
        load(context);
    }

    public static NumberSystemsRepository getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (NumberSystemsRepository.class) {
                INSTANCE = new NumberSystemsRepository(context);
            }
        }

        return INSTANCE;
    }

    /**
     * Loads the {@link #numberSystems} HashMap with the names of number systems as the keys
     * and their bases as the values. The names are obtained from string resources.
     */
    private void load(final Context context) {
        numberSystems = new HashMap<>();

        // Assign the name-base pairs
        numberSystems.put(context.getString(R.string.binary), Converter.BASE_BINARY);
        numberSystems.put(context.getString(R.string.octal), Converter.BASE_OCTAL);
        numberSystems.put(context.getString(R.string.decimal), Converter.BASE_DECIMAL);
        numberSystems.put(context.getString(R.string.hex), Converter.BASE_HEX);
    }

    public HashMap<String, Integer> getNumberSystems() {
        return numberSystems;
    }

}
