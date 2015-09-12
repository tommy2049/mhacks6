package com.tommy_creatine.deeznutz;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.Arrays;
import java.util.Date;



public class MainActivity extends Activity implements View.OnClickListener {

    enum State {
        START,
        TRAVELING,
        END
    }

    int i = 0;
    ParseObject testObject;  //= new ParseObject("Route");
    State currentState = State.START;


    @Override
    public void onClick(View v ){

        switch (currentState) {
            case START:
                testObject = new ParseObject("Route");
                testObject.put("startDateTime", new Date());
                currentState = State.TRAVELING;
                break;
            case TRAVELING:
                testObject.put("endDateTime", new Date());
                testObject.saveEventually();
                currentState = State.END;
                break;
            case END:
                currentState = State.START;
                Toast.makeText(this, "Press again to start!", Toast.LENGTH_SHORT).show();
                break;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "8F8M7VgwIhtxBqlh8wX1XW5qN4sf3XL89hPDItdk", "4Ps3sNTZtVZKRs4ssA3pwqnTCkOkh7lkILCtns2r");


        //button
        Button butt = (Button) findViewById(R.id.ass);
        butt.setOnClickListener((View.OnClickListener) this);


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
}
