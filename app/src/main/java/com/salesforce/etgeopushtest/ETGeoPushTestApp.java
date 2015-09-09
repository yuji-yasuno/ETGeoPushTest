package com.salesforce.etgeopushtest;

import android.app.Application;
import android.util.Log;

import com.exacttarget.etpushsdk.ETException;
import com.exacttarget.etpushsdk.ETPush;
import com.exacttarget.etpushsdk.ETPushConfig;
import com.exacttarget.etpushsdk.event.ReadyAimFireInitCompletedEvent;
import com.exacttarget.etpushsdk.util.EventBus;

/**
 * Created by yuji-1124-1984 on 15/09/09.
 */
public class ETGeoPushTestApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        EventBus.getInstance().register(this);

        try {
            ETPush.setLogLevel(Log.DEBUG);
            ETPush.readyAimFire(new ETPushConfig.Builder(this)
                    .setEtAppId("your exacttarget application id")
                    .setAccessToken("your exacttarget application access token")
                    .setGcmSenderId("your google application sender id")
                    .setAnalyticsEnabled(true)
                    .setPiAnalyticsEnabled(true)
                    .setLocationEnabled(true)
                    .setCloudPagesEnabled(true)
                    .setLogLevel(BuildConfig.DEBUG ? Log.VERBOSE : Log.ERROR)
                    .build()
            );
        } catch (ETException e) {
            Log.e("ETGeoPushTest", e.getMessage());
        }
    }

    public  void onEvent(ReadyAimFireInitCompletedEvent event) {
        Log.d("ETGeoPushTest", "onEvent(ReadyAimFireInitCompletedEvent event) is fired.");
        if(event.isReadyAimFireReady()) {
            Log.d("ETGeoPushTest", "Success to bootstrap JB4A SDK.");
        } else {
            Log.d("ETGeoPushTest", "Failed to bootstrap JB4A SDK");
        }
    }
}
