package com.example.gauravmitra.tictactoe;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by Gaurav Mitra on 18-02-2016.
 */
public class Game extends ActionBarActivity {

    public boolean visitedBoxes[][];
    public int board[][];
    public int count=0;
    private ListView listView;
    private BluetoothAdapter bluetoothAdapter;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        String populateDrawer[]=getResources().getStringArray(R.array.bluetooth);
        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        listView=(ListView)findViewById(R.id.left_drawer);
        listView.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_item,populateDrawer));
        bluetoothAdapter= BluetoothAdapter.getDefaultAdapter();
        visitedBoxes=new boolean[3][3];
        board=new int[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                visitedBoxes[i][j]=false;
                board[i][j]=0;
            }
        }
        listViewItemClick();



    }

    public void listViewItemClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView=(TextView)view;
                String text=textView.getText().toString();
                Log.v("Message : ",text);
                if (text.equals("Turn On")) {
                    if(bluetoothAdapter.isEnabled()) {
                        Toast.makeText(getApplicationContext(),"Bluetooth Already On",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent turnOn= new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(turnOn,0);
                        Toast.makeText(getApplicationContext(),"Turned On",Toast.LENGTH_LONG).show();
                    }
                }
                else if(text.equals("Turn Off")) {
                    if(bluetoothAdapter.isEnabled()) {
                        bluetoothAdapter.disable();
                        Toast.makeText(getApplicationContext(),"Already Off",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Turned Off",Toast.LENGTH_LONG).show();
                    }
                }
                else if(text.equals("Discoverable")) {
                    Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent,0);
                }
            }
        });
    }

    public void play_listener(View view) {
        //Log.v("This is Visited","Yes");

        switch (view.getId()) {
            case R.id.box_1 :
                if(visitedBoxes[0][0])
                    break;
                else {
                    count++;
                    visitedBoxes[0][0]=true;
                    if(count%2==0) {
                        setImageResource(view, false);
                        board[0][0]=-1;
                    }
                    else {
                        setImageResource(view, true);
                        board[0][0]=1;
                    }

                }
                Log.v("This is Visited", String.valueOf(visitedBoxes[1]));
                Log.v("Count : ",String.valueOf(count));
                break;
            case R.id.box_2 :
                if(visitedBoxes[0][1])
                    break;
                else {
                    count++;
                    visitedBoxes[0][1]=true;
                    if(count%2==0) {
                        setImageResource(view, false);
                        board[0][1]=-1;
                    }
                    else {
                        setImageResource(view, true);
                        board[0][1]=1;
                    }
                }
                Log.v("This is Visited", String.valueOf(visitedBoxes[2]));
                Log.v("Count : ",String.valueOf(count));
                break;
            case R.id.box_3 :
                if(visitedBoxes[0][2])
                    break;
                else {
                    count++;
                    visitedBoxes[0][2] = true;
                    if (count % 2 == 0) {
                        setImageResource(view, false);
                        board[0][2] = -1;
                    } else {
                        setImageResource(view, true);
                       board[0][2] = 1;
                    }
                }
                break;
            case R.id.box_4 :
                if(visitedBoxes[1][0])
                    break;
                else {
                    count++;
                    visitedBoxes[1][0]=true;
                    if(count%2==0) {
                        setImageResource(view, false);
                        board[1][0] = -1;
                    }
                    else {
                        setImageResource(view, true);
                        board[1][0] = 1;
                    }
                }
                break;

            case R.id.box_5 :
                if(visitedBoxes[1][1])
                    break;
                else {
                    count++;
                    visitedBoxes[1][1]=true;
                    if(count%2==0) {
                        setImageResource(view, false);
                        board[1][1] = -1;
                    }
                    else {
                        setImageResource(view, true);
                        board[1][1] = 1;
                    }
                }
                break;
            case R.id.box_6 :
                if(visitedBoxes[1][2])
                    break;
                else {
                    count++;
                    visitedBoxes[1][2]=true;
                    if(count%2==0) {
                        setImageResource(view, false);
                        board[1][2] = -1;
                    }
                    else {
                        setImageResource(view, true);
                        board[1][2] = 1;
                    }
                }
                break;

            case R.id.box_7 :
                if(visitedBoxes[2][0])
                    break;
                else {
                    count++;
                    visitedBoxes[2][0]=true;
                    if(count%2==0) {
                        setImageResource(view, false);
                        board[2][0]=-1;
                    }
                    else {
                        setImageResource(view, true);
                        board[2][0]=1;
                    }
                }
                break;

            case R.id.box_8 :
                if(visitedBoxes[2][1])
                    break;
                else {
                    count++;
                    visitedBoxes[2][1]=true;
                    if(count%2==0) {
                        setImageResource(view, false);
                        board[2][1] = -1;
                    }
                    else {
                        setImageResource(view, true);
                        board[2][1] = 1;
                    }
                }
                break;

            case R.id.box_9 :
                if(visitedBoxes[2][2])
                    break;
                else {
                    count++;
                    visitedBoxes[2][2] = true;
                    if (count % 2 == 0) {
                        setImageResource(view, false);
                        board[2][2] = -1;
                    } else {
                        setImageResource(view, true);
                        board[2][2] = 1;
                    }
                }
                break;

            default:
                Log.v("Button Clicked : ","Game Activity");
                break;
        }
        int score=returnScore();
        if (score == -1) {
            Log.v("Achieved : ", "-3");
           /* Toast toast=new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText("GAME OVER");
            toast.show();*/
            /*mediaPlayer.create(this,R.raw.game_over);
            mediaPlayer.start();*/
            Toast.makeText(getApplicationContext(), "GAME OVER", Toast.LENGTH_SHORT).show();
            ImageView imageView=(ImageView)findViewById(R.id.game_end);
            imageView.setImageResource(R.drawable.lose);
        }
        else if(score==1) {
            Log.v("Achieved : ","3");
           // Toast toast=new Toast();
           /* toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText("GAME OVER");
            toast.show();*/
            /*mediaPlayer.create(this,R.raw.game_over);
            mediaPlayer.start();*/
            Toast.makeText(getApplicationContext(),"GAME OVER",Toast.LENGTH_SHORT).show();
            ImageView imageView=(ImageView)findViewById(R.id.game_end);
            imageView.setImageResource(R.drawable.lose);
        }
    }

    private int returnScore() {
        Log.v("This Score is ","Successfully");
        int diag1=board[0][0]+board[1][1]+board[2][2];
        int diag2=board[0][2]+board[1][1]+board[2][0];
        int row[],column[];
        row=new int[3];
        column=new int[3];
        for(int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                row[i]+=board[i][j];
                column[j]+=board[i][j];
            }
        }
        if(diag1==3 || diag2==3)
            return 1;
        else if(diag1==-3 || diag2==-3)
            return -1;
        for(int i=0;i<3;i++) {
            if(row[i]==3 || column[i]==3)
                return 1;
            else if(row[1]==-3 || column[i]==-3)
                return -1;
        }
        return 0;
    }

    private void setImageResource(View view,boolean nature) {
        ImageButton image=(ImageButton)view;
        if(nature)
            image.setImageResource(R.drawable.cross);
        else
            image.setImageResource(R.drawable.nought);

    }
}
