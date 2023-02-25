package personal.equipe15.devoir3.fragments;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import personal.equipe15.devoir3.AddGroupActivity;
import personal.equipe15.devoir3.R;
import personal.equipe15.devoir3.profile.Group;
import personal.equipe15.devoir3.profile.Profile;
import personal.equipe15.devoir3.profile.ProfilesAdapter;
import personal.equipe15.devoir3.profile.User;


public class HomeFragment extends Fragment {


    User profile; // User actif
    ArrayList<Profile> profiles; // Tous les Users
    ArrayList<Profile> profilFiltered; // Users filtered

    // Instanction composantes
    TextView homeTitle;
    Button homeBtn;
    RadioGroup HomeRadioGroup;
    RadioButton friendsHome;
    RadioButton groupsHome;
    RadioButton allHome;


    // Constructeur du Fragment
    public HomeFragment(User profile) {
        super();
        this.profile = profile;
    }

    public HomeFragment(){
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate le fragment avec son Layout
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        // Link RecyclerView et le Home layout
        RecyclerView rvProfiles = (RecyclerView) v.findViewById(R.id.rvNumbers);


        // Set messages d'accueil
        String welcome  = getResources().getString(R.string.app_home);
        homeTitle = (TextView) v.findViewById(R.id.homeTitle);
        homeTitle.setText(welcome + " " + this.profile.name);
        getActivity().setTitle("Home");


        // Récupère les users
        profiles = Profile.getData();
        profilFiltered = new ArrayList<>();



        // Initialise button
        homeBtn = (Button) v.findViewById(R.id.homeBtn);


        // On initialise le radioGroup
        this.HomeRadioGroup = (RadioGroup) v.findViewById(R.id.HomeRadioGroup);
        this.friendsHome = (RadioButton) v.findViewById(R.id.friendsHome);
        this.groupsHome = (RadioButton) v.findViewById(R.id.groupsHome);
        this.allHome = (RadioButton) v.findViewById(R.id.allHome);
        allHome.toggle(); // Checked par défaut


        // Link adapter et RecyclerView
        ProfilesAdapter adapter = new ProfilesAdapter(profilFiltered, this.getActivity());
        rvProfiles.setAdapter(adapter);
        rvProfiles.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        // https://stackoverflow.com/questions/31242812/how-can-a-divider-line-be-added-in-an-android-recyclerview
        DividerItemDecoration rvDivider = new DividerItemDecoration(rvProfiles.getContext(), DividerItemDecoration.VERTICAL);
        Drawable divider = ContextCompat.getDrawable(getContext(), R.drawable.divider);
        rvDivider.setDrawable(divider);
        rvProfiles.addItemDecoration(rvDivider);

        //profilFiltered.addAll(this.profiles);
        getFilteredList(null, adapter, v);


        // On initialise le listener sur le radioGroup
        HomeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId); // Bouton checked

                if(checkedRadioButton == friendsHome) {
                    getFilteredList("User", adapter, v);
                } else if(checkedRadioButton == groupsHome) {
                    getFilteredList("Group", adapter, v);
                } else {
                    getFilteredList(null, adapter, v);
                }
            }
        });


        // On initialise le listener sur le button
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent interestsIntent = new Intent(getActivity(), AddGroupActivity.class);
                startActivity(interestsIntent);

            }
        });

        // On retourne finalement la view
        return v;
    }


    // Méthode permettant de filtrer la liste en fonction du RadioGroup qui est
    // checked. On notifie en dernier l'adapter du changement.
    public void getFilteredList(String filter, ProfilesAdapter adapter, View v) {

        ArrayList<Profile> elements = new ArrayList<>();

        if(filter == "User") {

            for(Profile p: profiles){
                if(p instanceof User && p.isFriend())  elements.add(p);
            }
        } else if(filter == "Group") {

            for(Profile p: profiles){
                if(p instanceof Group && p.isFriend())  elements.add(p);
            }
        } else {
            for(Profile p: profiles){
                if(p.isFriend())  elements.add(p);
            }
        }

        this.profilFiltered.clear();
        this.profilFiltered.addAll(elements);
        adapter.notifyDataSetChanged();
    }


}
