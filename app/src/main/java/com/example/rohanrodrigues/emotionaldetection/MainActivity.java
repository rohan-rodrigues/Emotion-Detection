package com.example.rohanrodrigues.emotionaldetection;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;

import java.util.HashMap;
import java.util.Map;

import youtube_demo.fragment.MainFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView response_text;
    EditText input_text;
    Button input_button;
    String sentiment;
    TextView response;

    private class AskWatsonTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... textsToAnalyse) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //  textView.setText("what is happening inside a thread - we are running Watson AlchemyAPI");
                }
            });
            AlchemyLanguage service = new AlchemyLanguage();
            service.setApiKey("bfe00c0b4cf9cb9de72bd374c565fa38de748568");

            Map<String, Object> params = new HashMap<String, Object>();
            params.put(AlchemyLanguage.TEXT, input_text.getText());
            DocumentSentiment sentiment = service.getSentiment(params).execute();
            System.out.println(sentiment);

            //passing the result to be displayed at UI in the main tread
            return sentiment.getSentiment().getType().name();
        }


        //setting UI with results
        @Override
        protected  void onPostExecute(String result) {
            String sentimentText = "The sentiment is : " + result;
            response_text.setText(sentimentText);
            Lists list = new Lists();

            String responseTemp = "Response: " + list.response(result);
            response.setText(responseTemp);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
      //  tx.replace(R.id.content_frame, new InstructionsFragment());
      //  tx.commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        response_text = (TextView) findViewById(R.id.textResponse);
        input_text = (EditText) findViewById(R.id.editText);
        input_button = (Button) findViewById(R.id.button);
        response = (TextView) findViewById(R.id.response);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

     //   android.app.FragmentManager fm = getFragmentManager();
    //    fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();

        input_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("button was pressed");
                AskWatsonTask task = new AskWatsonTask();
                task.execute();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
      //  android.app.FragmentManager fm = getFragmentManager();

        if (id == R.id.nav_instructions) {
            Toast.makeText(this, "Instructions", Toast.LENGTH_SHORT).show();
         //   fm.beginTransaction().replace(R.id.content_frame, new InstructionFragment()).commit();
         /*   InstructionsFragment instructionsFragment = new InstructionsFragment();
            FragmentManager manager = getSupportFragmentManager();

            manager.beginTransaction().replace(R.id.relative_layout_fragment, instructionsFragment, instructionsFragment.getTag()).commit(); */

        } else if (id == R.id.nav_textCheck) {
            Toast.makeText(this, "Text Check", Toast.LENGTH_SHORT).show();
            Intent x = new Intent(this, MainActivity.class);
            startActivity(x);

        } else if (id == R.id.nav_emailCheck) {
            Toast.makeText(this, "Email Check", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_interactiveCheck) {
            Toast.makeText(this, "Interactive Check", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_voiceInput) {


        } else if (id == R.id.nav_voiceOutput) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}
