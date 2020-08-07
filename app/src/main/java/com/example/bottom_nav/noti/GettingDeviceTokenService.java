package com.example.bottom_nav.noti;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class GettingDeviceTokenService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
       // super.onTokenRefresh();

        String DeviceToken= FirebaseInstanceId.getInstance().getToken();
        Log.d("DeviceToken",DeviceToken);

    }
}
