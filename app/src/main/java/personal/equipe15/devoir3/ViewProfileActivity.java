package personal.equipe15.devoir3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import personal.equipe15.devoir3.profile.Group;
import personal.equipe15.devoir3.profile.Profile;
import personal.equipe15.devoir3.profile.ProfilesAdapter;
import personal.equipe15.devoir3.profile.User;

/**
 * Activity that displays any Profile
 * Takes in its intent the id of the profile in Profile.getData()
 * Note that the id is the index
 * */
public class ViewProfileActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_profile_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Get the corresponding profile
        ArrayList<Profile> profiles = Profile.getData();


        int id = getIntent().getIntExtra("id", 0);
        Profile profile = profiles.get(id);

        getSupportActionBar().setTitle(profile.name + "'s profile");

        if(profile == null){
            finish();
        }

        Context context = this;

        //Button to add the person as a friend
        ImageButton friendButton = findViewById(R.id.add_person_btn);

        if(profile.isFriend()){
            friendButton.setImageResource(R.drawable.ic_baseline_check_24);
        }

        friendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!profile.isFriend()){
                    if(profile instanceof User){
                        Toast.makeText(context, "Friend request sent!", Toast.LENGTH_SHORT).show();
                        friendButton.setImageResource(R.drawable.ic_baseline_check_24);
                        profile.addFriend();
                    }
                    else{
                        Toast.makeText(context, "Group joined!", Toast.LENGTH_SHORT).show();
                        friendButton.setImageResource(R.drawable.ic_baseline_check_24);
                        profile.addFriend();

                    }
                }
            }
        });

        //Button to chat with the profile's owner/group
        Button chatbutton = findViewById(R.id.chatBtn);
        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(profile instanceof User){
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("name", profile.name);
                    intent.putExtra("id", profile.id);
                    context.startActivity(intent);
                }
                else{
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("name", profile.name);
                    intent.putExtra("isGroup", 1);

                    //Get all the group's members
                    Group g = (Group)profile;
                    ArrayList<Profile> members = g.getMembers();

                    int[] ids = new int[members.size()];

                    int i = 0;
                    for(Profile m: members){
                        ids[i] = m.id;
                        i++;
                    }

                    intent.putExtra("ids", ids);
                    context.startActivity(intent);
                }

            }
        });

        //Profile information displayed on page
        TextView profileText = this.findViewById(R.id.profileText);
        profileText.setText(profile.name);

        TextView descriptiontxt = this.findViewById(R.id.descriptionTxt);
        descriptiontxt.setText(profile.getDetails());


        if(profile instanceof User){
            User user = (User)profile;

            TextView associatedProfileTxt = this.findViewById(R.id.associatedProfilesTxt);
            associatedProfileTxt.setText(R.string.app_profile_groups);

            ProfilesAdapter adapter = new ProfilesAdapter(user.getGroups(), this);

            RecyclerView rvProfiles = (RecyclerView) findViewById(R.id.associatedProfiles);
            rvProfiles.setAdapter(adapter);

            rvProfiles.setLayoutManager(new LinearLayoutManager(this));
        }
        else if(profile instanceof Group){

            Group group = (Group)profile;

            TextView associatedProfileTxt = this.findViewById(R.id.associatedProfilesTxt);
            associatedProfileTxt.setText(R.string.app_profile_members);

            ProfilesAdapter adapter = new ProfilesAdapter(group.getMembers(), this);

            RecyclerView rvProfiles = (RecyclerView) findViewById(R.id.associatedProfiles);
            rvProfiles.setAdapter(adapter);

            rvProfiles.setLayoutManager(new LinearLayoutManager(this));

        }

    }
}
