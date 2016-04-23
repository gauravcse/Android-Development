package com.example.gauravmitra.personaldoctor;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Gaurav Mitra on 06-02-2016.
 */

public class HomePage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        Log.v("Data Received :", name);
        TextView textView;
        textView=(TextView)findViewById(R.id.header);
        textView.setText("WELCOME " + name.toUpperCase());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void director_function(View view) {
        switch (view.getId()) {
            case R.id.donate :
                Intent intent1=new Intent(HomePage.this,Donate.class);
                startActivity(intent1);
                break;
            case R.id.ambulan :
                Intent intent2=new Intent(HomePage.this,Ambulance.class);
                Log.v("Ambulance is Created", "Successfully");
                startActivity(intent2);
                break;
            case R.id.history :
                Intent intent3=new Intent(HomePage.this,Donate.class);
                startActivity(intent3);
                break;
            case R.id.diet :
                Intent intent4=new Intent(HomePage.this,Donate.class);

                startActivity(intent4);
                break;
            case R.id.bed_button :
                Intent intent5=new Intent(HomePage.this,Bed.class);
                startActivity(intent5);
                break;
            case R.id.six :
                break;
        }
    }




    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

}

