package com.safari.apps.itraffic_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.safari.apps.itraffic_system.Models.xml.Safari;
import com.safari.apps.itraffic_system.Models.xml.spat.SPAT;
import com.safari.apps.itraffic_system.Utils.SafariService;
import com.safari.apps.itraffic_system.Utils.TcpClient;

/*
    Bei dieser Klasse kann der Nutzer die Dienste auswaehlen sowie das Transportmittel der Nutzer nutzt.
*/

public class MainActivity extends AppCompatActivity implements TcpClient.OnMessageReceived {

    Button connectButton;

    Boolean connecting = false;
    Boolean auth = false;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);


        radioGroup = findViewById(R.id.radioGroup);

        connectButton = findViewById(R.id.connectButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToAmpel();
            }
        });

        setupToolbar();


    }

//suscribe zum Server Service
    @Override
    public void onResume() {
        super.onResume();
        SafariService service = SafariService.getInstance();
        service.handler = this;


    }


    @Override
    public void onPause() {
        super.onPause(); 

        SafariService service = SafariService.getInstance();
        service.handler = null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_items_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.optionsItem:
                MoveToOptions();
                return true;

            case R.id.aboutItem:
                MoveToAbout();
                return true;

            case R.id.logOutItem:
                Logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void setupToolbar() {
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public boolean Logout() {

/*        ApiUtils.getInstance().editor.putString("savedUser", "");

        ApiUtils.getInstance().editor.putString("AuthSession", "");
        ApiUtils.getInstance().editor.putString("user", "");
        ApiUtils.getInstance().editor.putString("password", "");
        ApiUtils.getInstance().editor.commit(); */

        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        return true;

    }


    public void MoveToOptions() {

        startActivity(new Intent(MainActivity.this, OptionsActivity.class));
    }

    public void MoveToAbout() {

        startActivity(new Intent(MainActivity.this, AboutActivity.class));
    }


// Check ob es sich verbinden kann
    public void MoveToAmpel() {

        if (!connecting) {

            connecting = true;

/*
            SafariService service =  SafariService.getInstance();

            service.connectWebSocket();*/


            SafariService service = SafariService.getInstance();

            auth = true;
            //Service Message


            int movementType = 0;

            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.radioButtonKraftfahrzeug: {
                    movementType = 1;
                }
                break;
                case R.id.radioButtonFahrrad: {
                    movementType = 2;
                }
                break;
                case R.id.radioButtonZufuß: {
                    movementType = 3;
                }
                break;
            }

// Sendet die Transportmitteldaten sowie die ausgewaehlte Dienste
            service.sendMessage("<?xml version=\"1.0\"?>\n" +
                    "<safari>\n" +
                    "\t<header>\n" +
                    "\t\t<request></request>\n" +
                    "\t\t<response>service</response>\n" +
                    "\t</header>\n" +
                    "\t<data>\n" +
                    "\t\t<cityID>0</cityID>\n" +
                    "\t\t<movementType>" + movementType + "</movementType>\t<!--\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t1 = motor\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t2 = bike\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t3 = feet\n" +
                    "\t\t\t\t\t\t\t\t\t\t-->\n" +
                    "\t\t<services>\n" +
                    "\t\t\t<service>2</service>\t<!-- GLOSA -->\n" +
// Mehrere Dienste sind momentan nicht implementiert
             //       "\t\t\t<service>4</service>\t<!-- not implemented -->\n" +
             //       "\t\t\t<service>8</service>\t<!-- not implemented -->\n" +
                    "\t\t</services>\n" +
                    "\t</data>\n" +
                    "</safari>\n");

        }

    }

    @Override
    public void messageReceived(Safari message, String original) {

// Fuer Testing Zweck
        
    /*    //Login Message
        if (message.getHeader().getRequest().equals("auth")&&!auth){
            SafariService service = SafariService.getInstance();

            service.sendMessage("<?xml version=\"1.0\"?>\n" +
                    "<safari>\n" +
                    "\t<header>\n" +
                    "\t\t<request></request>\n" +
                    "\t\t<response>auth</response>\n" +
                    "\t</header>\n" +
                    "\t<data>\n" +
                    "\t\t<user>testx</user>\n" +
                    "\t\t<pass>test</pass>\n" +
                    "\t</data>\n" +
                    "</safari>\n");
            auth=true;
        }
        else     if (message.getHeader().getRequest().equals("auth")&&auth) {
            SafariService service = SafariService.getInstance();


            //Service Message
            service.sendMessage("<?xml version=\"1.0\"?>\n" +
                    "<safari>\n" +
                    "\t<header>\n" +
                    "\t\t<request></request>\n" +
                    "\t\t<response>service</response>\n" +
                    "\t</header>\n" +
                    "\t<data>\n" +
                    "\t\t<cityID>0</cityID>\n" +
                    "\t\t<movementType>1</movementType>\t<!--\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t1 = motor\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t2 = bike\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t3 = feet\n" +
                    "\t\t\t\t\t\t\t\t\t\t-->\n" +
                    "\t\t<services>\n" +
                    "\t\t\t<service>2</service>\t<!-- GLOSA -->\n" +
                    "\t\t\t<service>4</service>\t<!-- not implemented -->\n" +
                    "\t\t\t<service>8</service>\t<!-- not implemented -->\n" +
                    "\t\t</services>\n" +
                    "\t</data>\n" +
                    "</safari>\n");
        }
        else   */

        if (message.getHeader().getRequest().equals("location") && auth) {
            startActivity(new Intent(MainActivity.this, AmpelAcitivity.class));
        } else {
            Log.d("", "");

        }

    }

    @Override
    public void messageReceived(SPAT message, String original) {

    }
}
