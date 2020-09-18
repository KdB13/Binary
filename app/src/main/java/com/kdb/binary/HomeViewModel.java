package com.kdb.binary;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.lifecycle.AndroidViewModel;

import com.kdb.binary.util.Converter;

import java.util.HashMap;

public class HomeViewModel extends AndroidViewModel {

    private HashMap<String, Integer> numberSystems;

    public NumberTextField number1;
    public NumberTextField number2;

    public HomeViewModel(Application application) {
        super(application);

        // Get the number systems HashMap from repository
        numberSystems = NumberSystemsRepository.getInstance(getApplication()).getNumberSystems();

        // Initialize the NumberTextFields objects with default number systems
        initNumberTextFields();
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

            // Change the input type
            number1.setInputType(getBase(newNumberSystem));

            // Convert number 2 to new number system
            convertNum2();
        }
    }

    public void onNumberSystem2Changed(final String newNumberSystem) {

        if (!newNumberSystem.equals(number2.numberSystem.get())) {
            number2.numberSystem.set(newNumberSystem);

            // Change the input type
            number2.setInputType(getBase(newNumberSystem));

            // Convert number 1 to new number system
            convertNum1();
        }
    }

    public void clearBothNumbers() {
        number1.value.set(null);
        number2.value.set(null);
    }

    /**
     * Saves the current selection of number systems to SharedPreferences.
     */
    public void saveNumberSystemsSelection() {
        final SharedPreferences sharedPreferences = getApplication()
                .getSharedPreferences(getPreferenceFileKey(), Context.MODE_PRIVATE);

        // Get the current number systems selection
        final String numberSystem1 = number1.numberSystem.get();
        final String numberSystem2 = number2.numberSystem.get();

        // Save it inside SharedPreferences
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("number_system_1", numberSystem1);
        editor.putString("number_system_2", numberSystem2);
        editor.apply();
    }

    private void initNumberTextFields() {
        final SharedPreferences sharedPreferences = getApplication()
                .getSharedPreferences(getPreferenceFileKey(), Context.MODE_PRIVATE);

        final String numberSystem1 = sharedPreferences.getString(
                getApplication().getString(R.string.number_system_1_key),
                getApplication().getString(R.string.default_number_system_1));

        final String numberSystem2 = sharedPreferences.getString(
                getApplication().getString(R.string.number_system_2_key),
                getApplication().getString(R.string.default_number_system_2));

        number1 = new NumberTextField(numberSystem1, getBase(numberSystem1));
        number2 = new NumberTextField(numberSystem2, getBase(numberSystem2));
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
     * @return The SharedPreferences file key string from resources.
     */
    private String getPreferenceFileKey() {
        return getApplication().getString(R.string.preference_file_key);
    }

    /**
     * A ultility method which returns corresponding base of a number system from {@link #numberSystems}
     * HashMap.
     *
     * @return The corresponding base in integer
     */
    private int getBase(final String numberSystem) {
        return numberSystems.get(numberSystem);
    }
}
