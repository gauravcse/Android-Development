package com.example.lenovo.yourdoctor;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

/**
 * Created by Gaurav Mitra on 06-02-2016.
 */

public class HomePage extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v("Reached New Page","");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        Intent intent=getIntent();
        String name=intent.
        Log.v("Data Received :", name);
        TextView textView;
        textView=(TextView)findViewById(R.id.header);
        textView.setText("WELCOME "+name.toUpperCase());
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
