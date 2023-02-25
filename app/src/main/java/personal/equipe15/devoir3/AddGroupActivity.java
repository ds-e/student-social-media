package personal.equipe15.devoir3;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AddGroupActivity extends AppCompatActivity {

    // Composantes activity
    Button addGroupBtn;
    EditText addGroupName, addGroupCourses, addGroupCategory;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home: {

                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add a group");


        // Instanciation composante activity
        addGroupBtn = findViewById(R.id.addGroupBtn);
        addGroupName = findViewById(R.id.addGroupName);
        addGroupCourses = findViewById(R.id.addGroupCourses);
        addGroupCategory = findViewById(R.id.addGroupCategory);

        // On initialise le listener sur le button
        addGroupBtn.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    if(verificationForm()) finish();
            }
        });
    }


    // Méthode assurant que le form ne soit pas vide lors du submit. Renvois
    // message erreur si c'est le cas.
    public boolean verificationForm() {

        if(addGroupName.getText().toString().equals("") || addGroupCourses.getText().toString().equals("") || addGroupCategory.getText().toString().equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Data missing", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }  else return true;

    }



}
