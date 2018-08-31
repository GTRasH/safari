package com.safari.apps.itraffic_system;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.safari.apps.itraffic_system.Models.xml.Safari;
import com.safari.apps.itraffic_system.Models.xml.spat.SPAT;
import com.safari.apps.itraffic_system.Utils.SafariService;
import com.safari.apps.itraffic_system.Utils.TcpClient;

/*
    Diese Klasse fuerth der Nutzer durch das Loginprozess
*/

public class LoginActivity extends AppCompatActivity implements TcpClient.OnMessageReceived {

    Button loginButton;
    EditText usernameText;
    EditText passwordText;

    Boolean connecting = false;
    Boolean auth = false;

    LoginActivity me = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //login(view);
                moveToMain();

            }
        });
        if (BuildConfig.DEBUG) {
            usernameText.setText("test");
            passwordText.setText("test");

        }

    }


    @Override
    public void onResume(){
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
    public void messageReceived(Safari message , String original) {

//Sendet die Logindaten nachdem der Server sie gefragt hat.
        if (message.getHeader().getRequest().equals("auth")&&!auth){
            SafariService service = SafariService.getInstance();

            service.sendMessage(
                    "<?xml version=\"1.0\"?>\n" +
                    "<safari>\n" +
                    "\t<header>\n" +
                    "\t\t<request></request>\n" +
                    "\t\t<response>auth</response>\n" +
                    "\t</header>\n" +
                    "\t<data>\n" +
                    "\t\t<user>"+ usernameText.getText() +"</user>\n" +
                    "\t\t<pass>"+ passwordText.getText() +"</pass>\n" +
                    "\t</data>\n" +
                    "</safari>\n");
            auth=true;
        }
        else   if (message.getHeader().getRequest().equals("auth")&&auth){

            startActivity(new Intent(LoginActivity.this, MainActivity.class));


// Sobald die Implementierung einer Fehlerhafte Anmeldung bei der Seite des Servers fertig ist:

        /* SafariService service =  SafariService.getInstance();

            service.disconnectWebSocket();
            connecting= false;
            auth=false;
            Toast.makeText(me,
                    "Error login", Toast.LENGTH_SHORT).show();*/

        }
    }

/* 
    Der Server fragt zweimal die Authetifizierungsdaten. 
    Sobald die zweite nachfrage kommt, wird der nutzer zum MainMenu Activity geschickt
*/
    @Override
    public void messageReceived(SPAT message, String original) {

    }

    public void moveToMain(){

        if(!connecting){

            connecting= true;


            SafariService service =  SafariService.getInstance();

            service.connectWebSocket();


        }

    }

}
