package edu.bellevue.cs470.madlibs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.start_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Storing the Context in a variable
                Context context = MainActivity.this;
                // Creating the class that we want to start (and open) when the button is clicked
                Class destinationActivity = fillIn.class;
                //Create the Intent that will start the Activity we specified above
                Intent startChildActivityIntent = new Intent(context, destinationActivity);
                //start the ChildActivity
                startActivity(startChildActivityIntent);
            }
        });
    }
}
