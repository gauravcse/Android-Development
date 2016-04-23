package com.example.gauravmitra.personaldoctor;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gaurav Mitra on 07-02-2016.
 */
public class Donate extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auxiliarry_main);
        String options[]=getResources().getStringArray(R.array.side_bar);
        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, options));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = ((TextView) view).getText().toString();
                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
            }
        });

        Button donate=(Button)findViewById(R.id.donate);
        Button receive=(Button)findViewById(R.id.receive);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView=(TextView)findViewById(R.id.output);
                textView.setText("We will get in Touch with you Soon");
                textView.setBackgroundColor(Color.parseColor("#D1F4F8"));
                ViewGroup.LayoutParams layoutParams=textView.getLayoutParams();
                layoutParams.height=getResources().getDimensionPixelSize(R.dimen.text_view_dimension);
                textView.setTextSize(25);
            }
        });

        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.output);
                textView.setText("We will Contact your Hospital Immediately");
                textView.setBackgroundColor(Color.parseColor("#D1F4F8"));
                ViewGroup.LayoutParams layoutParams=textView.getLayoutParams();
                layoutParams.height=getResources().getDimensionPixelSize(R.dimen.text_view_dimension);
                textView.setTextSize(25);
            }
        });
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

