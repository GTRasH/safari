package com.safari.apps.itraffic_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //How to send XML REQ: https://gist.github.com/mortenjust/db15d501aae1b414509a

    Button updateServicesButton;
    Button connectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);


        updateServicesButton = findViewById(R.id.updateServicesButton);
        updateServicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //update Services
                CheckServices();



                ArrayList<String> headlines = new ArrayList<>();

                RetrieveFeed getXML = new RetrieveFeed();
                getXML.execute();
                headlines = getXML.heads();


                // Binding data
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, headlines);

            }
        });

        connectButton = findViewById(R.id.connectButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToAmpel();
            }
        });

        setupToolbar();

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
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarGallery);
//        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbarGalleryTitle);
//
//        setSupportActionBar(toolbar);
//        mTitle.setText(toolbar.getTitle());
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public  boolean  Logout() {

/*        ApiUtils.getInstance().editor.putString("savedUser", "");

        ApiUtils.getInstance().editor.putString("AuthSession", "");
        ApiUtils.getInstance().editor.putString("user", "");
        ApiUtils.getInstance().editor.putString("password", "");
        ApiUtils.getInstance().editor.commit(); */

        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        return true;

    }

    public void CheckServices() {


    }

    public void MoveToAmpel() {

        startActivity(new Intent(MainActivity.this, AmpelAcitivity.class));

    }

    public void  MoveToOptions() {

        startActivity(new Intent(MainActivity.this, OptionsActivity.class));
}

    public void MoveToAbout() {

        startActivity(new Intent(MainActivity.this, AboutActivity.class));
}

}
