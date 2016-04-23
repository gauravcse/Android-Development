package com.example.android.rockpaperscissor;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    /* ROCK : 1
    *PAPER : 2
    *SCISSOR : 3
     */
    private int player_score=0;
    private int computer_score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
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
    public void Rock(View view) {
        int number= (int) ((Math.random()*3)+1);
        result(1, number);
    }
    public void Paper(View view) {
        int number=(int)(Math.random()*3+1);
        result(2, number);
    }

    public void Scissor(View view) {
        int number=(int)(Math.random()*3+1);
        result(3,number);
    }

    private void result(int player,int computer) {
        display(computer);
        TextView tvp= (TextView) findViewById(R.id.play);
        TextView tvc=(TextView) findViewById(R.id.comp);
        if(player!=computer) {
            if ((player == 1 && computer == 2) || (player == 2 && computer == 3) || (player == 3 && computer == 1)) {
                tvc.setText("" + (++computer_score));
            }
            else if ((player==1 && computer==3)||(player==2 && computer==1) || (player==3 && computer==2)) {
                tvp.setText(""+(++player_score));
            }
        }
        checkResult();
    }

    private void display(int n) {
        TextView tv = (TextView)findViewById(R.id.choose);
        switch(n) {
            case 1: tv.setText("\t\t\t\tROCK");
                break;
            case 2: tv.setText("\t\t\t\tPAPER");
                break;
            case 3: tv.setText("\t\t\tSCISSOR");
                break;
        }
        return;
    }

    private void checkResult() {
        TextView tv= (TextView)findViewById(R.id.choose);
        int flag=0;
        if(player_score==5) {
            tv.setText("\tPLAYER WON !");
            player_score=0;
            computer_score=0;
            flag=1;
        }
        else if(computer_score==5) {
            tv.setText("\t\tYOU LOSE !");
            player_score=0;
            computer_score=0;
            flag=1;
        }
        if(flag==1) {
            TextView tvp= (TextView) findViewById(R.id.play);
            TextView tvc=(TextView) findViewById(R.id.comp);
            tvc.setText(""+0);
            tvp.setText(""+0);
        }
        return;
    }
}
