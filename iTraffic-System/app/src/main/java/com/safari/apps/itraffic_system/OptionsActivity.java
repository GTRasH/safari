package com.safari.apps.itraffic_system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Toolbar toolbar = findViewById(R.id.toolbarOptions);
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
