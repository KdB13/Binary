package com.kdb.binary;

import android.app.Application;
import android.text.TextUtils;

import androidx.lifecycle.AndroidViewModel;

import com.kdb.binary.util.Converter;

import java.util.HashMap;

public class HomeViewModel extends AndroidViewModel {

    private HashMap<String, Integer> numberSystems;

    public final NumberTextField number1;
    public final NumberTextField number2;

    public HomeViewModel(Application application) {
        super(application);

        // Get the number systems HashMap from repository
        numberSystems = NumberSystemsRepository.getInstance(getApplication()).getNumberSystems();

        // Initialize the NumberModel objects with default number systems
        number1 = new NumberTextField(getApplication().getString(R.string.binary));
        number2 = new NumberTextField(getApplication().getString(R.string.decimal));
    }

    public void onNumber1Changed(final String newNumber) {

        if (!newNumber.equals(number1.value.get())) {
            number1.value.set(newNumber);

            // Convert number 1
            convertNum1();
        }
    }

    public void onNumber2Changed(final String newNumber) {

        if (!newNumber.equals(number2.value.get())) {
            number2.value.set(newNumber);

            // Convert number 2
            convertNum2();
        }
    }

    public void onNumberSystem1Changed(final String newNumberSystem) {

        if (!newNumberSystem.equals(number1.numberSystem.get())) {
            number1.numberSystem.set(newNumberSystem);

            // Convert number 2 to new number system
            convertNum2();
        }
    }

    public void onNumberSystem2Changed(final String newNumberSystem) {

        if (!newNumberSystem.equals(number2.numberSystem.get())) {
            number2.numberSystem.set(newNumberSystem);

            // Convert number 1 to new number system
            convertNum1();
        }
    }

    public void clearBothNumbers() {
        number1.value.set(null);
        number2.value.set(null);
    }

    private void convertNum1() {
        convert(number1, number2);
    }

    private void convertNum2() {
        convert(number2, number1);
    }

    private void convert(NumberTextField srcNumber, NumberTextField targetNumber) {
        // Set error to null and return, if empty
        if (TextUtils.isEmpty(srcNumber.value.get())) {
            srcNumber.error.set(null);
            targetNumber.value.set(null);
            return;
        }

        // Get the number system bases
        int srcBase = getBase(srcNumber.numberSystem.get());
        int targetBase = getBase(targetNumber.numberSystem.get());

        // Perform conversion
        String converted = Converter.convert(srcNumber.value.get(), srcBase, targetBase);

        // Check for errors
        if (converted == null) {
            srcNumber.error.set(getApplication().getString(R.string.error_invalid_number));
        } else {
            srcNumber.error.set(null);
        }

        // Set the converted value to targetNumber
        targetNumber.value.set(converted);
    }

    /**
     * A ultility method which returns corresponding base of a number system from {@link #numberSystems}
     * HashMap.
     * @return The corresponding base in integer
     */
    private int getBase(final String numberSystem) {
        return numberSystems.get(numberSystem);
    }
}
