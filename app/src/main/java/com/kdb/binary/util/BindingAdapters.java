package com.kdb.binary.util;

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

}
