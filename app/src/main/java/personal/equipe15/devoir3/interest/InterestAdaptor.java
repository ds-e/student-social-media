package personal.equipe15.devoir3.interest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import personal.equipe15.devoir3.ChatActivity;
import personal.equipe15.devoir3.R;
import personal.equipe15.devoir3.profile.Group;
import personal.equipe15.devoir3.profile.Profile;
import personal.equipe15.devoir3.profile.User;


public class InterestAdaptor extends RecyclerView.Adapter<InterestAdaptor.ViewHolder> {

    private ArrayList<Interest> interests;
    private Context context;

    public InterestAdaptor(ArrayList<Interest> interests) {
        this.interests = interests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interest_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.interestTxt.setText(interests.get(position).getText());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { ;

                remove(position);
            }
        });
    }

//  https://stackoverflow.com/questions/56147356/how-to-delete-recyclerview-item-when-click-on-button
    private void remove(int position) {
        interests.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, interests.size());
    }

    @Override
    public int getItemCount() {
        return interests.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView interestTxt;
        ImageButton deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            interestTxt = itemView.findViewById(R.id.interestTxt);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}


