package com.kdb.binary.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * A special text watcher which listens for changes inside text of an {@link EditText} and notifies
 * only when EditText was in focus.
 */
public class FocusedTextWatcher {

    public FocusedTextWatcher(EditText editText, TextChangedListener textChangedListener) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editText.hasFocus()) {
                    textChangedListener.onChanged(s.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    /**
     * Contains a single method which is called when the user types some text inside an
     * {@link EditText}.
     */
    public interface TextChangedListener {
        void onChanged(String newText);
    }

}
