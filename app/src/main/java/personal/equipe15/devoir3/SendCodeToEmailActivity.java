package personal.equipe15.devoir3;

import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SendCodeToEmailActivity extends AppCompatActivity implements Validation {

    /**
     * @see android.app.Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_code_to_email);

        Button button = findViewById(R.id.getcode_button);

        getSupportActionBar().setTitle(R.string.app_getcode_reset);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button.setOnClickListener(v -> {

            Toast.makeText(getApplicationContext(), R.string.app_sendcode, Toast.LENGTH_SHORT).show();
        });
    }
}