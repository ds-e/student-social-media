package personal.equipe15.devoir3.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import personal.equipe15.devoir3.R;
import personal.equipe15.devoir3.notifications.Notification;
import personal.equipe15.devoir3.notifications.NotificationsAdapter;
import personal.equipe15.devoir3.profile.Profile;

public class NotificationsFragment extends Fragment {

    ArrayList<Notification> notifications = new ArrayList<Notification>();
    ArrayList<Profile> profiles = Profile.getData();

    RecyclerView rvNotifications;
    NotificationsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;


    Notification n1 = new Notification(profiles.get(0).name + " sent you a friend request.", "request", 0);
    Notification n2 = new Notification(profiles.get(1).name + " sent you a friend request.", "request", 1);
    Notification n3 = new Notification("New message in " + profiles.get(4).getName() , "message", 4);
    Notification n4 = new Notification("New message in " + profiles.get(5).getName(), "message", 5);
    Notification n5 = new Notification("New message from " + profiles.get(2).getName(), "message", 2);
    Notification n6 = new Notification("New message from " + profiles.get(3).getName(), "message", 3);
    Notification n7 = new Notification("New message from " + profiles.get(9).getName(), "message", 9);
    Notification n8 = new Notification(profiles.get(6).name + " sent you a friend request.", "request", 6);
    Notification n9 = new Notification(profiles.get(7).name + " sent you a friend request.", "request", 7);
    Notification n10 = new Notification(profiles.get(8).name + " sent you a friend request.", "request", 8);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View notificationsView = inflater.inflate(R.layout.fragment_notifications, container, false);

        // https://www.youtube.com/watch?v=Nt1BPeV7OkE
        rvNotifications= (RecyclerView) notificationsView.findViewById(R.id.rvNotifications);
        layoutManager = new LinearLayoutManager(getContext());
        rvNotifications.setLayoutManager(layoutManager);

        // https://stackoverflow.com/questions/31242812/how-can-a-divider-line-be-added-in-an-android-recyclerview
        DividerItemDecoration rvDivider = new DividerItemDecoration(rvNotifications.getContext(), DividerItemDecoration.VERTICAL);
        Drawable divider = ContextCompat.getDrawable(getContext(), R.drawable.divider);
        rvDivider.setDrawable(divider);
        rvNotifications.addItemDecoration(rvDivider);

        adapter = new NotificationsAdapter(notifications);
        rvNotifications.setAdapter(adapter);

        notifications.add(n1);
        notifications.add(n2);
        notifications.add(n3);
        notifications.add(n4);
        notifications.add(n5);
        notifications.add(n6);
        notifications.add(n7);
        notifications.add(n8);
        notifications.add(n9);
        notifications.add(n10);

        return notificationsView;
    }


}
