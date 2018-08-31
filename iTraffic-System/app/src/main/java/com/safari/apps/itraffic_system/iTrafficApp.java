package com.safari.apps.itraffic_system;

import android.app.Application;
import android.os.StrictMode;


/*
    Diese Klasse oeffnet sich erstmal und redirect der Nutzer zum Login in diesem Fall
*/

public class iTrafficApp extends Application {

    public static iTrafficApp appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        appContext = this;

    }

}
