package personal.equipe15.devoir3.notifications;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import personal.equipe15.devoir3.ChatActivity;
import personal.equipe15.devoir3.R;
import personal.equipe15.devoir3.ViewProfileActivity;
import personal.equipe15.devoir3.profile.Group;
import personal.equipe15.devoir3.profile.Profile;
import personal.equipe15.devoir3.profile.User;


public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    private ArrayList<Notification> notifications;
    private Context context;

    public NotificationsAdapter(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_items, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.notificationText.setText(notifications.get(position).getText());

        if (notifications.get(position).getType() == "request") {
            holder.notificationBtn.setText(R.string.app_notif_accept);

            holder.notificationBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Request accepted!", Toast.LENGTH_SHORT).show();

                    //add friend system
                    Profile p = Profile.getData().get(notifications.get(position).getId());
                    p.addFriend();

                    remove(position);
                }
            });
        }
        else {
            holder.notificationBtn.setText(R.string.app_profile_view);

            holder.notificationBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Profile profile = Profile.getData().get(notifications.get(position).getId());

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

                    remove(position);
                }
            });
        }

    }

//  https://stackoverflow.com/questions/56147356/how-to-delete-recyclerview-item-when-click-on-button
    private void remove(int position) {
        notifications.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, notifications.size());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView notificationText;
        Button notificationBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            notificationText = itemView.findViewById(R.id.notification_text);
            notificationBtn = itemView.findViewById(R.id.notification_btn);
        }
    }
}


