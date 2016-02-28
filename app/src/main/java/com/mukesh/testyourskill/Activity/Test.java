package com.mukesh.testyourskill.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.mukesh.testyourskill.adapter.CustomAdapter;
import com.mukesh.testyourskill.R;
import com.mukesh.testyourskill.utils.SetAdView;
import com.mukesh.testyourskill.database.ApplicationConstant;
import com.mukesh.testyourskill.database.Query;
import com.mukesh.testyourskill.database.Ques_SetGet;
import com.mukesh.testyourskill.parsing.InetConnectionPOST;
import com.mukesh.testyourskill.parsing.Parsing;
import com.mukesh.testyourskill.parsing.Utilities;
import com.mukesh.testyourskill.parsing.Utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class Test extends ActionBarActivity implements View.OnClickListener {
    public static ArrayList<Ques_SetGet> questions = new ArrayList<>();
    Ques_SetGet ItemSetGet1 = new Ques_SetGet();
    private final long startTime = 120000;
    private final long interval = 1000;
    boolean networkError;
    InetConnectionPOST con2;
    private MalibuCountDownTimer countDownTimer;
    private long timeElapsed;
    private ProgressDialog mProgressDialog;
    JSONArray category = null;
    private AdView adView;
    private final String unitid = "ca-app-pub-6708308291116753/5569030620";
    private static final String TAG_CATEGORY = "Subject";
    int marks;
    ActionBar actionbar;
    public String url = "http://www.mukeshapps.somee.com/TestService.asmx/GetQuestion";
    ApplicationConstant app;
    ListView list;
    Vibrator vbr;
    LinearLayout timesUp, header;
    TextView show, score, nametv;
    Button tryAgain, btnResult;
    ImageButton btnStop;
    private final Handler handler = new Handler();
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        actionbar = getSupportActionBar();
        actionbar.hide();
        vbr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        adView = (AdView) this.findViewById(R.id.adView);
        SetAdView setad = new SetAdView(unitid, this, adView);
        setad.showAd();
        list = (ListView) findViewById(R.id.listView);
        timesUp = (LinearLayout) findViewById(R.id.llTimeUp);
        header = (LinearLayout) findViewById(R.id.llHeader);
        show = (TextView) findViewById(R.id.tvUpdate);
        score = (TextView) findViewById(R.id.tvMarks);
        nametv = (TextView) findViewById(R.id.tvName);
        tryAgain = (Button) findViewById(R.id.btnRetry);
        btnResult = (Button) findViewById(R.id.btnReult);
        btnStop = (ImageButton) findViewById(R.id.done);
        nametv.setText(Home.Name);
        countDownTimer = new MalibuCountDownTimer(startTime, interval);

        tryAgain.setOnClickListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vbr.vibrate(10);
            }
        });

        btnResult.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        list.setVisibility(View.VISIBLE);
        GetSubject1 getMenu = new GetSubject1();
        getMenu.execute();

    }



    public void setUserAnswer() {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).current == 0)
                questions.get(i).setUserAnswer(questions.get(i).getA());
            else if (questions.get(i).current == 1)
                questions.get(i).setUserAnswer(questions.get(i).getB());
            else if (questions.get(i).current == 2)
                questions.get(i).setUserAnswer(questions.get(i).getC());
            else if (questions.get(i).current == 3)
                questions.get(i).setUserAnswer(questions.get(i).getD());
            else
                questions.get(i).setUserAnswer("No Answer");
        }
    }




    public void showAlert(final String message, final String title) {

        Runnable updater = new Runnable() {
            public void run() {
                new AlertDialog.Builder(Test.this).setTitle(title)
                        .setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        System.exit(0);
                    }
                })
                        .show();
            }
        };
        handler.post(updater);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            list.setVisibility(View.GONE);
            show.setVisibility(View.GONE);

            countDownTimer.cancel();
            //show.setText("00:00");
            setUserAnswer();
            setResult();
            //score.setText("Your Score: " + marks * 10 + "%");
            timesUp.setVisibility(View.VISIBLE);
            return true;
        }
        if (id == R.id.exit) {

            return true;
        }
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent i= new Intent(Test.this, Home.class);
        i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
    public void setResult() {
        header.setVisibility(View.GONE);
        actionbar.show();
        marks = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getUserAnswer().equals(questions.get(i).getAnswer()))
                marks++;
        }
        score.setText("Your Score: " + marks * 10 + "%");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnReult) {
            vbr.vibrate(10);
            startActivity(new Intent(Test.this, Results.class));
        }
        if (id == R.id.btnRetry) {
            marks = 0;
            vbr.vibrate(10);
            this.finish();
        }
        if (id == R.id.done) {
            list.setVisibility(View.GONE);
            show.setVisibility(View.GONE);
            vbr.vibrate(10);
            countDownTimer.cancel();
            setUserAnswer();
            setResult();
            timesUp.setVisibility(View.VISIBLE);
        }

    }

    public static String convertMilisecToHMmSs(long millisUntilFinished) {
        long s = (millisUntilFinished / 1000) % 60;
        long m = ((millisUntilFinished / 1000) / 60) % 60;
        long h = ((millisUntilFinished / 1000) / (60 * 60)) % 24;
        //return String.format("%d:%02d:%02d", h,m,s);
        return String.format("%02d:%02d", m, s);
    }


    public class MalibuCountDownTimer extends CountDownTimer {

        public MalibuCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            list.setVisibility(View.GONE);
            show.setVisibility(View.GONE);

            setUserAnswer();
            setResult();
            timesUp.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //  text.setText("Time remain:" + millisUntilFinished);
            timeElapsed = startTime - millisUntilFinished;

            show.setText(convertMilisecToHMmSs(millisUntilFinished));
        }
    }


    public class GetSubject1 extends AsyncTask<Void, Void, ArrayList<Ques_SetGet>> {

        boolean Result = false, hasConnection = false;

        @Override
        protected void onPreExecute() {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }

            mProgressDialog = ProgressDialog.show(Test.this, "", "Please Wait..");
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Ques_SetGet> doInBackground(Void... params) {


            ArrayList<Ques_SetGet> categorySet = new ArrayList<Ques_SetGet>();

            try {
                if (Utils.checkInternetConnection(Test.this)) {

                    hasConnection = true;
                    System.out.println("Connected");

                    con2 = new InetConnectionPOST(url);

                    List<NameValuePair> params1 = new ArrayList<NameValuePair>();
                    params1.add(new BasicNameValuePair("Subject", getIntent().getExtras().getString("subject")));

                    con2.connect(Utilities.getQuery(params1));
                    if (con2.isTimeout()) {
                        networkError = true;
                        showAlert("Unable to complete task, Connection Time out .", "Message");
                    } else if ((con2.getResponseCodeString()).equalsIgnoreCase("-1")) {
                        networkError = true;
                    } else if (!(con2.getResponseCodeString()).equalsIgnoreCase("200")) {
                        networkError = true;
                        showAlert("Internet Connection is unavailable, Please try again later.", "Message");

                    } else if ((con2.getResponseCodeString()).equalsIgnoreCase("200")) {
                        networkError = false;
                        categorySet = Parsing.GetQuestionBySubject(con2.getResponseData());

                    }

                } else {
                    networkError = true;
                    showAlert("Please check Internet Connection" + ".", "Message");
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return categorySet;

        }

        @Override
        protected void onPostExecute(ArrayList<Ques_SetGet> data) {

            questions = data;
            list.setAdapter(new CustomAdapter(Test.this, questions));
            if (data != null)
                countDownTimer.start();


            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }


        }


    }


}


