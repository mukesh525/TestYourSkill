package com.mukesh.testyourskill.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.mukesh.testyourskill.R;
import com.mukesh.testyourskill.database.Ques_SetGet;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;

import java.util.ArrayList;


public class Welcome extends ActionBarActivity {
    public static int width, height;
    public static float textSizelogin, textSizeHeader, textSizeButton,textSizeListData,textsizegoal,textsizegoaltop;
    public static int radius;
    public static ArrayList<Ques_SetGet> FinalResult=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        try{
            if(getIntent().getExtras().getBoolean("Exit"))
                finish();}
        catch(Exception e){}
        //set all mobile text size
        final ActionBar actionbar=getSupportActionBar();
        actionbar.hide();
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        ParseInstallation.getCurrentInstallation().saveInBackground();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        width = metrics.widthPixels;
        height = metrics.heightPixels;

        switch (width) {

            case 240:
                textSizelogin = 12;
                textSizeHeader = 16;
                textSizeButton = 10;
                textSizeListData = 10;

                break;

            case 320:
                textSizelogin = 12;
                textSizeHeader = 20;
                textSizeButton = 10;

                break;

            case 480:
                textSizelogin = 33;
                textSizeHeader = 20;
                textSizeButton = 10;
                textSizeListData = 10;
                radius = 120;
                textsizegoal = 20;
                textsizegoaltop = 30;

                break;
            case 540:
                textSizelogin = 12;
                textSizeHeader = 25;
                textSizeButton = 10;

                break;
            case 600:
                textSizelogin = 60;
                textSizeHeader = 30;
                textSizeButton = 16;
                textSizeListData = 12;
                radius = 100;
                textsizegoal = 30;
                textsizegoaltop = 50;
                break;
            case 800:
                textSizelogin = 24;
                textSizeHeader = 28;
                textSizeButton = 16;
                break;
            case 720:
                textSizelogin = 40;
                textSizeHeader = 20;
                textSizeButton = 12;
                textSizeListData = 15;
                radius = 180;
                textsizegoal = 20;
                textsizegoaltop = 30;
                break;

            case 768:
                textSizelogin = 16;
                textSizeHeader = 20;
                textSizeButton = 12;
                break;

            case 1080:
                textSizelogin = 70;
                textSizeHeader = 30;
                textSizeButton = 16;
                textSizeListData = 20;
                textsizegoal = 30;
                textsizegoaltop = 50;
                break;
            case 1200:
                textSizelogin = 70;
                textSizeHeader = 30;
                textSizeButton = 16;
                break;

            case 2560:
                textSizelogin = 20;
                textSizeHeader = 24;
                textSizeButton = 16;
                break;

            default:
                textSizelogin = 12;
                textSizeHeader = 20;
                textSizeButton = 10;
                break;
        }
        final Welcome sPlashScreen = this;

        try {
            /*ApplicationConstant app;
            app = (ApplicationConstant) getApplication();
            app.ReadyApplicationDatabase(Welcome.this);*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        GoToNextScreen(7000);
    }

    private void GoToNextScreen(long deley){
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                startActivity(new Intent(Welcome.this,Home.class));
                finish();
            }};
        handler.sendEmptyMessageDelayed(0, deley);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
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
}
