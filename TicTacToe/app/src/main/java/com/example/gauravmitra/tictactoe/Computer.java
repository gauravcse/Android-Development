package com.example.gauravmitra.tictactoe;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Gaurav Mitra on 19-02-2016.
 */
public class Computer extends ActionBarActivity {

    public boolean visitedBoxes[][];
    public int board[][];
    private int[][] preferredMoves = {
            {1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
            {0, 1}, {1, 0}, {1, 2}, {2, 1}};
    public int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        visitedBoxes=new boolean[3][3];
        board=new int[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                visitedBoxes[i][j]=false;
                board[i][j]=0;
            }
        }

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
        decideGame();
        computerMove();

    }

    private void decideGame() {
        int score=returnScore();
        if (score == -1) {
            Log.v("Achieved : ", "-3");
           /* Toast toast=new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText("GAME OVER");
            toast.show();*/
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

    private void computerMove() {
        int x=0;
        int y=0;
        for(int i=0;i<9;i++) {
            x = preferredMoves[i][0];
            y = preferredMoves[i][1];
            if (visitedBoxes[x][y])
                continue;
            else
                break;
        }
        visitedBoxes[x][y]=true;
        Log.v(String.valueOf(x),String.valueOf(y));
        if(x==0 && y==0) {
            View imageButton= (View) findViewById(R.id.box_1);
            setImageResource(imageButton,false);
        }
        else if(x==0 && y==1)  {
            View imageButton= (View) findViewById(R.id.box_2);
            setImageResource(imageButton,false);
        }
        else if(x==0 && y==2)  {
            View imageButton= (View) findViewById(R.id.box_3);
            setImageResource(imageButton,false);
        }
        else if(x==1 && y==0)  {
            View imageButton= (View) findViewById(R.id.box_4);
            setImageResource(imageButton,false);
        }
        else if(x==1 && y==1)  {
            View imageButton= (View) findViewById(R.id.box_5);
            setImageResource(imageButton,false);
        }
        else if(x==1 && y==2)  {
            View imageButton= (View) findViewById(R.id.box_6);
            setImageResource(imageButton,false);
        }
        else if(x==2 && y==0)  {
            View imageButton= (View) findViewById(R.id.box_7);
            setImageResource(imageButton,false);
        }
        else if(x==2 && y==1)  {
            View imageButton= (View) findViewById(R.id.box_8);
            setImageResource(imageButton,false);
        }
        else if(x==2 && y==2)  {
            View imageButton= (View) findViewById(R.id.box_9);
            setImageResource(imageButton,false);
        }
        count++;
        decideGame();
    }
}
