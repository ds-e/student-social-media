package personal.equipe15.devoir3;

import android.text.InputType;

import android.util.Log;
import android.widget.EditText;

import java.util.regex.Pattern;


public interface Validation {

    // Sources which helped determine these regexes
        // https://regex101.com/
        // https://cheatography.com/davechild/cheat-sheets/regular-expressions/

    static final String EMAIL_REGEX = "^" +
        "([a-zA-Z0-9]+)" +                  // Email username
        "@" +                               // @ symbol
        "([a-z]+)\\.(com|ca|org|net|co)" +  // Domain name
        "?";

    static final String USERNAME_REGEX = "^" +
        "(?=.*[0-9a-zA-Z]).{3,}" + // 3+ of digit, lowercase or uppercase
        "$";

    static final String PASSWORD_REGEX = "^" +
        "(?=.*[0-9])" +                                         // 1+ digit
        "(?=.*[a-z])" +                                         // 1+ lowercase
        "(?=.*[A-Z])" +                                         // 1+ uppercase
        "(?=.*[!\"#$%&'()*+,-.\\/:;<=>?@\\[\\]^_`{|}~\\\\])" +  // 1+ special
        ".{6,}" +                                               // 6+ total
        "$";

    static final Pattern GENERIC_EMAIL = Pattern.compile(EMAIL_REGEX);
    static final Pattern GENERIC_USERNAME = Pattern.compile(USERNAME_REGEX);
    static final Pattern GENERIC_PASSWORD = Pattern.compile(PASSWORD_REGEX);

    static final int TYPE_OFFSET = 1;

    /**
     * Checks whether the passed {@link EditText} objects have no error message set.
     *
     * @param texts Set of {@link EditText} objects for which to verify
     * @return <ul><li>{@code true} if all the passed {@link EditText}s have no error message
     * set</li><li>{@code false} otherwise</li></ul>
     */

    default boolean checkNoErrors(EditText ... texts) {

        boolean noErrors = true;

        for (EditText t : texts) {

            noErrors = noErrors && t.getError() == null;
        }

        return noErrors;
    }

    /**
     * Checks whether the given user input conforms with certain conditions or
     * not.
     *
     * @param text {@link EditText} containing the user input
     */
    
    default void validateInput(EditText text) {

        String input = text.getText().toString().trim();

        if (input.isEmpty()) {

            text.setError("Field can't be empty!");
        }

        Log.i("Login", text.getInputType() + "");

        switch (text.getInputType()) {

            case InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS + TYPE_OFFSET: {

                if (GENERIC_EMAIL.matcher(input).matches()) {

                    text.setError(null);
                } else {

                    text.setError("Invalid email");
                }

                break;
            }

            case InputType.TYPE_TEXT_VARIATION_PERSON_NAME + TYPE_OFFSET: {

                if (GENERIC_USERNAME.matcher(input).matches()) {

                    text.setError(null);
                } else {

                    text.setError("Invalid username");
                }

                break;
            }

            case InputType.TYPE_TEXT_VARIATION_PASSWORD + TYPE_OFFSET: {

                if (GENERIC_PASSWORD.matcher(input).matches()) {

                    text.setError(null);
                } else {

                    text.setError("Password too weak");
                }

                break;
            }

            default: {

                text.setError(null);
            }
        }
    }
}

