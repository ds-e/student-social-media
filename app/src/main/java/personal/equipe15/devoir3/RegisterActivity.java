package personal.equipe15.devoir3;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements Validation {

    /**
     * @see android.app.Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        EditText email = findViewById(R.id.register_email);
        EditText username = findViewById(R.id.register_username);
        EditText password = findViewById(R.id.register_password);
        Button register = findViewById(R.id.register_button);

        getSupportActionBar().setTitle(R.string.app_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        register.setOnClickListener(v -> {

            validateInput(email);
            validateInput(username);
            validateInput(password);

            if (checkNoErrors(email, username, password)) {

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("email", username.getText().toString());
                startActivity(intent);
            } else {

                Toast.makeText(getApplicationContext(), "Account credentials invalid", Toast.LENGTH_SHORT).show();
            }
        });
    }
}