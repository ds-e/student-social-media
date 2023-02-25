package personal.equipe15.devoir3.chats;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import personal.equipe15.devoir3.R;
import personal.equipe15.devoir3.ViewProfileActivity;
import personal.equipe15.devoir3.profile.Group;
import personal.equipe15.devoir3.profile.Profile;
import personal.equipe15.devoir3.profile.ProfilesAdapter;


public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {

    //The listed chats
    private List<Chat> chats;

    public ChatsAdapter(List<Chat> chats) {
        this.chats = chats;
    }

    //View Holder
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView chatText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            chatText = itemView.findViewById(R.id.chatText);

        }
    }

    @Override
    public ChatsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View chatsView = inflater.inflate(R.layout.chats_item, parent, false);

        //Make viewHolder with profilesView and return it
        ChatsAdapter.ViewHolder viewHolder = new ChatsAdapter.ViewHolder(chatsView);

        return viewHolder;
    }

    /**
     * Populates the holder automatically
     * */
    @Override
    public void onBindViewHolder(ChatsAdapter.ViewHolder holder, int position) {
        holder.chatText.setText(chats.get(position).getFrom() + ": " + chats.get(position).getText());
    }

    //Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return chats.size();
    }


}

