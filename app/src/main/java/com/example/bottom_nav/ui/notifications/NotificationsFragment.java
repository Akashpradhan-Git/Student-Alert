package com.example.bottom_nav.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bottom_nav.MainActivity;
import com.example.bottom_nav.R;
import com.example.bottom_nav.noti.GettingDeviceTokenService;
import com.example.bottom_nav.noti.MyFirebaseMessagingService;

import static com.example.bottom_nav.noti.MyFirebaseMessagingService.msg;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    String str;
    TextView notification;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_notifications, null);
        notification=view.findViewById(R.id.Tvnotification);

        Bundle bundle = getArguments();
        String message = bundle.getString("message");
        //no.setText(message);
        if(message!=null){
            notification.setText(message);
        }
        else {
            notification.setText("No new Notification");
        }

        return view;
    }
}
