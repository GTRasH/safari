package com.safari.apps.itraffic_system;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.safari.apps.itraffic_system.Models.xml.Safari;
import com.safari.apps.itraffic_system.Models.xml.spat.SPAT;
import com.safari.apps.itraffic_system.Utils.SafariService;
import com.safari.apps.itraffic_system.Utils.TcpClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
    Verarbeitet die gespeicherte Daten der vom Server bekommenen XMLs Dateien
*/

public class AmpelAcitivity extends AppCompatActivity implements TcpClient.OnMessageReceived {

    LocationManager locationManager;
    double longitudeBest, latitudeBest;
    private static final int GPS_TIME_INTERVAL = 60000; // bekommt die GPS Location jeder Sek.
    private static final int GPS_DISTANCE = 1000; // stellt das Value der Strecke in Meter.

TextView ampelCenterKMH;
    public final static int TAG_PERMISSION_CODE = 1;
 boolean updateOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ampel_acitivity);

        Toolbar toolbar = findViewById(R.id.toolbarAmpel);
        setSupportActionBar(toolbar);

        ampelCenterKMH =findViewById(R.id.ampelCenterKMH);
        setupToolbar();


        if (!CheckPermission.checkPermission(AmpelAcitivity.this)) {
            CheckPermission.requestPermission(AmpelAcitivity.this, TAG_PERMISSION_CODE);

        }


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = locationManager.getBestProvider(criteria, true);
        if (provider != null) {
            locationManager.requestLocationUpdates(provider,  10 *1000, 10, locationListenerBest);


        }


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupToolbar() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

    }

    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onResume() {
        super.onResume();

        SafariService service = SafariService.getInstance();
        service.handler = this;
    }


    @Override
    public void onPause() {
        super.onPause();  // Ruf erstmal die Superclass Methode

        SafariService service = SafariService.getInstance();
        service.handler = null;
    }


    private final LocationListener locationListenerBest = new LocationListener() {
        public void onLocationChanged(Location location) {

            String strLongitude = location.convert(location.getLongitude(), location.FORMAT_DEGREES);
            String strLatitude = location.convert(location.getLatitude(), location.FORMAT_SECONDS);


            longitudeBest =   (location.getLongitude()  );
            latitudeBest =   (location.getLatitude()  );

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(!updateOnce) {
                     updateOnce = true;
                        sendLocation();
                    }
                }
            });



        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    public void messageReceived(Safari message ,String original) {
        if (message.getHeader().getRequest().equals("location")){
         //   sendLocation();
        }
    }

    @Override
    public void messageReceived(final SPAT message ,String original) {
        Log.d("", "");




        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                double kmh = Integer.parseInt(message.getIntersections().getIntersectionState().getStates().getMovementState().getStateTimeSpeed().getMovementEvent().getSpeeds().getAdvisorySpeed().getSpeed()) * 0.1 * 3.6 ;

                ampelCenterKMH.setText(String.format( "%.2f KM/H \n Lat %.4f \n Lon %.4f", kmh , latitudeBest,longitudeBest ));
            }
        });

    }
//Sendet die Koordinaten der Nutzer
    public void sendLocation() {

                SafariService service = SafariService.getInstance();

                service.sendMessage("<?xml version=\"1.0\"?>\n" +
                        "<safari>\n" +
                        "\t<header>\n" +
                        "\t\t<request></request>\n" +
                        "\t\t<response>location</response>\n" +
                        "\t</header>\n" +
                        "\t<data>\n" +
                        "\t\t<refPoint>\n" +
                        "\t\t\t<lat>" + (int) ( latitudeBest*10) + "</lat>\n" +
                        "\t\t\t<long>" + (int) (  longitudeBest*10 )+ "</long>\n" +
                        "\t\t</refPoint>\n" +
                        "\t</data>\n" +
                        "</safari>\n");

    }

    public static class CheckPermission {

//  Check fuer Location Zulassung
        public static boolean checkPermission(Activity activity) {
            int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
            if (result == PackageManager.PERMISSION_GRANTED) {

                return true;

            } else {

                return false;

            }
        }

// Frag fuer die Zulassung
        public static void requestPermission(Activity activity, final int code) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {

                Toast.makeText(activity, "GPS erlaubt uns die Locationdaten zu zugreiffen. Bitte erlauben Sie uns die Zulassung um eine schoene Erfahrung zu haben", Toast.LENGTH_LONG).show();

            } else {

                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, code);
            }
        }

    }

}
