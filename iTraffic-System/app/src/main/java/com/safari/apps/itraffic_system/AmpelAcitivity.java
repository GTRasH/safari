package com.safari.apps.itraffic_system;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class AmpelAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ampel_acitivity);

        Toolbar toolbar = findViewById(R.id.toolbarAmpel);
        setSupportActionBar(toolbar);


        setupToolbar();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupToolbar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarGallery);
//        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbarGalleryTitle);
//
//        setSupportActionBar(toolbar);
//        mTitle.setText(toolbar.getTitle());
//
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
//
    }



}
