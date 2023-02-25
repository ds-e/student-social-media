package personal.equipe15.devoir3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import personal.equipe15.devoir3.chats.Chat;
import personal.equipe15.devoir3.chats.ChatsAdapter;
import personal.equipe15.devoir3.messages.Message;
import personal.equipe15.devoir3.messages.MessagesAdapter;
import personal.equipe15.devoir3.profile.Profile;

public class ChatActivity extends AppCompatActivity {

    ArrayList<Message> messages = new ArrayList<Message>();

    EditText typingField;
    ImageButton sendBtn;
    ImageButton attachmentBtn;
    ImageView profileImg;

    RecyclerView rvChats;
    ChatsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

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


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Chat> chats = new ArrayList<Chat>();
        rvChats= (RecyclerView)this.findViewById(R.id.chats);

        layoutManager = new LinearLayoutManager(this);
        rvChats.setLayoutManager(layoutManager);
        adapter = new ChatsAdapter(chats);
        rvChats.setAdapter(adapter);

        typingField = this.findViewById(R.id.typingField);
        sendBtn = this.findViewById(R.id.sendBtn);
        attachmentBtn = this.findViewById(R.id.attachmentBtn);
        profileImg = this.findViewById(R.id.profileImg);

        Intent intent = this.getIntent();
        int type = intent.getIntExtra("isGroup", 0);

        //Chat's name
        String name = intent.getStringExtra("name");
        TextView chatTitle = findViewById(R.id.chatName);
        chatTitle.setText(name);

        getSupportActionBar().setTitle("Chat with: " + name);


        //if is not a group chat
        if(type == 0){

            //Get the person
            int id = intent.getIntExtra("id", 0);
            Profile profile = Profile.getData().get(id);

            if(profile == null){
                return;
            }

            chats.add(new Chat(profile.name, "Hi"));
            chats.add(new Chat(profile.name, "How are you?"));
        }
        //if is a group chat
        else{

            //Get all the concerned profiles
            int[] ids = intent.getIntArrayExtra("ids");

            if(ids == null){
                return;
            }

            ArrayList<Profile> profiles = new ArrayList();
            for(int i =0; i < ids.length; i++){
                profiles.add(Profile.getData().get(ids[i]));
            }

            for(Profile p: profiles){
                chats.add(new Chat(p.name, "Hi from" + p.name + "!"));
                chats.add(new Chat(p.name, "How are you?"));
            }

            profileImg.setImageResource(R.drawable.ic_baseline_group_24);
        }



        sendBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN) {
                    sendBtn.setBackgroundColor(Color.RED);

                    chats.add(new Chat("you", typingField.getEditableText().toString()));
                    adapter.notifyDataSetChanged();

                    //https://stackoverflow.com/questions/1109022/how-do-you-close-hide-the-android-soft-keyboard-using-java
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                } else
                    sendBtn.setBackgroundColor(Color.TRANSPARENT);
                    typingField.setText(null);
                    //https://stackoverflow.com/questions/44912239/automatically-scroll-to-bottom-of-recyclerview
                    rvChats.smoothScrollToPosition(chats.size()-1);

                return true;
            }

        });


        attachmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(attachmentBtn.getContext(), "ce bouton n'a pas ete implemente, " +
                        "mais devrait ouvrir les dossiers de l'utilisateur", Toast.LENGTH_LONG).show();
            }
        });


    }

}
