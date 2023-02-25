package personal.equipe15.devoir3.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import personal.equipe15.devoir3.R;
import personal.equipe15.devoir3.SearchResultsActivity;
import personal.equipe15.devoir3.profile.User;

public class ExploreFragment extends Fragment {

    User profile;

    public ExploreFragment(User profile) {

        this.profile = profile;
    }

    //https://stackoverflow.com/questions/38344308/starting-new-activity-from-a-fragment-with-a-button
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View exploreView = inflater.inflate(R.layout.fragment_explore, container, false);

        //Interest button
        Button interestsBtn = exploreView.findViewById(R.id.interestsBtn);
        interestsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent interestsIntent = new Intent(getActivity(), SearchResultsActivity.class);
                interestsIntent.putExtra("btnTextKey", interestsBtn.getText().toString());

                startActivity(interestsIntent);
            }
        });

        //Course button
        Button coursesBtn = exploreView.findViewById(R.id.coursesBtn);
        coursesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent interestsIntent = new Intent(getActivity(), SearchResultsActivity.class);
                interestsIntent.putExtra("btnTextKey", coursesBtn.getText().toString());

                startActivity(interestsIntent);


            }
        });

        //Group button
        Button groupBtn = exploreView.findViewById(R.id.groupsBtn);
        groupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent interestsIntent = new Intent(getActivity(), SearchResultsActivity.class);

                // prendre le text du bouton
                interestsIntent.putExtra("btnTextKey", groupBtn.getText().toString());

                startActivity(interestsIntent);

            }
        });

        //Name button
        Button nameBtn = exploreView.findViewById(R.id.nameBtn);
        nameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent interestsIntent = new Intent(getActivity(), SearchResultsActivity.class);
                interestsIntent.putExtra("btnTextKey", nameBtn.getText().toString());

                startActivity(interestsIntent);

            }
        });

        return exploreView;
    }
}
