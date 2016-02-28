package com.mukesh.testyourskill.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.ads.AdView;
import com.mukesh.testyourskill.utils.MyAlert;
import com.mukesh.testyourskill.R;
import com.mukesh.testyourskill.utils.SetAdView;
import com.mukesh.testyourskill.database.Ques_SetGet;
import com.mukesh.testyourskill.parsing.InetConnectionPOST;
import com.mukesh.testyourskill.parsing.Parsing;
import com.mukesh.testyourskill.parsing.Utils;
import com.mukesh.testyourskill.parsing.VolleySingleTon;

import org.json.JSONArray;

import java.util.ArrayList;


public class Home extends ActionBarActivity implements View.OnClickListener {
    ArrayList<Ques_SetGet> result = new ArrayList<>();
    public static ArrayList<Ques_SetGet> FinalResult = new ArrayList<>();
    public static String Name;Vibrator vbr;
    Spinner sp;int i;private VolleySingleTon volleySingleton;
    private RequestQueue requestQueue;
    private final Handler handler = new Handler();
    boolean networkError;EditText name;Button Start;
    private ProgressDialog mProgressDialog;
    JSONArray category = null;
    private AdView adView;

    private final String unitid = "ca-app-pub-6708308291116753/3289336621";
    private static final String TAG_CATEGORY = "Subject";
    public String url = "http://www.mukeshapps.somee.com/TestService.asmx/GetSubject";
    InetConnectionPOST con2;
    ArrayList<String> Subject = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final ActionBar actionbar = getSupportActionBar();
        actionbar.hide();
        vbr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        adView = (AdView) this.findViewById(R.id.adView);
        SetAdView setad = new SetAdView(unitid, this, adView);
        setad.showAd();
        sp = (Spinner) findViewById(R.id.spinner);
        name = (EditText) findViewById(R.id.etName);
        Start = (Button) findViewById(R.id.btnStart);
        GetSubject1 getMenu = new GetSubject1();
        getMenu.execute();
        Start.setOnClickListener(this);
        volleySingleton = VolleySingleTon.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
       // sendJsonRequest();

    }
    public void sendJsonRequest() {
        JsonArrayRequest request = new  JsonArrayRequest(Request.Method.POST,
               url
                , (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Respone:----------", response.toString());
                        if (response != null || response.length() != 0) {
                           // Toast.makeText(Home.this, response.toString(), Toast.LENGTH_LONG).show();
                            FinalResult = Parsing.GetSubjectVolley(response);
                            for (i = 0; i < FinalResult.size(); i++) {
                                Subject.add(FinalResult.get(i).getCategory());
                            }
                            ArrayAdapter adapter = new ArrayAdapter(Home.this, android.R.layout.simple_spinner_item, Subject);
                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            sp.setAdapter(adapter);
                        }
                        else {
                            Toast.makeText(Home.this, "No response from server check your Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleVolleyError(error);

                    }
                });
        requestQueue.add(request);
    }
    private void handleVolleyError(VolleyError error) {

        if (error instanceof TimeoutError || error instanceof NoConnectionError) {

            showAlert("Connection_timeout","Network Error");
            Toast.makeText(Home.this,R.string.error_timeout, Toast.LENGTH_LONG).show();

        } else if (error instanceof AuthFailureError) {

            Toast.makeText(Home.this,R.string.error_auth_failure, Toast.LENGTH_LONG).show();
            //TODO
        } else if (error instanceof ServerError) {

            Toast.makeText(Home.this,R.string.error_auth_failure, Toast.LENGTH_LONG).show();
            //TODO
        } else if (error instanceof NetworkError) {

            Toast.makeText(Home.this,R.string.error_network, Toast.LENGTH_LONG).show();
            //TODO
        } else if (error instanceof ParseError) {

            Toast.makeText(Home.this,R.string.error_parser, Toast.LENGTH_LONG).show();
            //TODO
        }
    }

    public void ExitDialog() {
        MyAlert myalert = new MyAlert();
        myalert.show(getFragmentManager(), "Myalert");
    }

    @Override
    public void onBackPressed() {
        ExitDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void showAlert(final String message, final String title) {
        Runnable updater = new Runnable() {
            public void run() {
                new AlertDialog.Builder(Home.this).setTitle(title)
                        .setMessage(message).setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        System.exit(0);


                    }
                })
                        .setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sendJsonRequest();
                               /* GetSubject1 getMenu = new GetSubject1();
                                getMenu.execute();*/


                            }
                        }).show();
            }
        };
        handler.post(updater);

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

    @Override
    public void onClick(View v) {

        Name = name.getText().toString();
        if (!Name.equals("")) {
            vbr.vibrate(10);
            Intent i = new Intent(Home.this, Test.class);
            i.putExtra("subject", sp.getSelectedItem().toString());
            startActivity(i);
        } else {
            Toast.makeText(Home.this, "Insert your Name to proceed ", Toast.LENGTH_LONG).show();
            name.requestFocus();
        }
    }


    public class GetSubject1 extends AsyncTask<Void, Void, ArrayList<Ques_SetGet>> {

        boolean Result = false, hasConnection = false;

        public void onPreExecute() {

            mProgressDialog = new ProgressDialog(Home.this);
            mProgressDialog.setMessage("Please Wait...");
            mProgressDialog.show();
            mProgressDialog.setCancelable(false);

        }

        @Override
        protected ArrayList<Ques_SetGet> doInBackground(Void... params) {


            ArrayList<Ques_SetGet> categorySet = new ArrayList<Ques_SetGet>();

            try {
                if (Utils.checkInternetConnection(Home.this)) {

                    hasConnection = true;
                    System.out.println("Connected");

                    con2 = new InetConnectionPOST(url);
                    con2.connect();
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
                        categorySet = Parsing.GetSubject(con2.getResponseData());

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

            result = data;
            FinalResult = data;
            for (i = 0; i < result.size(); i++) {
                Subject.add(result.get(i).getCategory());
            }
            ArrayAdapter adapter = new ArrayAdapter(Home.this, android.R.layout.simple_spinner_item, Subject);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            sp.setAdapter(adapter);

            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }


        }


    }


}

