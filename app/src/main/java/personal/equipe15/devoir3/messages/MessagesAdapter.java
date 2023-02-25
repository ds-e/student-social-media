package personal.equipe15.devoir3.messages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import personal.equipe15.devoir3.ChatActivity;
import personal.equipe15.devoir3.R;
import personal.equipe15.devoir3.profile.Group;
import personal.equipe15.devoir3.profile.Profile;


public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private ArrayList<Message> messages;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView messagesTitle;
        Button conversationText;
        ImageView msgProfileImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            messagesTitle = itemView.findViewById(R.id.message_title);
            conversationText = itemView.findViewById(R.id.conversation_text_btn);
            msgProfileImg = itemView.findViewById(R.id.messages_profile_img);

        }
    }

    public MessagesAdapter(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_item, parent, false);
        return new MessagesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.ViewHolder holder, int position) {

        holder.messagesTitle.setText(messages.get(position).getFrom());
        holder.conversationText.setText(messages.get(position).getText());

        if (messages.get(position).getType() == "person") {


            holder.conversationText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Profile profile = Profile.getData().get(messages.get(position).getFromId());

                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("name", profile.name);
                    intent.putExtra("id", profile.id);
                    context.startActivity(intent);
                }
            });
        }
        else {

            holder.msgProfileImg.setImageResource(R.drawable.ic_baseline_group_24);

            holder.conversationText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Profile profile = Profile.getData().get(messages.get(position).getFromId());

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
            });
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


}
