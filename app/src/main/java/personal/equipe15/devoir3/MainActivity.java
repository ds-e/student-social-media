package personal.equipe15.devoir3;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import personal.equipe15.devoir3.fragments.ExploreFragment;
import personal.equipe15.devoir3.fragments.HomeFragment;
import personal.equipe15.devoir3.fragments.MessagesFragment;
import personal.equipe15.devoir3.fragments.NotificationsFragment;
import personal.equipe15.devoir3.fragments.EditProfileFragment;
import personal.equipe15.devoir3.profile.User;
import personal.equipe15.devoir3.profile.Profile;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private User profile;//My profile
    private ArrayList<Profile> profiles;//The data

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      https://www.youtube.com/watch?v=tPV8xA7m-iw&t=709s

        bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        profile = new User(getIntent().getStringExtra("email"), "Description of my profile.", -1);

        // make home the first view
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment(profile)).commit();
        bottomNav.getMenu().getItem(2).setChecked(true);

        //NOTE: Voici les donnees du programme.  Juste appeler Profile.getData(); de n'importe ou
        //pour avoir ces donnees (pas besoin de les passer de Activity en Activity)
        profiles = Profile.getData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {

                        case R.id.nav_explore: {

                            getSupportActionBar().setTitle(R.string.app_frag_explore);
                            selectedFragment = new ExploreFragment(profile);
                            break;
                        }

                        case R.id.nav_profile: {

                            getSupportActionBar().setTitle(R.string.app_frag_editprofile);
                            selectedFragment = new EditProfileFragment(profile);
                            break;
                        }

                        case R.id.nav_home: {

                            getSupportActionBar().setTitle(R.string.app_frag_home);
                            selectedFragment = new HomeFragment(profile);
                            break;
                        }

                        case R.id.nav_messages: {

                            getSupportActionBar().setTitle(R.string.app_frag_message);
                            selectedFragment = new MessagesFragment();
                            break;
                        }

                        case R.id.nav_notifications: {

                            getSupportActionBar().setTitle(R.string.app_frag_notifications);
                            selectedFragment = new NotificationsFragment();
                            break;
                        }

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    // return true to select the menu item
                    return true;
                }
    };

    /**
     * Source: https://www.youtube.com/watch?v=2uCR0JSmMXc
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.logout: {

                startActivity(new Intent(this, LoginActivity.class));
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
