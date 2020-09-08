package com.kdb.binary.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    private static final String TAG = "BindingAdapters";

    /**
     * Sets text to an AutoCompleteTextView with filtering disabled.
     *
     * @param text The text to set
     */
    @BindingAdapter("app:textNoFilter")
    public static void setTextNoFilter(AutoCompleteTextView textView, String text) {
        textView.setText(text, false);
    }

    /**
     * Adds a TextWatcher to EditText which will only fire text change events when EditText was in
     * focus.
     *
     * @param listener A {@link FocusedTextWatcher.TextChangedListener} which is called when text changes
     */
    @BindingAdapter("app:onFocusedTextChanged")
    public static void addFocusedTextChangeListener(EditText editText,
                                                    FocusedTextWatcher.TextChangedListener listener) {
        new FocusedTextWatcher(editText, listener);
    }

    /**
     * Turns on soft word-wrap for EditText with maximum lines of {@link Byte#MAX_VALUE}.
     *
     * @param wordWrap A boolean indicating whether to word-wrap or not
     */
    @BindingAdapter("app:wordWrap")
    public static void setWordWrap(EditText editText, boolean wordWrap) {
        if (wordWrap) {
            editText.setMaxLines(Byte.MAX_VALUE);
        } else {
            editText.setMaxLines(1);
        }

        editText.setHorizontallyScrolling(!wordWrap);
    }

    /**
     * <p>This binding adapter is specifically for Material's exposed dropdown menu which has a bug —
     * setting {@code inputType=none} in XML has no effect and it doesn't hide the keyboard if we focus it
     * after the keyboard is shown by another editable {@link EditText}.</p>
     * <p>
     * <br/>
     *
     * <p>This adapter will add a {@link android.view.View.OnFocusChangeListener} to the passed
     * {@link AutoCompleteTextView}, which will hide the keyboard when in focus.</p>
     *
     * @param hide A boolean indicating whether to hide the keyboard on focus or not
     */
    @BindingAdapter("app:hideKeyboardOnFocus")
    public static void setHideKeyboardOnFocus(AutoCompleteTextView textView, boolean hide) {
        if (hide) {

            textView.setOnFocusChangeListener((v, hasFocus) -> {

                if (hasFocus) {
                    final InputMethodManager inputMethodManager =
                            (InputMethodManager) textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputMethodManager.hideSoftInputFromWindow(textView.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }

            });


        }
    }
}
