package personal.equipe15.devoir3.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import personal.equipe15.devoir3.R;
import personal.equipe15.devoir3.ViewProfileActivity;

//https://guides.codepath.com/android/using-the-recyclerview#recyclerview-adapter

/**
 * Adapter for a List of Profile, that allows us to display them in a RecyclerView
 * */
public class ProfilesAdapter extends RecyclerView.Adapter<ProfilesAdapter.ViewHolder> {

    //The listed profiles
    private List<Profile> profiles;
    //The activity that contains the RecycleView
    private Activity activity;

    public ProfilesAdapter(List<Profile> profiles, Activity activity) {
        this.profiles = profiles;
        this.activity = activity;
    }

    //View Holder
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public Button viewProfileButton;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.profile_name);
            viewProfileButton = (Button) itemView.findViewById(R.id.view_profile_button);
        }
    }

    @Override
    public ProfilesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View profilesView = inflater.inflate(R.layout.search_result_profile, parent, false);

        //Make viewHolder with profilesView and return it
        ViewHolder viewHolder = new ViewHolder(profilesView);
        return viewHolder;
    }

    /**
     * Populates the holder automatically
     * */
    @Override
    public void onBindViewHolder(ProfilesAdapter.ViewHolder holder, int position) {
        // Get the profile model based on position
        Profile profile = profiles.get(position);

        //The profile name text box
        TextView textView = holder.nameTextView;
        textView.setText(profile.name);

        //https://stackoverflow.com/questions/12684686/how-to-change-an-icon-of-the-textview-programmatically
        //Change icon if group
        if(profile instanceof Group){
            textView.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_group_24, //left
                    0, //top
                    0, //right
                    0);//bottom
        }
        else{
            textView.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_profile, //left
                    0, //top
                    0, //right
                    0);//bottom
        }

        //The view profile button
        Button button = holder.viewProfileButton;
        button.setText(R.string.app_profile_view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Starts a ProfilePageActivity from @activity
                Intent intent = new Intent(activity, ViewProfileActivity.class);
                intent.putExtra("id", profile.id);
                activity.startActivity(intent);
            }
        });
    }

    //Returns the total count of items in the list
    @Override
    public int getItemCount() {

        return profiles.size();
    }
}
