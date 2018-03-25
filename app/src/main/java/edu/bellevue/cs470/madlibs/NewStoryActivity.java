package edu.bellevue.cs470.madlibs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewStoryActivity extends AppCompatActivity {

    private TextView mDisplayText;
    private Button mMakeNewStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_story);

        mDisplayText = (TextView) findViewById(R.id.textView5);
        mMakeNewStory = (Button) findViewById(R.id.button2);

        //Use the getIntent method to store the Intent that started this Activity in a variable
        Intent intentThatStartedThisActivity = getIntent();
        //Create an if statement to check if this Intent has the extra we passed from MainActivity
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            final String textReceived = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            //Set the TextView text
            mDisplayText.setText(textReceived);
        }

        mMakeNewStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Storing the Context in a variable
                Context context = NewStoryActivity.this;
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