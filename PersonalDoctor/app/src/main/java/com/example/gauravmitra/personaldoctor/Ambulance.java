package com.example.gauravmitra.personaldoctor;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gaurav Mitra on 11-02-2016.
 */
public class Ambulance extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ambulance);
        ImageView imageView=(ImageView)findViewById(R.id.map);
        imageView.getLayoutParams().height=400;
        imageView.getLayoutParams().width=375;
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.map));
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

        Button book=(Button)findViewById(R.id.book);
        Button check=(Button)findViewById(R.id.check_rate);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) findViewById(R.id.map);
                imageView.setImageResource(0);
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.booked));
            }
        });



        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.lists);
                String hospitals[] = getResources().getStringArray(R.array.side_bar);
                ListView listView1 = (ListView) findViewById(R.id.data_list);
                listView1.setAdapter(new ArrayAdapter<String>(Ambulance.this, R.layout.list_item, hospitals));
            }
        });



    }
}
