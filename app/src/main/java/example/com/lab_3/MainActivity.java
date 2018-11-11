package example.com.lab_3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    private static final String ERROR_INPUT_VALIDATION = "Please correctly fill out the field";
    private static final String ERROR_CAPITAL_LETTER_VALIDATION = "First letter must be capital";
    private static final String ERROR_EMAIL_VALIDATION = "Please enter a valid email";
    private static final String ERROR_PHONE_VALIDATION = "Please enter a valid phone";
    private static final String ERROR_WEAK_PASSWORD_VALIDATION = "Password too weak";
    private static final String ERROR_MATCH_PASSWORD_VALIDATION = "Password doesn't match";
    public static final String USERS_INFO_VALUE = "usersInfo";
    public static final String USER_KEY_VALUE = "key";

    private static final Pattern NAME_PATTERN = Pattern.compile("[A-Z][a-z]+");
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText passwordEditText;
    private EditText submitPasswordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNameEditText = findViewById(R.id.etFirstName);
        lastNameEditText = findViewById(R.id.etLastName);
        emailEditText = findViewById(R.id.etEmail);
        phoneEditText = findViewById(R.id.etPhone);
        passwordEditText = findViewById(R.id.etPassword);
        submitPasswordEditText = findViewById(R.id.etSubmitPassword);

        Button viewListButton = findViewById(R.id.bViewList);
        viewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openListActivity = new Intent(MainActivity.this, ListActivity.class);
                startActivity(openListActivity);
            }
        });

        Button submitButton = findViewById(R.id.bSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDataValid();
            }
        });

    }

    private boolean isDataValid() {
        boolean validFirstName = isFirstNameValid();
        boolean validLastName = isLastNameValid();
        boolean validEmail = isEmailValid();
        boolean validPhone = isPhoneValid();
        boolean validPassword = isPasswordValid();
        boolean validSubmitPassword = isSubmitPasswordValid();
        if (validFirstName && validLastName && validEmail && validPhone && validPassword && validSubmitPassword) {
            saveInfo();
            clearInputData();

        }
        return true;
    }

    private boolean isFirstNameValid() {
        String firstName = firstNameEditText.getText().toString();
        if (firstName.isEmpty()) {
            firstNameEditText.setError(ERROR_INPUT_VALIDATION);
            return false;
        } else if (!NAME_PATTERN.matcher(firstName).matches()) {
            firstNameEditText.setError(ERROR_CAPITAL_LETTER_VALIDATION);
            return false;
        } else {
            return true;
        }
    }

    private boolean isLastNameValid() {
        String lastName = lastNameEditText.getText().toString();
        if (lastName.isEmpty()) {
            lastNameEditText.setError(ERROR_INPUT_VALIDATION);
            return false;
        } else if (!NAME_PATTERN.matcher(lastName).matches()) {
            lastNameEditText.setError(ERROR_CAPITAL_LETTER_VALIDATION);
            return false;
        } else {
            return true;
        }
    }

    private boolean isEmailValid() {
        String email = emailEditText.getText().toString();
        if (email.isEmpty()) {
            emailEditText.setError(ERROR_INPUT_VALIDATION);
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError(ERROR_EMAIL_VALIDATION);
            return false;
        } else {
            return true;
        }
    }

    private boolean isPhoneValid() {
        String phone = phoneEditText.getText().toString();
        if (phone.isEmpty()) {
            phoneEditText.setError(ERROR_INPUT_VALIDATION);
            return false;
        } else if (!Patterns.PHONE.matcher(phone).matches()) {
            phoneEditText.setError(ERROR_PHONE_VALIDATION);
            return false;
        } else {
            return true;
        }
    }

    private boolean isPasswordValid() {
        String password = passwordEditText.getText().toString();


        if (password.isEmpty()) {
            passwordEditText.setError(ERROR_INPUT_VALIDATION);
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            passwordEditText.setError(ERROR_WEAK_PASSWORD_VALIDATION);
            return false;
        } else {
            return true;
        }

    }

    private boolean isSubmitPasswordValid() {
        String submitPassword = submitPasswordEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (submitPassword.isEmpty()) {
            submitPasswordEditText.setError(ERROR_INPUT_VALIDATION);
            return false;
        } else if (!password.equals(submitPassword)) {
            submitPasswordEditText.setError(ERROR_MATCH_PASSWORD_VALIDATION);
            return false;
        } else {
            return true;
        }
    }

    private void saveInfo() {
        SharedPreferences sharedPref = getSharedPreferences(USERS_INFO_VALUE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int count = sharedPref.getAll().size() + 1;

        Set<String> user = new HashSet<>();
        user.add("name: " + firstNameEditText.getText().toString());
        user.add("surname: " + lastNameEditText.getText().toString());
        user.add("phone: " + phoneEditText.getText().toString());
        editor.putStringSet(USER_KEY_VALUE + count, user);
        editor.apply();
    }

    private void clearInputData() {
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        emailEditText.setText("");
        phoneEditText.setText("");
        passwordEditText.setText("");
        submitPasswordEditText.setText("");
    }

}
