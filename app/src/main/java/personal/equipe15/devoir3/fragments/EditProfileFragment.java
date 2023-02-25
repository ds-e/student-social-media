package personal.equipe15.devoir3.fragments;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import personal.equipe15.devoir3.R;
import personal.equipe15.devoir3.interest.Interest;
import personal.equipe15.devoir3.interest.InterestAdaptor;
import personal.equipe15.devoir3.messages.Message;
import personal.equipe15.devoir3.messages.MessagesAdapter;
import personal.equipe15.devoir3.profile.Profile;

/**
 * Displays the user's profile and allow Profile Edit
 * */
public class EditProfileFragment extends Fragment {

    Profile profile;

    ArrayList<Interest> interests;

    RecyclerView rvInterests;
    InterestAdaptor adapter;
    RecyclerView.LayoutManager layoutManager;

    int id;

    public EditProfileFragment(Profile profile){

        this.profile = profile;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View profileView = inflater.inflate(R.layout.fragment_profile, container, false);

        interests = new ArrayList<Interest>();

        rvInterests= (RecyclerView)profileView.findViewById(R.id.interests);

        // https://stackoverflow.com/questions/31242812/how-can-a-divider-line-be-added-in-an-android-recyclerview
        DividerItemDecoration rvDivider = new DividerItemDecoration(rvInterests.getContext(), DividerItemDecoration.VERTICAL);
        Drawable divider = ContextCompat.getDrawable(getContext(), R.drawable.divider);
        rvDivider.setDrawable(divider);
        rvInterests.addItemDecoration(rvDivider);

        layoutManager = new LinearLayoutManager(getContext());
        rvInterests.setLayoutManager(layoutManager);
        adapter = new InterestAdaptor(interests);
        rvInterests.setAdapter(adapter);

        interests.add(new Interest("IFT2905", 0));
        interests.add(new Interest("Chess", 1));
        interests.add(new Interest("Maths", 2));

        id = 2;

        ImageButton addInterestBtn = profileView.findViewById(R.id.addInterestBtn);
        ImageButton editProfile = profileView.findViewById(R.id.profilePicEditBtn);
        Button save = profileView.findViewById(R.id.saveBtn);
        TextView interestTxt = profileView.findViewById(R.id.addInterestTxt);

        addInterestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id++;

                interests.add(new Interest(interestTxt.getText().toString(), id));

                adapter.notifyDataSetChanged();
                //https://stackoverflow.com/questions/44912239/automatically-scroll-to-bottom-of-recyclerview
                rvInterests.smoothScrollToPosition(interests.size()-1);

                //https://stackoverflow.com/questions/1109022/how-do-you-close-hide-the-android-soft-keyboard-using-java
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                interestTxt.setText("");
            }
        });

        editProfile.setOnClickListener(v -> {

            Toast.makeText(getContext(), "Function not implemented", Toast.LENGTH_SHORT).show();
        });

        save.setOnClickListener(v -> {

            Toast.makeText(getContext(), "Function not implemented", Toast.LENGTH_SHORT).show();
        });

        return profileView;

    }
}
