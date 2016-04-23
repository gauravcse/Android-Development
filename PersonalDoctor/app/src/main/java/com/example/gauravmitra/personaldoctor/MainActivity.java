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
import android.widget.Toast;

import com.example.gauravmitra.personaldoctor.R;

/**
 * Created by Gaurav Mitra on 06-02-2016.
 */

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText1,editText2;
        Button button;
        editText1=(EditText)findViewById(R.id.edit1);
        editText2=(EditText)findViewById(R.id.edit2);
        button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (editText1.getText().toString().trim().equals("user") && editText2.getText().toString().trim().equals("user")) {*/
                    //Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent homePage=new Intent(MainActivity.this, HomePage.class);
                    Log.v("Intent is Created"," ");
                    homePage.putExtra("name", editText1.getText().toString());
                    Log.v("After put Extra", "Running Successfully");
                    startActivity(homePage);

                /*} else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials ! Try Again !", Toast.LENGTH_SHORT).show();
                }*/


            }
        });
       /* LoginFunction obj=new LoginFunction();
        obj.login();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

