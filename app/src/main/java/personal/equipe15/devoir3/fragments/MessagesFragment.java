package personal.equipe15.devoir3.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import personal.equipe15.devoir3.R;
import personal.equipe15.devoir3.messages.Message;
import personal.equipe15.devoir3.messages.MessagesAdapter;
import personal.equipe15.devoir3.profile.Profile;

public class MessagesFragment extends Fragment {

    ArrayList<Message> messages = new ArrayList<Message>();
    ArrayList<Profile> profiles = Profile.getData();


    RecyclerView rvMessages;
    MessagesAdapter adapter;
    RecyclerView.LayoutManager layoutManager;


    Message m1 = new Message(profiles.get(0).name,"Hey! how are you?", "person", 0);
    Message m2 = new Message(profiles.get(1).name,"Hey! how are you?", "person", 1);
    Message m3 = new Message(profiles.get(4).name,"contest tomorrow at..." , "group", 4);
    Message m4 = new Message(profiles.get(5).name,"can someone help me with...", "group", 5);
    Message m5 = new Message(profiles.get(2).name,"Hey! how are you?", "person", 2);
    Message m6 = new Message(profiles.get(3).name,"Hey! how are you?", "person", 3);
    Message m7 = new Message(profiles.get(6).name,"Hey! how are you?", "person", 6);
    Message m8 = new Message(profiles.get(7).name,"Hey! how are you?", "person", 7);



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View messagesView = inflater.inflate(R.layout.fragment_messages, container, false);
        // https://www.youtube.com/watch?v=Nt1BPeV7OkE

        rvMessages= (RecyclerView)messagesView.findViewById(R.id.rvMessages);

        // https://stackoverflow.com/questions/31242812/how-can-a-divider-line-be-added-in-an-android-recyclerview
        DividerItemDecoration rvDivider = new DividerItemDecoration(rvMessages.getContext(), DividerItemDecoration.VERTICAL);
        Drawable divider = ContextCompat.getDrawable(getContext(), R.drawable.divider);
        rvDivider.setDrawable(divider);
        rvMessages.addItemDecoration(rvDivider);

        layoutManager = new LinearLayoutManager(getContext());
        rvMessages.setLayoutManager(layoutManager);
        adapter = new MessagesAdapter(messages);
        rvMessages.setAdapter(adapter);


        messages.add(m1);
        messages.add(m2);
        messages.add(m3);
        messages.add(m4);
        messages.add(m5);
        messages.add(m6);
        messages.add(m7);
        messages.add(m8);

        return messagesView;

    }
}
