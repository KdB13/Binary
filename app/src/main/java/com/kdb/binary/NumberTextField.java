package com.kdb.binary;

import android.text.InputType;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.kdb.binary.util.Converter;

/**
 * It contains state related to an editable number which is will be converted from one number
 * system to another, when its value changes.
 */
public class NumberTextField {

    /**
     * The displayed number inside text field
     */
    public final ObservableField<String> value = new ObservableField<>();

    /**
     * The error to be displayed if the number doesn't belong to the specified number system
     */
    public final ObservableField<String> error = new ObservableField<>();

    /**
     * The number system of this number in string (eg: Binary)
     */
    public final ObservableField<String> numberSystem;

    /**
     * The input type of this text field.
     */
    public final ObservableInt inputType;

    /**
     * An input type for entering signed numbers.
     */
    public static final int INPUT_TYPE_NUMBER = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED;

    /**
     * An input type for entering numbers which also contains alphabetic letters.
     */
    public static final int INPUT_TYPE_TEXT = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;

    public NumberTextField(final String numberSystem, final int base) {
        this.numberSystem = new ObservableField<>(numberSystem);

        // Set the input type based on number system base
        this.inputType = new ObservableInt();
        setInputType(base);
    }

    /**
     * Sets the correct input type based on specified number system base.
     *
     * @param base The number system base
     */
    public void setInputType(final int base) {
        switch (base) {
            case Converter.BASE_BINARY:
            case Converter.BASE_DECIMAL:
            case Converter.BASE_OCTAL:
                inputType.set(INPUT_TYPE_NUMBER);
                break;

            case Converter.BASE_HEX:
                inputType.set(INPUT_TYPE_TEXT);
        }
    }

}
