package personal.equipe15.devoir3;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import personal.equipe15.devoir3.fragments.ExploreFragment;
import personal.equipe15.devoir3.profile.Group;
import personal.equipe15.devoir3.profile.Profile;
import personal.equipe15.devoir3.profile.ProfilesAdapter;

public class SearchResultsActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //https://guides.codepath.com/android/using-the-recyclerview#recyclerview-adapter
        RecyclerView rvProfiles = (RecyclerView) findViewById(R.id.searchResult);

        // https://stackoverflow.com/questions/31242812/how-can-a-divider-line-be-added-in-an-android-recyclerview
        DividerItemDecoration rvDivider = new DividerItemDecoration(rvProfiles.getContext(), DividerItemDecoration.VERTICAL);
        Drawable divider = ContextCompat.getDrawable(this, R.drawable.divider);
        rvDivider.setDrawable(divider);
        rvProfiles.addItemDecoration(rvDivider);

        ArrayList<Profile> profiles = Profile.getData();


        ArrayList<Profile> filteredProfiles;

        filteredProfiles = profiles;

        ProfilesAdapter adapter = new ProfilesAdapter(filteredProfiles, this);

        //Attach adapter to RecyclerView
        rvProfiles.setAdapter(adapter);
        // Set layout manager to position the items
        rvProfiles.setLayoutManager(new LinearLayoutManager(this));

            Intent intent = this.getIntent();
            String btnText = intent.getStringExtra("btnTextKey");
            getSupportActionBar().setTitle("Search: " + btnText );

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

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
}