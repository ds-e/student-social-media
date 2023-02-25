package personal.equipe15.devoir3;

import android.content.Intent;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    /**
     * @see android.app.Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText username = findViewById(R.id.login_username);
        EditText password = findViewById(R.id.login_password);
        Button login = findViewById(R.id.login_button);
        TextView forgotPassword = findViewById(R.id.login_forgot);
        TextView createAccount = findViewById(R.id.login_register);

        getSupportActionBar().setTitle(R.string.app_login);

        login.setOnClickListener(v -> {

            if ((username.getText().length() > 0 && password.getText().length() > 0)) {

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("email", username.getText().toString());
                startActivity(intent);
            } else {

                Toast.makeText(getApplicationContext(), "Account credentials invalid", Toast.LENGTH_SHORT).show();
            }
        });

        forgotPassword.setOnClickListener(v -> {

            startActivity(new Intent(this, SendCodeToEmailActivity.class));
        });

        createAccount.setOnClickListener(v -> {

            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}