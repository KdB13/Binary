package com.kdb.binary;

import androidx.databinding.ObservableField;

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

    public NumberTextField(final String numberSystem) {
        this.numberSystem = new ObservableField<>(numberSystem);
    }

}
