package com.goru.android.mycalculator;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

    private EditText Scr;
    private float NumberBf;
    private String operation;
    private ButtonClickListener BtnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Scr = (EditText) findViewById(R.id.editText);
        int idList[] = {R.id.button0,R.id.button,R.id.button2,R.id.button3,R.id.button4,R.id.button5,
                R.id.button6,R.id.button7,R.id.button8,R.id.button9,R.id.buttonDot,R.id.buttonMult,
                R.id.buttonDiv,R.id.buttonAdd,R.id.buttonSub,R.id.buttonEq};

        for(int id:idList) {
            BtnClick=new ButtonClickListener();
            View v = (View) findViewById(id);
            v.setOnClickListener(BtnClick);
        }
    }

    @Override
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
    }

    public void mMath(String str) {
        NumberBf=Float.parseFloat(Scr.getText().toString());
        operation =str;
        Scr.setText("0");

    }

    public void getKeyboard(String str) {

        String ScrCurrent=Scr.getText().toString();
        if(ScrCurrent.equals("0"))
            ScrCurrent="";
        ScrCurrent +=str;
        Scr.setText(ScrCurrent);

    }

    public void mResult() {

        float NumAf = Float.parseFloat(Scr.getText().toString());
        float result=0;
        if(operation.equals("+")) {
            result=NumberBf+NumAf;
        }
        if(operation.equals("-")) {
            result=NumAf-NumberBf;
        }
        if(operation.equals("*")) {
            result=NumAf*NumberBf;
        }
        if(operation.equals("/")) {
            result=NumberBf/NumAf;
        }
        Scr.setText(String.valueOf(result));




    }


    public class ButtonClickListener implements View.OnClickListener {

        public void onClick(View v){
            switch (v.getId()) {

                case R.id.button11:
                    Scr.setText("0");
                    NumberBf = 0;
                    operation ="";
                    break;
                case R.id.buttonAdd:
                    mMath("+");
                    break;
                case R.id.buttonSub:
                    mMath("-");
                    break;
                case R.id.buttonMult:
                    mMath("*");
                    break;
                case R.id.buttonDiv:
                    mMath("/");
                    break;
                case R.id.buttonEq:
                    mResult();
                    break;
                default:
                    String numb = ((Button) v).getText().toString();
                    getKeyboard(numb);
                    break;

            }
        }



    }
}
